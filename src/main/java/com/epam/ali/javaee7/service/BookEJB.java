package com.epam.ali.javaee7.service;

import com.epam.ali.javaee7.model.Book;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Named
@Stateless
public class BookEJB {

    @PersistenceContext(unitName = "bookStore")
    private EntityManager em;

    public List<Book> findAllBooks() {
        return em.createNamedQuery(Book.FIND_ALL, Book.class).getResultList();
    }

    public Book createBook(Book book) {
        em.persist(book);
        return book;
    }

    public Book findBookById(Long id) {
        return em.find(Book.class, id);
    }
}
