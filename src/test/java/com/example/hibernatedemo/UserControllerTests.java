package com.example.hibernatedemo;

import com.example.hibernatedemo.controller.UserController;
import com.example.hibernatedemo.persistence.entity.User;
import com.example.hibernatedemo.persistence.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class UserControllerTests {

    @Autowired
    private UserController userController;
    
    @Autowired
    private UserRepository userRepository;

    User user1;
    User user2;
    User user3;

    @BeforeEach
    public void init() {
        // Create and save data before each test
        user1 = new User("John", "Doe", 25);
        user2 = new User("Carl", "Doe", 30);
        user3 = new User("Matt", "Johnson", 40);

        userController.createUser(user1);
        userController.createUser(user2);
        userController.createUser(user3);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }


    @Test
    public void testCreateUser() {
        User user = new User("James", "Smith", 45);
        User createdUser = userController.createUser(user);
        assertNotNull(createdUser.getId());
        assertEquals(user.getFirstName(), createdUser.getFirstName());
        assertEquals(user.getLastName(), createdUser.getLastName());
        assertEquals(user.getAge(), createdUser.getAge());
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = userController.getAllUsers();
        System.out.println(users);
        assertNotNull(users);
        assertEquals(users.size(), 3);
    }

    @Test
    public void testGetUserByFirstNameAndLastName() {
        User foundUser = userController.getUserByFirstNameAndLastName(user1.getFirstName(), user1.getLastName());
        assertNotNull(foundUser);
        assertEquals(user1.getFirstName(), foundUser.getFirstName());
        assertEquals(user1.getLastName(), foundUser.getLastName());
        assertEquals(user1.getAge(), foundUser.getAge());
    }

    @Test
    public void testGetUserByFirstNameAndLastNameNotFound() {
        User foundUser = userController.getUserByFirstNameAndLastName("John", "Dooooe");
        assertNull(foundUser);
    }
}
