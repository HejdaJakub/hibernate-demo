package com.example.hibernatedemo.persistence.repository;

import com.example.hibernatedemo.persistence.entity.Address;
import com.example.hibernatedemo.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // List<User> findAll(); - method from extended JpaRepository

    User findByFirstNameAndLastName(String firstName, String lastName);

    User findByAddress(Address address);

    User findByAddressCityAndAddressStreet(String city, String street);
}
