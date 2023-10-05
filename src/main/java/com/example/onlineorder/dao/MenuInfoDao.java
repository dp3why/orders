package com.example.onlineorder.dao;

import com.example.onlineorder.entity.MenuItem;
import com.example.onlineorder.entity.Restaurant;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MenuInfoDao {

    @Autowired
    private SessionFactory sessionFactory;

    // GET: find all restaurants
    public List<Restaurant> getRestaurants() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
            criteria.from(Restaurant.class);
            return session.createQuery(criteria).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ArrayList<>();
    }

    // POST: create a  restaurant
    public Restaurant createRestaurant(Restaurant restaurant) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(restaurant);
            session.getTransaction().commit();
            return restaurant;
        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;

    }

    // delete a restaurant
    public void deleteRestaurantById(int id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Restaurant restaurant = session.get(Restaurant.class, id);
            session.delete(restaurant);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }



    // ================== Menu ====================

    // GET : find all menu items in a restaurant

    public List<MenuItem> getAllMenuItem(int restaurantId) {
        try (Session session = sessionFactory.openSession()) {
            Restaurant restaurant = session.get(Restaurant.class, restaurantId);
            if (restaurant != null) {
                return restaurant.getMenuItemList();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    // GET: find a menuItem by id
    public MenuItem getMenuItemById(int menuItemId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(MenuItem.class, menuItemId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // find a restaurant by id
    public Restaurant getRestaurantById(int restaurantId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Restaurant.class, restaurantId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // add  menuItem
    public MenuItem addMenuItem(int restaurantId, MenuItem menuItem) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Restaurant restaurant = session.get(Restaurant.class, restaurantId);
            menuItem.setRestaurant(restaurant);
            session.save(menuItem);
            session.getTransaction().commit();
            return menuItem;
        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }


    // delete a menuItem by menu item id
    public void deleteMenuItem(MenuItem menuItem) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();

            session.delete(menuItem);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    // update a menuItem

    public void updateMenuItem(MenuItem menuItem) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(menuItem);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    // find all menus
    public List<MenuItem> getAllMenus() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<MenuItem> criteria = builder.createQuery(MenuItem.class);
            criteria.from(MenuItem.class);
            return session.createQuery(criteria).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ArrayList<>();
    }


}
