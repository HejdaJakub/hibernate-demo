package com.example.hibernatedemo.service;

import com.example.hibernatedemo.persistence.entity.Address;
import com.example.hibernatedemo.persistence.entity.User;
import com.example.hibernatedemo.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User findByFirstNameAndLastName(String firstName, String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public User findByAddressCityAndAddressStreet(String city, String street) {
        return userRepository.findByAddressCityAndAddressStreet(city, street);
    }

}
