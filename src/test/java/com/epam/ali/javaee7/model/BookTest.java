package com.epam.ali.javaee7.model;

import junit.framework.TestCase;

import javax.persistence.*;
import javax.validation.ConstraintViolationException;

public class BookTest extends TestCase {

    private EntityManager em;
    private EntityTransaction tx;

    public void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookStoreTest");
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    public void tearDown() {
        if (em != null) {
            em.close();
        }
    }

    public void testShouldFindJavaee7Book() {
        Book book = em.find(Book.class, 1001L);
        assertEquals("Learn Java EE 7", book.getTitle());
    }

    public void testShouldCreateJavaBook() {
        Book book = new Book("Java", 12.5f, "Java EE 7 book", "13-84356-8", 760, true);
        tx.begin();
        em.persist(book);
        tx.commit();
        assertNotNull("Id can not be empty", book.getId());
        assertEquals(new Integer(1), book.getVersion());
        tx.begin();
        book = em.find(Book.class, book.getId());
        book.setDescription("Java EE 7 book version 2");
        tx.commit();
        assertEquals(new Integer(2), book.getVersion());
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.id=" + book.getId(), Book.class);
        Book result = query.getSingleResult();
        assertEquals("Java EE 7 book version 2", result.getDescription());
    }

    public void testShouldRaiseConstraintViolationCauseNullTitle() {
        try {
            Book book = new Book(null, 12.5f, "Empty", "13-84356-8", 760, true);
            em.persist(book);
            fail("Test failed");
        } catch (ConstraintViolationException e) {
            assertTrue("Test passed", true);
        }

    }


}