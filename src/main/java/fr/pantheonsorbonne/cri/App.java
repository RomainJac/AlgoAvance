package fr.pantheonsorbonne.cri;

import java.util.Random;

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
        testContains(1000);
        testContains(10000);
        testContains(100000);
        testContains(1000000);
        testContains(10000000);
    }

    public static void testContains(int size) {
        ArrayListe list = new ArrayListe();
        Random random = new Random();

        // Remplissage de la liste avec des éléments aléatoires
        for (int i = 0; i < size; i++) {
            list.add("Element_" + random.nextInt()); // Utilisation de nextInt() pour obtenir un int aléatoire
        }

        // Recherche d'un élément aléatoire dans la liste
        String randomElement = "Element_" + random.nextInt();

        // Test de la méthode contains
        long startTime = System.nanoTime();
        boolean contains = list.contains(randomElement);
        long endTime = System.nanoTime();

        // Affichage des résultats
        System.out.println("Taille de la liste : " + size);
        System.out.println("La liste contient l'élément '" + randomElement + "' ? " + contains);
        System.out.println("Temps d'exécution pour contains : " + (endTime - startTime) + " ns");
        System.out.println();
    }

}
