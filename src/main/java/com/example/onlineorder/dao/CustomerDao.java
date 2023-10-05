package com.example.onlineorder.dao;

import com.example.onlineorder.entity.Authorities;
import com.example.onlineorder.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository

public class CustomerDao {

    @Autowired
    private SessionFactory sessionFactory;
    public void signUp(Customer customer) {
        Authorities authorities = new Authorities();
        authorities.setEmail(customer.getEmail());
        authorities.setAuthorities("ROLE_USER");

        Session session = null;
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(authorities);
            session.save(customer);
            session.getTransaction().commit();;
        } catch (Exception ex){
            ex.printStackTrace();
            if( session != null ) session.getTransaction().rollback();
        } finally {
            if(session != null ){
                session.close();
            }
        }

    }

    public Customer getCustomer(String email) {
        Customer customer = null;
        try (Session session = sessionFactory.openSession()) {
            customer = session.get(Customer.class, email);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return customer;
    }

    // find all customers
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            customers = session.createQuery("FROM Customer", Customer.class).list();
        }
        return customers;
    }


    // delete customer
    public void deleteCustomer(Customer customer) {
        Session session = null;
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(customer);
            Authorities authorities = session.get(Authorities.class, customer.getEmail());
            session.delete(authorities);
            session.getTransaction().commit();
        } catch (Exception ex){
            ex.printStackTrace();
            if( session != null ) session.getTransaction().rollback();
        } finally {
            if(session != null ){
                session.close();
            }
        }
    }
}
