package a2;

import java.util.Scanner;

public class A2Novice {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int itemcount = scan.nextInt();
		int numberofveg = 0;
		double highestcal = 0;
		double lowestcal = 0;
		String highestitem = "";
		String lowestitem = "";
		for (int i=0; i<itemcount; i++) {
			String item = scan.next();
			double priceperounce = scan.nextDouble();
			if (scan.nextBoolean()==true) {
				numberofveg++;
			}
			double calper$ = scan.nextDouble() / priceperounce;
			if (calper$ > highestcal) {
				highestitem = item;
				highestcal = calper$;
			}
			if (i==0) {
				lowestitem = item;
				lowestcal = calper$;
			} else {
				if (calper$ < lowestcal) {
					lowestitem = item;
					lowestcal = calper$;
				}
			}
			
		}
	scan.close();
	
	System.out.println("Number of vegetarian ingredients: " + numberofveg);
	System.out.println("Highest cals/$: " + highestitem);
	System.out.println("Lowest cals/$: " + lowestitem);
		// Your code here.
	}
	
	// You can define helper methods here if needed.
	
}
