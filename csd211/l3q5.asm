.data
input_age_prompt: .asciiz "Enter age: "
input_id_prompt: .asciiz "Enter 1 if user has voter id, 0 if not: "
eligible_message: .asciiz "allowed to vote!\n"
not_eligible_message: .asciiz "not allowed to vote!\n"

.text
main:
    # Print prompt for age input
    li $v0, 4
    la $a0, input_age_prompt 
    syscall

    # Read age from user
    li $v0, 5             
    syscall
    move $t0, $v0                

    # Check voting eligibility
    blt $t0, 18, not_eligible  


    # Print prompt for voter ID input
    li $v0, 4             
    la $a0, input_id_prompt
    syscall

    # Read voter ID card flag from user
    li $v0, 5              
    syscall
    move $t1, $v0   
    
    # Age >= 18, check for voter ID card
    beqz $t1, not_eligible    

    # If age >= 18 and ID flag is 1, eligible
    li $v0, 4                
    la $a0, eligible_message 
    syscall
    j exit_program

not_eligible:
    li $v0, 4                
    la $a0, not_eligible_message
    syscall

exit_program:
    li $v0, 10     
    syscall
