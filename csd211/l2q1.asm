.data
pve: .asciiz " is positive"
nve: .asciiz " is negative"
zer: .asciiz " is zero"
inputstr: .asciiz "Enter the number to be checked: "

.text
la $a0, inputstr
li $v0, 4
syscall
li $v0, 5
syscall
la $t0, ($v0)

# conditional branches
bltz $t0, negative 
bgtz $t0, positive
beqz $t0, zero

negative: # storing -1 in $t1
la $t1, nve
b exit

positive: # storing 1 in $t1
la $t1, pve
b exit

zero: # storing 0 in $t1
la $t1, zer
b exit

exit: # printing the value stored in $t0
la $a0, ($t0)
li $v0, 1
syscall

la $a0, ($t1)
li $v0, 4
syscall
