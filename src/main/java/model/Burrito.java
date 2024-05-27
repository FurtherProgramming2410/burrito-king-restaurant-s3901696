package model;

public class Burrito extends FoodItem implements Cookable {
    public Burrito(double price, int quantity) {
        super(price, quantity);
    }

    @Override
    public int getPreparationTime(Restaurant restaurant) {
        return 10 * getQuantity();
    }

    @Override
    public int getActualQuantityCooked(Restaurant restaurant) {
        return getQuantity();
    }
}