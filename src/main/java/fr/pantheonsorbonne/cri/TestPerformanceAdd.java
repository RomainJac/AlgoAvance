package fr.pantheonsorbonne.cri;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.awt.*;

public class TestPerformanceAdd {
    private static final int[] SIZES = { 100, 500, 1000, 10000, 25000, 50000, 100000, 150000, 200000, 250000, 300000, 350000, 400000 };
    private static final int NUM_TESTS = 50;

    private interface TimeFunction {
        double measureTime(int nbElem);
    }

    private double averageTime(int nbElem, TimeFunction function) {
        double totalTime = 0;
        for (int i = 0; i < NUM_TESTS; i++) {
            totalTime += function.measureTime(nbElem);
        }
        return totalTime / NUM_TESTS;
    }

    private void runTestsAndGenerateData(DefaultCategoryDataset barChartDataset, XYSeriesCollection lineChartDataset, String[] labels, TimeFunction[] functions) {
        XYSeries[] lineChartSeries = new XYSeries[functions.length];

        for (int i = 0; i < functions.length; i++) {
            lineChartSeries[i] = new XYSeries(labels[i]);
        }

        for (int size : SIZES) {
            double[] averageTimes = new double[functions.length];
            for (int i = 0; i < functions.length; i++) {
                final int index = i;
                averageTimes[index] = averageTime(size, (nbElem) -> functions[index].measureTime(nbElem));
                if (barChartDataset != null) {
                    barChartDataset.addValue(averageTimes[index], labels[index], "Size: " + size);
                }
                lineChartSeries[index].add(size, averageTimes[index]);
            }
        }

        if (lineChartDataset != null) {
            for (XYSeries series : lineChartSeries) {
                lineChartDataset.addSeries(series);
            }
        }
    }

    public void runTestsGraphique() {
        DefaultCategoryDataset barChartDataset = new DefaultCategoryDataset();
        XYSeriesCollection lineChartDataset = new XYSeriesCollection();

        String[] labels = { "LinkedList", "HashSet", "TreeSet" };
        TimeFunction[] functions = {
                this::linkedListTime,
                this::hashSetTime,
                this::treeSetTime
        };

        runTestsAndGenerateData(barChartDataset, lineChartDataset, labels, functions);

        JFreeChart barChart = ChartFactory.createBarChart(
                "Comparaison des performances (add)",
                "Taille de la collection",
                "Temps d'exécution moyen (en ns)",
                barChartDataset);

        JFreeChart lineChart = ChartFactory.createXYLineChart(
                "Comparaison des performances (add)",
                "Taille de la collection",
                "Temps d'exécution moyen (en ns)",
                lineChartDataset);

        ChartPanel barChartPanel = new ChartPanel(barChart);
        ChartPanel lineChartPanel = new ChartPanel(lineChart);

        JFrame frame = new JFrame("Comparaison des performances (add)");
        frame.setLayout(new GridLayout(2, 1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(barChartPanel);
        frame.add(lineChartPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public void runTestsTableau() {
        Object[][] data = new Object[SIZES.length][4];

        for (int i = 0; i < SIZES.length; i++) {
            int size = SIZES[i];
            data[i][0] = size;
            data[i][1] = averageTime(size, this::linkedListTime);
            data[i][2] = averageTime(size, this::hashSetTime);
            data[i][3] = averageTime(size, this::treeSetTime);
        }

        String[] columnNames = { "Taille de la collection", "LinkedList", "HashSet", "TreeSet" };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JFrame frame = new JFrame("Tableau de comparaison des performances (add)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String... args) {
        TestPerformanceAdd test = new TestPerformanceAdd();
        test.runTestsGraphique();
        test.runTestsTableau();
    }

    // Méthodes de test spécifiques pour la fonction add
    private double hashSetTime(int nbElem) {
        StringHashSet set = new StringHashSet();
        long start = System.nanoTime();
        for (int i = 0; i < nbElem; i++) {
            set.add("elem" + i);
        }
        long end = System.nanoTime();
        return end - start;
    }

    private double linkedListTime(int nbElem) {
        LinkedListe list = new LinkedListe();
        long start = System.nanoTime();
        for (int i = 0; i < nbElem; i++) {
            list.add("elem" + i);
        }
        long end = System.nanoTime();
        return end - start;
    }

    private double treeSetTime(int nbElem) {
        ETreeSet<String> set = new ETreeSet<>();
        double start = System.nanoTime();
        for (int i = 0; i < nbElem; i++) {
            set.add("elem" + i);
        }
        double end = System.nanoTime();
        return end - start;
    }
}
