package a3;

public class ShortButFairDispatcher implements Dispatcher {

	private int _current_idx;
	private Driver[] _last_drivers = new Driver[5];
	
	public ShortButFairDispatcher() {
		_current_idx = 0;
	}
	
	@Override
	public Driver chooseDriver(Driver[] availableDrivers, RideRequest request) {
		
		Position p = request.getClientPosition();
		int shortest = 0;
		
		for (int i = 0; i < availableDrivers.length; i++) {
			for (int j = 0; j < 5; j++) {
				if (availableDrivers[i].equals(_last_drivers[j])) {
					j = 5;
				} else {
					Position y = availableDrivers[i].getVehicle().getPosition();
					if (i > 0 ) {
						if (p.getManhattanDistanceTo(y) < shortest) {
							shortest = p.getManhattanDistanceTo(y);
							_current_idx = i;
					} else {
						shortest = p.getManhattanDistanceTo(y);
						_current_idx = i;
					}
					}
				}
			}
		}
		_last_drivers[0] = availableDrivers[_current_idx];
		for (int k = 4; k > 0; k--) {
			_last_drivers[k] = _last_drivers[k-1];
		}
		return availableDrivers[_current_idx];
	}
}

