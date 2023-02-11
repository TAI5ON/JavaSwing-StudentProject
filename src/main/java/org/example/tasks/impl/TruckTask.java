package org.example.tasks.impl;

import org.example.Simulation;
import org.example.factory.FactoryType;
import org.example.factory.impl.CompanyFactory;
import org.example.factory.impl.WasteDisposalFacilityFactory;
import org.example.simulationUnit.impl.Company;
import org.example.simulationUnit.impl.Truck;
import org.example.simulationUnit.impl.WasteDisposalFacility;
import org.example.tasks.Task;

import java.util.Random;

public class TruckTask extends Task<Truck> {

    public TruckTask(Truck taskOwner) {
        super(taskOwner);
    }

    @Override
    public void run() {
        if (taskOwner.isInControl()) {
            return;
        }

        CompanyFactory companyFactory = (CompanyFactory) Simulation.getInstance()
                .getFactories()
                .get(FactoryType.COMPANY);

        Company company = companyFactory.findAll().get(new Random().nextInt(companyFactory.findAll().size()));
        taskOwner.setWaste(company.getWaste());
        company.setWaste(0);

        WasteDisposalFacilityFactory wasteDisposalFacilityFactory = (WasteDisposalFacilityFactory) Simulation.getInstance()
                .getFactories()
                .get(FactoryType.WASTE_DISPOSAL_FACILITY);

        WasteDisposalFacility wasteDisposalFacility = wasteDisposalFacilityFactory.findAll().get(new Random().nextInt(wasteDisposalFacilityFactory.findAll().size()));
        wasteDisposalFacility.addTruckToQueue(taskOwner);
        taskOwner.setInControl(true);
    }
}
