package fr.pantheonsorbonne.cri;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackLL<E> implements Iterable<E> {

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
        } else {
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

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            ElementStackLL<E> current = last;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (current == null) {
                    throw new NoSuchElementException();
                }
                E value = current.getValue();
                current = current.getPrevious();
                return value;
            }
        };
    }
}