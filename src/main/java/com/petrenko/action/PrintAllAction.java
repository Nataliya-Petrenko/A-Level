package com.petrenko.action;

public class PrintAllAction implements Action {
    @Override
    public void execute() {
        CAR_SERVICE.printAll();
        System.out.println();
    }
}
