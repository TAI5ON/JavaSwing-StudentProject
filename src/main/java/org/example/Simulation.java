package org.example;

import org.example.actionlistener.*;
import org.example.charts.CompaniesChartPanel;
import org.example.charts.TrucksChartPanel;
import org.example.charts.WasteDisposalFacilityPanel;
import org.example.factory.Factory;
import org.example.factory.FactoryType;
import org.example.factory.impl.CompanyFactory;
import org.example.factory.impl.TruckFactory;
import org.example.factory.impl.WasteDisposalFacilityFactory;
import org.example.simulationUnit.AbstractSimulationUnit;
import org.example.simulationUnit.SimulationUnit;
import org.example.simulationUnit.impl.Company;
import org.example.simulationUnit.impl.Truck;
import org.example.simulationUnit.impl.WasteDisposalFacility;
import org.example.tasks.impl.CompanyTask;
import org.example.tasks.impl.TruckTask;
import org.example.tasks.impl.WasteDisposalFacilityTask;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.*;

public final class Simulation extends JFrame {

    private static Simulation SINGLETON;

    public static DefaultCategoryDataset COMPANIES_DATASET = new DefaultCategoryDataset();
    public static DefaultCategoryDataset TRUCKS_DATASET = new DefaultCategoryDataset();
    public static DefaultCategoryDataset WASTE_DISPOSAL_FACILITY_DATASET = new DefaultCategoryDataset();

    public static void addCompanyValue(long id, int value) {
        COMPANIES_DATASET.addValue(value, "Odpady", String.valueOf(id));
    }

    public static void addTruckValue(long id, int value) {
        TRUCKS_DATASET.addValue(value, "Odpady", String.valueOf(id));
    }

    public static void addWFDValue(long id, int value) {
        WASTE_DISPOSAL_FACILITY_DATASET.addValue(value, "Odpady", String.valueOf(id));
    }


    public static Simulation getInstance() {
        return SINGLETON;
    }


    private final HashMap<FactoryType, Factory<? extends SimulationUnit>> factories;
    private final JMenuBar menuBar = new JMenuBar();
    private final JLabel truckCounter;
    private boolean isSimulationStart = false;



    public Simulation() {
        SINGLETON = this;

        this.factories = new HashMap<>();
        this.factories.put(FactoryType.COMPANY, new CompanyFactory(new ArrayList<>()));
        this.factories.put(FactoryType.TRUCK, new TruckFactory(new ArrayList<>()));
        this.factories.put(FactoryType.WASTE_DISPOSAL_FACILITY, new WasteDisposalFacilityFactory(new ArrayList<>()));

        setSize(1280, 720);
        setTitle("Symulacja");
        setLayout(null);
        setResizable(false);

        JFreeChart companiesChart = ChartFactory.createBarChart(
                "Firmy",
                "id firmy",
                "Odpady",
                COMPANIES_DATASET);
        CompaniesChartPanel companiesPanel = new CompaniesChartPanel(companiesChart);
        add(companiesPanel);

        JFreeChart trucksChart = ChartFactory.createBarChart(
                "Ciężarówki",
                "id ciężarówki",
                "Odpady",
                TRUCKS_DATASET);
        TrucksChartPanel trucksPanel = new TrucksChartPanel(trucksChart);
        this.truckCounter = trucksPanel.getLabel();
        add(trucksPanel);

        JFreeChart wasteDisposalFacilityChart = ChartFactory.createBarChart(
                "Punkty odbioru",
                "id punktu",
                "Odpady",
                WASTE_DISPOSAL_FACILITY_DATASET);
        WasteDisposalFacilityPanel wasteDisposalFacilityPanel = new WasteDisposalFacilityPanel(wasteDisposalFacilityChart);
        add(wasteDisposalFacilityPanel);

        JButton button = new JButton("Zacznij symulację!");
        button.setBounds(0, 620, 1280, 40);
        button.addActionListener(new StartSimulationButtonListener(this));
        add(button);

        menuBar.add(new JMenu("File"));
        menuBar.getMenu(0)
                .add(new JMenuItem("Zapisz"))
                .addActionListener(new SaveListener());
        menuBar.getMenu(0)
                .add(new JMenuItem("Wczytaj"))
                .addActionListener(new LoadListener());
        menuBar.add(new JMenu("Dodaj"));
        menuBar.getMenu(1)
                .add(new JMenuItem("Ciężarówka"))
                .addActionListener(new AddTruckListener());
        menuBar.getMenu(1)
                .add(new JMenuItem("Firma"))
                .addActionListener(new AddCompanyListener());
        menuBar.getMenu(1)
                .add(new JMenuItem("Punkt Odpadowy"))
                .addActionListener(new AddWasteDisposalFacilityListener());
        setJMenuBar(menuBar);
    }

    public void startSimulation() {
        if (this.isSimulationStart) {
            return;
        }
        System.out.println("START");
        factories.values().forEach(factory -> {
            factory.findAll().forEach(SimulationUnit::start);
        });
        this.isSimulationStart = true;
    }


    public HashMap<FactoryType, Factory<? extends SimulationUnit>> getFactories() {
        return factories;
    }

    public void addTruck() {
        TruckFactory truckFactory = (TruckFactory) factories.get(FactoryType.TRUCK);
        long idOfPreviousTruck = truckFactory.findAll()
                .stream()
                .max(Comparator.comparing(AbstractSimulationUnit::getId))
                .map(AbstractSimulationUnit::getId)
                .orElse(-1L);
        Truck truck = new Truck(idOfPreviousTruck + 1);
        truck.setTask(new TruckTask(truck));
        truckFactory.registerUnit(truck);
        if (isSimulationStart) {
            truck.start();
        }

        truckCounter.setText("Ilość ciężarówek: " + truckFactory.findAll().size());
    }

    public void addWasteDisposalFacility() {
        WasteDisposalFacilityFactory wasteDisposalFacilityFactory = (WasteDisposalFacilityFactory) factories.get(FactoryType.WASTE_DISPOSAL_FACILITY);
        long idOfPreviousTruck = wasteDisposalFacilityFactory.findAll()
                .stream()
                .max(Comparator.comparing(AbstractSimulationUnit::getId))
                .map(AbstractSimulationUnit::getId)
                .orElse(-1L);
        WasteDisposalFacility wasteDisposalFacility = new WasteDisposalFacility(
                idOfPreviousTruck + 1,
                100 + new Random().nextInt(900));
        wasteDisposalFacility.setTask(new WasteDisposalFacilityTask(wasteDisposalFacility));
        wasteDisposalFacilityFactory.registerUnit(wasteDisposalFacility);
        if (isSimulationStart) {
            wasteDisposalFacility.start();
        }
    }

    public void addCompany() {
        CompanyFactory companyFactory = (CompanyFactory) factories.get(FactoryType.COMPANY);
        long idOfPreviousTruck = companyFactory.findAll()
                .stream()
                .max(Comparator.comparing(AbstractSimulationUnit::getId))
                .map(AbstractSimulationUnit::getId)
                .orElse(-1L);

        int[] coordinates = findFreeCoordinate(companyFactory.findAll());
        Company company = new Company(idOfPreviousTruck + 1, coordinates[0], coordinates[1]);
        company.setTask(new CompanyTask(company));
        companyFactory.registerUnit(company);
        addCompanyValue(company.getId(), 0);
        if (isSimulationStart) {
            company.start();
        }
    }

    public JLabel getTruckCounter() {
        return this.truckCounter;
    }


    private int[] findFreeCoordinate(List<Company> companies) {

        while(true) {
            int x = new Random().nextInt(1000);
            int y = new Random().nextInt(1000);
            boolean isOccupied = companies.stream()
                    .filter(company -> company.getX() == x)
                    .anyMatch(company -> company.getY() == y);
            if (!isOccupied) {
                return new int[]{x, y};
            }
        }
    }
}