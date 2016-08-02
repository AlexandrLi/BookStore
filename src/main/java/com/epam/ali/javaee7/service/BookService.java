package com.epam.ali.javaee7.service;

import com.epam.ali.javaee7.annotation.Loggable;
import com.epam.ali.javaee7.annotation.ThirteenDigits;
import com.epam.ali.javaee7.model.Book;
import com.epam.ali.javaee7.model.Book_;
import com.epam.ali.javaee7.util.NumberGenerator;

import javax.inject.Inject;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Loggable
public class BookService {
    @Inject
    @ThirteenDigits
    private NumberGenerator numberGenerator;

    public Book createBook(String title, Float price, String description, Integer numberOfPages, Boolean illustrations) {
        String isbn = numberGenerator.generateNumber();
        return new Book(title, price, description, isbn, numberOfPages, illustrations);
    }

    public Book persistBook(Book book) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bookStore");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(book);
        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();
        return book;
    }

    public List<Book> getAllBooks() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookStore");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Book> query = em.createNamedQuery("Book.findAll", Book.class);
        List<Book> books = query.getResultList();
        em.close();
        emf.close();
        return books;
    }

    public List<Book> getBooksByTitle(String title) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookStore");
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Book> query = builder.createQuery(Book.class);
        Root<Book> book = query.from(Book.class);
        query.select(book).where(builder.equal(book.get(Book_.title), title));
        List<Book> books = em.createQuery(query).getResultList();
        em.close();
        emf.close();
        return books;
    }
}
