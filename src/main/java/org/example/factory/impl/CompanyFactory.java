package org.example.factory.impl;


import org.example.Simulation;
import org.example.factory.AbstractFactory;
import org.example.simulationUnit.impl.Company;

import java.util.List;

public class CompanyFactory extends AbstractFactory<Company> {


    public CompanyFactory(List<Company> factoryContent) {
        super(factoryContent, Simulation.getInstance());
    }


}
