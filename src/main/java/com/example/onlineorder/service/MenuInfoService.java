package com.example.onlineorder.service;

import com.example.onlineorder.dao.MenuInfoDao;
import com.example.onlineorder.entity.MenuItem;
import com.example.onlineorder.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuInfoService {

    @Autowired
    private MenuInfoDao menuInfoDao;

    public Restaurant getRestaurantById(int restaurantId) {
        return menuInfoDao.getRestaurantById(restaurantId);
    }
    public List<Restaurant> getRestaurants() {
        return menuInfoDao.getRestaurants();
    }

    public ResponseEntity<Restaurant> createRestaurant(Restaurant restaurant) {
        Restaurant result = menuInfoDao.createRestaurant(restaurant);
        if (result != null) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().build();
    }

    // delete a restaurant
    public void deleteRestaurant(int restaurantId) {
        menuInfoDao.deleteRestaurantById(restaurantId);
    }



    // ================= Menu ==================

    public List<MenuItem> getAllMenuItem(int restaurantId) {
        return menuInfoDao.getAllMenuItem(restaurantId);
    }

    public MenuItem getMenuItem(int id) {
        return menuInfoDao.getMenuItemById(id);
    }

    // POST: Create a new menu item
    public MenuItem addMenuItem(int restaurantId, MenuItem menuItem) {
         return menuInfoDao.addMenuItem(restaurantId, menuItem);
    }

    // DELETE: Delete a menu item
    public void deleteMenuItem(int menuId) {
        MenuItem menuItem = menuInfoDao.getMenuItemById(menuId);
        if (menuItem == null) {
            return;
        }
        menuInfoDao.deleteMenuItem(menuItem);
    }

    // GET: get all menu items
    public List<MenuItem> getAllMenus() {
        return menuInfoDao.getAllMenus();
    }



}
