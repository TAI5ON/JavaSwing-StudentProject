package org.example.simulationUnit.impl;

import org.example.Simulation;
import org.example.simulationUnit.AbstractSimulationUnit;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Truck extends AbstractSimulationUnit<Truck> {

    private int waste = 0;
    private boolean isInControl = false;


    public Truck(long id) {
        super(id);
    }

    @Override
    public void start() {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(getTask(), 0, 50, TimeUnit.MILLISECONDS);
    }

    public int getWaste() {
        return waste;
    }

    public void setWaste(int waste) {
        this.waste = waste;
        Simulation.addTruckValue(getId(), waste);
    }

    public boolean isInControl() {
        return isInControl;
    }

    public void setInControl(boolean inControl) {
        isInControl = inControl;
    }
}
