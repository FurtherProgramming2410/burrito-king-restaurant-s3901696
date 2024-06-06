package com.burrito.restaurant.model;

public class Burrito extends FoodItem implements Cookable {

    private static final int batchPrepTime = 9;
    private static final int batchSize = 2;

    public Burrito(double unitPrice, int quantity) {
        super(unitPrice);
        this.setItemName(this.getClass().getSimpleName());
    }

    @Override
    public int getPreparationTime() {
//        return batchPrepTime * ((int) Math.ceil(this.getQuantity() / ((double) batchSize)));
        return 0;
    }

    @Override
    public int getActualQuantityCooked() {
//        return this.getQuantity();
        return 0;
    }

    public void addQuantity(int additionalQuantity) {
//        this.quantity += additionalQuantity;
        return;
    }


}
