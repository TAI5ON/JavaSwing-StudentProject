package org.example.actionlistener;



import org.example.Simulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTruckListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Simulation.getInstance().addTruck();
    }
}
