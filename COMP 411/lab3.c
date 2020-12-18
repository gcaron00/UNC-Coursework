#include <stdio.h>

int stringLength(char str2[]) {
    int length = 0;
    while(str2[length] != '\0') {
    	length++;
    }
    return length;
}


void isPalindrome(char str3[]) {
	int forward = 0;
	int back = stringLength(str3) - 1;
	while (forward < back) {
		if (str3[forward] != str3[back]) {
			printf("\"%s\" is not a palindrome \n", str3);
			return;
		}
		back--;
		forward++;
	}
	printf("\"%s\" is a palindrome \n", str3);
	return;
} 

int main( int argc, char** argv ) {
	char str[100];
	printf("Please enter a string (minimum 4 characters): ");
	scanf(" %s", str);
	int j = stringLength(str);
	while (j < 4) {
		printf("len(%s) < 4 characters, please retry: ", str);
		scanf(" %s", str);
		j = stringLength(str);
	}

	char answer;
	printf("Replace a character in \"%s\" (y/n): ", str);
	scanf(" %c", &answer);
	if (answer == 'y') {
		char oldchar;
		char newchar;
		printf("What character do you want to replace: ");
		scanf(" %c", &oldchar);
		printf("What is the replacement character: ");
		scanf(" %c", &newchar);
		for (int i = 0; i < stringLength(str); i++) { //could also use str[i] != '\0'
			if (str[i] == oldchar) {
				str[i] = newchar;
			}
		}
	}	
	isPalindrome(str);
	return 0;
} 

