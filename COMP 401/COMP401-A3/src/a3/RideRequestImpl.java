package a3;

public class RideRequestImpl implements RideRequest {
	private Position _clientposition;
	private Position _destination;
	private boolean _completed = false;
	
	
	public RideRequestImpl(Position clientposition, Position destination) {
		if (clientposition == null) {
			throw new RuntimeException("no client position");
		} else {
			_clientposition = clientposition;
		}
		if (destination == null) {
			throw new RuntimeException("no destination");
		} else {
			_destination = destination;
		}
	}
	
	public Position getClientPosition() {
		return _clientposition;
	}
	
	public Position getDestination() {
		return _destination;
	}
	
	public boolean getIsComplete() {
		return _completed;
	}
	
	
	public CompletedRide complete(Driver driver) {
		if (driver == null) {
			throw new RuntimeException("no driver");
		} else {
			if (_completed == false) {
				_completed = true;
			}
			if (driver.getVehicle().getOriginalPosition() == null) {
				
				driver.getVehicle().moveToPosition(_clientposition);
				driver.getVehicle().moveToPosition(_destination);
			}
			CompletedRide theride = new CompletedRideImpl(this, driver);
			
			return theride;
		}
		
	}
	
	public int getRideTime() {
		return _clientposition.getManhattanDistanceTo(_destination);
	}
}
