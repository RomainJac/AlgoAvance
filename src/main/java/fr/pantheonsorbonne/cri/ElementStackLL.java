package fr.pantheonsorbonne.cri;

public class ElementStackLL<E> {
    private E value;
    private ElementStackLL<E> previous;
    public ElementStackLL() {
        value = null;
    }
    public ElementStackLL(E e) {
        value = e;
    }
    public E getValue() {
        return value;
    }
    public void setValue( E e) {
        value = e;
    }
    public ElementStackLL<E> getPrevious() {
        return previous;
    }
    public void setPrevious(ElementStackLL<E> e) {
        previous = e;
    }
}
