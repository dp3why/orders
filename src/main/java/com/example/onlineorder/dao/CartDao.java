package com.example.onlineorder.dao;

import com.example.onlineorder.entity.Cart;
import com.example.onlineorder.entity.OrderItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void removeCartItem(int orderItemId) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            OrderItem cartItem = session.get(OrderItem.class, orderItemId);
            Cart cart = cartItem.getCart();
            cart.getOrderItemList().remove(cartItem);

            session.beginTransaction();
            session.delete(cartItem);
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

    // find cart by cartId
    public Cart getCartById(int cartId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Cart.class, cartId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // find all carts
    public List<Cart> getAllCarts() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Cart", Cart.class).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    // clean cart: remove cart item List
    public void removeAllCartItems(Cart cart) {
        for (OrderItem item : cart.getOrderItemList()) {
            removeCartItem(item.getId());
        }
    }
}
