.data
input: .word 0              # Space to store the user input
result: .word 0             # Space to store the result
prompt: .asciiz "Enter a number: "  # Prompt for user input
msg_result: .asciiz "The factorial is: "  # Result message

.text


    # Print prompt message
    li $v0, 4               # System call code for print_str
    la $a0, prompt          # Load address of prompt message
    syscall

    # Read integer input from user
    li $v0, 5               # System call code for read_int
    syscall
    sw $v0, input           # Store the input value in 'input'

    # Load input value
    lw $a0, input           # Load the input value into $a0

    # Call factorial function
    jal factorial

    # Store the result
    sw $v0, result          # Store the result in 'result'

    # Print result message
    li $v0, 4               # System call code for string print
    la $a0, msg_result      # Load address of result message string
    syscall

    # Print result value
    li $v0, 1               # System call code for print_int
    lw $a0, result          # Load the result value into $a0
    syscall

    # Exit the program
    li $v0, 10              # System call code for exit
    syscall

# Factorial function (recursive)
# Arguments: $a0 = n
# Returns: $v0 = factorial(n)

factorial:
    # Base case: if n <= 1, return 1
    blez $a0, base_case

    # Recursive case: factorial(n) = n * factorial(n-1)
    addi $sp, $sp, -8       # Allocate stack space for 2 words
    sw $ra, 4($sp)          # Save return address
    sw $a0, 0($sp)          # Save argument (n)

    addi $a0, $a0, -1       # Set argument for recursive call (n-1)
    jal factorial           # Recursive call to factorial(n-1)

    lw $a0, 0($sp)          # Restore original argument (n)
    lw $ra, 4($sp)          # Restore return address
    addi $sp, $sp, 8        # Deallocate stack space

    mul $v0, $v0, $a0       # Multiply result of factorial(n-1) by n
    jr $ra                  # Return to caller

base_case:
    li $v0, 1               # Return 1 if n <= 1
    jr $ra                  # Return to caller


