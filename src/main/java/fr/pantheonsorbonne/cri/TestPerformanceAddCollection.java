package fr.pantheonsorbonne.cri;

import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.TreeSet;

public class TestPerformanceAddCollection {

    public double linkedListTime(int nbElem) {
        LinkedList<String> list = new LinkedList<>();
        long start = System.nanoTime();
        for (int i = 0; i < nbElem; i++) {
            list.add("elem" + i);
        }
        long end = System.nanoTime();
        return end - start;
    }

    public double hashSetTime(int nbElem) {
        HashSet<String> set = new HashSet<>();
        long start = System.nanoTime();
        for (int i = 0; i < nbElem; i++) {
            set.add("elem" + i);
        }
        long end = System.nanoTime();
        return end - start;
    }

    public double treeSetTime(int nbElem) {
        TreeSet<String> set = new TreeSet<>();
        double start = System.nanoTime();
        for (int i = 0; i < nbElem; i++) {
            set.add("elem" + i);
        }
        double end = System.nanoTime();
        return end - start;
    }

    public double averageTimeLinkedList(int nbElem) {
        double totalTime = 0;

        for (int i = 0; i < 20; i++) {
            totalTime += linkedListTime(nbElem);
        }

        return totalTime / 20;
    }

    public double averageTimeHashSet(int nbElem) {
        double totalTime = 0;

        for (int i = 0; i < 20; i++) {
            totalTime += hashSetTime(nbElem);
        }

        return totalTime / 20;
    }

    public double averageTimeTreeSet(int nbElem) {
        double totalTime = 0;

        for (int i = 0; i < 20; i++) {
            totalTime += treeSetTime(nbElem);
        }

        return totalTime / 20;
    }

    public void runTestsGraphiqueAddCollection() {
        DefaultCategoryDataset barChartDataset = new DefaultCategoryDataset();
        XYSeries lineChartLinkedListSeries = new XYSeries("LinkedList");
        XYSeries lineChartHashSetSeries = new XYSeries("HashSet");
        XYSeries lineChartTreeSetSeries = new XYSeries("TreeSet");

        int[] sizes = { 100, 500, 1000, 10000, 25_000, 50_000, 100000, 150_000, 200_000, 250_000, 300_000, 350_000,
            400_000 };
        for (int size : sizes) {
            double averageLinkedListTime = averageTimeLinkedList(size);
            double averageHashSetTime = averageTimeHashSet(size);
            double averageTreeSetTime = averageTimeTreeSet(size);
            barChartDataset.addValue(averageLinkedListTime, "LinkedList", "Size: " + size);
            barChartDataset.addValue(averageHashSetTime, "HashSet", "Size: " + size);
            barChartDataset.addValue(averageTreeSetTime, "TreeSet", "Size: " + size);
            lineChartLinkedListSeries.add(size, averageLinkedListTime);
            lineChartHashSetSeries.add(size, averageHashSetTime);
            lineChartTreeSetSeries.add(size, averageTreeSetTime);
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Comparaison des performances (Méthode add)",
                "Taille de la collection",
                "Temps d'exécution moyen (en ns)",
                barChartDataset
        );

        XYSeriesCollection lineChartDataset = new XYSeriesCollection();
        lineChartDataset.addSeries(lineChartLinkedListSeries);
        lineChartDataset.addSeries(lineChartHashSetSeries);
        lineChartDataset.addSeries(lineChartTreeSetSeries);

        JFreeChart lineChart = ChartFactory.createXYLineChart(
                "Comparaison des performances (Méthode add)",
                "Taille de la collection",
                "Temps d'exécution moyen (en ns)",
                lineChartDataset
        );

        ChartPanel barChartPanel = new ChartPanel(barChart);
        ChartPanel lineChartPanel = new ChartPanel(lineChart);

        JFrame frame = new JFrame("Comparaison des performances (Méthode add)");
        frame.setLayout(new GridLayout(2, 1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(barChartPanel);

        JFrame lineChartFrame = new JFrame("Comparaison des performances (Méthode add)");
        lineChartFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lineChartFrame.add(lineChartPanel);

        frame.pack();
        lineChartFrame.pack();
        frame.setVisible(true);
        lineChartFrame.setVisible(true);
    }

    public void runTestsTableauAddCollection() {
        int[] sizes = { 100, 500, 1000, 10000, 25_000, 50_000, 100000, 150_000, 200_000, 250_000, 300_000, 350_000,
            400_000 };
        Object[][] data = new Object[sizes.length][4];

        for (int i = 0; i < sizes.length; i++) {
            int size = sizes[i];
            data[i][0] = size;
            data[i][1] = averageTimeLinkedList(size);
            data[i][2] = averageTimeHashSet(size);
            data[i][3] = averageTimeTreeSet(size);
        }

        String[] columnNames = {"Taille de la collection", "LinkedList", "HashSet", "TreeSet"};

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JFrame frame = new JFrame("Tableau de comparaison des performances (Méthode add)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String... args) {
         TestPerformanceAddCollection testAdd = new TestPerformanceAddCollection();
         testAdd.runTestsGraphiqueAddCollection();
         testAdd.runTestsTableauAddCollection();
     }
}

