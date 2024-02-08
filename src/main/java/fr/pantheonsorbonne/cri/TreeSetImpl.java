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
        // Rééquilibrage de l'arbre pour respecter les règles des arbres rouges et noirs
        root.setColor(NoeudABR.NOIR); // La racine doit toujours être noire
        return true;
    }

    private NoeudABR<E> addRecursive(E e, NoeudABR<E> n, NoeudABR<E> parent) {
        if (n == null) {
            NoeudABR<E> newNode = new NoeudABR<>(e);
            newNode.setColor(NoeudABR.ROUGE); // Nouveau nœud inséré est rouge par défaut
            newNode.setParent(parent);
            return newNode;
        }
        int comparisonResult = e.compareTo(n.getElement());
        if (comparisonResult > 0) {
            n.setRight(addRecursive(e, n.getRight(), n));
        } else if (comparisonResult < 0) {
            n.setLeft(addRecursive(e, n.getLeft(), n));
        }
        // Rééquilibrage après insertion
        return reequilibrerApresInsertion(n);
    }

    private NoeudABR<E> reequilibrerApresInsertion(NoeudABR<E> n) {
        NoeudABR<E> parent = n.getParent();
        if (parent == null) {
            // Le noeud est la racine de l'arbre
            n.setColor(NoeudABR.NOIR); // Racine doit être noire
            return n;
        }
        if (parent.getColor() == NoeudABR.NOIR) {
            // Si le parent est noir, l'arbre est toujours valide
            return n;
        }
        // Le parent est rouge
        NoeudABR<E> grandParent = parent.getParent();
        NoeudABR<E> oncle = parent == grandParent.getLeft() ? grandParent.getRight() : grandParent.getLeft();
        if (oncle != null && oncle.getColor() == NoeudABR.ROUGE) {
            // Cas 1: l'oncle est rouge
            parent.setColor(NoeudABR.NOIR);
            oncle.setColor(NoeudABR.NOIR);
            grandParent.setColor(NoeudABR.ROUGE);
            return reequilibrerApresInsertion(grandParent);
        }
        // Cas 2: l'oncle est noir ou absent
        if (n == parent.getRight() && parent == grandParent.getLeft()) {
            // Rotation gauche-droite
            rotationGauche(parent);
            n = n.getLeft();
        } else if (n == parent.getLeft() && parent == grandParent.getRight()) {
            // Rotation droite-gauche
            rotationDroite(parent);
            n = n.getRight();
        }
        // Cas 3: rééquilibrage final
        parent = n.getParent();
        grandParent = parent.getParent();
        if (n == parent.getLeft()) {
            // Rotation droite
            rotationDroite(grandParent);
        } else {
            // Rotation gauche
            rotationGauche(grandParent);
        }
        parent.setColor(NoeudABR.NOIR);
        grandParent.setColor(NoeudABR.ROUGE);
        return n;
    }

    private void rotationGauche(NoeudABR<E> n) {
        NoeudABR<E> parent = n.getParent();
        NoeudABR<E> rightChild = n.getRight();
        n.setRight(rightChild.getLeft());
        if (rightChild.getLeft() != null) {
            rightChild.getLeft().setParent(n);
        }
        rightChild.setParent(parent);
        if (parent == null) {
            root = rightChild;
        } else if (n == parent.getLeft()) {
            parent.setLeft(rightChild);
        } else {
            parent.setRight(rightChild);
        }
        rightChild.setLeft(n);
        n.setParent(rightChild);
    }

    private void rotationDroite(NoeudABR<E> n) {
        NoeudABR<E> parent = n.getParent();
        NoeudABR<E> leftChild = n.getLeft();
        n.setLeft(leftChild.getRight());
        if (leftChild.getRight() != null) {
            leftChild.getRight().setParent(n);
        }
        leftChild.setParent(parent);
        if (parent == null) {
            root = leftChild;
        } else if (n == parent.getRight()) {
            parent.setRight(leftChild);
        } else {
            parent.setLeft(leftChild);
        }
        leftChild.setRight(n);
        n.setParent(leftChild);
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
            E minValue = findMinValue(root.getRight());
            root.setElement(minValue);
            root.setRight(removeRecursive(minValue, root.getRight()));
        } else if (root.getElement().compareTo(e) < 0) {
            root.setRight(removeRecursive(e, root.getRight()));
        } else {
            root.setLeft(removeRecursive(e, root.getLeft()));
        }
        return root;
    }

    private E findMinValue(NoeudABR<E> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node.getElement();
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
                NoeudABR<E> current = root;
                NoeudABR<E> successor = null;
                while (current != null && current != nextNode) {
                    if (nextNode.getElement().compareTo(current.getElement()) < 0) {
                        successor = current;
                        current = current.getLeft();
                    } else {
                        current = current.getRight();
                    }
                }
                nextNode = successor;
            }
            return result;
        }

        @Override
        public String toString() {
            return toStringRecursive(root);
        }

        private String toStringRecursive(NoeudABR<E> root) {
            if (root == null) {
                return "[]";
            }
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            toStringInOrder(root, sb);
            sb.delete(sb.length() - 2, sb.length());
            sb.append("]");
            return sb.toString();
        }

        private void toStringInOrder(NoeudABR<E> node, StringBuilder sb) {
            if (node != null) {
                toStringInOrder(node.getLeft(), sb);
                sb.append(node.getElement()).append(", ");
                toStringInOrder(node.getRight(), sb);
            }
        }

    }

}
