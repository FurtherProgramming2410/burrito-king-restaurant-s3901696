package com.burrito.restaurant.model;

public class Fries extends FoodItem implements Cookable {

    private static final int prepTimeForOneServe = 8;
    private static final int batchSize = 5;

    public Fries(double unitPrice, int quantity) {
        super(unitPrice, quantity);
    }

    private int getActualQuantityNeeded(int remainingServes) {
        if (remainingServes >= this.getQuantity()) {
            return 0;
        } else if (remainingServes > 0) {
            return this.getQuantity() - remainingServes;
        } else {
            return this.getQuantity();
        }
    }

    private int getActualBatchesCooked(int remainingServes) {
        return (int) Math.ceil(getActualQuantityNeeded(remainingServes) / ((double) batchSize));
    }

    @Override
    public int getActualQuantityCooked(Restaurant restaurant) {
        return getActualBatchesCooked(restaurant.getRemainedFries()) * batchSize;
    }

    @Override
    public int getPreparationTime(Restaurant restaurant) {
        return prepTimeForOneServe * getActualBatchesCooked(restaurant.getRemainedFries());
    }

    public void addQuantity(int additionalQuantity) {
        this.quantity += additionalQuantity;
    }
}
