package com.example.onlineorder.service;

import com.example.onlineorder.dao.OrderItemDao;
import com.example.onlineorder.entity.Customer;
import com.example.onlineorder.entity.MenuItem;
import com.example.onlineorder.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    @Autowired
    private MenuInfoService menuInfoService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderItemDao orderItemDao;

    // POST: add order item to cart
    public void saveOrderItem(int menuId, String userName) {
        final OrderItem orderItem = new OrderItem();
        final MenuItem menuItem = menuInfoService.getMenuItem(menuId);

//        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
//        String username = loggedInUser.getName();
        Customer customer = customerService.getCustomer(userName);

        orderItem.setMenuItem(menuItem);
        orderItem.setCart(customer.getCart());
        orderItem.setQuantity(1);
        orderItem.setPrice(menuItem.getPrice());
        orderItemDao.save(orderItem);
    }
    // POST: remove order item from cart
    public void removeOrderItem(int menuId) {
        final OrderItem orderItem = orderItemDao.findByMenuItemId(menuId);
        orderItemDao.delete(orderItem);
    }


    // GET: get order item by menu item id
    public OrderItem getOrderItem(int menuId) {
        return orderItemDao.findByMenuItemId(menuId);
    }

}

