package com.epam.ali.javaee7.service;

import com.epam.ali.javaee7.annotation.Loggable;
import com.epam.ali.javaee7.annotation.ThirteenDigits;
import com.epam.ali.javaee7.model.Book;
import com.epam.ali.javaee7.model.Book_;
import com.epam.ali.javaee7.util.NumberGenerator;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Loggable
@Stateless
public class BookEJB {

    private static final int INVENTORY_MAX_CAPACITY = 1000;

    @PersistenceContext(unitName = "bookStore")
    private EntityManager em;

    @EJB
    private CustomerEJB customerEJB;

    @Inject
    @ThirteenDigits
    private NumberGenerator numberGenerator;

    @Resource
    private SessionContext context;

    @Resource(name = "currencyEntry")
    private Float changeRate;
    @Resource(name = "changeRateEntry")
    private String currency;

    public Book persistBook(Book book) {
        if (!context.isCallerInRole("admin")) {
            throw new SecurityException("Only administrators can create books");
        }
        em.persist(book);
        if (inventoryLevel(book) > INVENTORY_MAX_CAPACITY) {
            context.setRollbackOnly();
        }
        return book;
    }

    private int inventoryLevel(Book book) {
        // TODO: 8/8/2016 Mock method. Add business logic
        return 0;
    }

    public List<Book> getAllBooks() {
        TypedQuery<Book> query = em.createNamedQuery(Book.FIND_ALL, Book.class);
        return query.getResultList();
    }

    public List<Book> getBooksByTitle(String title) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Book> query = builder.createQuery(Book.class);
        Root<Book> book = query.from(Book.class);
        query.select(book).where(builder.equal(book.get(Book_.title), title));
        return em.createQuery(query).getResultList();
    }

    public Book convertPrice(Book book) {
        book.setPrice(book.getPrice() * changeRate);
        book.setCurrency(currency);
        return book;
    }
}
