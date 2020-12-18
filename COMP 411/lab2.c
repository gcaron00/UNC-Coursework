#include <stdio.h>

int main( int argc, char** argv ) {

	int j = 0;
	while (j < 2 || j > 10) {
		printf("Please enter the array size (between 2 and 10): ");
		scanf("%d", &j);
	}
	int arr[j];
	for (int i = 0; i < j; i++) {
		printf("Please enter the value for array[%d]: ", i);
		scanf("%d", &arr[i]);
	}
	for (int i = 0; i <= j - 2; i++) {
		for (int k = 0; k <= j - 2 - i; k++) {
			if (arr[k + 1] < arr[k]) {
				int m = arr[k];
				arr[k] = arr[k + 1];
				arr[k + 1] = m;
			}
		}
	}
	printf("The array values sorted in non-decreasing order are: ");
	for (int i = 0; i < j; i++) {
		if (i + 1 == j) {
			printf("%d \n", arr[i]);
		} else {
			printf("%d, ", arr[i]);
		}
		
	}
	return 0;
}