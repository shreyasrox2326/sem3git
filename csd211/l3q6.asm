.data
    buffer: .space 100        # Buffer to store user input
    prompt: .asciiz "Enter a string: "
    pal_msg: .asciiz "Palindrome\n"
    not_pal_msg: .asciiz "Not Palindrome\n"

.text
.globl main

main:
    # Print prompt to the user
    li   $v0, 4              
    la   $a0, prompt         
    syscall

    # Read string from the user
    li   $v0, 8              
    la   $a0, buffer         
    li   $a1, 100            
    syscall

    # Remove newline character if present
    la   $a0, buffer         
    jal  remove_newline      

    # Get length of the input string
    la   $a0, buffer         
    jal  strlen              
    move $t0, $v0            

    # Initialize pointers
    la   $a1, buffer         
    addi $a2, $a1, -1        
    add  $a2, $a2, $t0       

check_palindrome:
    beq  $t0, $zero, is_palindrome   
    ble  $t0, 1, is_palindrome       

    lb   $t1, 0($a1)         
    lb   $t2, 0($a2)         
    bne  $t1, $t2, not_palindrome 

    addi $a1, $a1, 1         
    addi $a2, $a2, -1        
    addi $t0, $t0, -2        
    j    check_palindrome    

is_palindrome:
    li   $v0, 4              
    la   $a0, pal_msg        
    syscall
    j    exit

not_palindrome:
    li   $v0, 4              
    la   $a0, not_pal_msg    
    syscall

exit:
    li   $v0, 10             
    syscall

# Function to compute the length of a null-terminated string
strlen:
    li   $v0, 0              
strlen_loop:
    lb   $t3, 0($a0)         
    beq  $t3, $zero, strlen_done 
    addi $v0, $v0, 1         
    addi $a0, $a0, 1         
    j    strlen_loop         
strlen_done:
    jr   $ra                 

# Procedure to remove newline character from the end of the string
remove_newline:
    la   $t0, buffer         
find_end:
    lb   $t1, 0($t0)         
    beq  $t1, $zero, end_found 
    addi $t0, $t0, 1         
    j    find_end            

end_found:
    addi $t0, $t0, -1        
    lb   $t1, 0($t0)         
    bne  $t1, 10, done       
    sb   $zero, 0($t0)       

done:
    jr   $ra                 
