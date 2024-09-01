.data
string: .asciiz "Hello world!" #initialisng a variable and storing address of string
.text
li  $v0, 4 # required by syscall to print string
la $a0, string # storing address of string in $a0, required by syscall
syscall