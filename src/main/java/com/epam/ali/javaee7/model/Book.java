package com.epam.ali.javaee7.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.validation.constraints.NotNull;

import static com.epam.ali.javaee7.model.Book.FIND_ALL;

@Entity
@NamedNativeQuery(name = FIND_ALL, query = "SELECT * FROM BOOK")
public class Book {
    public static final String FIND_ALL = "Book.findAll";
    public static final String FIND_ALL_BY_TITLE = "Book.findAllByTitle";
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String title;
    private Float price;
    private String description;
    private String isbn;
    private Integer numberOfPages;
    private Boolean illustrations;

    public Book() {
    }

    public Book(String title, Float price, String description, String isbn, Integer numberOfPages, Boolean illustrations) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.isbn = isbn;
        this.numberOfPages = numberOfPages;
        this.illustrations = illustrations;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Boolean getIllustrations() {
        return illustrations;
    }

    public void setIllustrations(Boolean illustrations) {
        this.illustrations = illustrations;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", isbn='" + isbn + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", illustrations=" + illustrations +
                '}';
    }
}
