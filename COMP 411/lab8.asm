.data

pattern: 	.space 17	# array of 16 (1 byte) characters (i.e. string) plus one additional character to store the null terminator when N=16

N_prompt:	.asciiz "Enter the number of bits (N): "
newline: 	.asciiz "\n"
zero_char:	.asciiz "0"
one_char:	.asciiz "1"
null_char: 	.asciiz "\0"
.text

main:
add $t0, $0, $0 # int N = 0 not in s caues not needed again

addi $v0, $0, 4  	# print "Enter the number of bits (N): "
la $a0, N_prompt		
syscall

addi $v0, $0, 5		# scan for N
syscall				
add $t0, $0, $v0

add $t1, $t0, $0 # int n = N not in s cause not needed again

la $s0, pattern # pattern memory address, will be saved
sll $t2, $t0, 2
add $t2, $t2, $s0
la $t3, null_char
sb $t3, 0($t2) # pattern[N]=\0
add $a1, $t0, $0
add $a2, $t1, $0

jal bingen

j exit

bingen:
addi $sp, $sp, -8	# create new stack for bingen
sw $ra, 4($sp)
sw $fp, 0($sp)
addi $fp, $sp, 4

addi $sp, $sp, -4	# storing saved values
sb $s1, 0($sp)

#add $s1, $a0, $0 # n and N stored in saved reg
#add $s2, $a1, $0

slt $t0, $0, $a2  # if (n > 0)
beq $t0, $0, else

sub $s1, $a1, $a2 # N-n
sll $s1, $s1, 2   # accesing pattern[N-n]
add $s1, $s0, $s1

la $t1, zero_char # load '0' into t1
sb $t1, 0($s1)	   # pattern [N-n] = '0'

addi $sp, $sp, -8
sb $a1, -12($fp)
sb $a2, -16($fp)
addi $a2, $a2, -1 # setting a1 = n-1 a0 is already N
jal bingen	   # recursive call to bingen
lb $a1, -12($fp)
lb $a2, -16($fp)
addi $sp, $sp, 8

la $t1, one_char  # load '1' into t1
sb $t1, 0($s1)	   # pattern [N-n] = '1'

addi $sp, $sp, -8
sb $a1, -12($fp)
sb $a2, -16($fp)
addi $a2, $a2, -1 # setting a1 = n-1 a0 is already N
jal bingen	   # recursive call to bingen
lb $a1, -12($fp)
lb $a2, -16($fp)
addi $sp, $sp, 8

j end_if

else:

add $t0, $0, $0  # int i = 0
for_loop:

slt $t1, $t0, $a1 # for loop branch
beq $t1, $0, end_loop

sll $t1, $t0, 2
add $t1, $s0, $t1
lb $t1, 0($t1)

addi $v0, $0, 4		# print	pattern[i]
add $a0, $0, $t1			
syscall	

addi $t0, $t0, 1	# i++
j for_loop

end_loop:

addi $v0, $0, 4  	# newline
la $a0, newline
syscall

end_if:
# poping saved values off stack
lb $s1, -8($fp)

addi $sp, $fp, 4 #restoring stck
lw $ra, 0($fp)
lw $fp, -4($fp)
jr $ra


#----------------------------------------------
#
# Convert the lab8 C-code to MIPS instructions
#
# Please remember to read the "program specification"
# section in the lab assignment PDF very carefully.
# It has all the information needed to complete this
# assignment :)
#
# TODO: Put your MIPS code here
#
#----------------------------------------------





exit:                     
  addi $v0, $0, 10      	# system call code 10 for exit
  syscall               	# exit the program
