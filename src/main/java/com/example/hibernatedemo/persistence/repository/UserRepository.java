package com.example.hibernatedemo.persistence.repository;

import com.example.hibernatedemo.persistence.entity.Address;
import com.example.hibernatedemo.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // List<User> findAll(); - method from extended JpaRepository

    User findByFirstNameAndLastName(String firstName, String lastName);

//    User findByAddress(Address address);

//    User findByAddressCityAndAddressStreet(String city, String street);

    @Query("SELECT u FROM User u JOIN u.addresses a WHERE a = :address")
    User findByAddress(@Param("address") Address address);

    // JPQL and HQL
    @Query("SELECT u FROM User u JOIN u.addresses a WHERE a.city = :city AND a.street = :street")
    User findByAddressCityAndAddressStreet(@Param("city") String city, @Param("street") String street);

    // another syntax
//    @Query("SELECT u FROM User u JOIN FETCH u.addresses a WHERE a.city = ?1 AND a.street = ?2")
//    User findByAddressCityAndAddressStreet(String city, String street);

    // Native SQL
//    @Query(value = "SELECT ...", nativeQuery = true)
//    User findByAddressCityAndAddressStreet(@Param("city") String city, @Param("street") String street);

}
