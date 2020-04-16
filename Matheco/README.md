# Matheco

matheco is a sort of language based on C build by bison/flex, it is still under development by the way.

Quick Start:
* make a file with `.meo` type **e.g: test.meo**.
* write `max(15 21 32);`
* run the program and write the filename, if the programe is on same folder with the script that you made you may write it's name only `test.meo`.

Current Support Methods:
```
MAX MIN SORT
ADD SUB MUL DIV
FACT SQRT POW LOG EXP
COS SIN TAN COSR SINR TANR
EQ NEQ GEQ LEQ
```

Some othe examples that you can run:
```
max(20 10 56 2 99 41 33);
min(10 10 56 2 20 41 33);
sort(200 10 56 225 20 41 33);
add(20 10 56 2 99 41 33);
mul(10 2);
sub(20 10 56 2 99 41 33);
div(20 10);
fact(5);
sqrt(4);
pow(10 3);
exp(20);
log(20);
cos(20);
sin(20);
tan(20);
cosr(20);
sinr(20);
tanr(20);
eq(10 10);
neq(20 22);
geq(30 10);
leq(10 30);
```
