package fr.pantheonsorbonne.cri;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.Arrays;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/*
 * Les méthodes utilisent Random, sauf si on lui passe true en argument, dans ce cas là on
 * cherche le dernier élément ajouté.
 * 
 */
public class TestPerformance {

    public double hashSetTime(int nbElem, boolean deterministe) {
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
        return (end - start);
    }

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
        return (end - start);
    }

    public double treeSetTime(int nbElem, boolean deterministe) {
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

    /*public static void main(String... args) {
        //En nanosecondes
        TestPerformance test = new TestPerformance();
        System.out.println("hashset: " + test.hashSetTime(1000000, true));
        System.out.println("hashet: " + test.hashSetTime(100000, true));
        System.out.println("hashet: " + test.hashSetTime(10000, true));
        System.out.println("linkedListe: " + test.linkedListeTime(1000000, true));
        System.out.println("linkedListe: " + test.linkedListeTime(100000, true));
        System.out.println("linkedListe: " + test.linkedListeTime(10000, true));
        System.out.println("treeSet: " + test.treeSetTime(1000000, true));
        System.out.println("treeSet: " + test.treeSetTime(100000, true));
        System.out.println("treeSet: " + test.treeSetTime(10000, true));
    }*/
    public void runTestsGraphique() {
        int[] sizes = {100, 10000, 100000, 1000000};
        boolean[] deterministes = {true, false};

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int size : sizes) {
            for (boolean deterministe : deterministes) {
                double hashSetTime = hashSetTime(size, deterministe);
                double linkedListTime = linkedListeTime(size, deterministe);
                double treeSetTime = treeSetTime(size, deterministe);

                dataset.addValue(hashSetTime, "HashSet", "Size: " + size);
                dataset.addValue(linkedListTime, "LinkedList", "Size: " + size);
                dataset.addValue(treeSetTime, "TreeSet", "Size: " + size);
            }
        }

        // Create and show the chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Comparaison des performances",
                "Cas de test",
                "Temps d'execution en ns", // Updated label
                dataset
        );
        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame frame = new JFrame("Comparaison des performances");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(chartPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
        public void runTestsCourbe() {
        int[] sizes = {100, 10_000, 100_000, 500_000, 1_000_000};
        boolean[] deterministes = {true, false};

        XYSeries hashSetSeries = new XYSeries("HashSet");
        XYSeries linkedListSeries = new XYSeries("LinkedList");
        XYSeries treeSetSeries = new XYSeries("TreeSet");

        for (int size : sizes) {
            for (boolean deterministe : deterministes) {
                double hashSetTime = hashSetTime(size, deterministe);
                double linkedListTime = linkedListeTime(size, deterministe);
                double treeSetTime = treeSetTime(size, deterministe);

                hashSetSeries.add(size, hashSetTime);
                linkedListSeries.add(size, linkedListTime);
                treeSetSeries.add(size, treeSetTime);
            }
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(hashSetSeries);
        dataset.addSeries(linkedListSeries);
        dataset.addSeries(treeSetSeries);

        JFreeChart chart = ChartFactory.createXYLineChart(
            "Comparaison des performances",
            "Cas de test",
            "Temps d'execution en ns",
                dataset
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        JFrame frame = new JFrame("Comparaison des performances");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(chartPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public void runTestsTableau() {
        int[] sizes = {10000, 100000, 1000000};
        boolean[] deterministes = {true, false};

        // Création du tableau de données pour le modèle de tableau
        Object[][] data = new Object[sizes.length * deterministes.length][5];
        int rowIndex = 0;
        for (int size : sizes) {
            for (boolean deterministe : deterministes) {
                data[rowIndex][0] = size;
                data[rowIndex][1] = deterministe;
                data[rowIndex][2] = hashSetTime(size, deterministe);
                data[rowIndex][3] = linkedListeTime(size, deterministe);
                data[rowIndex][4] = treeSetTime(size, deterministe);
                rowIndex++;
            }
        }

        // Création des noms de colonnes
        String[] columnNames = {"Size", "Deterministic", "HashSet", "LinkedList", "TreeSet"};

        // Création du modèle de tableau avec les données
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // Création du tableau avec le modèle de tableau
        JTable table = new JTable(model);

        // Création du panneau de tableau
        JScrollPane scrollPane = new JScrollPane(table);

        // Création de la fenêtre
        JFrame frame = new JFrame("Tableau de comparaison des performances");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String... args) {
        TestPerformance test = new TestPerformance();
        test.runTestsGraphique();

        TestPerformance testCourbe = new TestPerformance();
        testCourbe.runTestsCourbe();

        TestPerformance testTableau = new TestPerformance();
        testTableau.runTestsTableau();
    }
}

