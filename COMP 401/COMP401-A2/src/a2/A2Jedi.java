package a2;

import java.util.Scanner;

public class A2Jedi {

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
		String[] menu = new String[menucount];
		double[][] measurements = new double[menucount][itemcount];
		for (int i=0; i<menucount; i++) {
			for (int j=0; j<itemcount; j++) {
				measurements[i][j] = 0;
			}
		}
		// might have to iniate all of measurements to 0
		
		for (int i=0; i<menucount; i++) {
			menu[i] = scan.next();
			int ingredientcount = scan.nextInt();
			//System.out.println(ingredientcount);
			for (int j=0; j<ingredientcount; j++) {
				String ingredient = scan.next();
				//System.out.println(ingredient);
				int k = 0;
				while (k<itemcount) {
					if (items[k][0].equals(ingredient)) {
						measurements[i][k] = scan.nextDouble();
						k = itemcount;
					} else {
						k++;
					}
				}
			}
		}
		int[] ordercount = new int[menucount];
		for (int i=0; i<menucount; i++) {
			ordercount[i] = 0;
		}
		int i = 0;
		while (i==0) {
			int j = 0;
			String order = scan.next();
			if (order.equals("EndOrder")) {
				i = 1;
			} else {
				while (j<menucount) {
					if (menu[j].equals(order)) {
						ordercount[j]++;
						j = menucount;
					} else {
						j++;
					}
				}
			}
			
		}
		double[] totals = new double[itemcount];
		for (int k=0; k<itemcount; k++) {
			totals[k] = 0;
		}
		for (int k=0; k<menucount; k++) {
			for (int t=0; t<itemcount; t++) {
				measurements[k][t] = measurements[k][t] * ordercount[k];
			}
		}
		for (int k=0; k<itemcount; k++) {
			for (int t=0; t<menucount; t++) {
				totals[k] = totals[k] + measurements[t][k];
			}
		}
		System.out.println("The order will require:");
		for (int k=0; k<itemcount; k++) {
			System.out.println(String.format("%.2f", totals[k]) + " ounces of " + items[k][0]);
		}
			 
		
			
	
		scan.close();
	}
		// Your code here.
	
	
	// You can define helper methods here if needed.
	
}
