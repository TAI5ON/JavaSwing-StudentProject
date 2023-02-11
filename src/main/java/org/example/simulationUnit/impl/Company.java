package org.example.simulationUnit.impl;


import org.example.Simulation;
import org.example.simulationUnit.AbstractSimulationUnit;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Company extends AbstractSimulationUnit<Company> {

    //Y
    private int wastePerSecond;
    //coordinates
    private int x, y;
    private int waste;


    public Company(long id, int x, int y) {
        super(id);
        this.wastePerSecond = 10 + new Random().nextInt(90);
        this.x = x;
        this.y = y;
        this.waste = 0;
    }


    //GETTERS
    public int getWastePerSecond() {
        return wastePerSecond;
    }

    @Override
    public void start() {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(getTask(), 0, 1, TimeUnit.SECONDS);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setWaste(int waste) {
        this.waste = waste;
        Simulation.addCompanyValue(getId(), waste);
    }

    public int getWaste() {
        return waste;
    }

    public void addWaste(int wasteToAdd) {
        this.waste += wasteToAdd;
        Simulation.addCompanyValue(getId(), waste);
    }
}
