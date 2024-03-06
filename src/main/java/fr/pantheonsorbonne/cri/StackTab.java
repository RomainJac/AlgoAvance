package fr.pantheonsorbonne.cri;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackTab<E> {

    private Object[] tab;
    private int top;
    private int capacity;

    public StackTab(int initialCapacity) {
        this.capacity = initialCapacity;
        tab = new Object[initialCapacity];
        top = -1;
    }

    public void push(E elem) {
        if (top == capacity - 1) {
            resize(capacity * 2);
        }
        tab[++top] = elem;
    }

    @SuppressWarnings("unchecked")
    public E pop() {
        E elem = (E) tab[top];
        tab[top--] = null;
        return elem;
    }

    @SuppressWarnings("unchecked")
    public E peek() {
        if (top == -1) {
            return null;
        }
        return (E) tab[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    private void resize(int newCapacity) {
        tab = Arrays.copyOf(tab, newCapacity);
        capacity = newCapacity;
    }

    @SuppressWarnings("unused")
    Iterator<E> Iterator() {
        return (Iterator<E>) new StackIterator();
    }

    private class StackIterator implements Iterator<E> {
        private int index = top;

        public boolean hasNext() {
            return index >= 0;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (E) tab[index--];
        }
    }
}
