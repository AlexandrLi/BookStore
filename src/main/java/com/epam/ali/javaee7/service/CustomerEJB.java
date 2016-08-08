package com.epam.ali.javaee7.service;

import com.epam.ali.javaee7.annotation.Loggable;
import com.epam.ali.javaee7.model.Customer;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@Loggable
@Stateless
public class CustomerEJB {

    @PersistenceContext(unitName = "bookStore")
    private EntityManager em;

    public Customer persistCustomer(Customer customer) {
        em.persist(customer);
        return customer;
    }

    public List<Customer> getAllCustomers() {
        TypedQuery<Customer> query = em.createNamedQuery(Customer.FIND_ALL, Customer.class);
        return query.getResultList();
    }

    public List<Customer> getCustomersByName(String firstName) {
        TypedQuery<Customer> query = em.createNamedQuery(Customer.FIND_ALL_BY_NAME, Customer.class)
                .setParameter("firstName", firstName);
        return query.getResultList();
    }
}
