.data

inputstr: .asciiz "Enter n to print n fibonacci terms: " 
nextline: .asciiz "\n"
.text
li $v0, 4 #set $v0 to 4 for printing string
la $a0, inputstr #load address of the input string into $a0
syscall #syscall

li $v0, 5 #set $v0 to 5 for taking int input
syscall # syscall

la $t3, ($v0) #move inputnumber to $t3 for future use

li $t0, 0 #first term
li $t1, 1 #second term

li $a0, 0 #initialising sum register
li $t2, 0 #initialising count


loop:
addi $t2, $t2, 1 #add 1 to count
add $a0, $t0, $t1 #add prev two terms and store in $a0
li $v0, 1 #syscall code to print int stored in $a0
syscall #syscall

la $t0, ($t1) #set first term to second term
la $t1, ($a0) #set second term to sum

la $a0, nextline #load address of nextline string into $a0
li $v0, 4 # syscall code to print string 
syscall #syscall

bne $t2, $t3, loop # loop until count != inputnumber


