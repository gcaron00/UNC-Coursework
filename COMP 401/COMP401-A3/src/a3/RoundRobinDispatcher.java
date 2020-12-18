package a3;

/*
 * RoundRobinDispatcher
 * A simple implementation of Dispatcher that chooses a driver based on index position
 * within the array. The index position used is simply one higher than the last index
 * position used, wrapping back around to 0 if necessary.
 */

public class RoundRobinDispatcher implements Dispatcher {

	private int _last_idx;
	
	public RoundRobinDispatcher() {
		_last_idx = -1;
	}
	
	@Override
	public Driver chooseDriver(Driver[] availableDrivers, RideRequest request) {
		_last_idx = (_last_idx + 1) % availableDrivers.length;
		return availableDrivers[_last_idx];
	}

}
