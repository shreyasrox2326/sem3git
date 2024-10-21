.data
    input_file:    .asciiz "input.txt"      # Input file name
    output_file:   .asciiz "output_2.txt"   # Output file name
    buffer:        .space 256                 # Buffer to hold input characters
    count_msg:     .asciiz "Vowel count: "    # Message for output
    newline:       .asciiz "\n"               # Newline character for formatting
    count_str:     .space 12                   # Buffer for the count string

.text
main:
    # Open the input file for reading
    li      $v0, 13                        # syscall: open
    la      $a0, input_file                # filename
    li      $a1, 0                          # read mode
    syscall
    move    $t0, $v0                        # save input file descriptor

    # Open the output file for writing
    li      $v0, 13                        # syscall: open
    la      $a0, output_file                # filename
    li      $a1, 1                          # write mode
    syscall
    move    $t1, $v0                        # save output file descriptor

    # Initialize vowel count
    li      $t2, 0                          # vowel count = 0

read_loop:
    # Read a line into buffer
    li      $v0, 14                         # syscall: read
    move    $a0, $t0                        # file descriptor
    la      $a1, buffer                     # buffer to store the line
    li      $a2, 256                        # max bytes to read
    syscall

    # Check if end of file (EOF)
    beqz    $v0, write_count                # if read returns 0 (EOF), go to write_count

    # Process the read characters to count vowels
    move    $t3, $v0                        # number of characters read
    li      $t4, 0                          # index for buffer

count_vowels:
    lb      $t5, buffer($t4)                # load character from buffer
    beqz    $t5, read_loop                  # if null (end of string), go back to read

    # Check for vowels (both lowercase and uppercase)
    li      $t6, 'a'                        # compare with 'a'
    li      $t7, 'e'                        # compare with 'e'
    li      $t8, 'i'                        # compare with 'i'
    li      $t9, 'o'                        # compare with 'o'
    li      $s0, 'u'                        # compare with 'u'
    li      $s1, 'A'                        # compare with 'A'
    li      $s2, 'E'                        # compare with 'E'
    li      $s3, 'I'                        # compare with 'I'
    li      $s4, 'O'                        # compare with 'O'
    li      $s5, 'U'                        # compare with 'U'

    # Check if the character is a vowel
    beq     $t5, $t6, increment_count       # if char == 'a', increment count
    beq     $t5, $t7, increment_count       # if char == 'e', increment count
    beq     $t5, $t8, increment_count       # if char == 'i', increment count
    beq     $t5, $t9, increment_count       # if char == 'o', increment count
    beq     $t5, $s0, increment_count       # if char == 'u', increment count
    beq     $t5, $s1, increment_count       # if char == 'A', increment count
    beq     $t5, $s2, increment_count       # if char == 'E', increment count
    beq     $t5, $s3, increment_count       # if char == 'I', increment count
    beq     $t5, $s4, increment_count       # if char == 'O', increment count
    beq     $t5, $s5, increment_count       # if char == 'U', increment count

    j       next_character                  # go to next character

increment_count:
    addi    $t2, $t2, 1                     # vowel count++

next_character:
    addi    $t4, $t4, 1                     # move to the next character
    j       count_vowels                    # repeat counting vowels

write_count:
    # Close the input file
    li      $v0, 16                         # syscall: close
    move    $a0, $t0                        # input file descriptor
    syscall

    # Convert the count to string
    move    $t6, $t2                        # move vowel count to $t6
    li      $t7, 0                          # index for count_str
    li      $t8, 10                         # divisor for conversion

convert_loop:
    # Get the last digit
    rem     $t9, $t6, $t8                   # t9 = t6 % 10
    addi    $t9, $t9, '0'                   # convert to ASCII
    sb      $t9, count_str($t7)             # store digit in count_str
    addi    $t7, $t7, 1                      # increment index
    div     $t6, $t6, $t8                    # t6 = t6 / 10
    bnez    $t6, convert_loop               # continue until all digits are processed

    # Reverse the string
    li      $t6, 0                          # reset t6 for string reversal
reverse_loop:
    addi    $t6, $t6, 1                     # increment index for reverse
    sub     $t9, $t7, $t6                   # index of the character to copy
    lb      $t0, count_str($t9)             # load character from count_str
    sb      $t0, count_str($t6)             # store in reversed position
    blt     $t6, $t7, reverse_loop          # continue until reversed

    # Null-terminate the string
    sb      $zero, count_str($t6)           # null-terminate the count string

    # Write the message and the count string to output file
    li      $v0, 15                         # syscall: write
    move    $a0, $t1                        # output file descriptor
    la      $a1, count_msg                  # load message
    li      $a2, 14                         # message length
    syscall

    li      $v0, 15                         # syscall: write
    move    $a0, $t1                        # output file descriptor
    la      $a1, count_str                  # load count string
    move    $a2, $t6                        # length of the count string
    syscall

    # Write newline for better formatting
    li      $v0, 15                         # syscall: write
    move    $a0, $t1                        # output file descriptor
    la      $a1, newline                    # newline to write
    li      $a2, 1                          # write 1 byte
    syscall

    # Close the output file
    li      $v0, 16                         # syscall: close
    move    $a0, $t1                        # output file descriptor
    syscall

    # Exit the program
    li      $v0, 10                         # syscall: exit
    syscall
