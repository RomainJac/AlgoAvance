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
