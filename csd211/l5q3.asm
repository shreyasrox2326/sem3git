.data
    input_file:    .asciiz "input.txt"      # Input file name
    output_file:   .asciiz "output_3.txt"   # Output file name
    buffer:        .space 256                 # Buffer to hold each line
    newline:       .asciiz "\n"               # Newline character

.text
main:
    # Open the input file for reading
    li      $v0, 13                          # syscall: open
    la      $a0, input_file                  # filename
    li      $a1, 0                            # read mode
    syscall
    move    $t0, $v0                          # save input file descriptor

    # Open the output file for writing
    li      $v0, 13                          # syscall: open
    la      $a0, output_file                  # filename
    li      $a1, 1                            # write mode
    syscall
    move    $t1, $v0                          # save output file descriptor

read_line:
    # Read a line into buffer
    li      $v0, 8                            # syscall: read string
    la      $a0, buffer                       # buffer to store the line
    li      $a1, 256                          # max bytes to read
    syscall

    # Check if end of file (EOF)
    lb      $t2, buffer                       # load first character
    beqz    $t2, finish                       # if first char is null, finish

    # Find the length of the line
    move    $t3, $zero                        # reset length counter
find_length:
    lb      $t2, buffer($t3)                  # load character
    beqz    $t2, reverse_line                  # if null, go to reverse
    addi    $t3, $t3, 1                        # increment length
    j       find_length                        # continue finding length

reverse_line:
    # Reverse the line
    move    $t4, $t3                           # length of the line in $t4
    addi    $t5, $t4, -1                       # index of last character

    # Start reversing
    move    $t3, $zero                         # Reset index for reversed storage
reverse_loop:
    lb      $t6, buffer($t5)                  # load character from end
    sb      $t6, buffer($t3)                  # store in the new position
    addi    $t3, $t3, 1                        # move forward in new position
    addi    $t5, $t5, -1                       # move backward in original position
    bgez    $t5, reverse_loop                  # loop until we reach the start

    sb      $zero, buffer($t3)                # null-terminate the reversed string

    # Write the reversed line to the output file
    li      $v0, 15                            # syscall: write
    move    $a0, $t1                           # output file descriptor
    la      $a1, buffer                        # buffer to write
    move    $a2, $t3                           # length of the reversed string
    syscall

    # Write a newline character to the output file
    li      $v0, 15                            # syscall: write
    move    $a0, $t1                           # output file descriptor
    la      $a1, newline                       # newline to write
    li      $a2, 1                             # write 1 byte
    syscall

    j       read_line                          # read the next line

finish:
    # Close the input file
    li      $v0, 16                            # syscall: close
    move    $a0, $t0                           # input file descriptor
    syscall

    # Close the output file
    li      $v0, 16                            # syscall: close
    move    $a0, $t1                           # output file descriptor
    syscall

    # Exit the program
    li      $v0, 10                            # syscall: exit
    syscall
