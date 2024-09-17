.data
buffer:      .space 256                # Reserve space for user input string
counts:      .space 104                # Space for 26 integers (4 bytes each)
new_line:    .asciiz "\n"              # New line for formatting
prompt:      .asciiz "Enter a string: " # Prompt for user input

.text
.globl main

main:
    # Prompt user for input
    li $v0, 4                  # Print string syscall
    la $a0, prompt             # Address of prompt message
    syscall

    # Read input string
    li $v0, 8                  # Read string syscall
    la $a0, buffer             # Address of buffer
    li $a1, 256                # Max bytes to read
    syscall

    # Initialize
    la $t0, buffer             # Load address of the input string
    la $t1, counts             # Load address of counts array

    # Clear the counts array
    li $t2, 0
    li $t3, 26
clear_counts:
    sw $t2, 0($t1)             # Set count to 0
    addi $t1, $t1, 4           # Move to next count
    addi $t3, $t3, -1
    bnez $t3, clear_counts    # Repeat until all counts cleared

    # Count occurrences
    la $t0, buffer             # Reload address of the input string
    la $t1, counts             # Reload address of counts array

count_loop:
    lb $t4, 0($t0)             # Load a byte from the string
    beqz $t4, print_counts     # End of string, go to print counts

    # Check if character is lowercase letter
    li $t5, 'a'
    li $t6, 'z'
    blt $t4, $t5, count_next
    bgt $t4, $t6, count_next

    # Update the count for the character
    sub $t7, $t4, $t5          # Index in counts array
    sll $t7, $t7, 2            # Multiply by 4 (size of integer)
    add $t7, $t7, $t1          # Address of the count
    lw $t8, 0($t7)             # Load current count
    addi $t8, $t8, 1           # Increment count
    sw $t8, 0($t7)             # Store updated count
    
count_next:
    addi $t0, $t0, 1           # Next character
    j count_loop               # Repeat loop

    # Print counts
print_counts:
    li $t9, 0                  # Initialize counter for letters
    la $t0, counts             # Load address of counts array

print_loop:
    bge $t9, 26, exit         # Done with all letters, exit
    lw $t4, 0($t0)             # Load the count
    beqz $t4, skip_print       # Skip if count is 0

    # Print the letter
    li $a0, 0
    li $v0, 11                 # Print character syscall
    addi $a0, $t9, 'a'         # Convert index to character
    syscall

    # Print the count
    move $a0, $t4              # Load count to print
    li $v0, 1                  # Print integer syscall
    syscall

    # Print newline
    li $v0, 4                  # Print string syscall
    la $a0, new_line
    syscall

skip_print:
    addi $t0, $t0, 4           # Move to next count
    addi $t9, $t9, 1           # Next letter
    j print_loop               # Repeat loop

exit:
    li $v0, 10                 # Exit syscall
    syscall
