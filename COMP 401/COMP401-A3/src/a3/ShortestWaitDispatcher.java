package a3;

public class ShortestWaitDispatcher implements Dispatcher {

	private int _current_idx;
	
	public ShortestWaitDispatcher() {
		_current_idx = 0;
	}
	
	@Override
	public Driver chooseDriver(Driver[] availableDrivers, RideRequest request) {
		Position p = request.getClientPosition();
		Position x = availableDrivers[0].getVehicle().getPosition();
		int shortest = p.getManhattanDistanceTo(x);
		for (int i = 1; i < availableDrivers.length; i++) {
			Position y = availableDrivers[i].getVehicle().getPosition();
			if (p.getManhattanDistanceTo(y) < shortest) {
				shortest = p.getManhattanDistanceTo(y);
				_current_idx = i;
			}
		}
		return availableDrivers[_current_idx];
	}

}