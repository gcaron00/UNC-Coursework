package a1;

import java.util.Scanner;

public class A1Jedi {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int itemcount = scan.nextInt();
		String[] items = new String[itemcount];
		double[] costs = new double[itemcount];
		for(int i=0; i<itemcount; i++) {
			items[i] = scan.next();
			costs[i] = scan.nextDouble();
		}
		int	peoplecount = scan.nextInt();
		String[] people = new String[peoplecount];
		int[] numberofcustomers = new int[itemcount];
		int[] itemsbought = new int[itemcount];
		for(int i=0; i<peoplecount; i++) {
			people[i] = scan.next() + " " + scan.next();
			int itemspurchased = scan.nextInt();
			String[] track = new String[itemspurchased];
			int[] numberofeach = new int[itemspurchased];
			for(int x=0; x<itemspurchased; x++) {
				int numberof = scan.nextInt();
				numberofeach[x] = numberof;
				String item = scan.next();
				track[x] = item;
			}
			for (int r=0; r<track.length; r++) {
				int y = 0;
				while(y<itemcount) {
					String a = items[y];
					if (a.equals(track[r])) {
						itemsbought[y] = itemsbought[y] + numberofeach[r];
						if (r==0) {
							numberofcustomers[y]++;
						} else {
							int z = 0;
							while(z<r) {
								if (track[z].equals(track[r])) {
									z = itemspurchased;
								} else {
									if (z == (r-1)) {
										numberofcustomers[y]++;
										z = r;	
									} else {
										z++;
									}
								}
							}
						}
						y = itemcount;
					} else {
						y++;
					}
				}
			}
		}
		for (int i=0; i<itemcount; i++) {
			if (numberofcustomers[i]>0) {
				System.out.println(numberofcustomers[i] + " customers bought " + itemsbought[i] + " " + items[i]);
			} else {
				System.out.println("No customers bought" + " " + items[i]);
			}
		}		
		scan.close();
	}
}

