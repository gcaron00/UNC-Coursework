package a1;

import java.util.Scanner;

public class A1Novice {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		

		// Your code follows here.
		
		int count = scan.nextInt();
		// This is the total number of customers
		double[] storage = new double[count];
		String[] name = new String[count];
		for (int x=0; x<count; x++) {
			name[x] = scan.next().charAt(0) + ". " + scan.next();
			int count2 = scan.nextInt();
			double[] values = new double[count2];
			for (int i=0; i<values.length; i++) {
				values[i] = scan.nextInt();
				String kind = scan.next();
				values[i] = values[i] * scan.nextDouble();
			}
			storage[x] = calculateValueSum(values);
		}
		for (int i=0; i<storage.length; i++) {
			System.out.println(name[i] + ": " + String.format("%.2f", storage[i]));
		}
		scan.close();
	}
	static double calculateValueSum(double[] vals) {	
		
		double sum = 0;
			
		for (int i=0; i<vals.length; i++) {
				sum += vals[i];
		}
		
		
		return sum;
		
				
	}
}
		