package com.epam.ali.javaee7.util;

import com.epam.ali.javaee7.annotation.Loggable;
import com.epam.ali.javaee7.annotation.ThirteenDigits;

import javax.inject.Inject;
import java.util.Random;
import java.util.logging.Logger;

@ThirteenDigits
public class IsbnGenerator implements NumberGenerator {
    @Inject
    private Logger logger;

    @Loggable
    @Override
    public String generateNumber() {
        String isbn = "13-84356-" + Math.abs(new Random().nextInt());
        logger.info("Generated ISBN: " + isbn);
        return isbn;
    }
}
