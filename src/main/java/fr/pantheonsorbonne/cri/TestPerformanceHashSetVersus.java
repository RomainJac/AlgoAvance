package fr.pantheonsorbonne.cri;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.table.JTableHeader;

import java.util.HashSet;


public class TestPerformanceHashSetVersus {
    public double hashSetTimeCustom(int nbElem, boolean deterministe) {
        StringHashSet set = new StringHashSet();
        for (int i = 0; i < nbElem; i++) {
            set.add("elem" + i);
        }
        int rand = (int) (Math.random() * nbElem);
        long start = System.nanoTime();
        if (deterministe) {
            set.contains("elem" + nbElem);
        } else {
            set.contains("elem" + rand);
        }
        long end = System.nanoTime();
        return end - start ;
    }

    public double hashSetTimeStandard(int nbElem, boolean deterministe) {
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < nbElem; i++) {
            set.add("elem" + i);
        }
        int rand = (int) (Math.random() * nbElem);
        long start = System.nanoTime();
        if (deterministe) {
            set.contains("elem" + nbElem);
        } else {
            set.contains("elem" + rand);
        }
        long end = System.nanoTime();
        return end - start;
    }

    public double averageTimeHashSetCustom(int nbElem) {
        double totalTime = 0;

        for (int i = 0; i < 20; i++) {
            totalTime += hashSetTimeCustom(nbElem, false);
        }

        return totalTime / 20;
    }

    public double averageTimeHashSetStandard(int nbElem) {
        double totalTime = 0;

        for (int i = 0; i < 20; i++) {
            totalTime += hashSetTimeStandard(nbElem, false);
        }

        return totalTime / 20;
    }

    public void runTestsGraphique() {
        DefaultCategoryDataset barChartDataset = new DefaultCategoryDataset();
        XYSeries lineChartCustomSeries = new XYSeries("StringHashSet");
        XYSeries lineChartStandardSeries = new XYSeries("Standard HashSet");

        int[] sizes = { 100, 500, 1000, 10000, 25_000, 50_000, 100000, 150_000, 200_000, 250_000, 300_000, 350_000,
                400_000 };
        for (int size : sizes) {
            double averageCustomTime = averageTimeHashSetCustom(size);
            double averageStandardTime = averageTimeHashSetStandard(size);
            barChartDataset.addValue(averageCustomTime, "StringHashSet", "Size: " + size);
            barChartDataset.addValue(averageStandardTime, "Standard HashSet", "Size: " + size);
            lineChartCustomSeries.add(size, averageCustomTime);
            lineChartStandardSeries.add(size, averageStandardTime);
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Comparaison des performances HashSet (contains)",
                "Taille de la collection",
                "Temps d'exécution moyen (en ns)",
                barChartDataset);

        XYSeriesCollection lineChartDataset = new XYSeriesCollection();
        lineChartDataset.addSeries(lineChartCustomSeries);
        lineChartDataset.addSeries(lineChartStandardSeries);

        JFreeChart lineChart = ChartFactory.createXYLineChart(
                "Comparaison des performances HashSet (contains)",
                "Taille de la collection",
                "Temps d'exécution moyen (en ns)",
                lineChartDataset);

        ChartPanel barChartPanel = new ChartPanel(barChart);
        ChartPanel lineChartPanel = new ChartPanel(lineChart);

        JFrame frame = new JFrame("Comparaison des performances HashSet (contains)");
        frame.setLayout(new GridLayout(2, 1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(barChartPanel);
        frame.add(lineChartPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public void runTestsTableau() {
        int[] sizes = { 100, 500, 1000, 10000, 25_000, 50_000, 100000, 150_000, 200_000, 250_000, 300_000, 350_000,
            400_000 };
        Object[][] data = new Object[sizes.length][3];

        for (int i = 0; i < sizes.length; i++) {
            int size = sizes[i];
            data[i][0] = size;
            data[i][1] = averageTimeHashSetCustom(size);
            data[i][2] = averageTimeHashSetStandard(size);
        }

        String[] columnNames = { "Taille de la collection", "StringHashSet", "Standard HashSet" };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JFrame frame = new JFrame("Tableau de comparaison des performances HashSet (contains)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String... args) {
        //En nanosecondes
        TestPerformanceHashSetVersus test = new TestPerformanceHashSetVersus();
        test.runTestsGraphique();
        test.runTestsTableau();
    }
}

