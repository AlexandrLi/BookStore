package com.epam.ali.javaee7;

import com.epam.ali.javaee7.model.Book;
import com.epam.ali.javaee7.service.BookService;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class Runner {
    public static void main(String[] args) {
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();
        BookService bookService = container.instance().select(BookService.class).get();
        Book book = bookService.createBook("Harry Potter", 12.5f, "Story about sorcerer boy");
        System.out.println(book);
        weld.shutdown();
    }
}
