package a3;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

/*
 * Simulation
 * A ride simulator.
 * 
 * This class simulates a random sequence of ride requests on
 * a grid of a certain size. A number of random drivers with
 * vehicles are created. A number of "hotspot" positions are
 * randomly selected. Requests are generated from/to a hotspot
 * to/from a random position on the grid. A Dispatcher object is
 * employed to choose for each request which driver to use.
 * 
 * The long value "seed" is used to initialize the random number
 * generator used by the simulation. Using the same seed will 
 * result in the same sequence of random drivers, hotspots, and
 * ride requests. This makes it possible to repeat a simulation
 * and get the same results each time.
 * 
 * getRideLog()
 *   Returns an array of CompletedRide objects representing the
 *   results of the simulation.
 */

public class Simulation {

	/* Named constants for defaults */
	
	private static final int DEFAULT_GRID_WIDTH = 100;
	private static final int DEFAULT_GRID_HEIGHT = 100;
	private static final int DEFAULT_HOTSPOT_COUNT = 10;
	private static final int DEFAULT_DRIVER_COUNT = 10;

	private int _gridWidth;
	private int _gridHeight;
	private Position[] _hotSpots;
	private List<CompletedRide> _rideLog;
	private Random _rng;
	private Dispatcher _dispatcher;
	
	public Simulation(long seed, int request_count, Dispatcher dispatcher) {
		_gridWidth = DEFAULT_GRID_WIDTH;
		_gridHeight = DEFAULT_GRID_HEIGHT;
		_hotSpots = new Position[DEFAULT_HOTSPOT_COUNT];
		_rideLog = new ArrayList<CompletedRide>();
		_rng = new Random(seed);
		_dispatcher = dispatcher;
		
		for (int i=0; i<_hotSpots.length; i++) {
			_hotSpots[i] = createRandomPosition();
		}
		
		Driver[] drivers = new Driver[DEFAULT_DRIVER_COUNT];
		
		for (int i=0; i<drivers.length; i++) {
			drivers[i] = createRandomDriver(i);
		}

		
		for (int r=0; r<request_count; r++) {
			RideRequest request = createRandomRequest();
			CompletedRide ride = request.complete(_dispatcher.chooseDriver(drivers, request));
			_rideLog.add(ride);
		}
	}
	
	public CompletedRide[] getRideLog() {
		return _rideLog.toArray(new CompletedRide[_rideLog.size()]);
	}

	private Position createRandomPosition() {
		 return new PositionImpl(_rng.nextInt(_gridWidth), _rng.nextInt(_gridHeight));
	}
	
	private Driver createRandomDriver(int id) {
		String[] first_names = {"Alice", "Bob", "Carol", "Daveon", "Ebony", 
				"Fatima", "Geraldo", "Hannah", "Ito", "Javier"};
		String[] last_names = {"Smith", "Patel", "Kim", "Garcia", "Okafor"};
		
		String first = first_names[_rng.nextInt(first_names.length)];
		String last = last_names[_rng.nextInt(last_names.length)];
		Vehicle vehicle = new VehicleImpl("Toyota", "Prius", "DRV-" + String.format("%03d",  id), createRandomPosition());

		return new DriverImpl(first, last, id, vehicle);
						
	}
	
	private RideRequest createRandomRequest() {
		Position hotspot = _hotSpots[_rng.nextInt(_hotSpots.length)];
		Position rand_pos = createRandomPosition();
		
		// Half the time create a request to a hotspot, 
		// half the time create a request from a hotspot.

		boolean to_hotspot = _rng.nextBoolean();

		return (to_hotspot) ? new RideRequestImpl(rand_pos, hotspot) : new RideRequestImpl(hotspot, rand_pos);
	}
}
