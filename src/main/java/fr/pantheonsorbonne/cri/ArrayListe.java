package fr.pantheonsorbonne.cri;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayListe implements Iterable<String> {
    public String[] data;
    public int size;

    public ArrayListe() {
        data = new String[10];
        size = 0;
    }

    public boolean add(String element) {
        ensureCapacity(size + 1);
        data[size++] = element;
        return true;
    }

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

    public void add(int index, String element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        ensureCapacity(size + 1);

        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

    public boolean contains(String element) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > data.length) {
            int newCapacity = Math.max(data.length * 2, minCapacity);
            data = Arrays.copyOf(data, newCapacity);
        }
    }

    public String get(int index) {
        return data[index];
    }

    public int indexOf(String element) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        String removedElement = data[index];

        System.arraycopy(data, index + 1, data, index, size - index - 1);

        size--;
        return removedElement;
    }

    public String set(int index, String element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        String oldValue = data[index];
        data[index] = element;
        return oldValue;
    }

    public void clear() {
        data = new String[5];
        size = 0;
    }

    @Override
    public Iterator<String> iterator() {
        return new ArrayListeIterator();
    }

    private class ArrayListeIterator implements Iterator<String> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return data[currentIndex++];
        }
    }
}
