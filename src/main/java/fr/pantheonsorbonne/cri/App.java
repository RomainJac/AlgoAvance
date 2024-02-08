package fr.pantheonsorbonne.cri;

public class App {
    public static void main(String[] args) {

        NoeudABR<Integer> node1 = new NoeudABR<>(5);
        NoeudABR<Integer> node2 = new NoeudABR<>(3);
        NoeudABR<Integer> node3 = new NoeudABR<>(8);


        node1.setLeft(node2);
        node1.setRight(node3);


        System.out.println("Node 1: " + node1);
        System.out.println("Node 2: " + node2);
        System.out.println("Node 3: " + node3);


        NoeudABR<Integer> node4 = new NoeudABR<>(6);
        System.out.println("Comparison result between node 3 and node 4: " + node3.compareTo(node4));


        NoeudABR<Integer> node5 = new NoeudABR<>(8);
        System.out.println("Equality check result between node 3 and node 5: " + node3.equals(node5));
    }
}
