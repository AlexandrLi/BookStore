package com.epam.ali.javaee7.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;

import static com.epam.ali.javaee7.model.Book.FIND_ALL;

@Entity
@NamedQuery(name = FIND_ALL, query = "SELECT b from Book b ORDER BY b.title DESC")
public class Book implements Serializable {
    public static final String FIND_ALL = "Book.findAll";
    @Id
    @GeneratedValue
    private Long id;
    @Version
    private Integer version;
    @NotNull
    @Size(min = 4, max = 50)
    @Column(nullable = false)
    private String title;
    private Float price;
    @Column(length = 2000)
    private String description;
    private Integer numberOfPages;
    private Boolean illustrations;

    public Book() {
    }

    public Book(String title, Float price, String description, Integer numberOfPages, Boolean illustrations) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.numberOfPages = numberOfPages;
        this.illustrations = illustrations;
    }

    public Integer getVersion() {
        return version;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return id != null ? id.equals(book.id) : book.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }


}
