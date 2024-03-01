package fr.pantheonsorbonne.cri;

public class NoeudABR<E extends Comparable<E>> implements Comparable<NoeudABR<E>> {
    private E element;
    private NoeudABR<E> left;
    private NoeudABR<E> right;
    private NoeudABR<E> parent;
    private boolean color;

    public static final boolean ROUGE = false;
    public static final boolean NOIR = true;

    public NoeudABR(E e) {
        this.element = e;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.color = ROUGE;
    }

    public NoeudABR(E e, NoeudABR<E> parent, boolean color) {
        this.element = e;
        this.left = null;
        this.right = null;
        this.parent = parent;
        this.color = color;
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
        return this.parent;
    }

    public void setParent(NoeudABR<E> n) {
        this.parent = n;
    }

    public boolean getColor() {
        return this.color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }
    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }
}
