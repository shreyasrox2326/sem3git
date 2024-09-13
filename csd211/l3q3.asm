.data
# Strings for prompts and results
prompt1:    .asciiz "Enter the first integer: "
prompt2:    .asciiz "Enter the second integer: "
result_sum: .asciiz "Sum of two numbers is : "
result_diff:.asciiz "Difference of two numbers is : "
result_prod:.asciiz "Product of two numbers is : "
newline:    .asciiz "\n"

.text
main:
    # Print prompt for the first integer
    li $v0, 4
    la $a0, prompt1
    syscall

    # Read the first integer
    li $v0, 5
    syscall
    move $t0, $v0   # Store the first integer in $t0

    # Print prompt for the second integer
    li $v0, 4
    la $a0, prompt2
    syscall

    # Read the second integer
    li $v0, 5
    syscall
    move $t1, $v0   # Store the second integer in $t1

    # Calculate and print sum
    jal funcADD
    li $v0, 4
    la $a0, result_sum
    syscall
    li $v0, 1
    move $a0, $t2   # Print the result of addition stored in $t2
    syscall
    li $v0, 4
    la $a0, newline
    syscall

    # Calculate and print difference
    jal funcSUB
    li $v0, 4
    la $a0, result_diff
    syscall
    li $v0, 1
    move $a0, $t2   # Print the result of subtraction stored in $t2
    syscall
    li $v0, 4
    la $a0, newline
    syscall

    # Calculate and print product
    jal funcMUL
    li $v0, 4
    la $a0, result_prod
    syscall
    li $v0, 1
    move $a0, $t2   # Print the result of multiplication stored in $t2
    syscall
    li $v0, 4
    la $a0, newline
    syscall

    # Exit program
    li $v0, 10
    syscall

# Addition function
funcADD:
    add $t2, $t0, $t1
    jr $ra           

# Subtraction function
funcSUB:
    sub $t2, $t0, $t1
    jr $ra           

# Multiplication function
funcMUL:
    mul $t2, $t0, $t1
    jr $ra           
