package com.burrito.restaurant.model;

public class Burrito extends FoodItem implements Cookable {
    private static final int batchPrepTime = 9;
    private static final int batchSize = 2;

    public Burrito(double unitPrice, int quantity) {
        super(unitPrice, quantity);
    }

    @Override
    public int getPreparationTime(Restaurant restaurant) {
        return batchPrepTime * ((int) Math.ceil(this.getQuantity() / ((double) batchSize)));
    }

    @Override
    public int getActualQuantityCooked(Restaurant restaurant) {
        return this.getQuantity();
    }

    public void addQuantity(int additionalQuantity) {
        this.quantity += additionalQuantity;
    }


}
