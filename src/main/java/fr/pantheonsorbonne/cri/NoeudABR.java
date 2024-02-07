package fr.pantheonsorbonne.cri;

public class NoeudABR<E extends Comparable<E>> implements Comparable<NoeudABR<E>> {
    private E element;
    private NoeudABR<E> left;
    private NoeudABR<E> right;
    private NoeudABR<E> parent;

    public NoeudABR(E e) {
        this.element = e;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public E getElement() {
        return this.element;
    }

    @Override
    public int compareTo(NoeudABR<E> e) {
        if (e == null) {
            return 1;
        }
        return this.element.compareTo(e.getElement());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        NoeudABR<?> noeudABR = (NoeudABR<?>) o;

        return element != null ? element.equals(noeudABR.element) : noeudABR.element == null;
    }

    @Override
    public int hashCode() {
        return element != null ? element.hashCode() : 0;
    }

    public NoeudABR<E> getLeft() {
        return this.left;
    }

    public NoeudABR<E> getRight() {
        return this.right;
    }

    public void setLeft(NoeudABR<E> n) {
        this.left = n;
    }

    public void setRight(NoeudABR<E> n) {
        this.right = n;
    }

    public void setElement(E e) {
        this.element = e;
    }

    public NoeudABR<E> getParent() {
        return parent;
    }

    public void setParent(NoeudABR<E> parent) {
        this.parent = parent;
    }
}
