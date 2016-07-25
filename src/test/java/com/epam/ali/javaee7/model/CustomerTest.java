package com.epam.ali.javaee7.model;

import junit.framework.TestCase;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class CustomerTest extends TestCase {
    private static ValidatorFactory factory;
    private static Validator validator;

    public void setUp() throws Exception {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public void tearDown() throws Exception {
        factory.close();
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
}