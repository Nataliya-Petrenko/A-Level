package com.petrenko.action;

import com.petrenko.service.CarService;

public interface Action {
    CarService CAR_SERVICE = CarService.getInstance();

    void execute();
}
