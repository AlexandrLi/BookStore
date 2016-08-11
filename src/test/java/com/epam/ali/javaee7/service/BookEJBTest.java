package com.epam.ali.javaee7.service;

import junit.framework.TestCase;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class BookEJBTest extends TestCase {

    public void testShouldCreateBook() throws Exception {
        Map<String, Object> properties = new HashMap<>();
        properties.put(EJBContainer.MODULES, new File("target/classes"));
        try (EJBContainer ec = EJBContainer.createEJBContainer(properties)) {
            Context context = ec.getContext();
            assertNotNull(context.lookup("java:global/jdbc/bookStoreDS"));
            assertNotNull(context.lookup("java:global/classes/BookEJB!com.epam.ali.javaee7.service.BookEJBRemote"));
            assertNotNull(context.lookup("java:global/classes/BookEJB!com.epam.ali.javaee7.service.BookEJB"));
        }
    }

}