package fr.pantheonsorbonne.cri;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeSetImpl<E extends Comparable<E>> {
    private NoeudABR<E> root;

    public TreeSetImpl() {
        this.root = null;
    }

    

    public boolean add(E e) {
        if (contains(e)) {
            return false;
        }
        root = addRecursive(e, root, null);
        return true;
    }
    
    private NoeudABR<E> addRecursive(E e, NoeudABR<E> n, NoeudABR<E> parent) {
        if (n == null) {
            n = new NoeudABR<>(e);
            n.setParent(parent); 
            return n;
        }
        if (e.compareTo(n.getElement()) > 0) {
            n.setRight(addRecursive(e, n.getRight(), n)); 
        } else if (e.compareTo(n.getElement()) < 0) {
            n.setLeft(addRecursive(e, n.getLeft(), n));
        }
        return n;
    }
    

    public E ceiling(E e) {
        return ceilingRecursive(e, root);
    }

    private E ceilingRecursive(E e, NoeudABR<E> root) {
        if (root == null) {
            return null;
        }
        if (root.getElement().compareTo(e) == 0) {
            return root.getElement();
        }
        if (root.getElement().compareTo(e) < 0 && root.getRight() != null) {
            return ceilingRecursive(e, root.getRight());
        }
        if (root.getElement().compareTo(e) < 0 && root.getRight() == null) {
            return null;
        }
        if (root.getElement().compareTo(e) > 0) {
            E tmp = ceilingRecursive(e, root.getLeft());
            return tmp != null ? tmp : root.getElement();
        }
        return null;
    }

    public void clear() {
        this.root = null;
    }

    public boolean contains(Object o) {
        if (o == null || !(o instanceof Comparable)) {
            return false;
        }
        @SuppressWarnings("unchecked")
        E e = (E) o;
        return containsRecursive(e, root);
    }

    private boolean containsRecursive(E e, NoeudABR<E> root) {
        if (root == null) {
            return false;
        }
        if (root.getElement().equals(e)) {
            return true;
        }
        if (root.getElement().compareTo(e) < 0) {
            return containsRecursive(e, root.getRight());
        }
        if (root.getElement().compareTo(e) > 0) {
            return containsRecursive(e, root.getLeft());
        }
        return false;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean remove(Object o) {
        if (!contains(o)) {
            return false;
        }
        @SuppressWarnings("unchecked")
        E e = (E) o;
        root = removeRecursive(e, root);
        return true;
    }

    private NoeudABR<E> removeRecursive(E e, NoeudABR<E> root) {
        if (root == null) {
            return null;
        }
        if (root.getElement().compareTo(e) == 0) {
            if (root.getLeft() == null) {
                return root.getRight();
            }
            if (root.getRight() == null) {
                return root.getLeft();
            }
            E tmp = root.getRight().getElement();
            root.setElement(tmp);
            root.setRight(removeRecursive(tmp, root.getRight()));
            return root;
        }
        if (root.getElement().compareTo(e) < 0) {
            root.setRight(removeRecursive(e, root.getRight()));
        } else {
            root.setLeft(removeRecursive(e, root.getLeft()));
        }
        return root;
    }

    public int size() {
        return sizeRecursive(root);
    }

    private int sizeRecursive(NoeudABR<E> root) {
        if (root == null) {
            return 0;
        }
        return 1 + sizeRecursive(root.getLeft()) + sizeRecursive(root.getRight());
    }

    public E first() {
        if (root == null) {
            return null;
        }
        NoeudABR<E> current = root;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current.getElement();
    }

    public Iterator<E> iterator() {
        return new TreeSetIterator();
    }

    private class TreeSetIterator implements Iterator<E> {
        private NoeudABR<E> nextNode;

        public TreeSetIterator() {
            this.nextNode = root;
            if (nextNode != null) {
                while (nextNode.getLeft() != null) {
                    nextNode = nextNode.getLeft();
                }
            }
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E result = nextNode.getElement();
            if (nextNode.getRight() != null) {
                nextNode = nextNode.getRight();
                while (nextNode.getLeft() != null) {
                    nextNode = nextNode.getLeft();
                }
            } else {
                NoeudABR<E> parent = nextNode.getParent();
                while (parent != null && nextNode == parent.getRight()) {
                    nextNode = parent;
                    parent = parent.getParent();
                }
                nextNode = parent;
            }
            return result;
        }
    }
}
