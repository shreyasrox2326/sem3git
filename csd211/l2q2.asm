.data
prompt1:       .asciiz "Enter first number: "
prompt2:       .asciiz "Enter second number: "
prompt_op:     .asciiz "Choose operation (1: Add, 2: Subtract, 3: Multiply, 4: Divide): "
result_msg:    .asciiz "The result is: "
error_msg:     .asciiz "Error: Division by zero or invalid operation."

.text
.globl main

main:
    # Print prompt for the first number
    li $v0, 4
    la $a0, prompt1
    syscall

    # Read the first number
    li $v0, 5
    syscall
    move $t0, $v0          # Store the first number in $t0

    # Print prompt for the second number
    li $v0, 4
    la $a0, prompt2
    syscall

    # Read the second number
    li $v0, 5
    syscall
    move $t1, $v0          # Store the second number in $t1

    # Print the operation prompt
    li $v0, 4
    la $a0, prompt_op
    syscall

    # Read the operation choice
    li $v0, 5
    syscall
    move $t2, $v0          # Store the operation choice in $t2

    # Check the operation choice
    beq $t2, 1, add_op
    beq $t2, 2, sub_op
    beq $t2, 3, mul_op
    beq $t2, 4, div_op

    # If invalid choice, print error message and exit
    li $v0, 4
    la $a0, error_msg
    syscall
    j end_program

add_op:
    add $t3, $t0, $t1     # Perform addition
    j print_result

sub_op:
    sub $t3, $t0, $t1     # Perform subtraction
    j print_result

mul_op:
    mul $t3, $t0, $t1     # Perform multiplication
    j print_result

div_op:
    beq $t1, $zero, div_by_zero # Check if divisor is zero
    div $t0, $t1          # Perform division
    mflo $t3              # Move quotient to $t3
    j print_result

div_by_zero:
    li $v0, 4
    la $a0, error_msg
    syscall
    j end_program

print_result:
    # Print the result message
    li $v0, 4
    la $a0, result_msg
    syscall

    # Print the result
    li $v0, 1
    move $a0, $t3
    syscall

end_program:
    # Exit the program
    li $v0, 10
    syscall
