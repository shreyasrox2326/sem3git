.data
prompt_int:   .asciiz "Enter an integer: "
prompt_shift: .asciiz "Enter number of bits to shift: "
result_msg:   .asciiz "Result after shifting: "

.text
main:
    li $v0, 4
    la $a0, prompt_int
    syscall

    li $v0, 5
    syscall
    move $t0, $v0

    li $v0, 4
    la $a0, prompt_shift
    syscall

    li $v0, 5
    syscall
    move $t1, $v0

    sllv $t2, $t0, $t1

    li $v0, 4
    la $a0, result_msg
    syscall

    li $v0, 1
    move $a0, $t2
    syscall

    li $v0, 10
    syscall
