package fr.pantheonsorbonne.cri;

public class App {
    public static void main(String[] args) {
            addElements(0);
            addElements(10);
            addElements(100);
            addElements(1000);
            addElements(100000);
            addElements(10000000);
        }
    
    public static void addElements(int numElements) {
        ArrayListe list = new ArrayListe();

        long startTime = System.nanoTime();
        for (int i = 0; i < numElements; i++) {
            list.add("Element " + i);
        }
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000000; 
        System.out.println("Ajout de " + numElements + " elements en " + duration + " milliseconds");
    }
    public static void addElementsLinked(int numElements) {
        
        LinkedListe list = new LinkedListe();

        long startTime = System.nanoTime();
        for (int i = 0; i < numElements; i++) {
            list.add("Element " + i);
        }
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000000; // Convert nanoseconds to milliseconds
        System.out.println("Ajout de " + numElements + " elements en " + duration + " milliseconds");
    }

    public static void addElementsTreeSet(int numElements) {
        ETreeSet<Integer> set = new ETreeSet<>();

        long startTime = System.nanoTime();
        for (int i = 0; i < numElements; i++) {
            set.add(i);
        }
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000000; // Convert nanoseconds to milliseconds
        System.out.println("Added " + numElements + " elements in " + duration + " milliseconds");
    }

}
