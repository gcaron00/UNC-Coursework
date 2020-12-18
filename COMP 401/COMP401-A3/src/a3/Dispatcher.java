package a3;

/*
 * Dispatcher
 * Represents a strategy for choosing among a collection of available drivers
 * to assign to a specific ride request. 
 * 
 * chooseDriver(Driver[] availableDrivers, RideRequest request)
 *   Given an array of available drivers, choose and return one of the
 *   Driver objects in the array to assign to the given ride request.
 *   The array availableDrivers is expected to contain at least one Driver.
 *   The Driver object returned must be one of the objects in this array.
 *   
 *   Notes:
 *   - This method simply chooses a driver. It does NOT actually
 *     complete the ride request. 
 *   - This method should also not alter the array availableDrivers in any way. 
 *   - The array of drivers passed to this method may be different in length 
 *     and/or in element values (i.e., which Driver objects are included) 
 *     from one call to the method to the next.
 */

public interface Dispatcher {

	Driver chooseDriver(Driver[] availableDrivers, RideRequest request);
}
