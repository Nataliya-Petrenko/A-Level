package com.petrenko;

import com.petrenko.config.HibernateFactoryUtil;
import com.petrenko.model.*;
import com.petrenko.service.OrderService;

public class Main {
    public static void main(String[] args) {

        HibernateFactoryUtil.getSessionFactory();
        OrderService orderService = OrderService.getInstance();
        orderService.create(5);
        orderService.printAll();
        String id = orderService.getAll()
                .stream()
                .findFirst()
                .map(Order::getId)
                .get();

        System.out.println("Delete: " + id);
        orderService.deleteById(id);

        orderService.printAll();

    }

}

