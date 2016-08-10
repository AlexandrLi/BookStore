package com.epam.ali.javaee7;

import com.epam.ali.javaee7.model.Address;
import com.epam.ali.javaee7.model.Book;
import com.epam.ali.javaee7.model.Customer;
import com.epam.ali.javaee7.service.BookEJB;
import com.epam.ali.javaee7.service.CustomerEJB;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Runner {
    public static void main(String[] args) {
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();
        CustomerEJB customerService = container.instance().select(CustomerEJB.class).get();
        Address address = new Address("Alihanova", "", "Karaganda", "", "100008", "Kazakhstan");
        Customer customer = new Customer("Alex", "Li", "muzikant1990@gmail.com", "+77051794745", new Date(642729600000L), address);
        customer.setCreationDate(new Date());
        customer = customerService.persistCustomer(customer);
        Map<String, Object> properties = new HashMap<>();
        properties.put(EJBContainer.MODULES, new File("target/classes"));
        try (EJBContainer ec = EJBContainer.createEJBContainer(properties)) {
            Context context = ec.getContext();
            BookEJB bookEJB = (BookEJB) context.lookup("java:global/classes/BookEJB!com.epam.ali.javaee7.service.BookEJB");
            List<Book> allBooks = bookEJB.getAllBooks();
            allBooks.forEach(System.out::println);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        weld.shutdown();
    }
}

