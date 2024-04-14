package fr.pantheonsorbonne.cri;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStringStack implements Iterable<String>{
    private Node top;

    public LinkedStringStack() {
        top = null;
    }

    public boolean empty() {
        return top == null;
    }

    public String peek() {
        if (empty()) {
            throw new EmptyStackException();
        }
        return top.data;
    }

    public String pop() {
        if (empty()) {
            throw new EmptyStackException();
        }
        String item = top.data;
        top = top.next;
        return item;
    }

    public String push(String item) {
        Node newNode = new Node(item);
        newNode.next = top;
        top = newNode;
        return item;
    }

    private class Node {
        String data;
        Node next;

        Node(String data) {
            this.data = data;
            next = null;
        }
    }
    @Override
    public Iterator<String> iterator() {
        return new LinkedIterator();
    }

    private class LinkedIterator implements Iterator<String> {
        private Node current = top;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            String data = current.data;
            current = current.next;
            return data;
        }
    }
}

