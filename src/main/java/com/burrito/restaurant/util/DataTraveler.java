package com.burrito.restaurant.util;

/**
 * Interface for transferring data between two controllers.
 * This interface defines a method for passing data from one controller to another.
 */
@FunctionalInterface
public interface DataTraveler {
    /**
     * Transfers data between two controllers.
     *
     * @param o Variable number of objects representing the data to be transferred.
     */
    public void data(Object... o);
}

