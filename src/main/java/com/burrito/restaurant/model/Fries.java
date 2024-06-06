package com.burrito.restaurant.model;

public class Fries extends FoodItem implements Cookable {

    private static final int prepTimeForOneServe = 8;
    private static final int batchSize = 5;

    public Fries(double unitPrice, int quantity) {
        super(quantity);
        this.setItemName(this.getClass().getSimpleName());
    }

    @Override
    public int getActualQuantityCooked() {
//        return getActualBatchesCooked(restaurant.getRemainedFries()) * batchSize;
        return 0;
    }

    @Override
    public int getPreparationTime() {
//        return prepTimeForOneServe * getActualBatchesCooked(restaurant.getRemainedFries());
        return 0;
    }
}
