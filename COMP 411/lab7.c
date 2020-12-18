#include <stdio.h>

// -----------------------------------
// Global variables

int AA[100];  		// linearized version of A[10][10]
int BB[100];  		// linearized version of B[10][10]
int CC[100];  		// linearized version of C[10][10]
int m;       		// actual size of the above matrices is mxm, where m is at most 10

// -----------------------------------
// Function proto-type defintions

void run();
void matrixmult();
void printC();

// -----------------------------------
// Main function

int main( int argc, char** argv ) {

	m = 0;

	printf("m = ");
	scanf( "%d", &m);	// assume the value of m enter by the user is ( 1 <= m <= 10 )

	run();

	return 0;

}

// -------------------------------
// Function definitions

void run() {

	for ( int i=0; i<m*m; i++ ) {

		printf("AA[%d] = ", i );
		scanf( "%d", &AA[i] );
		printf("BB[%d] = ", i );
		scanf( "%d", &BB[i] );

	}

	matrixmult();

}

void matrixmult() {

	int s = 0;

	for ( int i=0; i<m; i++ ) {

		for ( int j=0; j<m; j++ ) {

			s=0;

			for ( int k=0; k<m; k++ ) {

				s += AA[i * m + k] * BB[k * m + j];

			}

			CC[i * m + j] = s;
		}

	}

	printC();

}

void printC() {

	for ( int i=0; i<m*m; i++ ) {

		printf( "CC[%d] = %d\n", i, CC[i] );

	}

}