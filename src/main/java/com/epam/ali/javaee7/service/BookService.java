package com.epam.ali.javaee7.service;

import com.epam.ali.javaee7.annotation.Loggable;
import com.epam.ali.javaee7.annotation.ThirteenDigits;
import com.epam.ali.javaee7.model.Book;
import com.epam.ali.javaee7.util.NumberGenerator;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
}
