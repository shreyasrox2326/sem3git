.data
num: 665 # number to be checked

.text

lw $t0, num # loading number into $t0

loop: # loop to keep subtracting 2 from $t0 until $t0 <= 0

beqz $t0, even # if number is even, it is divisible by 2 and repeated subtraction of 2 from the number will result in 0
bltz $t0, odd # if number is odd, repeated subtraction of 2 will not result in zero
subi $t0, $t0, 2
bgez $t0, loop 

even: # storing 1 in $t1
li $t1, 1
b exit

odd: # storing 0 in $t1
li $t1, 0
b exit

exit: # printing the value stored in $t1
la $a0, ($t1)
li $v0, 1
syscall
