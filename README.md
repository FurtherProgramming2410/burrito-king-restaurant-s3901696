
# README.md

This is a javafx applican for resturant order management 

## Deployment

To deploy this project run

```bash
  git@github.com:FurtherProgramming2410/burrito-king-restaurant-s3901696.git
  cd burrito-restaurant
  mvn clean javafx:run
  mvn clean javafx:run

```


## Module Dependencies

javafx.controls: Required for JavaFX user interface controls.
javafx.fxml: Required for JavaFX FXML files.
java.sql: Required for database operations.
org.slf4j: Required for logging.


## Design patterns

## Model-View-Controller (MVC) Pattern
Description: The MVC pattern separates an application into three main components: Model, View, and Controller. This separation enhances maintainability and scalability.
Usage:
Model: Represents the data and business logic of the application. In your project, classes like User, FoodItem, and Order serve as models.
View: Represents the user interface. FXML files and associated controller classes handle the presentation logic.
Controller: Acts as an intermediary between the Model and View. Controller classes like ProfileController, BasketController, and OrderController handle user input and update the Model accordingly.

##
