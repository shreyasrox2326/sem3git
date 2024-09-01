# rule for checking leap year:
# 1) year must be divisible by 4 and not by 100
# 2) or year must be divisible by 400
# if the year meets either one of these criteria then it is a leap year

.data
year: 1601
no: .asciiz " is not a leap year"
yes: .asciiz " is a leap year"

.text # initialising the registers
lw $t0, year
lw $t1, year
lw $t2, year

loop1: # if year is divisible by 400, print yes, else check divisibility by 100
beqz $t0, printYes
bltz $t0, loop2
subi $t0, $t0, 400 
bgez $t0, loop1

loop2: # if year is divisible by 100, print no, else check divisibility by 4
beqz $t1, printNo
bltz $t1, loop3
subi $t1,$t1, 100
bgez $t1, loop2

loop3: # if year is divisible by 4 print yes, else print no
beqz $t2, printYes
bltz $t2, printNo
subi $t2, $t2, 4
bgez $t2, loop3

printNo: # print no
lw $a0, year
li $v0, 1
syscall
la $a0, no
li $v0, 4
syscall
b exit

printYes: # print yes
lw $a0, year
li $v0, 1
syscall
la $a0, yes
li $v0, 4
syscall
b exit


exit: