package org.example.tasks.impl;

import org.example.simulationUnit.impl.Company;
import org.example.tasks.Task;

public class CompanyTask extends Task<Company> {

    public CompanyTask(Company taskOwner) {
        super(taskOwner);
    }

    @Override
    public void run() {
        int wastePerSecond = taskOwner.getWastePerSecond();
        taskOwner.addWaste(wastePerSecond);
    }
}
