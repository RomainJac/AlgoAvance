package fr.pantheonsorbonne.cri;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayStringStack implements Iterable<String> {
    private String[] array;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayStringStack() {
        array = new String[DEFAULT_CAPACITY];
        size = 0;
    }

    public boolean empty() {
        return size == 0;
    }

    public String peek() {
        if (empty()) {
            throw new EmptyStackException();
        }
        return array[size - 1];
    }

    public String pop() {
        if (empty()) {
            throw new EmptyStackException();
        }
        String item = array[--size];
        array[size] = null; // Remove reference to the popped element
        return item;
    }

    public String push(String item) {
        if (size == array.length) {
            resizeArray();
        }
        array[size++] = item;
        return item;
    }

    private void resizeArray() {
        int newSize = array.length * 2;
        String[] newArray = new String[newSize];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    @Override
    public Iterator<String> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<String> {
        private int currentIndex = size - 1;

        @Override
        public boolean hasNext() {
            return currentIndex >= 0;
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return array[currentIndex--];
        }
    }
}
