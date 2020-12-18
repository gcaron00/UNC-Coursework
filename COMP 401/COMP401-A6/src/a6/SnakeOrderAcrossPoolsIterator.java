package a6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class SnakeOrderAcrossPoolsIterator implements Iterator<Driver> {

	//private List<Iterable<Driver>> _driver_pools;
	private List<Iterator<Driver>> _driver_poolsIterator;
	private int _iterator_count;
	private Driver _next_driver;
	//private List<Integer> _iterator_sizes;
	private int _driver_count;
	private int _iterator_index;
	//private List<Iterator<Driver>> _driver_poolsIteratorReverse;
	private int _flag;
	
	public SnakeOrderAcrossPoolsIterator(List<Iterable<Driver>> driver_pools) {
		if (driver_pools == null) {
			throw new IllegalArgumentException("NO");
		} else {
			//_driver_pools = driver_pools;
			_driver_poolsIterator = new ArrayList<Iterator<Driver>>();
			//_driver_poolsIteratorReverse = new ArrayList<Iterator<Driver>>();
			//_iterator_sizes = new ArrayList<Integer>();
			_driver_count = 0;
			for (Iterable<Driver> i: driver_pools) {
				if (i == null) throw new IllegalArgumentException("NO");
				_driver_poolsIterator.add(i.iterator());
				//_driver_poolsIteratorReverse.add(0, i.iterator());
				int _length = 0;
				for(Driver j: i) {
					_length++;
				}
				//_iterator_sizes.add(_length);
				_driver_count = _driver_count + _length;
			}
			_iterator_count = driver_pools.size();
			_next_driver = null;
			_flag = 1;
			_iterator_index = 0;
		}
	}
	
	public boolean hasNext() {
		if (_next_driver != null) {
			return true;
		} else {
			while (_driver_count > 0) {
				// could have removed iterater from list when empty and done while (_iterator_count > 0) for first while loop and iterator_count would change when iterator removed
				while (_next_driver == null) {
					if (_flag == 1) {
						if (_iterator_index < _iterator_count) {
							if (_driver_poolsIterator.get(_iterator_index).hasNext()) {
								_next_driver = _driver_poolsIterator.get(_iterator_index).next();
								_iterator_index++;
								_driver_count--;
								return true;
							} else {
								_iterator_index++;
							}
						} else {
							_flag = _flag * -1;
							_iterator_index = _iterator_count - 1;
						}
					} else {
						if (_iterator_index >= 0) {
							if (_driver_poolsIterator.get(_iterator_index).hasNext()) {
								_next_driver = _driver_poolsIterator.get(_iterator_index).next();
								_iterator_index--;
								_driver_count--;
								return true;
							} else {
								_iterator_index--;
							}
						} else {
							_flag = _flag * -1;
							_iterator_index = 0;
						}
						
					}	
				}
			}
			return false;
		}
		
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