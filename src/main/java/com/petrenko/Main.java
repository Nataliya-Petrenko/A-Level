package com.petrenko;

//        Homework 9
//● Створити клас com.{lastname}.util.RandomGenerator та метод який
//генерує випадкове число в діапазоні 0 - 10.
//● Додати метод create() в клас CarService , який приймає клас
//RandomGenerator і створює кількість машин, що дорівнює
//згенерованому числу, і показує їх на консоль. Після чого повертає
//кількість створених машин.
//● Додати в метод create() перевірку
//○ Якщо число дорівнює 0, то нічого не робити та повертати -1.
//● Створити позитивні та негативні тести на клас CarService

import com.petrenko.repository.CarRepository;
import com.petrenko.service.CarService;
import com.petrenko.util.RandomGenerator;

public class Main {
    public static void main(String[] args) {
        final CarService carService = new CarService(new CarRepository());
        final RandomGenerator rg = new RandomGenerator();
        carService.create(rg);
    }
}
