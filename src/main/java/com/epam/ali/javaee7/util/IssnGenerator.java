package com.epam.ali.javaee7.util;

import com.epam.ali.javaee7.annotation.EightDigits;
import com.epam.ali.javaee7.annotation.Loggable;

import javax.inject.Inject;
import java.util.Random;
import java.util.logging.Logger;

@EightDigits
public class IssnGenerator implements NumberGenerator {
    @Inject
    private Logger logger;

    @Loggable
    @Override
    public String generateNumber() {
        String issn = "8-" + Math.abs(new Random().nextInt());
        logger.info("Generated ISSN: " + issn);
        return issn;
    }
}
