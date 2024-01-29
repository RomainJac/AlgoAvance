package fr.pantheonsorbonne.cri;

public class App {

    public static void main(String[] args) {
        LinkedListe Link = new LinkedListe();

        Link.addFirst("Premier");
        Link.add("Deuxième");
        Link.add("Troisième");

        System.out.println("Liste actuelle : " + Link);

        Link.addFirst("Nouveau Premier");

        System.out.println("Liste après ajout au début : " + Link);

        Link.add("Dernier");

        System.out.println("Liste après ajout à la fin : " + Link);
    }
}
