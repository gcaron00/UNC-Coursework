package a6;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ProximityIterator implements Iterator<Driver> {
	
	private Iterator<Driver> _driver_poolIterator;
	private Position _client_position;
	private int _proximity;
	private Driver _next_driver = null;
	
	public ProximityIterator(Iterable<Driver> driver_pool, Position client_position, int proximity) {
		if (driver_pool == null || client_position == null || proximity < 0) {
			throw new IllegalArgumentException("NO");
		} else {
			_driver_poolIterator = driver_pool.iterator();
			_client_position = client_position;
			_proximity = proximity;
		}
	}
	
	public boolean hasNext() {
		if (_next_driver != null) {
			return true;
		}
		while (_driver_poolIterator.hasNext()) {
			Driver isit = _driver_poolIterator.next();
			if (isit.getVehicle().getPosition().getManhattanDistanceTo(_client_position) <= _proximity) {
				_next_driver = isit;
				return true;				
			}
		}
		return false;
	}
	public Driver next() {
		if (hasNext() == false) {
			throw new NoSuchElementException("you can't do that");
		} else {
			Driver next_driver = _next_driver;
			_next_driver = null;
			return next_driver;
		}
	}
	
}
	
