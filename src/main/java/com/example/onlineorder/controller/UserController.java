package com.example.onlineorder.controller;

import com.example.onlineorder.entity.Customer;
import com.example.onlineorder.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private CustomerService customerService;

    @DeleteMapping("/delete/{email}")
    public void deleteUser(@PathVariable("email") String email) {
        Customer customer = customerService.getCustomer(email);
        customerService.deleteCustomer(customer);
    }

    @GetMapping("")
    public List<Customer> getCustomers() {
        return customerService.findAll();
    }
    @GetMapping("/uid/{uid}")
    public Customer getCustomerByUid(@PathVariable("uid") String uid) {
        return customerService.getCustomerByUid(uid);
    }




}
