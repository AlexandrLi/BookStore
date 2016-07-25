package com.epam.ali.javaee7.model;

import junit.framework.TestCase;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class AddressTest extends TestCase {

    public void testShouldRaiseConstraintViolationCauseInvalidZipCode() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Address address = new Address("Alihanova str.", "", "Karaganda", "", "dummyZipCode", "KZ");
        Set<ConstraintViolation<Address>> violations = validator.validate(address);
        assertEquals(1, violations.size());
        validatorFactory.close();
    }
}