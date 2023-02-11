package org.example.simulationUnit.impl;

import org.example.Simulation;
import org.example.simulationUnit.AbstractSimulationUnit;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WasteDisposalFacility extends AbstractSimulationUnit<WasteDisposalFacility> {

    private final int TIME_TO_UNLOAD_TRUCK;
    private ConcurrentLinkedQueue<Truck> trucksQueue;
    private int waste;

    public WasteDisposalFacility(long id, int TIME_TO_UNLOAD_TRUCK) {
        super(id);
        this.TIME_TO_UNLOAD_TRUCK = TIME_TO_UNLOAD_TRUCK;
        this.trucksQueue = new ConcurrentLinkedQueue<>();
    }

    @Override
    public void start() {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(getTask(), 0, TIME_TO_UNLOAD_TRUCK, TimeUnit.MILLISECONDS);
    }

    public ConcurrentLinkedQueue<Truck> getTrucksQueue() {
        return trucksQueue;
    }

    public int getWaste() {
        return waste;
    }

    public void setWaste(int waste) {
        this.waste = waste;
        Simulation.addWFDValue(getId(), waste);
    }

    public int getTIME_TO_UNLOAD_TRUCK() {
        return TIME_TO_UNLOAD_TRUCK;
    }

    public void addTruckToQueue(Truck truck) {
        trucksQueue.add(truck);
    }

    public Truck removeTruckFromQueue() {
        Truck truck = trucksQueue.peek();
        trucksQueue.remove(truck);
        return truck;
    }
}
