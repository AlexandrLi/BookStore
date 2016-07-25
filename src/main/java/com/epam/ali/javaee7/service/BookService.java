package com.epam.ali.javaee7.service;

import com.epam.ali.javaee7.util.NumberGenerator;
import com.epam.ali.javaee7.annotation.Loggable;
import com.epam.ali.javaee7.annotation.ThirteenDigits;
import com.epam.ali.javaee7.model.Book;

import javax.inject.Inject;

@Loggable
public class BookService {
    @Inject
    @ThirteenDigits
    private NumberGenerator numberGenerator;

    public Book createBook(String title, Float price, String description) {
        Book book = new Book(title, price, description);
        book.setNumber(numberGenerator.generateNumber());
        return book;
    }
}
