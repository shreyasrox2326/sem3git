.data
    input_file:    .asciiz "input.txt"       # Input file name
    output_file:   .asciiz "output_1.txt"    # Output file name
    buffer:        .byte 0                     # Buffer to store the read character
    count_msg:     .asciiz "Character count: " # Message for output
    count_num:     .space 4                    # Space to store the count
    count_string:  .space 12                   # Space for converting count to string

.text
main:
    # Open the input file for reading
    li      $v0, 13                          # syscall: open
    la      $a0, input_file                  # filename
    li      $a1, 0                            # read mode
    syscall
    move    $t0, $v0                          # save file descriptor in $t0

    # Initialize character count
    li      $t1, 0                            # character count = 0

read_loop:
    # Read a character
    li      $v0, 14                          # syscall: read
    move    $a0, $t0                          # file descriptor
    la      $a1, buffer                       # buffer to store character
    li      $a2, 1                            # number of bytes to read
    syscall

    # Check if end of file (EOF)
    beqz    $v0, write_count                  # if read returns 0 (EOF), go to write_count

    # Increment character count
    addi    $t1, $t1, 1                       # character count++
    j       read_loop                         # repeat the loop

write_count:
    # Close the input file
    li      $v0, 16                          # syscall: close
    move    $a0, $t0                          # file descriptor
    syscall

    # Open the output file for writing
    li      $v0, 13                          # syscall: open
    la      $a0, output_file                  # filename
    li      $a1, 1                            # write mode
    syscall
    move    $t2, $v0                          # save output file descriptor in $t2

    # Store character count in memory
    sw      $t1, count_num                   # store count in memory

    # Convert count to string
    li      $t3, 0                            # counter for string index
    li      $t4, 10                           # divisor for converting to string
    move    $t5, $t1                          # copy character count to $t5

    # Create the string in reverse
    addi    $t3, $t3, 12                      # Start from the end of the buffer
    sb      $zero, count_string($t3)         # Null-terminate the string

convert_loop:
    # Get last digit
    rem     $t6, $t5, $t4                     # $t6 = $t5 % 10
    addi    $t6, $t6, '0'                     # convert to ASCII
    addi    $t3, $t3, -1                      # move to previous position
    sb      $t6, count_string($t3)           # store in string buffer
    div     $t5, $t5, $t4                     # $t5 = $t5 / 10
    bnez    $t5, convert_loop                 # continue until $t5 is 0

    # Calculate the length of the string
    li      $t7, 12                           # Starting length
    sub     $t7, $t7, $t3                      # Length of the number string
    addi    $t3, $t3, 1                       # Adjust to point to the first character

    # Write the message to the output file
    li      $v0, 15                           # syscall: write
    move    $a0, $t2                          # file descriptor
    la      $a1, count_msg                    # message to write
    li      $a2, 19                           # message length
    syscall

    # Write the character count string
    li      $v0, 15                           # syscall: write
    move    $a0, $t2                          # file descriptor
    move    $a1, $t3           # pointer to the start of the count string
    sub     $a2, $t7, $t3                     # length of the count string
    syscall

    # Close the output file
    li      $v0, 16                           # syscall: close
    move    $a0, $t2                          # output file descriptor
    syscall

    # Exit the program
    li      $v0, 10                           # syscall: exit
    syscall
