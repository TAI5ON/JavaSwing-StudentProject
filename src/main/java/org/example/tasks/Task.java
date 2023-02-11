package org.example.tasks;

import java.io.Serializable;

public abstract class Task<SimulationUnit> implements Runnable, Serializable {

    protected final SimulationUnit taskOwner;

    protected Task(SimulationUnit taskOwner) {
        this.taskOwner = taskOwner;
    }

    public SimulationUnit getTaskOwner() {
        return taskOwner;
    }
}
