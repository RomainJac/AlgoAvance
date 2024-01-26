package fr.pantheonsorbonne.cri;

public class App {

    public static void main(String[] args) {
        LinkedListe Link = new LinkedListe();

        Link.addLast("Premier");
        Link.addLast("Deuxième");
        Link.addLast("Troisième");

        System.out.println("Liste actuelle : " + Link);

        Link.addFirst("Nouveau Premier");

        System.out.println("Liste après ajout au début : " + Link);

        Link.addLast("Dernier");

        System.out.println("Liste après ajout à la fin : " + Link);
    }
}
