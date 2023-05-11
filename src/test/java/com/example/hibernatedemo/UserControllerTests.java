package com.example.hibernatedemo;

import com.example.hibernatedemo.controller.UserController;
import com.example.hibernatedemo.persistence.entity.Address;
import com.example.hibernatedemo.persistence.entity.User;
import com.example.hibernatedemo.persistence.repository.AddressRepository;
import com.example.hibernatedemo.persistence.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
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

    @Autowired
    private AddressRepository addressRepository;

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

    @Test
    public void testFindUserByAddress() {
        User user4 = new User("Jack", "Smith", 40);
        Address address = new Address("Praha", "Dlouhá", "2955");
        Address address2 = new Address("Brno", "Dlouhá", "2955");
        Address address3 = new Address("Ostrava", "Dlouhá", "2955");

        // fix for @OneToMany
        // user4.setAddress(address);
        user4.setAddresses(Collections.singletonList(address));

        userController.createUser(user4);

        User user4FromDB = userController.getUserByFirstNameAndLastName("Jack", "Smith");

        System.out.println(user4FromDB);
        System.out.println(addressRepository.findAll());
        System.out.println("Find user by address");
        System.out.println(userRepository.findByAddress(address));

        System.out.println("Find address by user");
        System.out.println(addressRepository.findByUser(user4));
    }

    @Test
    public void testFindUserByCityAndStreet() {
        User user4 = new User("Jack", "Smith", 40);
        Address address = new Address("Praha", "Dlouhá", "2955");
        Address address2 = new Address("Brno", "Dlouhá", "2955");
        Address address3 = new Address("Ostrava", "Dlouhá", "2955");

        // fix for @OneToMany
        // user4.setAddress(address);
        user4.setAddresses(Collections.singletonList(address));

//        List<Address> addresses = new ArrayList<>();
//        addresses.add(address);
//        addresses.add(address2);
//        user4.setAddresses(addresses);

        userController.createUser(user4);

        User user4FromDB = userController.getUserByFirstNameAndLastName("Jack", "Smith");

        System.out.println(user4FromDB);
        System.out.println("*** User by city and street ***");
        System.out.println(userController.findByAddressCityAndAddressStreet("Praha", "Dlouhá"));
        System.out.println(userController.findByAddressCityAndAddressStreet("Brno", "Dlouhá"));
    }
}
