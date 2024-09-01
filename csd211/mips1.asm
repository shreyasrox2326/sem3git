.data
string: .asciiz "hello world"
.text
li  $v0, 1
li $a0, -1
syscall
