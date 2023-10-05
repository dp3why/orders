package com.example.onlineorder.service;

import com.example.onlineorder.dao.CartDao;
import com.example.onlineorder.entity.Cart;
import com.example.onlineorder.entity.Customer;
import com.example.onlineorder.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CartDao cartDao;


    // GET:  Cart summary of order items
    public Cart getCart() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        Customer customer = customerService.getCustomer(username);

        if (customer != null) {
            Cart cart = customer.getCart();
            double totalPrice = 0;
            for (OrderItem item : cart.getOrderItemList()) {
                totalPrice += item.getPrice() * item.getQuantity();
            }
            cart.setTotalPrice(totalPrice);
            return cart;
        }
        return new Cart();
    }

    // check out clean the cart
    public void cleanCart() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        Customer customer = customerService.getCustomer(username);
        if (customer  != null) cartDao.removeAllCartItems(customer.getCart());
    }

    // get all carts
    public List<Cart> getAllCarts() {
        List<Cart> result = cartDao.getAllCarts();;
        if (result != null) {
            return result;

        }
        return new ArrayList<>();
    }


}

