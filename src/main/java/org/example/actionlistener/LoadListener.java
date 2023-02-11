package org.example.actionlistener;


import org.example.Simulation;
import org.example.factory.FactoryType;
import org.example.factory.impl.CompanyFactory;
import org.example.factory.impl.TruckFactory;
import org.example.factory.impl.WasteDisposalFacilityFactory;
import org.example.simulationUnit.impl.Company;
import org.example.simulationUnit.impl.Truck;
import org.example.simulationUnit.impl.WasteDisposalFacility;
import org.example.tasks.impl.CompanyTask;
import org.example.tasks.impl.TruckTask;
import org.example.tasks.impl.WasteDisposalFacilityTask;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class LoadListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            ObjectInputStream[] objectInputStream = new ObjectInputStream[] {
                    new ObjectInputStream(new FileInputStream("simulation0.file")),
                    new ObjectInputStream(new FileInputStream("simulation1.file")),
                    new ObjectInputStream(new FileInputStream("simulation2.file"))
            };


            TruckFactory truckFactory = (TruckFactory) Simulation.getInstance()
                    .getFactories().get(FactoryType.TRUCK);

            CompanyFactory companyFactory = (CompanyFactory) Simulation.getInstance()
                    .getFactories().get(FactoryType.COMPANY);

            WasteDisposalFacilityFactory wasteDisposalFacilityFactory = (WasteDisposalFacilityFactory) Simulation.getInstance()
                    .getFactories().get(FactoryType.WASTE_DISPOSAL_FACILITY);

            List<Company> companies = (List<Company>) objectInputStream[0].readObject();
            companyFactory.loadAll(companies);

            List<WasteDisposalFacility> wasteDisposalFacilities = (List<WasteDisposalFacility>) objectInputStream[1].readObject();
            wasteDisposalFacilityFactory.loadAll(wasteDisposalFacilities);

            List<Truck> trucks = (List<Truck>) objectInputStream[2].readObject();
            truckFactory.loadAll(trucks);

            Simulation.getInstance().getTruckCounter().setText("Ilość ciężarówek: " + truckFactory.findAll().size());

            companyFactory.findAll()
                    .forEach(company -> {
                        Simulation.addCompanyValue(company.getId(), company.getWaste());
                        company.setTask(new CompanyTask(company));
                    });

            truckFactory.findAll()
                    .forEach(truck -> {
                        Simulation.addCompanyValue(truck.getId(), truck.getWaste());
                        truck.setTask(new TruckTask(truck));
                    });

            wasteDisposalFacilityFactory.findAll()
                    .forEach(wasteDisposalFacility -> {
                        Simulation.addCompanyValue(wasteDisposalFacility.getId(), wasteDisposalFacility.getWaste());
                        wasteDisposalFacility.setTask(new WasteDisposalFacilityTask(wasteDisposalFacility));
                    });

            System.out.println("Wczytano pomyślnie!");
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}
