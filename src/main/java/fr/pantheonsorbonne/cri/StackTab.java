package fr.pantheonsorbonne.cri;

import java.util.Arrays;

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
        if (top == -1) {
            return null; 
        }
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
}
