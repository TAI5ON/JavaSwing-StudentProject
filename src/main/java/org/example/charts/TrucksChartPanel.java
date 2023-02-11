package org.example.charts;

import org.example.Simulation;
import org.example.factory.FactoryType;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.awt.*;

public class TrucksChartPanel extends AbstractChartPanel {

    private final JLabel label;

    public TrucksChartPanel(JFreeChart chart) {
        super(chart);

        Simulation simulation = Simulation.getInstance();

        setBounds(640, 0, 624, 310);
        this.label = new JLabel();
        setLayout(null);
        label.setBounds(10, 270, 400, 50);
        label.setText("Ilość ciężarówek: " + simulation.getFactories().get(FactoryType.TRUCK).findAll().size());
        label.setFont(new Font("Monospaced", Font.BOLD, 20));
        add(label);
    }

    public JLabel getLabel() {
        return label;
    }
}
