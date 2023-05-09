package com.example.hibernatedemo;

import com.example.hibernatedemo.controller.UserController;
import com.example.hibernatedemo.persistence.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class HibernateDemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(HibernateDemoApplication.class, args);
		System.out.println("Here the application started");

		UserController controller = context.getBean(UserController.class);

		User user = new User("John", "Doe", 25);
		User user2 = new User("Carl", "Doe", 30);
		User user3 = new User("Matt", "Johanson", 40);

		controller.createUser(user);
		controller.createUser(user2);
		controller.createUser(user3);

		List<User> users = controller.getAllUsers();
		System.out.println(users);

		System.out.println(controller.getUserByFirstNameAndLastName("Carl", "Doe"));
		System.out.println(controller.getUserByFirstNameAndLastName("Carl", "Dooooe"));
	}

}
