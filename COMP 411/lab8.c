#include<stdio.h>

// global variable
char pattern[17] = {0};

// function prototype
void bingen( unsigned int N, unsigned int n );

int main( int argc, char** argv ) {

		unsigned int N = 0;
		// You can assume the user enters a
		// value of N >= 1 and N <= 16.
		// i.e. no error checking is necessary
		printf( "Enter the number of bits (N): ");
		scanf("%u", &N );

		unsigned int n = N;
		pattern[N] = '\0'; // Null terminate the string
		bingen( N, n );

		return 0;

} // end main 


// prototype implementation
void bingen( unsigned int N, unsigned int n ) {

    if ( n > 0 ) {

        pattern[N-n] = '0';
        bingen( N, n - 1 );

        pattern[N-n] = '1';
        bingen( N, n - 1 );

    } else printf( "%s\n", pattern );

} // end bingen