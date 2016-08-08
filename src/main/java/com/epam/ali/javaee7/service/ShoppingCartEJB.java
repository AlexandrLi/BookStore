package com.epam.ali.javaee7.service;

import com.epam.ali.javaee7.model.Book;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Stateful
@StatefulTimeout(value = 20, unit = TimeUnit.SECONDS)
public class ShoppingCartEJB {

    private List<Book> cartItems = new ArrayList<>();

    public void addBook(Book book) {
        if (!cartItems.contains(book)) {
            cartItems.add(book);
        }
    }

    public void removeBook(Book book) {
        if (cartItems.contains(book)) {
            cartItems.remove(book);
        }
    }

    public Integer getNumberOfItems() {
        if (cartItems == null || cartItems.isEmpty()) {
            return 0;
        } else {
            return cartItems.size();
        }
    }

    public Float getTotal() {
        if (cartItems == null || cartItems.isEmpty()) {
            return 0f;
        } else {
            Float total = 0f;
            for (Book cartItem : cartItems) {
                total += cartItem.getPrice();
            }
            return total;
        }
    }

    public void empty() {
        cartItems.clear();
    }

    @Remove
    public void checkout() {

        empty();
    }
}
