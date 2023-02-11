package org.example.factory.impl;


import org.example.Simulation;
import org.example.factory.AbstractFactory;
import org.example.simulationUnit.impl.Truck;

import java.util.List;

public class TruckFactory extends AbstractFactory<Truck> {

    public TruckFactory(List<Truck> factoryContent) {
        super(factoryContent, Simulation.getInstance());
    }

}
