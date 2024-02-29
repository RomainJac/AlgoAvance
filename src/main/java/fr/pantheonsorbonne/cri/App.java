import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.DefaultXYDataset;

import javax.swing.*;
import java.awt.*;

public class ChartApp extends JFrame {
    private DefaultXYDataset dataset;

    public ChartApp(String title) {
        super(title);

        dataset = new DefaultXYDataset();
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Temps d'exécution de la méthode contains",
                "Taille de la liste",
                "Temps d'exécution (ms)",
                dataset
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        getContentPane().add(chartPanel);
    }

    public void addData(double[][] data, String seriesName) {
        dataset.addSeries(seriesName, data);
    }

    public static void main(String[] args) {
        ChartApp chartApp = new ChartApp("Performance test");
        chartApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Ajoutez les données de performance
        chartApp.addData(createData(1000), "1000");
        chartApp.addData(createData(10000), "10000");
        chartApp.addData(createData(100000), "100000");
        chartApp.addData(createData(1000000), "1000000");

        chartApp.pack();
        chartApp.setVisible(true);
    }

    private static double[][] createData(int size) {
        ArrayListe list = new ArrayListe();
        Random random = new Random();
        double[][] data = new double[2][size];

        for (int i = 0; i < size; i++) {
            list.add("Element_" + random.nextInt());
        }

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            list.contains("Element_" + random.nextInt());
        }
        long endTime = System.currentTimeMillis();

        data[0][0] = size;
        data[1][0] = endTime - startTime;

        return data;
    }
}
