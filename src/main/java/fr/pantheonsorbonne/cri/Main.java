package fr.pantheonsorbonne.cri;

public class Main {
    public static void main(String[] args) {
        
        String a = "toto";
        String b = "toto";
        LinkedListe list = new LinkedListe();
        list.push(a);
        System.out.println(list.contains(b));
    }
}
