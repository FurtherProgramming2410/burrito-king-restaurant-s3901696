
# Burrito Restaurant Order Management

This is a JavaFX application for restaurant order management.

## Deployment

To deploy this project, run:

```bash
cd burrito-restaurant
mvn clean javafx:run
```

## Module Dependencies

- **javafx.controls**: Required for JavaFX user interface controls.
- **javafx.fxml**: Required for JavaFX FXML files.
- **java.sql**: Required for database operations.
- **org.slf4j**: Required for logging.

## Design Patterns

### Model-View-Controller (MVC) Pattern

**Description**: The MVC pattern separates an application into three main components: Model, View, and Controller. This separation enhances maintainability and scalability.

**Usage**:
- **Model**: Represents the data and business logic of the application. In your project, classes like `User`, `FoodItem`, and `Order` serve as models.
- **View**: Represents the user interface. FXML files and associated controller classes handle the presentation logic.
- **Controller**: Acts as an intermediary between the Model and View. Controller classes like `ProfileController`, `BasketController`, and `PlaceOrderController` handle user input and update the Model accordingly.

### Data Access Object (DAO) Pattern

**Description**: The DAO pattern provides an abstract interface to some type of database or other persistence mechanism. This allows for the separation of low-level data accessing API or operations from high-level business services.

**Usage**:
- **UserDao**: Interface defining the operations for accessing user data.
- **UserDaoImpl**: Implementation of the `UserDao` interface, handling database operations for user data.
- **DbConnection**: Utility class to manage the database connection.

## JUnit Testing

JUnit is used to write and run repeatable automated tests to ensure the correctness of your code.

### Setting Up JUnit Tests

1. **Dependencies**:
   Ensure you have the necessary dependencies in your `pom.xml` file:

    ```xml
    <dependencies>
        <!-- Other dependencies -->

        <!-- JUnit 5 -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>5.8.1</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.8.1</version>
            <scope>test</scope>
        </dependency>

        <!-- SQLite JDBC Driver -->
        <dependency>
            <groupId>org.xerial</groupId>
            <artifactId>sqlite-jdbc</artifactId>
            <version>3.34.0</version>
        </dependency>
    </dependencies>
    ```

2. **Test Class Structure**:
    - Each DAO implementation class should have a corresponding test class.
    - Example: `UserDaoImplTest` for `UserDaoImpl`.

3. **Sample Test Class**:
   Below is a sample test class for `UserDaoImpl`:

    ```java
    package com.burrito.restaurant.db.implementation;

    import com.burrito.restaurant.db.DbConnection;
    import com.burrito.restaurant.model.User;
    import org.junit.jupiter.api.*;

    import java.sql.Connection;
    import java.sql.SQLException;
    import java.util.List;

    import static org.junit.jupiter.api.Assertions.*;

    class UserDaoImplTest {

        private static UserDaoImpl userDao;

        @BeforeAll
        static void setup() throws SQLException {
            // Set up in-memory SQLite database for testing
            DbConnection.setDatabaseUrl("jdbc:sqlite::memory:");
            userDao = new UserDaoImpl();
        }

        @AfterEach
        void cleanup() throws SQLException {
            try (Connection connection = DbConnection.getConnection();
                 var stmt = connection.createStatement()) {
                stmt.execute("DELETE FROM users");
            }
        }

        @Test
        void testCreateAndGetById() throws SQLException {
            User user = new User();
            user.setUsername("testuser");
            user.setPassword("password");
            user.setFirstName("Test");
            user.setLastName("User");
            user.setVip(false);
            user.setEmail("testuser@example.com");
            user.setPoints(10);

            User createdUser = userDao.create(user);

            assertNotNull(createdUser.getId());
            assertEquals("testuser", createdUser.getUsername());

            User retrievedUser = userDao.getById(createdUser.getId());
            assertNotNull(retrievedUser);
            assertEquals("testuser", retrievedUser.getUsername());
            assertEquals("password", retrievedUser.getPassword());
        }

        @Test
        void testGetByUsername() throws SQLException {
            User user = new User();
            user.setUsername("testuser");
            user.setPassword("password");
            user.setFirstName("Test");
            user.setLastName("User");
            user.setVip(false);
            user.setEmail("testuser@example.com");
            user.setPoints(10);

            userDao.create(user);

            User retrievedUser = userDao.getByUsername("testuser");
            assertNotNull(retrievedUser);
            assertEquals("testuser", retrievedUser.getUsername());
            assertEquals("password", retrievedUser.getPassword());
        }

        @Test
        void testLogin() throws SQLException {
            User user = new User();
            user.setUsername("testuser");
            user.setPassword("password");
            user.setFirstName("Test");
            user.setLastName("User");
            user.setVip(false);
            user.setEmail("testuser@example.com");
            user.setPoints(10);

            userDao.create(user);

            User loggedInUser = userDao.login("testuser", "password");
            assertNotNull(loggedInUser);
            assertEquals("testuser", loggedInUser.getUsername());
        }

        @Test
        void testUpdate() throws SQLException {
            User user = new User();
            user.setUsername("testuser");
            user.setPassword("password");
            user.setFirstName("Test");
            user.setLastName("User");
            user.setVip(false);
            user.setEmail("testuser@example.com");
            user.setPoints(10);

            User createdUser = userDao.create(user);
            createdUser.setPassword("newpassword");
            createdUser.setEmail("newemail@example.com");

            User updatedUser = userDao.update(createdUser);
            assertNotNull(updatedUser);
            assertEquals("newpassword", updatedUser.getPassword());
            assertEquals("newemail@example.com", updatedUser.getEmail());
        }

        @Test
        void testDeleteById() throws SQLException {
            User user = new User();
            user.setUsername("testuser");
            user.setPassword("password");
            user.setFirstName("Test");
            user.setLastName("User");
            user.setVip(false);
            user.setEmail("testuser@example.com");
            user.setPoints(10);

            User createdUser = userDao.create(user);
            int userId = createdUser.getId();

            User deletedUser = userDao.deleteById(userId);
            assertNotNull(deletedUser);
            assertEquals("testuser", deletedUser.getUsername());

            User retrievedUser = userDao.getById(userId);
            assertNull(retrievedUser);
        }

        @Test
        void testGetAll() throws SQLException {
            User user1 = new User();
            user1.setUsername("testuser1");
            user1.setPassword("password1");
            user1.setFirstName("Test1");
            user1.setLastName("User1");
            user1.setVip(false);
            user1.setEmail("testuser1@example.com");
            user1.setPoints(10);

            User user2 = new User();
            user2.setUsername("testuser2");
            user2.setPassword("password2");
            user2.setFirstName("Test2");
            user2.setLastName("User2");
            user2.setVip(false);
            user2.setEmail("testuser2@example.com");
            user2.setPoints(20);

            userDao.create(user1);
            userDao.create(user2);

            List<User> users = userDao.getAll();
            assertNotNull(users);
            assertEquals(2, users.size());
        }
    }
    ```

### Running Tests

To run the tests, use the following command:

```bash
mvn test
```

This command will compile the test classes and run them, displaying the results in the console.

## Summary

This project demonstrates a restaurant order management system using JavaFX, following the MVC and DAO design patterns. JUnit is used for testing to ensure the correctness of the application.
