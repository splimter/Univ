%option noyywrap
%{
#include "matheco.tab.h"
#include<string.h>
int yyerror(char *ss,char *sr);
extern YYSTYPE yylval;
char path[20];
int errLine=1,errCol=0;
%}

%%

[A-Z]+  {yylval.str=yytext;errCol+=strlen(yytext);return VARNAME;}
"="    {errCol++;return AFFECT;}

"max"  {errCol+=3;return KWMAX;}
"min"  {errCol+=3;return KWMIN;}
"sort" {errCol+=4;return KWSORT;}

"add"  {errCol+=3;return KWADD;}
"sub"  {errCol+=3;return KWSUB;}
"mul"  {errCol+=3;return KWMUL;}
"div"  {errCol+=3;return KWDIV;}

"fact" {errCol+=4;return KWFACT;}
"sqrt" {errCol+=4;return KWSQRT;}
"pow"  {errCol+=3;return KWPOW;}
"exp"  {errCol+=3;return KWEXP;}
"log"  {errCol+=3;return KWLOG;}

"cos"  {errCol+=3;return KWCOS;}
"sin"  {errCol+=3;return KWSIN;}
"tan"  {errCol+=3;return KWTAN;}

"cosr" {errCol+=4;return KWCOSR;}
"sinr" {errCol+=4;return KWSINR;}
"tanr" {errCol+=4;return KWTANR;}

"eq"   {errCol+=2;return KWEQ;}
"neq"  {errCol+=3;return KWNEQ;}
"geq"  {errCol+=3;return KWGEQ;}
"leq"  {errCol+=3;return KWLEQ;}

"("    {errCol++;return LP;}
")"    {errCol++;return RP;}

[0-9]+ {yylval.integer = atoi(yytext);errCol+=strlen(yytext);return NUM;}

" "    { errCol++; }
";"    { errCol++; return END; }

"\(\)" { errCol+=2;yyerror("At leaste one value must be provided at",""); }
[a-z]+ { errCol+=strlen(yytext);yyerror("Unknow command",yytext); }
"\n"   { errCol=0;errLine++; }

[^\;]$ { yyerror("Missig ; ",""); }
.      { yyerror("Syntax error ",yytext);errCol++; }

%%

int yyerror(char *ss, char *sr){
    printf("%s %s at L:%d C:%d\n",ss,sr,errLine,errCol);
    exit(1);
}

int main(){
    label:printf("give path: ");
    scanf("%s",path);
    FILE *f=fopen(path,"r") ;

    if(f==NULL){
        goto label;
    }else{
        yyin=f;
    }

    yyparse();
}
