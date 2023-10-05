package com.example.onlineorder.controller;

import com.example.onlineorder.dto.OrderDTO;
import com.example.onlineorder.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ItemOrderController {

    @Autowired
    private OrderItemService orderItemService;


    // POST: add order item to cart
    @RequestMapping(value = "/order/create", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addMenuItemToCart(@RequestBody OrderDTO request) {
        int menuId = request.getMenuId();
        String userName = request.getUserName();
        orderItemService.saveOrderItem(menuId, userName);
    }

    // POST: remove order item from cart
    @RequestMapping(value = "/order/delete/{menuId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeMenuItemFromCart(@PathVariable("menuId") int menuId) {
        orderItemService.removeOrderItem(menuId);
    }


}
