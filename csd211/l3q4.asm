.data
input_prompt: .asciiz "Enter an integer: "
output_prompt: .asciiz "Binary representation: "
buffer: .space 33          # reserved space in memory
newline: .asciiz "\n"

.text
main:
    # Print input prompt
    li $v0, 4                
    la $a0, input_prompt     
    syscall

    # Read integer from user
    li $v0, 5                
    syscall
    move $t0, $v0            

    # set $t1 to start of the reserved space
    la $t1, buffer           
    li $t2, 32               

    # Handle negative numbers
    bltz $t0, convert_negative

    # Handle positive integer
    move $t4, $t0            
    j convert_to_binary

convert_negative:
    # 2's complement, by bitwise not and adding 1
    li $t5, 0                
    sub $t4, $t5, $t0        
    not $t4, $t4             
    addi $t4, $t4, 1         

convert_to_binary:
    # Convert integer to binary
    li $t5, 31               
    li $t6, 1                

    # Store binary digits in reverse
    la $t1, buffer           
    addi $t1, $t1, 32        
    sb $zero, 0($t1)         

binary_loop:
    and $t7, $t4, $t6        
    addi $t7, $t7, 48        
    addi $t1, $t1, -1        
    sb $t7, 0($t1)           

    srl $t4, $t4, 1          
    addi $t5, $t5, -1        
    bgez $t5, binary_loop    

    # Print binary form
    li $v0, 4                
    la $a0, output_prompt    
    syscall

    li $v0, 4                
    la $a0, buffer           
    syscall

    # Print \n
    li $v0, 4                
    la $a0, newline          
    syscall

    # Exit
    li $v0, 10               
    syscall
