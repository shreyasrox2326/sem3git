.data
input_prompt: .asciiz "Enter your number: "

.text
li $v0, 4
la $a0, input_prompt
syscall
li $v0, 5
syscall
move $t0, $v0

#calculate number of digits

move $t1, $t0
li $t2, 1
li $t3, 1
loop:
div $t1, $t2
mflo $t4
blt $t4, 10, break1
mul $t2, $t2, 10
addi $t3, $t3, 1
b loop
break1: # $t3 has number of digits now, $t0 has the number, $t2 has correct power of 10 just less than the number

li $s0, 1
addi $t4, $t3, 1
loop1:

div $t0, $t2 #get individual digits of the number by dividing with appropriate power of 10
mflo $s1 #s1 has some digit of the number now
li $a3, 10
div $s1, $a3
mfhi $s1
move $s6, $s1
move $s3, $s0 

subi $s3, $s3, 1
loop2:
beqz $s3, exit2
mul $s1, $s6, $s1
subi $s3, $s3, 1
b loop2
exit2:
add $t9, $t9, $s1
div $t2, $t2, 10

addi $s0, $s0, 1
blt $s0, $t4, loop1

li $v0, 1
move $a0, $t9
syscall