package org.example.actionlistener;


import org.example.Simulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartSimulationButtonListener implements ActionListener {

    private Simulation simulation;

    public StartSimulationButtonListener(Simulation simulation) {
        this.simulation = simulation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        simulation.startSimulation();
    }
}
