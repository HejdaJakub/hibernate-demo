package com.example.hibernatedemo;

import com.example.hibernatedemo.controller.AddressController;
import com.example.hibernatedemo.controller.UserController;
import com.example.hibernatedemo.persistence.entity.User;
import com.example.hibernatedemo.persistence.entity.Address;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.sound.midi.Soundbank;
import java.util.List;

@SpringBootApplication
public class HibernateDemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(HibernateDemoApplication.class, args);
		System.out.println("Here the application started");

		UserController userController = context.getBean(UserController.class);
		AddressController addressController = context.getBean(AddressController.class);

		User user = new User("John", "Doe", 25);
		User user2 = new User("Carl", "Doe", 30);
		User user3 = new User("Matt", "Johnson", 40);

		userController.createUser(user);
		userController.createUser(user2);
		userController.createUser(user3);

		List<User> users = userController.getAllUsers();
		System.out.println(users);

		System.out.println(userController.getUserByFirstNameAndLastName("Carl", "Doe"));
		System.out.println(userController.getUserByFirstNameAndLastName("Carl", "Dooooe"));

		// Try second entity Address and its relations
		User user4 = new User("Jack", "Smith", 40);
        Address address = new Address("Praha", "Dlouhá", "2955");
		user4.setAddress(address);

		userController.createUser(user4);

		User user4FromDB = userController.getUserByFirstNameAndLastName("Jack", "Smith");

		System.out.println("*** User with address ***");
		System.out.println(user4FromDB);
		System.out.println("*** All addresses ***");
		System.out.println(addressController.getAllAddresses());

		System.out.println(userController.findByAddressCityAndAddressStreet("Praha", "Dlouhá"));

	}

}
