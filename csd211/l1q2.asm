.text
li $t0, 7 # storing 7 in $t0
li $t1, 3 # storing 3 in $t1

#after execution, $t0 will be 3 and $t1 will be 7
add $t0, $t1, $t0
sub $t1, $t0, $t1
sub $t0, $t0, $t1