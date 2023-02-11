package org.example.charts;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractChartPanel extends ChartPanel {

    public AbstractChartPanel(JFreeChart chart) {
        super(chart);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}
