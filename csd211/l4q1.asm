.data
    prompt1:    .asciiz "Enter the first string: "
    prompt2:    .asciiz "Enter the second string: "
    buffer1:    .space 100    # Space for first string (max 100 chars)
    buffer2:    .space 100    # Space for second string (max 100 chars)

.text
    .globl main

# Main program
main:
    # Read the first string
    li $v0, 4                  # Syscall for print string
    la $a0, prompt1             # Load prompt1 address
    syscall
    
    jal read_string             # Read first string into buffer1
    
    # Read the second string
    li $v0, 4                  # Syscall for print string
    la $a0, prompt2             # Load prompt2 address
    syscall
    
    jal read_string2          

    # Compare the strings
    jal compare_strings
    
    # Print the numerical result
    move $a0, $v0             
    jal print_result

    # Exit program
    li $v0, 10                
    syscall

# Procedure to read the first string
read_string:
    la $a0, buffer1            
    li $a1, 100                
    li $v0, 8                  
    syscall
    jr $ra                     

# Procedure to read the second string
read_string2:
    la $a0, buffer2            
    li $a1, 100                
    li $v0, 8                  
    syscall
    jr $ra                     

# Procedure to compare the strings lexicographically
compare_strings:
    la $t0, buffer1            
    la $t1, buffer2            
    li $v0, 0                  

compare_loop:
    lb $a0, 0($t0)             
    lb $a1, 0($t1)             
    beq $a0, $zero, check_end  
    beq $a1, $zero, check_end 
    bne $a0, $a1, set_result  
    
    addi $t0, $t0, 1          
    addi $t1, $t1, 1          
    j compare_loop            

check_end:
    beq $a0, $a1, end_compare 
    bne $a0, $zero, set_gt 
    bne $a1, $zero, set_lt 

set_result:
    blt $a0, $a1, set_lt 
    bgt $a0, $a1, set_gt 

set_lt:
    li $v0, -1         
    j end_compare

set_gt:
    li $v0, 1          
    j end_compare

end_compare:
    jr $ra             

# Procedure to print the numerical result
print_result:
    li $v0, 1          
    syscall
    jr $ra             
