package com.epam.ali.javaee7.service;

import com.epam.ali.javaee7.model.Customer;
import com.epam.ali.javaee7.model.Order;

import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import java.util.concurrent.Future;

@Stateless
@Asynchronous
public class OrderEJB {

    @Resource
    SessionContext context;

    public void sendEmailOrderComplete(Order order, Customer customer) {
        // TODO: 8/9/2016 Mock method. Add business logic
    }

    public void printOrder(Order order) {
        // TODO: 8/9/2016 Mock method. Add business logic
    }

    public Future<Integer> sendOrderToWorkflow(Order order) {
        Integer status = 0;
        // TODO: 8/9/2016 Add business logic here
        status = 1;
        if (context.wasCancelCalled()) {
            return new AsyncResult<>(2);
        }
        // TODO: 8/9/2016 Add business logic here
        return new AsyncResult<>(status);
    }
}
