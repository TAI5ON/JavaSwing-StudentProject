package org.example.simulationUnit;


import org.example.tasks.Task;

import java.io.Serializable;

public interface SimulationUnit extends Serializable {

    long getId();

    Task<?> getTask();

    default void start() {
        if (getTask() == null) {
            throw new NullPointerException();
        }
    }
}
