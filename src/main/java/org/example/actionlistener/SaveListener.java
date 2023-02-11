package org.example.actionlistener;

import org.example.Simulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        try {

            ObjectOutputStream[] objectOutputStream = new ObjectOutputStream[] {
                    new ObjectOutputStream(new FileOutputStream("simulation0.file")),
                    new ObjectOutputStream(new FileOutputStream("simulation1.file")),
                    new ObjectOutputStream(new FileOutputStream("simulation2.file")),
            };
            final int[] i = {0};
            Simulation.getInstance()
                    .getFactories()
                    .values()
                    .forEach(factory -> {
                        try {
                            objectOutputStream[i[0]].writeObject(factory.findAll());
                            i[0]++;
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
            System.out.println("Zapisano pomyślnie");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
