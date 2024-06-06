package com.burrito.restaurant.old;

public class FoodItem {
	private double unitPrice;
	private int quantity;
	public FoodItem(double price, int quantity) {
		this.unitPrice = price;
		this.quantity = quantity;
	}
	
	public double getUnitPrice() {
		return this.unitPrice;
	}
	public void setQuantity(int count) {
		this.quantity = count;
	}
	public void addQuantity(int count) {
		this.quantity += count;
	}
	public int getQuantity() {
		return this.quantity;
	}
	public double getTotalPrice() {
		return this.unitPrice * this.quantity;
	}

}
