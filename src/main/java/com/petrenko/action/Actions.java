package com.petrenko.action;

import lombok.Getter;

@Getter
public enum Actions {
    CREATE(" Create cars", new CreateAction()),
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
