package org.example.simulationUnit;


import org.example.tasks.Task;

public abstract class AbstractSimulationUnit<T> implements SimulationUnit {

    private long id;

    private Task<T> task;

    protected AbstractSimulationUnit(long id) {
        this.id = id;
    }

    public void setTask(Task<T> task) {
        this.task = task;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public Task<T> getTask() {
        return task;
    }
}
