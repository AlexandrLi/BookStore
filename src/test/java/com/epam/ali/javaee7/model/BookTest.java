package com.epam.ali.javaee7.model;

import com.epam.ali.javaee7.service.BookService;
import junit.framework.TestCase;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolationException;

public class BookTest extends TestCase {

    private EntityManager entityManager;
    private EntityTransaction transaction;
    private BookService bookService;
    private WeldContainer container;

    public void setUp() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bookStoreTest");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
        Weld weld = new Weld();
        container = weld.initialize();
        bookService = container.instance().select(BookService.class).get();
    }

    public void tearDown() {
        if (entityManager != null) {
            entityManager.close();
            container.close();
        }
    }

    public void testShouldFindJavaee7Book() {
        Book book = entityManager.find(Book.class, 1001L);
        assertEquals("Learn Java EE 7", book.getTitle());
    }

    public void testShouldCreateJavaBook() {
        Book book = bookService.createBook("Java", 12.5f, "Java EE 7 book", 760, true);
        transaction.begin();
        entityManager.persist(book);
        transaction.commit();
        assertNotNull("Id can not be empty", book.getId());
        book = entityManager.createNamedQuery("findBookJava", Book.class).getSingleResult();
        assertEquals("Java EE 7 book", book.getDescription());
    }

    public void testShouldRaiseConstraintViolationCauseNullTitle() {
        try {
            Book book = bookService.createBook(null, 12.5f, "Empty", 760, true);
            entityManager.persist(book);
            fail("Test failed");
        } catch (ConstraintViolationException e) {
            assertTrue("Test passed", true);
        }

    }
}