package com.burrito.restaurant.old;

/**
 * The Main class is the entry point of the sample console program.
 */
public class Main {
	
	public static void main(String args[]){
		Restaurant restaurant = new Restaurant("Burrito Restaurant");
		Menu menu = new Menu(restaurant);
		menu.show();
		
	}

}
