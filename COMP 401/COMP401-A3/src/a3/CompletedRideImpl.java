package a3;

public class CompletedRideImpl implements CompletedRide {
	private RideRequest _request;
	private Driver _driver;
	//Position Original;
	
	
	public CompletedRideImpl(RideRequest request, Driver driver) {
		if (request == null) {
			throw new RuntimeException("no request");
		} else {
			_request = request;
		}
		if (driver == null) {
			throw new RuntimeException("no driver");
		} else {
			_driver = driver;
		}
		//Original = new Position(driver.getVehicle().getPosition().getX(), driver.getVehicle().getPosition().getY());
		
	
		
	}
	
	public RideRequest getRequest() {
		return _request;
	}
	
	public Driver getDriver() {
		return _driver;
	}
	
	public int getWaitTime() {
		  if (_driver.getVehicle().getOriginalPosition() == null) {
			return _driver.getVehicle().getPosition().getManhattanDistanceTo(_request.getClientPosition());
		  } else {
			return _driver.getVehicle().getOriginalPosition().getManhattanDistanceTo(_request.getClientPosition());
		  }
	}
	
	public int getTotalTime() {
		return _request.getRideTime() + getWaitTime();
	}
	
	public double getCost() {
		double i = _request.getRideTime();
		double t = getWaitTime();
		return .5*i + .1*t;
	}
	
	public double getPrice() {
		double i = _request.getRideTime();
		
		if (getWaitTime() < 25) {
			return 2.5 * i;
		}
		else if ( getWaitTime() < 50) {
			return 2.0 * i;
		}
		else if (getWaitTime() < 100) {
			return i;
		} else {
			return 0.5 * i;
		}
		
	}
	
	public double getProfit() {
		return getPrice() - getCost();
	}
}