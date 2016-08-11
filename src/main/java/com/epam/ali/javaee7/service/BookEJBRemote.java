package com.epam.ali.javaee7.service;

import com.epam.ali.javaee7.model.Book;

import javax.ejb.Remote;
import javax.validation.constraints.NotNull;
import java.util.List;

@Remote
public interface BookEJBRemote {
    List<Book> findBooks();

    @NotNull Book createBook(@NotNull Book book);

    @NotNull Book updateBook(@NotNull Book book);

    void deleteBook(@NotNull Book book);


}
