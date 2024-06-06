package com.burrito.restaurant.model;

public class Burrito extends FoodItem implements Cookable {
    private static final int batchPrepTime = 9;
    private static final int batchSize = 2;

    public Burrito(double unitPrice, int quantity) {
        super(unitPrice, quantity);
    }

    /**
     * Burritos can be cooked in a batch of 2.
     * Therefore, we first compute the number of batches needed.
     * And then multiply it by the prep time for each batch
     */
    @Override
    public int getPreparationTime(Restaurant restaurant) {
        return batchPrepTime * ((int) Math.ceil(this.getQuantity() / ((double) batchSize)));
    }

    /**
     * Burritos cannot be left to next order.
     * Therefore, we always cook the number of burritos ordered by the customer
     */
    @Override
    public int getActualQuantityCooked(Restaurant restaurant) {
        return this.getQuantity();
    }
}
