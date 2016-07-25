package com.epam.ali.javaee7;

import com.epam.ali.javaee7.annotation.Loggable;
import com.epam.ali.javaee7.annotation.ThirteenDigits;
import com.epam.ali.javaee7.util.NumberGenerator;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.util.Random;
import java.util.logging.Logger;

@Alternative
@ThirteenDigits
public class MockGenerator implements NumberGenerator {
    @Inject
    private Logger logger;

    @Loggable
    public String generateNumber() {
        String mock = "MOCK-" + Math.abs(new Random().nextInt());
        logger.info("Generated Mock: " + mock);
        return mock;
    }
}
