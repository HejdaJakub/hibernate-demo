package com.example.hibernatedemo.persistence.repository;

import com.example.hibernatedemo.persistence.entity.Address;
import com.example.hibernatedemo.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByUser(User user);
}
