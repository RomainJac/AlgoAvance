package fr.pantheonsorbonne.cri;

public class MainStack {
    public static void main(String[] args) {
        // Test de l'implémentation avec tableau
        ArrayStringStack arrayStack = new ArrayStringStack();
        arrayStack.push("A");
        arrayStack.push("B");
        arrayStack.push("C");

        System.out.println("Parcours de la pile avec tableau :");
        for (String item : arrayStack) {
            System.out.println(item);
        }

        // Test de l'implémentation avec liste chaînée
        LinkedStringStack linkedStack = new LinkedStringStack();
        linkedStack.push("X");
        linkedStack.push("Y");
        linkedStack.push("Z");

        System.out.println("\nParcours de la pile avec liste chaînée :");
        for (String item : linkedStack) {
            System.out.println(item);
        }
    }
}
