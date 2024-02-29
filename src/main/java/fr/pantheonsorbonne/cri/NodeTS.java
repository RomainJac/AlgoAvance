package fr.pantheonsorbonne.cri;

public class NodeTS<E extends Comparable<E>> implements Comparable<NodeTS<E>> {
    private E element;
    private NodeTS<E> left;
    private NodeTS<E> right;
    private NodeTS<E> parent;
    private boolean color;
    public static final boolean ROUGE = false;
    public static final boolean NOIR = true;

    public NodeTS(E data) {
        this.element = data;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.color = ROUGE;
    }

    public NodeTS(E e, NodeTS<E> parent, boolean color) {
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
    public int compareTo(NodeTS<E> e) {
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

        NodeTS<?> noeudABR = (NodeTS<?>) o;

        return element != null ? element.equals(noeudABR.element) : noeudABR.element == null;
    }

    @Override
    public int hashCode() {
        return element != null ? element.hashCode() : 0;
    }

    public NodeTS<E> getLeft() {
        return this.left;
    }

    public NodeTS<E> getRight() {
        return this.right;
    }

    public void setLeft(NodeTS<E> n) {
        this.left = n;
    }

    public void setRight(NodeTS<E> n) {
        this.right = n;
    }

    public void setElement(E e) {
        this.element = e;
    }

    public NodeTS<E> getParent() {
        return this.parent;
    }

    public void setParent(NodeTS<E> n) {
        this.parent = n;
    }

    public boolean getColor() {
        return this.color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

}