package com.burrito.restaurant.old;

import java.util.HashMap;
import java.util.Scanner;

/**
 * The class for menu navigation.
 */
public class Menu {
	private RestaurantOld restaurant;
	private Order activeOrder;
	public Menu(RestaurantOld restaurant) {
		this.restaurant = restaurant;
		this.activeOrder = new Order();
	}
	
	/**
	 * The method for starting the menu.
	 */
    public void show() {
    	boolean exit = false;
    	do {
    		printMainMenu(this.restaurant.getName());
			
			String stringInput = readUserInput();
			
			// Check the user input and continue with the next iteration
			// if no input is provided
			if (stringInput.isEmpty()) {
				System.out.println("Please select a valid menu option.");
				continue;
			}

			char input = stringInput.charAt(0);
			
			switch (input) {
				case 'a':
					this.order();
					break;
				case 'b':
					this.showSalesReport();
					break;
				case 'c':
					this.updatePrice();
					break;
				case 'd':
					exit = true;
					System.out.println("Bye bye!");
					break;
				default:
					System.out.println("Please select a valid menu option.");
					break;
			}
		} while (!exit);
    }
    
	/**
     * The utility method to print menu options.
     */
	public static void printMainMenu(String name){
		String banner = new String(new char[50]).replace('\u0000', '=');
		System.out.println(banner + "\n" + "Welcome to " + name + "\n" + banner);
		System.out.printf("   %s%n", "a) Order");
		System.out.printf("   %s%n", "b) Show Sales Report");
		System.out.printf("   %s%n", "c) Update prices");
		System.out.printf("   %s%n", "d) Exit");
		System.out.print("Please select: ");
	}
	
	/**
	 * The utility method for showing the food menu
	 */
	public static void showFoodMenu(String additionalOptions) {
    	System.out.println("> Select the food item");
		System.out.printf("   %s%n", "1. Burrito");
		System.out.printf("   %s%n", "2. Fries");
		System.out.printf("   %s%n", "3. Soda");
		System.out.print(additionalOptions);
		System.out.print("Please select: ");
    }
    
	/**
	 * The utility method for reading a user option given a valid range of [minValid, maxValid]
	 */
    public static int readUserOption(int minValid, int maxValid) {   		
		int option = 0;
		while (true) {
			option = Integer.parseInt(readUserInput());    	
			if (option < minValid || option > maxValid) {
				System.out.println("Please enter a valid option.");
			}else {
				return option;
			}
		}
    }
	
	/**
     * The utility method to read user input.
     */
    public static String readUserInput() {
	    Scanner sc = new Scanner(System.in);
	    String line = sc.nextLine();
//	    sc.close();
	    return line;
	}
    
    /**
     * The method to place orders.
     */
    public void order() {
    	boolean finishedOrdering = false;
    	while(!finishedOrdering) {
    		showFoodMenu(String.format("   %s%n   %s%n", "4. Meal", "5. No more"));
    		int option = readUserOption(1, 5);
    		if (option == 1) {
    			System.out.print("How many burritos would you like to buy: ");
    			int quantity = Integer.parseInt(readUserInput());
    			this.activeOrder.addFoodItem(new Burrito(RestaurantOld.getPrice("Burrito"), quantity));
    		}else if (option == 2){
    			System.out.print("How many serves of fries would you like to buy: ");
    			int quantity = Integer.parseInt(readUserInput());
    			this.activeOrder.addFoodItem(new Fries(RestaurantOld.getPrice("Fries"), quantity));
    		}else if (option == 3) {
    			System.out.print("How many soda would you like to buy: ");
    			int quantity = Integer.parseInt(readUserInput());
    			this.activeOrder.addFoodItem(new Soda(RestaurantOld.getPrice("Soda"), quantity));
    		}else if (option == 4) {
    			System.out.print("How many meals would you like to buy: ");
    			int quantity = Integer.parseInt(readUserInput());
    			double price = RestaurantOld.getPrice("Burrito") + RestaurantOld.getPrice("Fries")
    			+ RestaurantOld.getPrice("Soda") - RestaurantOld.getDiscount();
    			this.activeOrder.addFoodItem(new Meal(price, quantity));
    		}else if (option == 5) {
    			finishedOrdering = true;
    		}   	
    	}
    	this.processPayment();
    	System.out.println("Time taken: " + this.activeOrder.getPrepTime(this.restaurant) + " minutes");
    	if(this.restaurant.updateRemainingServes(this.activeOrder)) {
    		System.out.println(this.restaurant.getRemainedFries() + " serves of fries will be left for next order.");
    	}
    	this.restaurant.addOrderToHistory(this.activeOrder);
    	this.activeOrder = new Order();
    }
    
    /*
     * The method to process payment
     */
    public void processPayment() {
    	double price = this.activeOrder.getTotalPrice();
    	StringBuilder sb = new StringBuilder("Total for ");
    	for (int i=0; i < this.activeOrder.getItems().size(); i++) {
    		FoodItem item = this.activeOrder.getItems().get(i);
    		sb.append(item.getQuantity() + " ");
    		String foodClass = item.getClass().getName();
    		if (foodClass.equals("Burrito")){
    			sb.append("burrito(s) ");
    		}else if (foodClass.equals("Fries")){
    			sb.append("fries ");
    		}else if (foodClass.equals("Soda")) {
    			sb.append("soda ");
    		}else if (foodClass.equals("Meal")) {
    			sb.append("meal(s) ");
    		}
    		if (i < this.activeOrder.getItems().size()-1) {
    			sb.append("and ");
    		}
    	}
    	sb.append("is $" + price);
    	System.out.println(sb.toString());
    	
    	boolean paid = false;
    	
    	while(!paid) {
    		System.out.print("Please enter money: ");
    		double amount = Double.parseDouble(readUserInput());
    		if(amount < price) {
    			System.out.println("Sorry, that’s not enough to pay for order");
    		}else {
    			double change = amount - price;
    			if (change > 0.0) {
    				System.out.println("Change returned $" + change);
    			}
    			paid = true;
    		}   		
    	}
    }
    
    /*
     * The method for showing sales report
     */
    public void showSalesReport() {
    	HashMap<String, Integer> quantities = new HashMap<String, Integer>();
    	HashMap<String, Double> prices = new HashMap<String, Double>();
    	quantities.put("Burrito", 0);
    	quantities.put("Fries", 0);
    	quantities.put("Soda", 0);
    	quantities.put("Meal", 0);
    	prices.put("Burrito", 0.0);
    	prices.put("Fries", 0.0);
    	prices.put("Soda", 0.0);
    	prices.put("Meal", 0.0);
    	for(Order order: this.restaurant.getAllOrders()) {
    		for(FoodItem item: order.getItems()) {
    			String key = item.getClass().getName();
    			int oldQ = quantities.get(key);
    			double oldP = prices.get(key);
    			quantities.put(key, oldQ + item.getQuantity());
    			prices.put(key, oldP + item.getTotalPrice());
    		}
    	}
    	
    	System.out.println("Unsold Serves of Fries: " + this.restaurant.getRemainedFries() +"\n\nTotal Sales:");
    	System.out.printf("%-9s%-9d%9s%n", "Burritos", quantities.get("Burrito"), String.format("$%.2f", prices.get("Burrito")));
    	System.out.printf("%-9s%-9d%9s%n", "Fries", quantities.get("Fries"), String.format("$%.2f", prices.get("Fries")));
    	System.out.printf("%-9s%-9d%9s%n", "Soda", quantities.get("Soda"), String.format("$%.2f", prices.get("Soda")));
    	System.out.printf("%-9s%-9d%9s%n", "Meal", quantities.get("Meal"), String.format("$%.2f", prices.get("Meal")));
    	System.out.println(new String(new char[27]).replace('\u0000', '-'));
    	int totalQ = quantities.get("Burrito") + quantities.get("Fries") + quantities.get("Soda") + quantities.get("Meal");
    	double totalP = prices.get("Burrito") + prices.get("Fries") + prices.get("Soda") + prices.get("Meal");
    	System.out.printf("%-9s%-9d%9s%n", "", totalQ, String.format("$%.2f", totalP));
    	System.out.println(new String(new char[27]).replace('\u0000', '-'));
    }
    
    /*
     * The method for updating food price
     */
    public void updatePrice() {
    	System.out.println(">select the food item to update the price");
    	showFoodMenu(String.format("   %s%n", "4. Exit"));
    	int option = readUserOption(1, 4);
    	System.out.print("Please enter new price:");
    	int newPrice = Integer.parseInt(readUserInput());
    	switch(option) {
    	case 1:
    		RestaurantOld.updatePrice("Burrito", newPrice);
    		System.out.println("The unit price of burrito is updated to $" + newPrice + ".");
    		break;
    	case 2:
    		RestaurantOld.updatePrice("Fries", newPrice);
    		System.out.println("The unit price of fries is updated to $" + newPrice + ".");
    		break;
    	case 3:
    		RestaurantOld.updatePrice("Soda", newPrice);
    		System.out.println("The unit price of soda is updated to $" + newPrice + ".");
    		break;
    	case 4:
    		break;
    	}
    }
    
    

}
