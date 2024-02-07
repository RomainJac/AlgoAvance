package fr.pantheonsorbonne.cri;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayListe implements Iterable<String> {
    public String[] data;
    public int size;

    // Complexité : O(1)
    public ArrayListe() {
        data = new String[10];
        size = 0;
    }

    // Méthode pour ajouter un élément à la liste
    // Complexité : O(n) (amorti), où n est la taille actuelle du tableau
    public boolean add(String element) {
        ensureCapacity(size + 1);
        data[size++] = element;
        return true;
    }

    // Méthode pour représenter la liste sous forme de chaîne de caractères
    // Complexité : O(n), où n est la taille du tableau
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            result.append(data[i]);
            if (i < size - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }

    // Méthode pour ajouter un élément à une position spécifique dans la liste
    // Complexité : O(n), où n est la taille actuelle du tableau
    public void add(int index, String element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Taille: " + size);
        }

        ensureCapacity(size + 1);

        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

    // Méthode pour vérifier si la liste contient un élément donné
    // Complexité : O(n), où n est la taille du tableau
    public boolean contains(String element) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    // Méthode pour garantir une capacité minimale du tableau
    // Complexité : O(1) (amorti), où n est la taille du tableau
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > data.length) {
            int newCapacity = Math.max(data.length * 2, minCapacity);
            data = Arrays.copyOf(data, newCapacity);
        }
    }

    // Méthode pour obtenir l'élément à une position spécifique dans la liste
    // Complexité : O(1)
    public String get(int index) {
        return data[index];
    }

    // Méthode pour obtenir l'indice de la première occurrence d'un élément donné
    // Complexité : O(n), où n est la taille du tableau
    public int indexOf(String element) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    // Méthode pour vérifier si la liste est vide
    // Complexité : O(1)
    public boolean isEmpty() {
        return size == 0;
    }

    // Méthode pour supprimer l'élément à une position spécifique dans la liste
    // Complexité : O(n), où n est la taille actuelle du tableau
    public String remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Taille: " + size);
        }

        String removedElement = data[index];

        System.arraycopy(data, index + 1, data, index, size - index - 1);

        size--;
        return removedElement;
    }

    // Méthode pour remplacer l'élément à une position spécifique dans la liste
    // Complexité : O(1)
    public String set(int index, String element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Taille: " + size);
        }

        String oldValue = data[index];
        data[index] = element;
        return oldValue;
    }

    // Méthode pour vider la liste
    // Complexité : O(1)
    public void clear() {
        data = new String[5];
        size = 0;
    }

    // Méthode de l'interface Iterable
    // Complexité : O(1)
    @Override
    public Iterator<String> iterator() {
        return new ArrayListeIterator();
    }

    // Classe interne pour implémenter l'interface Iterator
    private class ArrayListeIterator implements Iterator<String> {
        private int currentIndex = 0;

        // Méthode pour vérifier s'il y a un élément suivant dans l'itérateur
        // Complexité : O(1)
        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        // Méthode pour obtenir l'élément suivant dans l'itérateur
        // Complexité temporelle : O(1)
        @Override
        public String next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return data[currentIndex++];
        }
    }
}
