%{
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include<string.h>
int yylex();
int yyerror(const char *s);
extern int errLine;
int e = 0,current = 0,i,j,c;

int iserie = 0, iv = 0, serieArr[10];

typedef struct Variable Variable;
struct Variable {
    char* name;
    int value;
};

Variable v[10];

char* type;

int max(){
    int i, m=0;
    for(i=0;i<iserie;i++)
        if(serieArr[i]>m)
            m = serieArr[i];
    return m;
}
int min(){
    int i, m=100000;
    for(i=0;i<iserie;i++)
        if(serieArr[i]<m)
            m = serieArr[i];
    return m;
}

void sort(){
    for(i=0;i<iserie;i++) 
        for(j=0;j<iserie-1;j++)
        { if(serieArr[j]>serieArr[j+1]) {
            current=serieArr[j];
            serieArr[j]=serieArr[j+1] ;
            serieArr[j+1]=current ;
        }

        }

    for(c=0;c<iserie;c++){
        printf("%d  ",serieArr[c]);
    }
     printf("\n",serieArr[c]);
}

int add(){
    int i,m=serieArr[0];
    for(i=1;i<iserie;i++)
        m += serieArr[i];
    return m;
}
int sub(){
    int i,m=serieArr[0];
    for(i=1;i<iserie;i++)
        m -= serieArr[i];
    return m;
}
double mul(){
    int i;
    double m=serieArr[0];
    for(i=1;i<iserie;i++)
        m *= serieArr[i];
    return m;
}
double divI(){
    int i;
    double m=serieArr[0];
    for(i=1;i<iserie;i++)
        if(serieArr[i]!=0)
            m /= serieArr[i];
    return m;
}

int fact(){
    int i,m=1;
    for(i=1;i<serieArr[0];i++)
        m*=i;
    return m;
}

%}

%union {
    int integer;
    char *str; 
};
%type <str> VARNAME
%type <integer> NUM

%token NUM END VAR LP RP
%token AFFECT VARNAME
%token KWMAX KWMIN KWSORT
%token KWADD KWSUB KWMUL KWDIV
%token KWFACT KWSQRT KWPOW KWLOG KWEXP
%token KWCOS KWSIN KWTAN KWCOSR KWSINR KWTANR
%token KWEQ KWNEQ KWGEQ KWLEQ

%%

begin: | var | multiPram ;

var: begin VARNAME AFFECT NUM END {
        char* stemp = $2;
        v[iv].name =  stemp;
        int itemp = $4;
        v[iv].value = itemp; iv++;
        printf("var name *%s val *%d\n", v[iv-1].name, v[iv-1].value);
    };

multiPram: 
      begin KWmax LP Num RP END { printf("max is: %d\n",max()); iserie=0;}
    | begin KWmin LP Num RP END { printf("min is: %d\n",min()); iserie=0;}
    | begin KWsort LP Num RP END { printf("sorted ");sort(); iserie=0;}
    | begin KWadd LP Num RP END { printf("sum of addition is: %d\n",add()); iserie=0;}
    | begin KWsub LP Num RP END { printf("sum of subtracion is: %d\n",sub()); iserie=0;}
    | begin KWmul LP Num RP END { printf("sum of multiplication is: %f\n",mul()); iserie=0;}
    | begin KWdiv LP Num RP END { printf("sum of division is: %f\n",divI()); iserie=0;}
    | begin KWfact LP Num RP END { printf("factorial of %d is %d\n",serieArr[0],fact()); iserie=0;}
    | begin KWsqrt LP Num RP END { printf("sqrt of %d is %f\n",serieArr[0],sqrt(serieArr[0])); iserie=0;}
    | begin KWpow LP Num RP END { 
        (iserie < 2)  
        ? printf("Few values %s at line %d\n",type,errLine)
        : printf("power of %d ^ %d is %d\n",serieArr[0],serieArr[1],(int) pow((double)serieArr[0],(double)serieArr[1]));
        iserie=0;
        }
    | begin KWcos LP Num RP END { 
        double x = (3.141592654*(double)serieArr[0])/180;
        printf("Cos of %d is %.2lf\n",serieArr[0],cos(x));
        iserie=0;
        }
    | begin KWsin LP Num RP END { 
        double x = (3.141592654*(double)serieArr[0])/180;
        printf("Sin of %d is %.2lf\n",serieArr[0],sin((x)));
        iserie=0;
        }
    | begin KWtan LP Num RP END { 
        double x = (3.141592654*(double)serieArr[0])/180;
        printf("Tan of %d is %.2lf\n",serieArr[0],tan((x)));
        iserie=0;
        }
    | begin KWcosr LP Num RP END { 
        printf("Cos of %d is %.2lf\n",serieArr[0],cos((double)serieArr[0]));
        iserie=0;
        }
    | begin KWsinr LP Num RP END { 
        printf("Sin of %d is %.2lf\n",serieArr[0],sin(((double)serieArr[0])));
        iserie=0;
        }
    | begin KWtanr LP Num RP END { 
        printf("Tan of %d is %.2lf\n",serieArr[0],tan(((double)serieArr[0])));
        iserie=0;
        }
    | begin KWexp LP Num RP END { 
        printf("Exp of %d is %.2lf\n",serieArr[0],exp(((double)serieArr[0])));
        iserie=0;
        }
    | begin KWlog LP Num RP END { 
        printf("Log of %d is %.2lf\n",serieArr[0],log(((double)serieArr[0])));
        iserie=0;
        }
    | begin KWeq LP Num RP END { 
        if(iserie < 2) { 
            printf("Few values %s at line %d\n",type,errLine); 
            exit(1); 
        }
        else{ 
            if(serieArr[0]==serieArr[1])
                printf("true\n");
            else
                printf("false\n");   
            }   
            iserie=0;
        }
    | begin KWneq LP Num RP END { 
        if(iserie < 2) { 
        printf("Few values %s at line %d\n",type,errLine);
        exit(1);
        }
        else { 
            if(serieArr[0]!=serieArr[1]) 
                printf("true\n");
            else 
                printf("false\n"); 
            iserie=0;
        }
    }
    | begin KWgeq LP Num RP END { 
        if(iserie < 2) {
            printf("Few values %s at line %d\n",type,errLine);
            exit(1);
        }
        else{ 
            if(serieArr[0]>=serieArr[1])
                printf("true\n");
            
            else
                printf("false\n");} 
            
            iserie=0;
        }
    | begin KWleq LP Num RP END { 
        if(iserie < 2)  
        {
            printf("Few values %s at line %d\n",type,errLine);
            exit(1);
        }
        else {
            if(serieArr[0]<=serieArr[1])
                printf("true\n");
                
            else
                printf("false\n");
                
            iserie=0;
            }
        }
    ;

KWmax: KWMAX {
    type="max";
};
KWmin: KWMIN {
    type="min";
};
KWsort: KWSORT {
    type="sort";
};

KWadd: KWADD {
    type="add";
};
KWsub: KWSUB {
    type="sub";
};
KWmul: KWMUL {
    type="mul";
};
KWdiv: KWDIV {
    type="div";
};

KWfact: KWFACT {
    type="fact";
}
KWsqrt: KWSQRT {
    type="sqrt";
}
KWpow: KWPOW {
    type="pow";
}
KWexp: KWEXP {
    type="exp";
}
KWlog: KWLOG {
    type="log";
}

KWcos: KWCOS {
    type="cos";
}
KWsin: KWSIN {
    type="sin";
}
KWtan: KWTAN {
    type="tan";
}

KWcosr: KWCOSR {
    type="cosr";
}
KWsinr: KWSINR {
    type="sinr";
}
KWtanr: KWTANR {
  type="tanr";
}

KWeq : KWEQ { type="eq"; } 
KWneq : KWNEQ { type="neq"; } 
KWgeq : KWGEQ { type="geq"; } 
KWleq : KWLEQ { type="leq"; }

Num: monoNum | monoNum Num ;

monoNum: NUM {
    if( (type=="fact" || type=="sqrt" || type=="exp" || type=="log" 
    || type=="cos" || type=="sin" || type=="tan"
    || type=="cosr" || type=="sinr" || type=="tanr") && iserie==1){
        printf("too many values for %s at line %d\n",type,errLine);
        exit(1);
    }
    else if( (type=="pow" || type=="eq" || type=="neq"
         || type=="geq" || type=="leq") && iserie==2){
         printf("too many values for %s at line %d\n",type,errLine);
        exit(1);
    }
    int temp = $1; 
    *(serieArr + iserie) = temp; iserie++;
};

%%
