package org.example.tasks.impl;


import org.example.simulationUnit.impl.Truck;
import org.example.simulationUnit.impl.WasteDisposalFacility;
import org.example.tasks.Task;

public class WasteDisposalFacilityTask extends Task<WasteDisposalFacility> {


    public WasteDisposalFacilityTask(WasteDisposalFacility taskOwner) {
        super(taskOwner);
    }

    @Override
    public void run() {
        Truck truck = taskOwner.removeTruckFromQueue();
        if (truck == null) {
            return;
        }
        int wasteInWDF = taskOwner.getWaste();
        taskOwner.setWaste(wasteInWDF + truck.getWaste());
        truck.setWaste(0);
        truck.setInControl(false);
    }
}
