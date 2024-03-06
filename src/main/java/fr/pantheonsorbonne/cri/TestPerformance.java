package fr.pantheonsorbonne.cri;

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
        return end - start;
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
        return end - start;

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

<<<<<<< HEAD
=======
    public double averageTime(int nbElem) {
        double totalTime = 0;

        for (int i = 0; i < 20; i++) {
            totalTime += linkedListeTime(nbElem, false);
        }

        return totalTime / 20;
    }

    public double averageTimeHashSet(int nbElem) {
        double totalTime = 0;

        for (int i = 0; i < 20; i++) {
            totalTime += hashSetTime(nbElem, false);
        }

        return totalTime / 20;
    }

    public double averageTimeTreeSet(int nbElem) {
        double totalTime = 0;

        for (int i = 0; i < 20; i++) {
            totalTime += treeSetTime(nbElem, false);
        }

        return totalTime / 20;
    }

    public void runTestsGraphique() {
        DefaultCategoryDataset barChartDataset = new DefaultCategoryDataset();
        XYSeries lineChartLinkedListSeries = new XYSeries("LinkedList");
        XYSeries lineChartHashSetSeries = new XYSeries("HashSet");
        XYSeries lineChartTreeSetSeries = new XYSeries("TreeSet");

        int[] sizes = { 100, 500, 1000, 10000, 25_000, 50_000, 100000, 150_000, 200_000, 250_000, 300_000, 350_000,
                400_000 };
        for (int size : sizes) {
            double averageLinkedListTime = averageTime(size);
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
                "Comparaison des performances (contains)",
                "Taille de la collection",
                "Temps d'exécution moyen (en ns)",
                barChartDataset);

        XYSeriesCollection lineChartDataset = new XYSeriesCollection();
        lineChartDataset.addSeries(lineChartLinkedListSeries);
        lineChartDataset.addSeries(lineChartHashSetSeries);
        lineChartDataset.addSeries(lineChartTreeSetSeries);

        JFreeChart lineChart = ChartFactory.createXYLineChart(
                "Comparaison des performances (contains)",
                "Taille de la collection",
                "Temps d'exécution moyen (en ns)",
                lineChartDataset);

        ChartPanel barChartPanel = new ChartPanel(barChart);
        ChartPanel lineChartPanel = new ChartPanel(lineChart);

        JFrame frame = new JFrame("Comparaison des performances (contains)");
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
        Object[][] data = new Object[sizes.length][4];

        for (int i = 0; i < sizes.length; i++) {
            int size = sizes[i];
            data[i][0] = size;
            data[i][1] = averageTime(size);
            data[i][2] = averageTimeHashSet(size);
            data[i][3] = averageTimeTreeSet(size);
        }

        String[] columnNames = { "Taille de la collection", "LinkedList", "HashSet", "TreeSet" };

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JFrame frame = new JFrame("Tableau de comparaison des performances (contains)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

>>>>>>> adf8c2637585bd292de1afa65844035d8973cc77
    public static void main(String... args) {
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
    }


}
