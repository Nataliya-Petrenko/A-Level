package com.petrenko.model;

import com.petrenko.service.CarService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarBot {
    private Type type;
    private Car car;
    private List<Car> cars;

}
