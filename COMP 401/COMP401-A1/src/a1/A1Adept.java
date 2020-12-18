package a1;

import java.util.Scanner;

public class A1Adept {

	public static void main(String[] args) {
		
	
		
		Scanner scan = new Scanner(System.in);
		
		int itemcount = scan.nextInt();
		// number of items sold
		String[] items = new String[itemcount];
		double[] costs = new double[itemcount];
		for(int i=0; i<itemcount; i++) {
			items[i] = scan.next();
			costs[i] = scan.nextDouble();
		}
		
		
		int	peoplecount = scan.nextInt();
		String[] people = new String[peoplecount];
		double[] costofall = new double[peoplecount];
		for(int i=0; i<peoplecount; i++) {
			people[i] = scan.next() + " " + scan.next();
			int itemspurchased = scan.nextInt();
			//double[] itemcost = new double[itemspurchased];
			double itemcost = 0;
			for(int x=0; x<itemspurchased; x++) {
				double numberofeach = scan.nextDouble();
				String item = scan.next();
				int y = 0;
				while(y<itemcount) {
					String a = items[y];
					if (a.equals(item)) {
						itemcost = itemcost + costs[y] * numberofeach;
						y = itemcount;
						
					} else {
						y++;
					}
				}
				
				// add decimal and change novice
			}
			costofall[i] = itemcost;
		}
	
		double biggest = findValueMax(costofall);
		double smallest = findValueMin(costofall);
		int i = 0;
		while (i<peoplecount) {
			if (costofall[i]==biggest) {
				System.out.println("Biggest: " + people[i] + " (" + String.format("%.2f", biggest) + ")");
				i = peoplecount;
				int x = 0;
				while (x<peoplecount) {
					if (costofall[x]==smallest) {
						System.out.println("Smallest: " + people[x] + " (" + String.format("%.2f", smallest) + ")");
						x = peoplecount;
					} else {
						x++;
					}
				}
			} else {
				i++;
			}
		}
		System.out.println("Average: " + String.format("%.2f", calculateValueAvg(costofall)));
		
		scan.close();
			
	}
	static double calculateValueSum(double[] vals) {
		
		double sum = 0;
			
		for (int i=0; i<vals.length; i++) {
				sum += vals[i];
		}
		
		
		return sum;
		
				
	}
	static double findValueMin(double[] vals) {
		
		
		double cur_min = vals[0];
		
		
		
		for (int i=1; i < vals.length; i++) {
			if (vals[i] < cur_min) {
				cur_min = vals[i];
			}
		}
		
		return cur_min;
	}

	
	static double findValueMax(double[] vals) {
		
		double cur_max = vals[0];
		
		
		
		for (int i=1; i < vals.length; i++) {
			if (vals[i] > cur_max) {
				cur_max = vals[i];
			}
		}
		
		return cur_max;
	}
	static double calculateValueAvg(double[] vals) {
		
		double sum = 0;
			
		for (int i=0; i<vals.length; i++) {
				sum += vals[i];
		}
		
		
		return sum / vals.length;
		
				
	}
}	
	
