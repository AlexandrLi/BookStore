package com.epam.ali.javaee7.annotation;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Target({FIELD, TYPE, METHOD})
@Retention(RUNTIME)
public @interface KZ {
}
