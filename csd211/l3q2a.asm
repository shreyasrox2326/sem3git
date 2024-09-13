.data
prompt:      .asciiz "Enter the number of rows: "
newline:     .asciiz "\n"
star:        .asciiz "*"

.text
main:
    li $v0, 4
    la $a0, prompt
    syscall

    li $v0, 5
    syscall
    move $t0, $v0

    li $t1, 1
print_rows:
    bgt $t1, $t0, exit

    li $t2, 0
print_stars:
    bge $t2, $t1, print_newline

    li $v0, 4
    la $a0, star
    syscall

    addi $t2, $t2, 1
    j print_stars

print_newline:
    li $v0, 4
    la $a0, newline
    syscall

    addi $t1, $t1, 1
    j print_rows

exit:
    li $v0, 10
    syscall
