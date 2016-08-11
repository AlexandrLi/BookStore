package com.epam.ali.javaee7.producer;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DatabaseProducer {

    @Produces
    @PersistenceContext(unitName = "bookStore")
    EntityManager em;
}
