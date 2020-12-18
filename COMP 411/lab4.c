#include <stdio.h>

// constants used in this lab
#define ADD 0
#define ADDI 1
#define AND 2
#define OR 3
#define NOR 4
#define SLL 5
#define SRA 6
#define EXIT 7

// MIPS OP and FUNC Codes
#define ADDI_OPCODE 8
#define R_OPCODE 0
#define ADD_FUNC 32
#define AND_FUNC 36
#define OR_FUNC 37
#define NOR_FUNC 39
#define SLL_FUNC 0
#define SRA_FUNC 3

// helper function that can be used for debugging 
void show_register_values( int*, int );

int main( int argc, char** argv ) {

	// ------------------------------------------------------
	// SIMULATOR VARIABLES
	//
	//	Please do not modify these variables or create new ones
	//
	// ------------------------------------------------------

	// lets create some variables that represent the three different registers
	// (rs = source, rt = target, and rd = destination) used in R and I type 
	// instructions.
	int rs, rt, rd;		

	// our simulator has six 16 bit temporary registers.
	// the initial value of each register is zero.
	short t_registers[6] = { 0, 0, 0, 0, 0, 0 }; 	

	// instruction selected by the user
	int instruction;	

	// signed immediate value using 2's complement representation
	short immediate_value;	

	// shift amount (see page 88 in the patterson and hennessy course textbook)
	short shamt;	
		
	int exit = 0;

	/* ------------------------------------------

	Welcome to the Comp 411 MIPS instruction simulator lab
	assignment. In this lab we'll simulate R and I type MIPS 
	instructions. Specifically:
		- bitwise logical operations (and, or, nor)
		- shift right and left operations
		- add and add immediate operations
	
	All of these instructions can be found in Fig. 2 
	(page 64) in the Patterson and Hennessy course 
	textbook.

	**** IMPORTANT: Please follow the input/output format shown in the ADD 
	and ADDI code below AND provided in the sample_sim_output.txt file.
	------------------------------------------- */


	while ( !exit ) {

		printf( "Simulator instructions:\n");
		printf( "(0)\tadd\n");
		printf( "(1)\taddi\n");
		printf( "(2)\tand\n");
		printf( "(3)\tor\n");
		printf( "(4)\tnor\n");
		printf( "(5)\tsll\n");
		printf( "(6)\tsra\n");
		printf( "(7)\texit simulator\n");
		printf( "Please select 0,1,2,3,4,5,6, or 7: ");
		scanf( "%d", &instruction );

		switch ( instruction ) {

			case ADD:
				// To get you going, below is the ADD simulator code.
				printf( "Choose the source (rs) register [0,1,2,3,4,5]: ");
				scanf( "%d", &rs );
				printf( "Choose the target (rt) register [0,1,2,3,4,5]: ");
				scanf( "%d", &rt );
				printf( "Choose the destination (rd) register [0,1,2,3,4,5]: ");
				scanf( "%d", &rd );
				printf( "The R-type message (in decimal) is: opcode=%d, rs=%d, rt=%d, rd=%d, sa=%d, and func=%d\n", R_OPCODE, rs, rt, rd, 0, ADD_FUNC );
				t_registers[rd] = t_registers[rs] + t_registers[rt];
				printf( "The hexadecimal value of $t%d=%04X\n", rd, t_registers[rd] );
				break;

			case ADDI:
				// To get you going, below is the ADDI simulator code.
				printf( "Choose the source (rs) register [0,1,2,3,4,5]: ");
				scanf( "%d", &rs );
				printf( "Enter a 16-bit immediate value (in hexadecimal): " );
				scanf( "%hx", &immediate_value );
				printf( "Choose the target (rt) register [0,1,2,3,4,5]: ");
				scanf( "%d", &rt );
				printf( "The I-type instruction (in decimal) is: opcode=%d, rs=%d, rt=%d, and imm=%d\n", ADDI_OPCODE, rs, rt, immediate_value );
				t_registers[rt] = t_registers[rs] + immediate_value;
				printf( "The hexadecimal value of $t%d=%04X\n", rt, t_registers[rt] );
				break;

			case AND:
				// TODO: You complete the code for this instruction
				printf( "Choose the source (rs) register [0,1,2,3,4,5]: ");
				scanf( "%d", &rs );
				printf( "Choose the source (rt) register [0,1,2,3,4,5]: " );
				scanf( "%d", &rt);
				printf( "Choose the destination (rd) register [0,1,2,3,4,5]: ");
				scanf( "%d", &rd );
				printf( "The R-type message (in decimal) is: opcode=%d, rs=%d, rt=%d, rd=%d, sa=%d, func=%d\n", R_OPCODE, rs, rt, rd, 0, AND_FUNC);
				t_registers[rd] = t_registers[rt] & t_registers[rs];
				printf( "The hexadecimal value of $t%d=%04X\n", rd, t_registers[rd] );
				break;

			case OR:
				//TODO: You complete the code for this instruction
				printf( "Choose the source (rs) register [0,1,2,3,4,5]: ");
				scanf( "%d", &rs );
				printf( "Choose the source (rt) register [0,1,2,3,4,5]: " );
				scanf( "%d", &rt);
				printf( "Choose the destination (rd) register [0,1,2,3,4,5]: ");
				scanf( "%d", &rd );
				printf( "The R-type message (in decimal) is: opcode=%d, rs=%d, rt=%d, rd=%d, sa=%d, func=%d\n", R_OPCODE, rs, rt, rd, 0, OR_FUNC);
				t_registers[rd] = t_registers[rt] | t_registers[rs];
				printf( "The hexadecimal value of $t%d=%04X\n", rd, t_registers[rd] );
				break;

			case NOR:
				// TODO: You complete the code for this instruction
				printf( "Choose the source (rs) register [0,1,2,3,4,5]: ");
				scanf( "%d", &rs );
				printf( "Choose the source (rt) register [0,1,2,3,4,5]: " );
				scanf( "%d", &rt);
				printf( "Choose the destination (rd) register [0,1,2,3,4,5]: ");
				scanf( "%d", &rd );
				printf( "The R-type message (in decimal) is: opcode=%d, rs=%d, rt=%d, rd=%d, sa=%d, func=%d\n", R_OPCODE, rs, rt, rd, 0, NOR_FUNC);
				t_registers[rd] = ~(t_registers[rt] | t_registers[rs]);
				printf( "The hexadecimal value of $t%d=%04X\n", rd, t_registers[rd] );
				break;

			case SLL:
				// TODO: You complete the code for this instruction
				printf( "Choose the source (rt) register [0,1,2,3,4,5]: " );
				scanf( "%d", &rt);
				printf( "Choose the destination (rd) register [0,1,2,3,4,5]: ");
				scanf( "%d", &rd );
				printf( "Enter left bit-shift amount (shamt): ");
				scanf( "%hx", &shamt );
				printf( "The R-type message (in decimal) is: opcode=%d, rs=0, rt=%d, rd=%d, sa=%d, func=%d\n", R_OPCODE, rt, rd, shamt, SLL_FUNC);
				t_registers[rd] = t_registers[rt] << shamt;
				printf( "The hexadecimal value of $t%d=%04X\n", rd, t_registers[rd] );
				break;

			case SRA:
				// TODO: You complete the code for this instruction
				printf( "Choose the source (rt) register [0,1,2,3,4,5]: " );
				scanf( "%d", &rt);
				printf( "Choose the destination (rd) register [0,1,2,3,4,5]: ");
				scanf( "%d", &rd );
				printf( "Enter right bit-shift amount (shamt): ");
				scanf( "%hx", &shamt );
				printf( "The R-type message (in decimal) is: opcode=%d, rs=0, rt=%d, rd=%d, sa=%d, func=%d\n", R_OPCODE, rt, rd, shamt, SRA_FUNC);
				t_registers[rd] = t_registers[rt] >> shamt;
				printf( "The hexadecimal value of $t%d=%04X\n", rd, t_registers[rd] );
				break;

			default:
				exit = 1;
				break;

		
		}

	}
	
	return 0;

} // end main function


void show_register_values( int* registers, int size ) {

	int i=0;

	for ( i=0; i<size; i++ ) {

		printf( "$s%d=%d\n", i, registers[i] );

	}

}