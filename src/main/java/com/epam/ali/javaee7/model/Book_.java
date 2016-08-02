package com.epam.ali.javaee7.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated("EclipseLink")
@StaticMetamodel(Book.class)
public class Book_ {
    public static volatile SingularAttribute<Book, Long> id;
    public static volatile SingularAttribute<Book, String> title;
    public static volatile SingularAttribute<Book, Float> price;
    public static volatile SingularAttribute<Book, String> description;
    public static volatile SingularAttribute<Book, String> isbn;
    public static volatile SingularAttribute<Book, Integer> numberOfPages;
    public static volatile SingularAttribute<Book, Boolean> illustrations;
}
