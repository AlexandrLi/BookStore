package com.epam.ali.javaee7.service;

import com.epam.ali.javaee7.annotation.Loggable;
import com.epam.ali.javaee7.model.Customer;

import javax.persistence.*;
import java.util.List;

@Loggable
public class CustomerService {

    public Customer persistCustomer(Customer customer) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bookStore");
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(customer);
        tx.commit();
        em.close();
        entityManagerFactory.close();
        return customer;
    }

    public List<Customer> getAllCustomers() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bookStore");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Customer> query = entityManager.createNamedQuery(Customer.FIND_ALL, Customer.class);
        List<Customer> customers = query.getResultList();
        entityManager.close();
        entityManagerFactory.close();
        return customers;
    }

    public List<Customer> getCustomersByName(String firstName) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bookStore");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Customer> query = entityManager.createNamedQuery(Customer.FIND_ALL_BY_NAME, Customer.class)
                .setParameter("firstName", firstName);
        List<Customer> customers = query.getResultList();
        entityManager.close();
        entityManagerFactory.close();
        return customers;
    }
}
