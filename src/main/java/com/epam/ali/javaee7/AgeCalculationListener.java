package com.epam.ali.javaee7;

import com.epam.ali.javaee7.model.Customer;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AgeCalculationListener {
    @PostLoad
    @PostPersist
    @PostUpdate
    public void calculateAge(Customer customer) {
        if (customer.getDateOfBirth() == null) {
            customer.setAge(null);
            return;
        }
        GregorianCalendar birth = new GregorianCalendar();
        birth.setTime(customer.getDateOfBirth());
        GregorianCalendar now = new GregorianCalendar();
        now.setTime(new Date());
        int adjust = 0;
        if (birth.get(Calendar.DAY_OF_YEAR) > now.get(Calendar.DAY_OF_YEAR)) {
            adjust = -1;
        }
        customer.setAge(now.get(Calendar.YEAR) - birth.get(Calendar.YEAR) + adjust);
    }
}
