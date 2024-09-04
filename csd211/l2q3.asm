.text
li $s0, 0 #sum
li $t0, 0 #count
loop:
addi, $t0, $t0, 1 #add 1 to count
andi, $t2, $t0, 0x00000001 #bitwise and with 1 gives 0 if num is even and 1 if it is odd
bnez, $t2, skip #if count is odd, skip adding it to sum
add $s0, $s0, $t0 #add count to sum
skip:
bne $t0, 10, loop #if count not equal to 10, loop
li $v0, 1 #syscall code for printing integer
la $a0, ($s0) #storing sum in $a0 for printing
syscall #syscall