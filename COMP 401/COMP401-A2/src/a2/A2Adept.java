package a2;

import java.util.Scanner;

public class A2Adept {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int itemcount = scan.nextInt();
		String[][] items = new String[itemcount][2];
		double[][] calandcost = new double[itemcount][2];
		for (int i=0; i<itemcount; i++) {
			items[i][0] = scan.next();
			calandcost[i][0] = scan.nextDouble();
			if (scan.nextBoolean()==true) {
				items[i][1] = "Vegetarian";
			} else {
				items[i][1] = "Non-Vegetarian";
			}
			calandcost[i][1] = scan.nextDouble();	
		}
		int menucount = scan.nextInt();
		String[][] menu = new String[menucount][2];
		double[][] menucalandcost = new double[menucount][2];
		for (int i=0; i<menucount; i++) {
			menucalandcost[i][0] = 0;
			menucalandcost[i][1] = 0;
		}
		//System.out.println(items[2][0]);
		//System.out.println(items[2][1]);
		for (int i=0; i<menucount; i++) {
			menu[i][0] = scan.next();
			int ingredientcount = scan.nextInt();
			//System.out.println(ingredientcount);
			for (int j=0; j<ingredientcount; j++) {
				String ingredient = scan.next();
				//System.out.println(ingredient);
				int k = 0;
				while (k<itemcount) {
					if (items[k][0].equals(ingredient)) {
						double ounces = scan.nextDouble();
						menucalandcost[i][0] = calandcost[k][0] * ounces + menucalandcost[i][0];
						menucalandcost[i][1] = calandcost[k][1] * ounces + menucalandcost[i][1];
						if (items[k][1].equals("Non-Vegetarian")) {
							menu[i][1] = "Non-Vegetarian";
						} else {
							if (j==0) {
								menu[i][1] = "Vegetarian";
							}
						}
						k = itemcount;
					} else {
						k++;
					}
				}
			}
		}
		scan.close();
		for (int i=0; i<menucount; i++) {
			System.out.println(menu[i][0] + ":");
			System.out.println("  " + ((int) (menucalandcost[i][1] + 0.5)) + " calories");
			System.out.println("  $" + String.format("%.2f", menucalandcost[i][0]));
			System.out.println("  " + menu[i][1]);
		}
		// Your code here.
	}
	
	// You can define helper methods here if needed.
	
}
