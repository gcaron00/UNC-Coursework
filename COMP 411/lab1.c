#include <stdio.h>

int main(int argc, char** argv) {

	printf("Please select a 2D shape (1 = circle, 2 = rectangle, 3 = triangle): ");
	int i;
	scanf("%d", &i);
	double area = 0;
	switch (i) {
		case 1:
			printf("You selected a circle, please enter the radius: ");
			double r;
			scanf("%le", &r);
			
			area = 3.14159265358979323846 * r * r;
			printf("The area of your circle is: ");
			printf("%.1f \n", area);
			break;
		case 2:
			printf("You selected a rectangle, please enter the length: ");
			double l;
			scanf("%le", &l);
			
			printf("You selected a rectangle, please enter the width: ");
			double w;
			scanf("%le", &w);

			area = l * w;
			printf("The area of your rectangle is: ");
			printf("%.1f \n", area);
			break;
		case 3:
			printf("You selected a triangle, please enter the base: ");
			double b;
			scanf("%le", &b);

			printf("You selected a triangle, please enter the height: ");
			double h;
			scanf("%le", &h);

			area = 0.5 * b * h;
			printf("The area of your triangle is: ");
			printf("%.1f \n", area);
			break;
	}
	


}