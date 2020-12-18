# Assignment 6

In this assignment, you will create three classes that implement the `Iterator<>` interface in specialized ways.

Read through the code provided. You should recognize the interfaces Position, Driver, and Vehicle and their implementations PositionImpl, DriverImpl, and VehicleImpl from Assignment 3. These classes are complete and you should not have to modify them in any way to complete this assignment.

# Novice (10 points)

Create a class called `ProximityIterator` that implements the interface `Iterator<Driver>`. The constructor for the class should be declared as:

```
	public ProximityIterator(Iterable<Driver> driver_pool, Position client_position, int proximity_limit)
```

A ProximityIterator should iterate through all of the Driver objects in the collection that have a Manhattan distance to the provided `client_position` that is less than or equal to `proximity_limit`. If `next()` is called but there is no next eligible driver, throw a `NoSuchElementException`.

Tips:
* In your constructor, use the `iterator` method of the provided driver_pool to create an iterator for all of the Driver objects in the collection. Store this iterator in an instance field.
* Use an instance field to store the next driver that matches the proximity limit. Initialize this to null in your constructor
* To implement hasNext(), first see if you already found the next appropriate driver by checking your next driver field (i.e., testing to see if it is not null). If you already found the next driver, just return true. If not, retrieve drivers from your driver pool iterator until you either find the next appropriate driver or you run out of drivers. If you find an appropriate driver, store it in your next driver instance field and return true. If the pool runs out of drivers, then return false.
* To implement next(), first call hasNext(). If false, throw a NoSuchElementException. If true, then you know that the next eligible driver must be in your next driver instance field. Copy this to a local variable. Reset your next driver field to be null so that you don't keep returning the same driver. Now return the value of the local variable that you copied the next driver to.

# Adept (5 points)

Create a class called `ExpandingProximityIterator` that implements the interface `Iterator<Driver>`. The constructor for ths class should be declared as:

```
	public ExpandingProximityIterator(Iterable<Driver> driver_pool, Position client_position, int expansion_step)
```

An ExpandingProximityIterator should first iterate through all of the Driver objects in the collect that have a Manhattan distance to the provided `client_position` that is less than or equal to 1. After all such drivers have been exhausted, the iterator should start at the beginning of the pool and iterate through drivers that have a distance that is greater than one but less than or equal to 1+expansion_step. After all of these drivers have been exhausted, the iterator should start at the beginning of the pool and iterate through drivers that have a distance that is greater then 1+expansion_step but less than or equal to 1+2\*expansion_step. After these have been exhausted, go through drivers that are greater than 1+2\*expansion_step but less than or equal to 1+3\*expansion_step. And so on and so forth until there are no drivers available whatsoever.

Tips:
* You will need to encapsulate `driver_pool` as an instance field because you will need to create a new iterator for the pool each time you run out of drivers for a particular "ring" size. 
* You will need to detect when you have run out of drivers completely so that hasNext() returns false eventually. There are a couple of different ways to do this. 
  * One way is to maintain a boolean flag that is set whenever a driver is encountered that is outside of the current ring while looking for the next driver. Then when you have run out of drivers at the current ring size, you can check this flag to make sure that at least one driver is still outside of the current ring and so you should keep expanding. Be sure to reset the flag each time you expand.
  * Another way to do this is to keep a count of the number of times next() has been successfully called. When this count is equal to the size of the driver pool, you know that every driver has been found at some ring size already and that expanding the ring won't help (i.e., there are no more drivers).

# Jedi (5 points)

Create a class called `SnakeOrderAcrossPoolsIterator` that implements the interface `Iterator<Driver>`. The constructor for this class should be declared as:

```
	public SnakeOrderAcrossPoolsIterator(List<Iterable<Driver>> driver_pools)
```

The constructor to this class is given a List of iterable driver collections. This version of the iterator should retrieve the next driver from each pool in turn in "snake" order. Snake order means first going from first to last and then reversing and going from last to first. For example, let's say there are 4 iterable collections in the driver_pools list. The first driver retrieved should come from pool 0, followed by the next driver from pool 1, then pool 2, then pool 3, then pool 3 again, then pool 2, then pool 1, then pool 0, then pool 0 again, then pool 1, and so on and so forth. The pools may have a different number of drivers in them. Once a pool runs out of drivers, it is simply skipped over. This iterator will run out of drivers once all of the pools in the list have run out.

Tips:
* In your constructor, use the list of driver pools provided to create a corresponding list of driver iterators for each pool and store this as an instance field. Keep track of an index into this list for which pool iterator should be used next.
