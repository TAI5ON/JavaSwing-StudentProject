package org.example.factory.impl;


import org.example.Simulation;
import org.example.factory.AbstractFactory;
import org.example.simulationUnit.impl.WasteDisposalFacility;

import java.util.List;

public class WasteDisposalFacilityFactory extends AbstractFactory<WasteDisposalFacility> {

    public WasteDisposalFacilityFactory(List<WasteDisposalFacility> factoryContent) {
        super(factoryContent, Simulation.getInstance());
    }

}
