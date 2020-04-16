# Assmebler Simulator
### Just like the old days writing how the asm code behave on a table ^^.
### Overview

legendes:
* Begin of search cycle (BCS)
* End of search cycle (ECS)
* End of execution cycle (EES)

* Ordinal counter (OC)
* Instruction register (IR)
* Adress register (AR)
* Memory register (MR)
* Register 1 (R1), Register 2 (R2)

* Supported commands `MOV,ADD,CLR,INC,DEC,BR,CMP,BGE,BGT,BLE,BLT,BEQ`

* quick example:
```
ADD 10 R1
BR T
ADD  20 R1
T
HALT
```

```
CLR R1
bgnlbl:
cmp r1 2
beq endlbl
add 10 r1
add 9 r1
br bgnlbl
endlbl:
inc r1
halt
```

![](https://i.imgur.com/QGgXfGt.png)
