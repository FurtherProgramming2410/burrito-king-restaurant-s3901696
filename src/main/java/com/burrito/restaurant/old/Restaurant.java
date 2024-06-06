package com.burrito.restaurant.old;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * The Restaurant class tracks the state of food prices, all placed orders, and remained fries on the food stack.
 */
public class Restaurant {
	private String name;
    // keep food prices
    private static final HashMap<String, Double> priceMap = new HashMap<String, Double>();
    // track left-overs from previous order
    private int remainedFries;
    // store all historical orders
    private static final LinkedList<Order> allOrders = new LinkedList<Order>();
    private static final int mealDiscount = 3;
    
    public Restaurant(String name) {
    	priceMap.put("Burrito", 7.0);
		priceMap.put("Fries", 4.0);
		priceMap.put("Soda", 2.5);
		this.remainedFries = 0;
		this.name = name;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public static int getDiscount() {
    	return mealDiscount;
    }

      
    public static double getPrice(String foodName) {
		return priceMap.get(foodName);
	}
    
    public LinkedList<Order> getAllOrders(){
    	return this.allOrders;
    }
    
    public int getRemainedFries() {
		return this.remainedFries;
	}
    
    /**
     * The method to update food price.
     */
	public static void updatePrice(String foodName, double newPrice) {
		priceMap.put(foodName, newPrice);
	}
	
	/**
     * The method to update remaining Fries
     */
	public boolean updateRemainingServes(Order order) {
		HashMap<String, Integer> cookables = order.mapToCookables();
		Fries friesToCook = new Fries(Restaurant.getPrice("Fries"), cookables.get("Fries"));
		int oldRemained = this.remainedFries;
		this.remainedFries = friesToCook.getActualQuantityCooked(this) + oldRemained - friesToCook.getQuantity();
		if (this.remainedFries != oldRemained) {
			return true;						
		}
		return false;
	}
	
	
	/**
	 * The method to add a new order to the list of historical orders
	 */
    public void addOrderToHistory(Order order) {
    	this.allOrders.add(order);
    }   
}
