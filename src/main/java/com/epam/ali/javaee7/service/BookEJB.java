package com.epam.ali.javaee7.service;

import com.epam.ali.javaee7.model.Book;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;

@Stateless
@LocalBean
public class BookEJB implements BookEJBRemote {

    @Inject
    private EntityManager em;

    @Override
    public List<Book> findBooks() {
        TypedQuery<Book> query = em.createNamedQuery(Book.FIND_ALL, Book.class);
        return query.getResultList();
    }

    @Override
    public
    @NotNull
    Book createBook(@NotNull Book book) {
        em.persist(book);
        return book;
    }

    @Override
    public
    @NotNull
    Book updateBook(@NotNull Book book) {
        return em.merge(book);
    }

    @Override
    public void deleteBook(@NotNull Book book) {
        em.remove(em.merge(book));
    }
}
