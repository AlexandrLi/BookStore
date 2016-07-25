package com.epam.ali.javaee7.util;

import com.epam.ali.javaee7.annotation.KZ;
import com.epam.ali.javaee7.annotation.ZipCode;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZipCodeValidator implements ConstraintValidator<ZipCode, String> {
    @Inject
    @KZ
    private ZipCodeChecker checker;
    private Pattern zipPattern = Pattern.compile("\\d{6}");

    public void initialize(ZipCode constraint) {
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        Matcher matcher = zipPattern.matcher(value);
        if (!matcher.matches()) {
            return false;
        }
        return checker.isZipCodeValid(value);
    }
}
