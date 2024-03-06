package fr.pantheonsorbonne.cri;

import java.util.LinkedList;

public class StackLL<E> {

    private ElementStackLL<E> last;
    public StackLL() {
        this.last = null;
    }
    public StackLL(E e) {
        ElementStackLL<E> element = new ElementStackLL<>();
        element.setValue(e);
        this.last = element;
    }
    public void push(E e) {
        if (this.isEmpty()) {
            this.last = new ElementStackLL<>(e);
        }
        else {
            ElementStackLL<E> element = new ElementStackLL<>(e);
            element.setPrevious(this.last);
            this.last = element;
        }
    }
    public E pop() {
        if (this.isEmpty()) {
            return null;
        }
        E value = last.getValue();
        last = last.getPrevious();
        return value;
    }
    public E peek() {
        if (this.isEmpty()) {
            return null;
        }
        return last.getValue();
    }
    public boolean isEmpty() {
        return last == null;
    }
}

