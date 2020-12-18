package a3test;

import a3.CompletedRide;
import a3.RoundRobinDispatcher;
import a3.Simulation;

public class SimulationTest {

	public static void main(String[] args) {
		Simulation sim = new Simulation(0, 10, new RoundRobinDispatcher());
		
		double total_profit = 0.0;
		CompletedRide[] ride_log = sim.getRideLog();
		
		for (int i=0; i<ride_log.length; i++) {
			double profit = ride_log[i].getProfit();
			System.out.println("Ride " + i + ": " + String.format("%.2f", profit));
			total_profit += profit;
		}
		System.out.println("Total Profit: " + String.format("%.2f", total_profit));
	}
}
