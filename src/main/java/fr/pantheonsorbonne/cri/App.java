package fr.pantheonsorbonne.cri;

public class App {
    public static void main(String[] args) {
        StringHashSet set = new StringHashSet();

        set.add("apple", "red");
        set.add("banana", "yellow");
        set.add("grape", "purple");

        System.out.println("Contains apple? " + set.contains("apple")); // Devrait afficher true
        System.out.println("Contains banana? " + set.contains("banana")); // Devrait afficher true
        System.out.println("Contains grape? " + set.contains("grape")); // Devrait afficher true
        System.out.println("Contains orange? " + set.contains("orange")); // Devrait afficher false

        set.add("apple", "green");

    }

}
