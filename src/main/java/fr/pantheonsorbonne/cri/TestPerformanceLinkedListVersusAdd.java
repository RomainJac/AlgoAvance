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

import java.util.LinkedList;

public class TestPerformanceLinkedListVersusAdd {
    public double linkedListeTimeAdd(int nbElem) {
        LinkedListe list = new LinkedListe();
        long start = System.nanoTime();
        for (int i = 0; i < nbElem; i++) {
            list.add("elem" + i);
        }
        long end = System.nanoTime();
        return end - start;
    }

    public double linkedListTimeAdd(int nbElem) {
        LinkedList<String> list = new LinkedList<>();
        long start = System.nanoTime();
        for (int i = 0; i < nbElem; i++) {
            list.add("elem" + i);
        }
        long end = System.nanoTime();
        return end - start;
    }

    public double averageTimeLinkedListeAdd(int nbElem) {
        double totalTime = 0;

        for (int i = 0; i < 20; i++) {
            totalTime += linkedListeTimeAdd(nbElem);
        }

        return totalTime / 20;
    }

    public double averageTimeLinkedListAdd(int nbElem) {
        double totalTime = 0;

        for (int i = 0; i < 20; i++) {
            totalTime += linkedListTimeAdd(nbElem);
        }

        return totalTime / 20;
    }

    public void runTestsGraphique() {
        DefaultCategoryDataset barChartDataset = new DefaultCategoryDataset();
        XYSeries lineChartLinkedListeSeries = new XYSeries("LinkedListe");
        XYSeries lineChartLinkedListSeries = new XYSeries("LinkedList");

        int[] sizes = { 100, 500, 1000, 10000, 25000, 50000, 100000, 150000, 200000, 250000, 300000, 350000, 400000 };
        for (int size : sizes) {
            double averageLinkedListeTime = averageTimeLinkedListeAdd(size);
            double averageLinkedListTime = averageTimeLinkedListAdd(size);
            barChartDataset.addValue(averageLinkedListeTime, "LinkedListe", "Size: " + size);
            barChartDataset.addValue(averageLinkedListTime, "LinkedList", "Size: " + size);
            lineChartLinkedListeSeries.add(size, averageLinkedListeTime);
            lineChartLinkedListSeries.add(size, averageLinkedListTime);
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Comparaison des performances (add) - LinkedListe vs LinkedList",
                "Taille de la collection",
                "Temps d'exécution moyen (en ns)",
                barChartDataset);

        XYSeriesCollection lineChartDataset = new XYSeriesCollection();
        lineChartDataset.addSeries(lineChartLinkedListeSeries);
        lineChartDataset.addSeries(lineChartLinkedListSeries);

        JFreeChart lineChart = ChartFactory.createXYLineChart(
                "Comparaison des performances (add) - LinkedListe vs LinkedList",
                "Taille de la collection",
                "Temps d'exécution moyen (en ns)",
                lineChartDataset);

        ChartPanel barChartPanel = new ChartPanel(barChart);
        ChartPanel lineChartPanel = new ChartPanel(lineChart);

        JFrame frame = new JFrame("Comparaison des performances (add) - LinkedListe vs LinkedList");
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
            data[i][1] = averageTimeLinkedListeAdd(size);
            data[i][2] = averageTimeLinkedListAdd(size);
        }

        String[] columnNames = { "Taille de la collection", "LinkedListe", "LinkedList" };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JFrame frame = new JFrame("Tableau de comparaison des performances (add) - LinkedListe vs LinkedList");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String... args) {
        // En nanosecondes
        TestPerformanceLinkedListVersusAdd test = new TestPerformanceLinkedListVersusAdd();
        test.runTestsGraphique();
        test.runTestsTableau();
    }
}
