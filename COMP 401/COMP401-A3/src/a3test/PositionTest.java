package a3test;

import a3.Position;
import a3.PositionImpl;

public class PositionTest {

	public static void main(String[] args) {
		
		// Create a few positions.
		Position a = new PositionImpl(3,5);
		Position b = new PositionImpl(10,5);
		Position c = new PositionImpl(0,0);
		
		// Make sure that the positions report x and y properly.
		
		if (a.getX() != 3) {
			throw new RuntimeException("Expected a's x coordinate to be 3");
		}
		if (b.getX() != 10) {
			throw new RuntimeException("Expected b's x coordinate to be 10");
		}
		if (c.getX() != 0) {
			throw new RuntimeException("Expected c's x coordinate to be 0");
		}
		
		if (a.getY() != 5) {
			throw new RuntimeException("Expected a's y coordinate to be 5");
		}
		if (b.getY() != 5) {
			throw new RuntimeException("Expected b's y coordinate to be 5");
		}
		if (c.getY() != 0) {
			throw new RuntimeException("Expected c's y coordinate to be 0");
		}

		// Check manahattan distance calculations
		
		if (a.getManhattanDistanceTo(b) != 7) {
			throw new RuntimeException("Expected manhattan distance from a to b to be 7");
		}
		if (a.getManhattanDistanceTo(c) != 8) {
			throw new RuntimeException("Expected manhattan distance from a to c to be 8");
		}
		if (b.getManhattanDistanceTo(a) != 7) {
			throw new RuntimeException("Expected manhattan distance from b to a to be 7");
		}
		if (c.getManhattanDistanceTo(a) != 8) {
			throw new RuntimeException("Expected manhattan distance from c to a to be 8");
		}
		if (a.getManhattanDistanceTo(a) != 0) {
			throw new RuntimeException("Expected manhattan distance from a to itself to be 0");			
		}
		
		// If we are here, then everything must have worked.
		System.out.println("Test passed");
		
	}
}
