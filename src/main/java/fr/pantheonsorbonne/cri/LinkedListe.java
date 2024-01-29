package fr.pantheonsorbonne.cri;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListe implements Iterable<String> {

    private Noeud premier;
    private Noeud dernier;

    public LinkedListe() {
        premier = null;
        dernier = null;
    }

    public void addFirst(String contenu) {
        Noeud nouveauNoeud = new Noeud(contenu, premier);
        premier = nouveauNoeud;
        if (dernier == null) {
            dernier = premier;
        }
    }

    public boolean add(String contenu) {
        Noeud nouveauNoeud = new Noeud(contenu, null);
        if (premier == null) {
            premier = nouveauNoeud;
            dernier = premier;
        } else {
            dernier.setNextNoeud(nouveauNoeud);
            dernier = nouveauNoeud;
        }
        return true;
    }

    public void add(int index, String contenu) {
        if (index < 0) {
            throw new IllegalArgumentException("L'index ne peut pas être négatif : " + index);
        }

        Noeud nouveauNoeud = new Noeud(contenu, null);

        if (index == 0) {
            nouveauNoeud.setNextNoeud(premier);
            premier = nouveauNoeud;
            if (dernier == null) {
                dernier = premier;
            }
        } else {
            Noeud precedent = getIndexNoeud(index - 1);
            if (precedent == null) {
                throw new IndexOutOfBoundsException("Index en dehors des limites : " + index);
            }
            nouveauNoeud.setNextNoeud(precedent.getNextNoeud());
            precedent.setNextNoeud(nouveauNoeud);
            if (nouveauNoeud.getNextNoeud() == null) {
                dernier = nouveauNoeud;
            }
        }
    }

    public void remove() {
        if (premier != null) {
            premier = premier.getNextNoeud();
            if (premier == null) {
                dernier = null;
            }
        }
    }

    public String removeLast() {
        if (premier == null) {
            throw new NoSuchElementException("La liste est vide.");
        }

        String contenu;

        if (premier == dernier) {
            contenu = premier.getContenu();
            premier = dernier = null;
        } else {
            Noeud avantDernier = premier;
            while (avantDernier.getNextNoeud() != dernier) {
                avantDernier = avantDernier.getNextNoeud();
            }

            contenu = dernier.getContenu();
            avantDernier.setNextNoeud(null);
            dernier = avantDernier;
        }

        return contenu;
    }

    private Noeud getIndexNoeud(int index) {
        Noeud courant = premier;
        for (int i = 0; i < index; i++) {
            if (courant == null) {
                throw new IndexOutOfBoundsException("Index en dehors des limites");
            }
            courant = courant.getNextNoeud();
        }
        return courant;
    }

    public String remove(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("L'index ne peut pas être négatif : " + index);
        }

        String contenu;

        if (index == 0) {
            if (premier == null) {
                throw new IndexOutOfBoundsException("Index en dehors des limites : " + index);
            }
            contenu = premier.getContenu();
            premier = premier.getNextNoeud();
            if (premier == null) {
                dernier = null;
            }
        } else {
            Noeud precedent = getIndexNoeud(index - 1);
            if (precedent == null || precedent.getNextNoeud() == null) {
                throw new IndexOutOfBoundsException("Index en dehors des limites : " + index);
            }
            contenu = precedent.getNextNoeud().getContenu();
            precedent.setNextNoeud(precedent.getNextNoeud().getNextNoeud());
            if (precedent.getNextNoeud() == null) {
                dernier = precedent;
            }
        }

        return contenu;
    }

    public String get(int index) {
        Noeud noeud = getIndexNoeud(index);
        if (noeud == null) {
            throw new IndexOutOfBoundsException("Index en dehors des limites : " + index);
        }
        return noeud.getContenu();
    }

    public String getFirst() {
        if (premier == null) {
            throw new NoSuchElementException("La liste est vide.");
        }
        return premier.getContenu();
    }

    public String getLast() {
        if (dernier == null) {
            throw new NoSuchElementException("La liste est vide.");
        }
        return dernier.getContenu();
    }

    public boolean contains(Object element) {
        Noeud courant = premier;
        while (courant != null) {
            if (courant.getContenu().equals(element)) {
                return true;
            }
            courant = courant.getNextNoeud();
        }
        return false;
    }

    public void clear() {
        premier = dernier = null;
    }

    public int size() {
        int count = 0;
        Noeud courant = premier;
        while (courant != null) {
            count++;
            courant = courant.getNextNoeud();
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Noeud courant = premier;
        while (courant != null) {
            result.append(courant.getContenu());
            if (courant.getNextNoeud() != null) {
                result.append(", ");
            }
            courant = courant.getNextNoeud();
        }
        result.append("]");
        return result.toString();
    }

    public boolean offer(String element) {
        Noeud nouveauNoeud = new Noeud(element, null);
        if (premier == null) {
            premier = nouveauNoeud;
            dernier = premier;
        } else {
            dernier.setNextNoeud(nouveauNoeud);
            dernier = nouveauNoeud;
        }
        return true;
    }

    @Override
    public Iterator<String> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<String> {
        private Noeud current = premier;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            String content = current.getContenu();
            current = current.getNextNoeud();
            return content;
        }
    }

    public String poll() {
        if (premier == null) {
            throw new NoSuchElementException("La liste est vide.");
        }
        String contenu = premier.getContenu();
        this.premier = this.premier.getNextNoeud();
        return contenu;
    }

    public void set(int index, String element) {
        Noeud courant = premier;
        for (int i = 0; i < index; i++) {
            courant = courant.getNextNoeud();
        }
        courant.setContenu(element);
    }

    public void push(String s) {
        this.addFirst(s);
    }

    public boolean isEmpty() {
        return premier == null;
    }

}
