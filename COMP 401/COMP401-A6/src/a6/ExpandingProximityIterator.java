package a6;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ExpandingProximityIterator implements Iterator<Driver> {
	
	private Iterable<Driver> _driver_pool;
	private Position _client_position;
	private int _expansion_step;
	private Driver _next_driver = null;
	private int _length;
	private int _used;
	private int iterates;
	private int iterator_counter;
	private Iterator<Driver> _driver_poolIterator;
	
	public ExpandingProximityIterator(Iterable<Driver> driver_pool, Position client_position, int expansion_step) {
		if (driver_pool == null || client_position == null || expansion_step < 0) {
			throw new IllegalArgumentException("NO");
		} else {
			_driver_pool = driver_pool;
			_expansion_step = expansion_step;
			_client_position = client_position;
			_length = 0;
			for (Driver i: driver_pool) {
				_length++;
			}
			_driver_poolIterator = _driver_pool.iterator();
			_used = 0;
			iterates = 0;
			iterator_counter = 0;
		}	
	}
	
	public boolean hasNext() {
		if (_length > _used) {
			if (_next_driver != null) {
				return true;
			} while(_next_driver == null) {
				if (iterator_counter == _length) {
					_driver_poolIterator = _driver_pool.iterator();
					iterates++;
					iterator_counter = 0;
				}
				while(_driver_poolIterator.hasNext()) {
					iterator_counter++;
					Driver isit = _driver_poolIterator.next();
					if (iterates == 0) {
						if (isit.getVehicle().getPosition().getManhattanDistanceTo(_client_position) <= (1 + iterates * _expansion_step) && isit.getVehicle().getPosition().getManhattanDistanceTo(_client_position) >= 0) {
							_next_driver = isit;
							return true;	
						}
					} else {
						if (isit.getVehicle().getPosition().getManhattanDistanceTo(_client_position) <= (1 + iterates * _expansion_step) && isit.getVehicle().getPosition().getManhattanDistanceTo(_client_position) > (1 + (iterates - 1) * _expansion_step)) {
							_next_driver = isit;
							return true;	
						}
					}
				}
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
			_used++;
			return next_driver;
		}
	}
	
}