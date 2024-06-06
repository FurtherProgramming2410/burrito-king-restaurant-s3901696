package com.burrito.restaurant.test;

import com.burrito.restaurant.dao.*;
import com.burrito.restaurant.implementtion.FoodItemDaoImpl;
import com.burrito.restaurant.implementtion.OrderDaoImpl;
import com.burrito.restaurant.implementtion.RestaurantDaoImpl;
import com.burrito.restaurant.implementtion.UserDaoImpl;
import com.burrito.restaurant.model.*;

import java.sql.SQLException;

public class RestaurantDAOTest {

    public static void main(String[] args) {
        try {
            // Initialize DAOs
            UserDao userDao = new UserDaoImpl();
            OrderDao orderDao = new OrderDaoImpl();
            RestaurantDao restaurantDao = new RestaurantDaoImpl();
            FoodItemDao foodItemDao = new FoodItemDaoImpl();

            // Setup database

            userDao.setup();
            orderDao.setup();
            restaurantDao.setup();
            foodItemDao.setup();

            // Test User DAO
         //   testUserDao(userDao);

            // Test FoodItem DAO
            testFoodItemDao(foodItemDao);

            // Test Restaurant DAO
             testRestaurantDao(restaurantDao);

            // Test Order DAO
            testOrderDao(orderDao);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void testOrderDao(OrderDao orderDao) throws SQLException {

        Order order = new Order();
        order.addFoodItem(new Burrito(7.99, 2));
        order.addFoodItem(new Fries(4.00, 1));
        orderDao.addOrder(order);
        System.out.println("Order added: " + order);

        // Test order retrieval
        Order retrievedOrder = orderDao.getOrder(order.getOrderId());
        System.out.println("Retrieved order: " + retrievedOrder);

        // Test order deletion
        orderDao.deleteOrder(order.getOrderId());
        System.out.println("Order deleted");

        // Make sure order is deleted
        Order deletedOrder = orderDao.getOrder(order.getOrderId());
        if (deletedOrder == null) {
            System.out.println("Order deleted successfully");
        } else {
            System.out.println("Order deletion failed");
        }
    }

    private static void testFoodItemDao(FoodItemDao foodItemDao) throws SQLException {

        // Test adding a food item
        FoodItem foodItem = new FoodItem(5.99, 10);
        foodItemDao.addFoodItem(foodItem);
        System.out.println("Food item added: " + foodItem);

        // Test retrieving a food item
        FoodItem retrievedFoodItem = foodItemDao.getFoodItem(foodItem.getItemId());
        System.out.println("Retrieved food item: " + retrievedFoodItem);

        // Test updating a food item
        retrievedFoodItem.setUnitPrice(6.99);
        foodItemDao.updateFoodItem(retrievedFoodItem.getItemId(), retrievedFoodItem);
        System.out.println("Food item updated: " + retrievedFoodItem);

        // Test deleting a food item
        foodItemDao.deleteFoodItem(retrievedFoodItem.getItemId());
        System.out.println("Food item deleted");

        // Make sure food item is deleted
        FoodItem deletedFoodItem = foodItemDao.getFoodItem(retrievedFoodItem.getItemId());
        if (deletedFoodItem == null) {
            System.out.println("Food item deleted successfully");
        } else {
            System.out.println("Food item deletion failed");
        }
    }

    private static void testRestaurantDao(RestaurantDao restaurantDao) throws SQLException {
        // Test restaurant creation
        Restaurant restaurant = new Restaurant("Burrito Palace");
        restaurantDao.addRestaurant(restaurant);
        System.out.println("Restaurant added: " + restaurant);

        // Test restaurant retrieval
        Restaurant retrievedRestaurant = restaurantDao.getRestaurant("Burrito Palace");
        System.out.println("Retrieved restaurant: " + retrievedRestaurant);

        // Test restaurant deletion
        restaurantDao.deleteRestaurant("Burrito Palace");
        System.out.println("Restaurant deleted");

        // Make sure restaurant is deleted
        Restaurant deletedRestaurant = restaurantDao.getRestaurant("Burrito Palace");
        if (deletedRestaurant == null) {
            System.out.println("Restaurant deleted successfully");
        } else {
            System.out.println("Restaurant deletion failed");
        }
    }
    private static void testUserDao(UserDao userDao) {
    }
}
