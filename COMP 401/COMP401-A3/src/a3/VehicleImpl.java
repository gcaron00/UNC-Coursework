package a3;

public class VehicleImpl implements Vehicle {
	
	private String _make;
	private String _model;
	private String _plate;
	private Position _position;
	private int _mileage = 0;
	private Position _original_position;
	
	public VehicleImpl(String make, String model, String plate, Position position) {
		if (make == null) {
			throw new RuntimeException("no make");
		} else {
			_make = make;
		}
		if (model == null) {
			throw new RuntimeException("no model");
		} else {
			_model = model;
		}
		if (plate == null) {
			throw new RuntimeException("no plate");
		} else {
			_plate = plate;
		}
		if (position == null) {
			throw new RuntimeException("no position");
		} else {
			_position = position;
		}
		_original_position = null;
	}
	
	public String getMake() {
		return _make;
	}
	
	public String getModel() {
		return _model;
	}
	
	public String getPlate() {
		return _plate;
	}
	
	public int getMileage() {
		return _mileage;
	}
	
	public Position getPosition() {
		return _position;
	}
	
	public void moveToPosition(Position p) {
		if (p == null) {
			throw new RuntimeException("no position p");
		} else {
			if (_original_position == null) {
				_original_position = _position;
			}
			_mileage = _mileage + _position.getManhattanDistanceTo(p) ;
			_position = p;
		}
	}
	public void setOriginalPosition(Position x) {
		_original_position = x;
	}
	
	public Position getOriginalPosition() {
		return _original_position;
	}
}