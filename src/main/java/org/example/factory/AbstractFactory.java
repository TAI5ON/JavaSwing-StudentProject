package org.example.factory;


import org.example.Simulation;
import org.example.simulationUnit.SimulationUnit;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class AbstractFactory<T extends SimulationUnit> implements Factory<T>, Serializable {

    protected List<T> factoryContent;

    protected Simulation simulation;

    protected AbstractFactory(List<T> factoryContent, Simulation simulation) {
        this.factoryContent = factoryContent;
        this.simulation = simulation;
    }

    @Override
    public Optional<T> findById(long id) {
        return factoryContent.stream().filter(simulationUnit -> simulationUnit.getId() == id).findFirst();
    }

    @Override
    public List<T> findAll() {
        return factoryContent;
    }

    @Override
    public void loadAll(List<T> factoryContentToLoad) {
        factoryContent = factoryContentToLoad;
    }
}
