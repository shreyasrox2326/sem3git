.data

rollno: .asciiz "hello meow meow"  # works with any string
str1: .asciiz "Original roll number: "
str2: .asciiz "\nReversed roll number: "

.text

la $a0, str1
li $v0, 4
syscall # print str1
la $a0, rollno
syscall # print roll number
la $a0, str2
syscall # print str2 

la $t0, rollno # load address of rollno string into $t0

loop1: # loop to count number of chars in the rollno and store count in $t2
lb $t1, ($t0) # load next char
beqz $t1, doNotIncrement # if char is null char, don't increment
addi $t2, $t2, 1 # increment count
addi $t0, $t0, 1 # increment address in $t0 to go to next char
doNotIncrement:
bnez $t1, loop1 # if char was not null char, loop

loop2: # loop to print chars in reverse
subi $t2, $t2, 1 # decrement count
bltz $t2, doNotPrint  # if count is less than zero, don't print
subi $t0, $t0, 1 # decrement address in $t0
lb $a0, ($t0) # load character into $a0
li $v0, 11 # syscall code for printing a character
syscall 
doNotPrint:
bgtz $t2, loop2 # if count>0, loop
