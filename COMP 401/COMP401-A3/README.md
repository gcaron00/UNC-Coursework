# Car and Driver


In this assignment, you will write a number of relatively simple classes that implement interfaces that we have specified.
These classes are in support of a Uber simulator with clients making ride requests and drivers moving around
a city grid completing those requests.

## Novice (7 points)

Review the comments in the code for the following interfaces:
* Position
* Driver
* Vehicle
* RideRequest
* CompletedRide

Create the following classes which implement each of the interfaces above:
* PositionImpl
  * The constructor for this class should have the following form:
    ```
    public PositionImpl(int x, int y)
    ```
* DriverImpl
  * The constructor for this class should have the following form:
    ```
    public DriverImpl(String first, String last, int id, Vehicle vehicle)
    ```
* VehicleImpl
  * The constructor for this class should have the following form:
    ```
    public VehicleImpl(String make, String model, String plate, Position position)
    ```
  * The mileage a new vehicle should be initialized to 0.
* RideRequestImpl
  * The constructor for this class should have the following form:
    ```
    public RideRequestImpl(Position clientPosition, Position destination)
    ```
* CompletedRideImpl
  * The constructor for this class should have the following form:
    ```
    public CompletedRideImpl(RideRequest request, Driver driver)
    ```
  
A few notes:
* For now, ignore the code in the interface Dispatcher and the classes Simulation and RoundRobinDispatcher
* The constructors and methods of your implementations should appropriately check all values
  for validity. In particular, any reference types (i.e., String, Position, Driver, etc.) provided
  as parameters should be checked against null. If a reference is null, throw a runtime exception
  as explained in class.
* Any interface methods that can be implemented as default methods should be. This will require
  you to change the interfaces themselves to include the default method definition. 
* You should get in the habit of writing testing code. For this assignment, I suggest writing 
  small test programs that exercises each of the implementation and confirms that things are
  working as you expect. Get in the habit of putting your testing code in a separate package than
  the code it is testing. I've included an example in the package a3test called PositionTest for
  testing PositionImpl.

Once you have correct implementations for all of the classes described above, you should be able
to run the program SimulationTest in the a3test package. This test uses the code in the class Simulation
to simulate a series of random ride requests given a specific dispatching strategy. SimulationTest will
print the profit associated with each ride in the simulation as well as the total overall profit. If all
is well, you should be able to run this program and get the following output:
```
Ride 0: -10.60
Ride 1: 36.70
Ride 2: 151.10
Ride 3: 22.50
Ride 4: 81.60
Ride 5: 70.40
Ride 6: 51.10
Ride 7: 50.00
Ride 8: -13.60
Ride 9: 49.70
Total Profit: 488.90
```

## Adept (2 points)

Read the comments in the interface Dispatcher and the code in RoundRobinDispatcher as an example
of its implementation. Create the following two implementations of Dispatcher (both should provide
constructors that take no arguments):

* ShortestWaitDispatcher
  * This dispatcher should choose the driver that is closest to the client's position (i.e. will incur
    the shortest wait time.
  
* ShortButFairDispatcher
  * This dispatcher should choose the driver that is closest to the client's position but without
    considering the last five drivers chosen (you can assume that there will be more than five
    available drivers). In other words, if your dispatcher first chooses DriverA, then the next four
    times that the dispatcher is asked to choose a driver, DriverA should not be eligible for being
    chosen. 
  
## Jedi (1 point)

Update the code in Simulation to provide a more general constructor that allows one to specify
the grid dimensions, hotspot count, and driver count with the following form:
```
public Simulation(long seed, int request_count, Dispatcher dispatcher,
                  int grid_width, int grid_height, int hotspot_count, int driver_count)
```

Hint: You shouldn't have to write very much code here at all. Alter the code of the existing
constructor to become this more general constructor and then rewrite the existing constructor
to chain to the general constructor.
