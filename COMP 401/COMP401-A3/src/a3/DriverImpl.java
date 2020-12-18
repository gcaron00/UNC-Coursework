package a3;

public class DriverImpl implements Driver {
	private String _first;
	private String _last;
	private int _id;
	private Vehicle _vehicle;

	
	public DriverImpl(String first, String last, int id, Vehicle vehicle) {
		if (first == null) {
			throw new RuntimeException("no first name");
		} else {
			_first = first;
		}
		if (last == null) {
			throw new RuntimeException("no last name");
		} else {
			_last = last;
		}
			_id = id;
		if (vehicle == null) {
			throw new RuntimeException("no vehicle");
		} else {
			_vehicle = vehicle;
		}
		 
	}
	
	public String getFirstName() {
		return _first;
	}
	
	public String getLastName() {
		return _last;
	}
	
	public String getFullName() {
		return _first + " " + _last;
	}
	
	public int getID() {
		return _id;
	}
	
	public Vehicle getVehicle() {
		return _vehicle;
	}
	
	public void setVehicle(Vehicle v) {
		if (v == null) {
			throw new RuntimeException("no vehicle");
		} else {
			_vehicle = v;
		}
	}
	
}