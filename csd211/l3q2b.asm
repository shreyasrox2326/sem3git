.data
prompt:      .asciiz "Enter the number of lines: "
newline:     .asciiz "\n"

.text
main:
    # Print prompt message
    li $v0, 4
    la $a0, prompt
    syscall


    li $v0, 5 
    syscall
    move $t0, $v0    

    
    li $t1, 0        

print_triangle:
    bge $t1, $t0, done_printing  

    
    move $t2, $t1
    li $t3, 32  
print_spaces:
    beqz $t2, print_asterisks   
    li $v0, 11         
    move $a0, $t3    
    syscall
    subi $t2, $t2, 1   
    j print_spaces

print_asterisks:
    # Calculate number of asterisks to print
    move $t2, $t0      
    sub $t2, $t2, $t1  
    mul $t2, $t2, 2    
    addi $t2, $t2, -1  
    li $t3, 42         # ascii codfe for '*'
print_asterisk_loop:
    beqz $t2, end_line  
    li $v0, 11         
    move $a0, $t3      
    syscall
    subi $t2, $t2, 1   
    j print_asterisk_loop

end_line:
    # Print newline
    li $v0, 4          
    la $a0, newline    # load address of newline
    syscall

    
    addi $t1, $t1, 1   
    j print_triangle   

done_printing:

    li $v0, 10         # syscall for exit
    syscall
