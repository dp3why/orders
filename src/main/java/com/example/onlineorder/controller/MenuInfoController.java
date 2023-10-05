package com.example.onlineorder.controller;

import com.example.onlineorder.entity.MenuItem;
import com.example.onlineorder.entity.Restaurant;
import com.example.onlineorder.service.MenuInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;
@Controller
public class MenuInfoController {

    @Autowired
    private MenuInfoService menuInfoService;

    // find a restaurant by its id
    @RequestMapping(value = "/restaurant/{restaurantId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable(value = "restaurantId") int restaurantId) {
        Restaurant restaurant = menuInfoService.getRestaurantById(restaurantId);
        if (restaurant != null) {
            return ResponseEntity.ok(restaurant);
        }
        return ResponseEntity.badRequest().build();
    }


    // find all restaurants
    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Restaurant>> getRestaurants() {
        List<Restaurant> res =  menuInfoService.getRestaurants();
        if (res != null) {
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.ok(new ArrayList<>());
    }
    // create a new restaurant
    @RequestMapping(value = "/restaurants", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin(origins = "*")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {

        return menuInfoService.createRestaurant(restaurant);
    }

    // delete a restaurant

    @RequestMapping(value = "/restaurant/{restaurantId}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteRestaurant(@PathVariable(value = "restaurantId") int restaurantId) {
        menuInfoService.deleteRestaurant(restaurantId);
    }

    // ============ Menu ==============
    // GET all menu items of a restaurant
    @RequestMapping(value = "/restaurant/{restaurantId}/menu", method = RequestMethod.GET)
    @ResponseBody
    public List<MenuItem> getMenus(@PathVariable(value = "restaurantId") int restaurantId) {
        return menuInfoService.getAllMenuItem(restaurantId);
    }

    // CREATE a new menu item
    @RequestMapping(value = "/restaurant/{restaurantId}/new", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<MenuItem> createMenuItem(@PathVariable(value = "restaurantId") int restaurantId,
                                                   @RequestBody MenuItem menuItem) {
        MenuItem newMenuItem = menuInfoService.addMenuItem(restaurantId, menuItem);
        if (newMenuItem != null) {
            return ResponseEntity.ok(newMenuItem);
        }
        return ResponseEntity.badRequest().build();

    }

    // DELETE a menu item
    @RequestMapping(value = "/menu/{menuItemId}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteMenuItem(@PathVariable(value = "menuItemId") int menuItemId) {
        menuInfoService.deleteMenuItem(menuItemId);

    }

    // GET all menus

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    @ResponseBody
    public List<MenuItem> getMenus() {
        return menuInfoService.getAllMenus();
    }




}


