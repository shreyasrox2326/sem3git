.data
num: -9 # number to be checked

.text
lw $t0, num # load number into $t0

# conditional branches
bltz $t0, negative 
bgtz $t0, positive
beqz $t0, zero

negative: # storing -1 in $t1
li $t1, -1
b exit

positive: # storing 1 in $t1
li $t1, 1
b exit

zero: # storing 0 in $t1
li $t1, 0
b exit

exit: # printing the value stored in $t1
la $a0, ($t1)
li $v0, 1
syscall