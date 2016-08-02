package com.epam.ali.javaee7.model;

import junit.framework.TestCase;

import javax.persistence.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Set;

public class CustomerTest extends TestCase {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookStoreTest");
    private static ValidatorFactory factory;
    private static Validator validator;
    private EntityTransaction tx;
    private EntityManager em;

    public void setUp() throws Exception {
        em = emf.createEntityManager();
        tx = em.getTransaction();
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public void tearDown() throws Exception {
        factory.close();
        if (em != null) em.close();
    }

    public void testShouldRaiseNoConstraintViolation() throws Exception {
        Customer customer = new Customer("Alexandr", "Li", "muzikant1990@gmail.com", "87051794745", null, null);
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertEquals(0, violations.size());
    }

    public void testShouldRaiseConstraintViolationCauseInvalidEmail() throws Exception {
        Customer customer = new Customer("Alexandr", "Li", "dummyEmail", "87051794745", null, null);
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertEquals(1, violations.size());
        assertEquals("dummyEmail", violations.iterator().next().getInvalidValue());
        assertEquals("{annotation.email.message}", violations.iterator().next().getMessageTemplate());
    }

    public void testShouldCheckCustomerInCacheThenRemoveIt() {
        Address address = new Address("Alihanova", "", "Karaganda", "", "100008", "Kazakhstan");
        Customer customer = new Customer("Alex", "Li", "muzikant1990@gmail.com", "+77051794745", new Date(642729600000L), address);
        tx.begin();
        em.persist(customer);
        tx.commit();
        Cache cache = emf.getCache();
        assertTrue(cache.contains(Customer.class, customer.getId()));
        cache.evict(Customer.class,customer.getId());
        assertFalse(cache.contains(Customer.class, customer.getId()));
    }
}