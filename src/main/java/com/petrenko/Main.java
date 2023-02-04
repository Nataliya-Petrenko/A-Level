package com.petrenko;

import com.petrenko.repository.JdbcCarRepository;
import com.petrenko.repository.JdbcEngineRepository;
import com.petrenko.repository.JdbcOrderRepository;
import com.petrenko.service.OrderService;

public class Main {
    public static void main(String[] args) {

        JdbcOrderRepository.dropTable();
        JdbcCarRepository.dropTable();
        JdbcEngineRepository.dropTable();

        JdbcEngineRepository.getInstance();
        JdbcCarRepository.getInstance();
        JdbcOrderRepository.getInstance();

        System.out.println();

        OrderService orderService = OrderService.getInstance();
        orderService.create(3);
        orderService.printAll();


    }


}
