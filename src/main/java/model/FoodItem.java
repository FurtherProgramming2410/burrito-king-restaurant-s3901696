package model;

public class FoodItem {
    private double price;
    private int quantity;

    public FoodItem(double price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getTotalPrice() {
        return price * quantity;
    }

    public void addQuantity(int count) {
        this.quantity += count;
    }
}