package com.example.onlineorder.service;

import com.example.onlineorder.dao.CustomerDao;
import com.example.onlineorder.entity.Cart;
import com.example.onlineorder.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public void signUp(Customer customer) {
        Cart cart = new Cart();
        cart.setTotalPrice(0.0);
        customer.setCart(cart);

        customer.setEnabled(true);
        customerDao.signUp(customer);

    }

    public Customer getCustomer(String email) {
        return customerDao.getCustomer(email);
    }

    // find all customers
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    // delete customer
    public void deleteCustomer(Customer customer) {
        customerDao.deleteCustomer(customer);
    }
}

