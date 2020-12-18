.data
  AA:       .space 400  		# int AA[100]
  BB:       .space 400  		# int BB[100]
  CC:       .space 400  		# int CC[100]
  m:        .word  0   		# m is an int whose value is at most 10
  prompt1: .asciiz		"m = "
  prompt2: .asciiz		"AA["
  prompt3: .asciiz		"] = "
  prompt4: .asciiz		"BB["
  prompt5: .asciiz		"CC["
  newline: 		.asciiz 	"\n" 
.text

main:
	addi $v0, $0, 4  	# print "m = "
	la $a0, prompt1		
	syscall
	
	addi $v0, $0, 5		# scan for m
	syscall				
	add $t0, $0, $v0
	
	jal run			# call run
	
	j exit

run:
	addi $sp, $sp, -8	# create new stack for run
	sw $ra, 4($sp)
	sw $fp, 0($sp)
	addi $fp, $sp, 4
	
	add $t1, $0, $0		# int i = 0
	mul $t2, $t0, $t0	# m*m
	for_loop1: 
		slt $t3, $t1, $t2
		beq $t3, $0, end_loop1
		
		addi $v0, $0, 4  	# print "AA["
		la $a0, prompt2	
		syscall
		 
		addi $v0, $0, 1		# print	i
  		add $a0, $0, $t1			
  		syscall			 
		
		addi $v0, $0, 4  	# print "] = "
		la $a0, prompt3
		syscall
		
		sll $t3, $t1, 2		# get AA[i] mem address
		la $t4, AA
		add $t4, $t3, $t4
		
		addi $v0, $0, 5		# scan into AA[i]
		syscall				
		add $t5, $0, $v0	
		sw $t5, 0($t4)
				
		addi $v0, $0, 4  	# print "BB["
		la $a0, prompt4	
		syscall
		
		addi $v0, $0, 1		# print	i
  		add $a0, $0, $t1			
  		syscall
		
		addi $v0, $0, 4  	# print "] = "
		la $a0, prompt3
		syscall
		
		la $t4, BB		# get BB[i] mem address
		add $t4, $t3, $t4
		
		addi $v0, $0, 5		# scan into BB[i]
		syscall				
		add $t5, $0, $v0	
		sw $t5, 0($t4)
		
		addi $t1, $t1, 1	# i++
		j for_loop1
		
	end_loop1:
	
	jal matrixmult
	
	addi $sp, $fp, 4
	lw $ra, 0($fp)
	lw $fp, -4($fp)
	jr $ra

matrixmult: 
	addi $sp, $sp, -8	# create new stack for matrixmult
	sw $ra, 4($sp)
	sw $fp, 0($sp)
	addi $fp, $sp, 4
	
	add $t1, $0, $0		# int s = 0
	
	add $t2, $0, $0		# int i = 0
	for_loop2:	
		slt $t3, $t2, $t0
		beq $t3, $0, end_loop2
		
		add $t4, $0, $0		# int j = 0 
		for_loop3:
			slt $t3, $t4, $t0
			beq $t3, $0, end_loop3
			
			add $t1, $0, $0		# s = 0
			
			add $t5, $0, $0		# int k = 0
			for_loop4:
				slt $t3, $t5, $t0
				beq $t3, $0, end_loop4
				
				la $t6, AA
				mul $t7, $t2, $t0
				add $t7, $t7, $t5	# i * m + k
				sll $t7, $t7, 2
				add $t7, $t6, $t7
				lw $t7, 0($t7) 		# AA[i * m + k]
				
				la $t8, BB
				mul $t9, $t5, $t0
				add $t9, $t9, $t4	# k * m + j
				sll $t9, $t9, 2
				add $t9, $t9, $t8
				lw $t9, 0($t9)		# BB[k * m + j]
				
				mul $t7, $t7, $t9	# AA[i * m + k] * BB[k * m + j]
				add $t1, $t1, $t7	# s += AA[i * m + k] * BB[k * m + j]
				
				addi $t5, $t5, 1	# k++
				j for_loop4
				
			end_loop4:
			
			la $t6, CC
			mul $t7, $t2, $t0
			add $t7, $t7, $t4	# i * m + j
			sll $t7, $t7, 2
			add $t7, $t6, $t7
			sw $t1, 0($t7) 		# CC[i * m + j] = s
			
			addi $t4, $t4, 1	# j++
			j for_loop3
			
		end_loop3:
		
		addi $t2, $t2, 1		# i++
		j for_loop2
		
	end_loop2:
	
	jal printC
	
	addi $sp, $fp, 4
	lw $ra, 0($fp)
	lw $fp, -4($fp)
	jr $ra	

printC:
	addi $sp, $sp, -8	# create new stack for printC
	sw $ra, 4($sp)
	sw $fp, 0($sp)
	addi $fp, $sp, 4
	
	add $t1, $0, $0		# int i = 0 using $t1 because done with s in program
	mul $t2, $t0, $t0	# m*m
	for_loop5:
		slt $t3, $t1, $t2
		beq $t3, $0, end_loop5
		
		addi $v0, $0, 4  	# print "CC["
		la $a0, prompt5
		syscall
		 
		addi $v0, $0, 1		# print	i
  		add $a0, $0, $t1			
  		syscall			 
		
		addi $v0, $0, 4  	# print "] = "
		la $a0, prompt3
		syscall
		
		la $t3, CC
		sll $t4, $t1, 2
		add $t4, $t3, $t4
		lw $t4, 0($t4)		# CC[i]
		
		addi $v0, $0, 1		# print	CC[i]
  		add $a0, $0, $t4			
  		syscall	
		
		addi $v0, $0, 4  	# newline
		la $a0, newline
		syscall
		
		addi $t1, $t1, 1
		j for_loop5
		
	end_loop5:
	
	addi $sp, $fp, 4
	lw $ra, 0($fp)
	lw $fp, -4($fp)
	jr $ra	
	
exit:                     
  	addi $v0, $0, 10      	# system call code 10 for exit
  	syscall               	# exit the program
