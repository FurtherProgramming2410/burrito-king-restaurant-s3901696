package com.burrito.restaurant.old;

public interface Cookable {
	/**
	 * The method to calculate the total preparation time of a food item
	 */
	public abstract int getPreparationTime(RestaurantOld restaurant);
	/**
	 * The method to calculate the actual number of items that need to be cooked
	 */
	public abstract int getActualQuantityCooked(RestaurantOld restaurant);
}
