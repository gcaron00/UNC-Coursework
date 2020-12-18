.data

A:			.space 	80  	# create integer array with 20 elements ( A[20] )
size_prompt:		.asciiz 	"Enter array size [between 1 and 20]: "
array_prompt:		.asciiz 	"A["
sorted_array_prompt:	.asciiz 	"Sorted A["
close_bracket: 		.asciiz 	"] = "
search_prompt:		.asciiz		"Enter search value: "
not_found:		.asciiz		" not in sorted A"
newline: 		.asciiz 	"\n" 
space:			.asciiz		"   $s5 "
space2:			.asciiz		"   $s6 "

.text

main:	
	# ----------------------------------------------------------------------------------
	# Do not modify
	#
	# MIPS code that performs the C-code below:
	#
	# 	int A[20];
	#	int size = 0;
	#	int v = 0;
	#
	#	printf("Enter array size [between 1 and 20]: " );
	#	scanf( "%d", &size );
	#
	#	for (int i=0; i<size; i++ ) {
	#
	#		printf( "A[%d] = ", i );
	#		scanf( "%d", &A[i]  );
	#
	#	}
	#
	#	printf( "Enter search value: " );
	#	scanf( "%d", &v  );
	#
	# ----------------------------------------------------------------------------------
	la $s0, A			# store address of array A in $s0
  
	add $s1, $0, $0			# create variable "size" ($s1) and set to 0
	add $s2, $0, $0			# create search variable "v" ($s2) and set to 0
	add $t0, $0, $0			# create variable "i" ($t0) and set to 0

	addi $v0, $0, 4  		# system call (4) to print string
	la $a0, size_prompt 		# put string memory address in register $a0
	syscall           		# print string
  
	addi $v0, $0, 5			# system call (5) to get integer from user and store in register $v0
	syscall				# get user input for variable "size"
	add $s1, $0, $v0		# copy to register $s1, b/c we'll reuse $v0
  
prompt_loop:
	# ----------------------------------------------------------------------------------
	slt $t1, $t0, $s1		# if( i < size ) $t1 = 1 (true), else $t1 = 0 (false)
	beq $t1, $0, end_prompt_loop	 
	sll $t2, $t0, 2			# multiply i * 4 (4-byte word offset)
				
  	addi $v0, $0, 4  		# print "A["
  	la $a0, array_prompt 			
  	syscall  
  	         			
  	addi $v0, $0, 1			# print	value of i (in base-10)
  	add $a0, $0, $t0			
  	syscall	
  					
  	addi $v0, $0, 4  		# print "] = "
  	la $a0, close_bracket		
  	syscall					
  	
  	addi $v0, $0, 5			# get input from user and store in $v0
  	syscall 			
	
	add $t3, $s0, $t2		# A[i] = address of A + ( i * 4 )
	sw $v0, 0($t3)			# A[i] = $v0 
	addi $t0, $t0, 1		# i = i + 1
		
	j prompt_loop			# jump to beginning of loop
	# ----------------------------------------------------------------------------------	
end_prompt_loop:

	addi $v0, $0, 4  		# print "Enter search value: "
  	la $a0, search_prompt 			
  	syscall 
  	
  	addi $v0, $0, 5			# system call (5) to get integer from user and store in register $v0
	syscall				# get user input for variable "v"
	add $s2, $0, $v0		# copy to register $s2, b/c we'll reuse $v0

	# ----------------------------------------------------------------------------------
	# TODO:	PART 1
	#	Write the MIPS-code that performs the the C-code (bubble sort) shown below.
	#	You may only use the $s, $v, $a, $t registers (and of course the $0 register)
	#	The above code has already created array A and A[0] to A[size-1] have been 
	#	entered by the user. After the bubble sort has been completed, the values im
	#	A are sorted in increasing order, i.e. A[0] holds the smallest value and 
	#	A[size -1] holds the largest value.
	#	
	#	int t = 0;
	#	
	#	for ( int i=0; i<size-1; i++ ) {
	#		for ( int j=0; j<size-1-i; j++ ) {
 	#			if ( A[j] > A[j+1] ) {
	#				t = A[j+1];
	#				A[j+1] = A[j];
	#				A[j] = t;
	#			}
	#		}
	#	}
	#		1 -1 2 10   i = 1  j = 1   size - 1 = 3
	# ----------------------------------------------------------------------------------
	
	add $t0, $0, $0  ## set i = 0
	addi $s3, $s1, -1 ## create size-1 variable
LOOP1:
	slt $t1, $t0, $s3 
	beq $t1, $0, LOOP1_DONE
	sub $s4, $s3, $t0 ## creates size-1-i variable
	add $t2, $0, $0 ## set j = 0
	addi $t5, $t2, 1 ## create j + 1
LOOP2:
	slt $t1, $t2, $s4
	beq $t1, $0, LOOP2_DONE
	sll $t3, $t5, 2 ## can use $t3 because never use j+1 again directly just for A[j+1]
	add $t3, $t3, $s0
	lw $s5, 0($t3) ## A[j+1]
	
  	
	sll $t4, $t2, 2
	add $t4, $t4, $s0
	lw $s6, 0($t4) ## A[j]
	
	
	
	slt $t1, $s5, $s6
	beq $t1, $0, IF_DONE
	sw $s6, 0($t3)
	sw $s5, 0($t4)
IF_DONE:
	addi $t2, $t2, 1
	addi $t5, $t5, 1
	j LOOP2
LOOP2_DONE:
	addi $t0, $t0, 1
	j LOOP1
LOOP1_DONE:
	
	# ----------------------------------------------------------------------------------
	# TODO:	PART 2
	#	Write the MIPS-code that performs the C-code (binary earch) shown below.
	#	You may only use the $s, $v, $a, $t registers (and of course the $0 register)
	#	The array A has already been sorted by your code above int PART 1, where A[0] 
	#	holds the smallest value and A[size -1] holds the largest value, and v holds 
	# 	the search value entered by the user
	#	
	#	int left = 0;
	# 	int right = size - 1;
	#	int middle = 0;
	#	int element_index = -1;
 	#
	#	while ( left <= right ) { 
        #
        #		middle = left + (right - left) / 2; 
	#
        #		if ( array[middle] == v) {
        #    			element_index = middle;
        #    			break;
        #		}
        #
        #		if ( array[middle] < v ) 
        #    			left = middle + 1; 
        #		else
        #    			right = middle - 1; 
	#
    	#	} 
	#
    	#	if ( element_index < 0 ) 
    	#		printf( "%d not in sorted A\n", v );
    	#	else 
    	#		printf( "Sorted A[%d] = %d\n", element_index, v );
	#			
	# ----------------------------------------------------------------------------------
	
	add $t0, $0, $0  ## left = 0
	addi $t2, $s1, -1 ## right = size - 1
	add $t3, $0, $0 ## middle = 0
	addi $t4, $0, -1 ## element_index = -1
LOOP3:
	slt $t1, $t2, $t0
	bne $t1, $0, LOOP3_DONE
	sub $t3, $t2, $t0
	srl $t3, $t3, 1
	add $t3, $t3, $t0
	
	sll $t5, $t3, 2
	add $t5, $t5, $s0
	lw $s3, 0($t5) ## A[middle]
	
	slt $t1, $s3, $s2
	bne $t1, $0, IF2_END
	slt $t1, $s2, $s3
	bne $t1, $0, IF2_END
	add $t4, $t3, $0
	j LOOP3_DONE
IF2_END:
	slt $t1, $s3, $s2
	beq $t1, $0, ELSE
	addi $t0, $t3, 1
	j IF3_END
ELSE:
	subi $t2, $t3, 1
	## j IF3_END
IF3_END:
	j LOOP3
LOOP3_DONE:
	slt $t1, $t4, $0
	beq $t1, $0, ELSE2	
	
	addi $v0, $0, 1  		# print v
  	add $a0, $0, $s2 		
  	syscall	
	
	addi $v0, $0, 4  		# print "not sorted in a"
  	la $a0, not_found		
  	syscall	
  	j IF4_END
 ELSE2:
 	addi $v0, $0, 4
 	la $a0, sorted_array_prompt
 	syscall
 	
 	addi $v0, $0, 1
 	add $a0, $0, $t4
 	syscall
 	
 	addi $v0, $0, 4
 	la $a0, close_bracket
 	syscall
 	
 	addi $v0, $0, 1
 	add $a0, $0, $s2
 	syscall
 IF4_END:
  	
# ----------------------------------------------------------------------------------
# Do not modify the exit
# ----------------------------------------------------------------------------------
exit:                     
  addi $v0, $0, 10      		# system call (10) exits the progra
  syscall               		# exit the program
