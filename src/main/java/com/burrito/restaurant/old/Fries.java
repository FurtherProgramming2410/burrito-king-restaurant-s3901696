package com.burrito.restaurant.old;

public class Fries extends FoodItem implements Cookable{
	private static final int prepTimeForOneServe = 8;
	private static final int batchSize = 5;
	
	public Fries(double price, int quantity) {
		super(price, quantity);
	}
		
	private int getActualQuantityNeeded(int remainingServes) {
		if(remainingServes >= this.getQuantity()) {
			return 0;
		}else if(remainingServes > 0){
			return this.getQuantity() - remainingServes;
		}else {
			return this.getQuantity();
		}
	}
	private int getActualBatchesCooked(int remainingServes) {
		return (int) Math.ceil(getActualQuantityNeeded(remainingServes) / ((double) batchSize));
	}
	/**
	 * Fries can be left to next order. 
	 * Therefore, we first calculate how many batches needed to fulfill the current order.
	 * Then we multiply it by batch size.
	 * For example, if a restaurant has 2 fries on the stack and a customer orders 3 fries.
	 * We need to cook 1 batch of fries (5 serves) to fulfill this order.
	 * Therefore, this method returns 1 (number of batches) * 5 (batch size), meaning 5 fries will be cooked.
	 */
	@Override
	public int getActualQuantityCooked(RestaurantOld restaurant) {
		return getActualBatchesCooked(restaurant.getRemainedFries()) * batchSize;
	}
	/**
	 * Fries can be cooked in a batch of 5. 
	 * Therefore, we first compute the number of batches needed.
	 * And then multiple it by the prep time for each batch
	 */
	@Override
	public int getPreparationTime(RestaurantOld restaurant) {
		return prepTimeForOneServe * getActualBatchesCooked(restaurant.getRemainedFries());		
	}

}
