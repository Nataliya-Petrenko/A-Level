package com.petrenko.action;

import lombok.Getter;

@Getter
public enum Actions {
    CREATE(" Create cars", new CreateAction()),
    PRINT_ALL_CARS(" Print cars", new PrintAllAction()),
    COMPARE(" Compare cars", new CompareAction()),
    EXIT(" Exit", new ExitAction());

    private final String name;
    private final Action action;

    Actions(final String name, final Action action) {
        this.name = name;
        this.action = action;
    }

    public void execute() {
        action.execute();
    }
}
