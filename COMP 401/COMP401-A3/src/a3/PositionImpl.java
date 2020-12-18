package a3;

public class PositionImpl implements Position {
	private int _x;
	private int _y;
	
	public PositionImpl(int x, int y) { 
		_x = x;
		_y = y;
	}
		
	public int getX() {
		return _x;
	}

	public int getY() {
		return _y;
	}

	public int getManhattanDistanceTo(Position p) {
		int a = p.getX() - _x;
		int b = p.getY() - _y;
		if (b>0) {
			if (a>0) {
				return a + b;
			} else {
				return b - a;
			}
		} else {
			if (a>0) {
				return a - b;
			} else {
				return 0 - a - b;
			}
		}
	
	}

}