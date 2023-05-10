package com.example.hibernatedemo.controller;

import com.example.hibernatedemo.persistence.entity.Address;
import com.example.hibernatedemo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/getAllAddresses")
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }

}
