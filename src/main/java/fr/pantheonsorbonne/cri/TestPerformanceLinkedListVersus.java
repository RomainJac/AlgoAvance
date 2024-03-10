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
import java.util.LinkedList;
import java.util.TreeSet;

public class TestPerformanceLinkedListVersus {
    public double linkedListeTime(int nbElem, boolean deterministe) {
        LinkedListe list = new LinkedListe();
        for (int i = 0; i < nbElem; i++) {
            list.add("elem" + i);
        }
        int rand = (int) (Math.random() * nbElem);
        long start = System.nanoTime();
        if (deterministe) {
            list.contains("elem" + nbElem);
        } else {
            list.contains("elem" + rand);
        }
        long end = System.nanoTime();
        return end - start;
    }

    public double linkedListTime(int nbElem, boolean deterministe) {
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < nbElem; i++) {
            list.add("elem" + i);
        }
        int rand = (int) (Math.random() * nbElem);
        long start = System.nanoTime();
        if (deterministe) {
            list.contains("elem" + nbElem);
        } else {
            list.contains("elem" + rand);
        }
        long end = System.nanoTime();
        return end - start;
    }

    public double averageTimeLinkedListe(int nbElem) {
        double totalTime = 0;

        for (int i = 0; i < 20; i++) {
            totalTime += linkedListeTime(nbElem, false);
        }

        return totalTime / 20;
    }

    public double averageTimeLinkedList(int nbElem) {
        double totalTime = 0;

        for (int i = 0; i < 20; i++) {
            totalTime += linkedListTime(nbElem, false);
        }

        return totalTime / 20;
    }

    public void runTestsGraphique() {
        DefaultCategoryDataset barChartDataset = new DefaultCategoryDataset();
        XYSeries lineChartLinkedListeSeries = new XYSeries("LinkedListe");
        XYSeries lineChartLinkedListSeries = new XYSeries("LinkedList");

        int[] sizes = { 100, 500, 1000, 10000, 25000, 50000, 100000, 150000, 200000, 250000, 300000, 350000, 400000 };
        for (int size : sizes) {
            double averageLinkedListeTime = averageTimeLinkedListe(size);
            double averageLinkedListTime = averageTimeLinkedList(size);
            barChartDataset.addValue(averageLinkedListeTime, "LinkedListe", "Size: " + size);
            barChartDataset.addValue(averageLinkedListTime, "LinkedList", "Size: " + size);
            lineChartLinkedListeSeries.add(size, averageLinkedListeTime);
            lineChartLinkedListSeries.add(size, averageLinkedListTime);
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Comparaison des performances (contains) - LinkedListe vs LinkedList",
                "Taille de la collection",
                "Temps d'exécution moyen (en ns)",
                barChartDataset);

        XYSeriesCollection lineChartDataset = new XYSeriesCollection();
        lineChartDataset.addSeries(lineChartLinkedListeSeries);
        lineChartDataset.addSeries(lineChartLinkedListSeries);

        JFreeChart lineChart = ChartFactory.createXYLineChart(
                "Comparaison des performances (contains) - LinkedListe vs LinkedList",
                "Taille de la collection",
                "Temps d'exécution moyen (en ns)",
                lineChartDataset);

        ChartPanel barChartPanel = new ChartPanel(barChart);
        ChartPanel lineChartPanel = new ChartPanel(lineChart);

        JFrame frame = new JFrame("Comparaison des performances (contains) - LinkedListe vs LinkedList");
        frame.setLayout(new GridLayout(2, 1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(barChartPanel);
        frame.add(lineChartPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public void runTestsTableau() {
        int[] sizes = { 100, 500, 1000, 10000, 25000, 50000, 100000, 150000, 200000, 250000, 300000, 350000, 400000 };
        Object[][] data = new Object[sizes.length][3];

        for (int i = 0; i < sizes.length; i++) {
            int size = sizes[i];
            data[i][0] = size;
            data[i][1] = averageTimeLinkedListe(size);
            data[i][2] = averageTimeLinkedList(size);
        }

        String[] columnNames = { "Taille de la collection", "LinkedListe", "LinkedList" };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JFrame frame = new JFrame("Tableau de comparaison des performances (contains) - LinkedListe vs LinkedList");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String... args) {
        // En nanosecondes
        TestPerformanceLinkedListVersus test = new TestPerformanceLinkedListVersus();
        test.runTestsGraphique();
        test.runTestsTableau();
    }
}
