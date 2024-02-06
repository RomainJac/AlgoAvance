package fr.pantheonsorbonne.cri;

public class Noeud {
    public String contenu;
    public Noeud suivant;

    public Noeud(String c, Noeud suivant) {
        this.contenu = c;
        this.suivant = suivant;

    }

    public String getContenu() {
        return this.contenu;
    }

    public void setContenu(String c) {
        this.contenu = c;
    }

    public Noeud getNextNoeud() {
        return this.suivant;
    }

    public void setNextNoeud(Noeud suivant) {
        this.suivant = suivant;
    }
    public boolean contains(String element) {
        if (this.contenu.equals(element)) {
            return true;
        }
        if (this.suivant == null) {
            return false;
        }
        return this.suivant.contains(element);
    }
}
