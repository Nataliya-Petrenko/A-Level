package com.petrenko.repository;

import com.petrenko.model.PassengerCar;
import com.petrenko.model.Color;
import com.petrenko.model.Engine;
import com.petrenko.model.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarRepositoryTest {
    private CarRepository target;
    private PassengerCar car;

    @BeforeEach
    void setUp() {
        target = CarRepository.getInstance();
        car = new PassengerCar("abc", new Engine(Type.CAR), Color.BLUE);
        target.save(car);
    }

    @Test
    void save_Test() {
        Assertions.assertDoesNotThrow(() -> target.save(car));
    }

    @Test
    void save_CarIsNull() {
        Assertions.assertDoesNotThrow(() -> target.save(null));
    }

    @Test
    void save_NumberOfCarMore10() {
        for (int i = 0; i < 100; i++) {
            PassengerCar car = new PassengerCar();
            Assertions.assertDoesNotThrow(() -> target.save(car));
        }
    }

    @Test
    void insert_Test() {
        final int indexInsertCar = 5;
        Assertions.assertDoesNotThrow(() -> target.insert(car, indexInsertCar));
    }

    @Test
    void insert_CarIsNull() {
        final int indexInsertCar = 5;
        Assertions.assertDoesNotThrow(() -> target.insert(null, indexInsertCar));
    }

    @Test
    void insert_IndexIsZero() {
        final int indexInsertCar = 0;
        Assertions.assertDoesNotThrow(() -> target.insert(car, indexInsertCar));
    }

    @Test
    void insert_IndexIsNegative() {
        final int indexInsertCar = -5;
        Assertions.assertDoesNotThrow(() -> target.insert(car, indexInsertCar));
    }

    @Test
    void insert_IndexIsBig() {
        final int indexInsertCar = 5555;
        Assertions.assertDoesNotThrow(() -> target.insert(car, indexInsertCar));
    }

    @Test
    void getAll_Test() {
        Assertions.assertNotNull(target.getAll());
    }

    @Test
    void getByUuid_Test() {
        final String uuidOfCar = car.getUuidOfCar();
        Assertions.assertNotNull(target.getByUuid(uuidOfCar));
    }

    @Test
    void getByUuid_Null() {
        Assertions.assertNull(target.getByUuid(null));
    }

    @Test
    void updateColor_Test() {
        final String uuidOfCar = car.getUuidOfCar();
        final Color color = Color.BLUE;
        Assertions.assertDoesNotThrow(() -> target.updateColor(uuidOfCar, color));
    }

    @Test
    void updateColor_UuidIsNull() {
        final String uuidOfCar = null;
        final Color color = Color.BLUE;
        Assertions.assertDoesNotThrow(() -> target.updateColor(uuidOfCar, color));
    }

    @Test
    void updateColor_ColorIsNull() {
        final String uuidOfCar = car.getUuidOfCar();
        final Color color = null;
        Assertions.assertDoesNotThrow(() -> target.updateColor(uuidOfCar, color));
    }

    @Test
    void updateColorRandom_Test() {
        final String uuidOfCar = car.getUuidOfCar();
        Assertions.assertDoesNotThrow(() -> target.updateColorRandom(uuidOfCar));
    }

    @Test
    void updateColorRandom_UuidIsNull() {
        final String uuidOfCar = null;
        Assertions.assertDoesNotThrow(() -> target.updateColorRandom(uuidOfCar));
    }

    @Test
    void updatePrice_Test() {
        final String uuidOfCar = car.getUuidOfCar();
        final int price = 1000;
        Assertions.assertDoesNotThrow(() -> target.updatePrice(uuidOfCar, price));
    }

    @Test
    void updatePrice_UuidIsNull() {
        final String uuidOfCar = null;
        final int price = 1000;
        Assertions.assertDoesNotThrow(() -> target.updatePrice(uuidOfCar, price));
    }

    @Test
    void updatePrice_PriseIsNegative() {
        final String uuidOfCar = car.getUuidOfCar();
        final int price = -1000;
        Assertions.assertDoesNotThrow(() -> target.updatePrice(uuidOfCar, price));
    }

    @Test
    void deleteByUuid_Test() {
        final String uuidOfCar = car.getUuidOfCar();
        Assertions.assertDoesNotThrow(() -> target.deleteByUuid(uuidOfCar));
    }

    @Test
    void deleteByUuid_UuidIsNull() {
        final String uuidOfCar = null;
        Assertions.assertDoesNotThrow(() -> target.deleteByUuid(uuidOfCar));
    }
}