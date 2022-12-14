package com.petrenko.action;

public class CreateAction implements Action {

    private static final int DEFAULT_COUNT = 10;

    @Override
    public void execute() {
        CAR_SERVICE.create(DEFAULT_COUNT);
        System.out.printf("Created %d cars%n", DEFAULT_COUNT);
    }
}
