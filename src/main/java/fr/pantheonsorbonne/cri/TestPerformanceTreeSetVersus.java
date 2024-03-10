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

import java.util.TreeSet;

public class TestPerformanceTreeSetVersus {

    public double treeSetTime(int nbElem, boolean deterministe) {
        TreeSet<String> set = new TreeSet<>();
        for (int i = 0; i < nbElem; i++) {
            set.add("elem" + i);
        }
        int rand = (int) (Math.random() * nbElem);
        double start = System.nanoTime();
        if (deterministe) {
            set.contains("elem" + nbElem);
        } else {
            set.contains("elem" + rand);
        }
        double end = System.nanoTime();
        return end - start;
    }

    public double eTreeSetTime(int nbElem, boolean deterministe) {
        ETreeSet<String> set = new ETreeSet<>();
        for (int i = 0; i < nbElem; i++) {
            set.add("elem" + i);
        }
        int rand = (int) (Math.random() * nbElem);
        double start = System.nanoTime();
        if (deterministe) {
            set.contains("elem" + nbElem);
        } else {
            set.contains("elem" + rand);
        }
        double end = System.nanoTime();
        return end - start;
    }

    public double averageTimeTreeSet(int nbElem) {
        double totalTime = 0;

        for (int i = 0; i < 20; i++) {
            totalTime += treeSetTime(nbElem, false);
        }

        return totalTime / 20;
    }

    public double averageTimeETreeSet(int nbElem) {
        double totalTime = 0;

        for (int i = 0; i < 20; i++) {
            totalTime += eTreeSetTime(nbElem, false);
        }

        return totalTime / 20;
    }

    public void runTestsGraphique() {
        DefaultCategoryDataset barChartDataset = new DefaultCategoryDataset();
        XYSeries lineChartTreeSetSeries = new XYSeries("TreeSet");
        XYSeries lineChartETreeSetSeries = new XYSeries("ETreeSet");

        int[] sizes = { 100, 500, 1000, 10000, 25000, 50000, 100000, 150000, 200000, 250000, 300000, 350000, 400000 };
        for (int size : sizes) {
            double averageTreeSetTime = averageTimeTreeSet(size);
            double averageETreeSetTime = averageTimeETreeSet(size);
            barChartDataset.addValue(averageTreeSetTime, "TreeSet", "Size: " + size);
            barChartDataset.addValue(averageETreeSetTime, "ETreeSet", "Size: " + size);
            lineChartTreeSetSeries.add(size, averageTreeSetTime);
            lineChartETreeSetSeries.add(size, averageETreeSetTime);
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Comparaison des performances (TreeSet versus ETreeSet)",
                "Taille de la collection",
                "Temps d'exécution moyen (en ns)",
                barChartDataset);

        XYSeriesCollection lineChartDataset = new XYSeriesCollection();
        lineChartDataset.addSeries(lineChartTreeSetSeries);
        lineChartDataset.addSeries(lineChartETreeSetSeries);

        JFreeChart lineChart = ChartFactory.createXYLineChart(
                "Comparaison des performances (TreeSet versus ETreeSet)",
                "Taille de la collection",
                "Temps d'exécution moyen (en ns)",
                lineChartDataset);

        ChartPanel barChartPanel = new ChartPanel(barChart);
        ChartPanel lineChartPanel = new ChartPanel(lineChart);

        JFrame frame = new JFrame("Comparaison des performances (TreeSet versus ETreeSet)");
        frame.setLayout(new GridLayout(2, 1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(barChartPanel);
        frame.add(lineChartPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public void runTestsTableau() {
        int[] sizes = { 100, 500, 1000, 10000, 25000, 50000, 100000, 150000, 200000, 250000, 300000, 350000, 400000 };
        Object[][] data = new Object[sizes.length][4];

        for (int i = 0; i < sizes.length; i++) {
            int size = sizes[i];
            data[i][0] = size;
            data[i][1] = averageTimeTreeSet(size);
            data[i][2] = averageTimeETreeSet(size);
        }

        String[] columnNames = { "Taille de la collection", "TreeSet", "ETreeSet" };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JFrame frame = new JFrame("Tableau de comparaison des performances (TreeSet versus ETreeSet)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String... args) {
        // En nanosecondes
        TestPerformanceTreeSetVersus test = new TestPerformanceTreeSetVersus();
        test.runTestsGraphique();
        test.runTestsTableau();
    }
}
