.data
    prompt:      .asciiz "Enter the value of n: "
    newline:     .asciiz "\n"
    fib_msg:     .asciiz "Fibonacci sequence: "
    error_msg:   .asciiz "Error: Invalid input. Please enter a non-negative integer.\n"

.text
main:
    # Print prompt and read the value of n
    li $v0, 4               # syscall for print string
    la $a0, prompt          # address of the prompt string
    syscall
    
    li $v0, 5               # syscall for read integer
    syscall
    move $t0, $v0           # save the input value of n into $t0

    # Validate input
    blt $t0, $zero, error   # if n < 0, print error message and exit
    bgt $t0, 46, error      # if n > 46, print error message and exit (to avoid overflow)

    # Print Fibonacci sequence message
    li $v0, 4               # syscall for print string
    la $a0, fib_msg         # address of the Fibonacci sequence message
    syscall
    
    # Initialize loop variables
    li $t1, 0               # initialize counter to 0
    li $t2, 0               # initialize fib(0) to 0
    li $t3, 1               # initialize fib(1) to 1

print_fib_sequence:
    bge $t1, $t0, end_program # if counter >= n, end program
    move $a0, $t2           # set $a0 = fib(i)
    
    li $v0, 1               # syscall for print integer
    syscall
    
    # Print newline after each number
    li $v0, 4               # syscall for print string
    la $a0, newline         # address of the newline string
    syscall
    
    # Calculate next Fibonacci number
    add $t4, $t2, $t3       # fib(i+1) = fib(i) + fib(i-1)
    move $t2, $t3           # update fib(i) to fib(i-1)
    move $t3, $t4           # update fib(i-1) to fib(i+1)
    
    # Increment counter and loop
    addi $t1, $t1, 1        # increment counter
    j print_fib_sequence    # jump to print_fib_sequence

end_program:
    li $v0, 10              # syscall to exit
    syscall

error:
    li $v0, 4               # syscall for print string
    la $a0, error_msg       # address of the error message
    syscall
    j end_program           # exit program