package fr.pantheonsorbonne.cri;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ETreeSet<E extends Comparable<E>> {
    private NodeTS<E> root;
    private int steps = 0;

    public NodeTS<E> getRoot() {
        return this.root;
    }

    public ETreeSet() {
        this.root = null;
    }

    public boolean add(E e) {
        System.out.println("\n\n");
        System.out.println(steps++ + "\n");
        printChildren(this.root);
        if (this.root == null) {
            this.root = new NodeTS<>(e, null, NodeTS.NOIR);
            return true;
        }
        insert(e);
        return true;

    }

    public void printChildren(NodeTS<E> node) {
        if (node != null) {
            System.out.println("Node: " + node.getElement());
            if (node.getLeft() != null) {
                System.out.println("Node: " + node.getElement());
                System.out.println("  Left child: " + node.getLeft().getElement());
                printChildren(node.getLeft());
            }
            if (node.getRight() != null) {
                System.out.println("Node: " + node.getElement());
                System.out.println("  Right child: " + node.getRight().getElement());
                printChildren(node.getRight());
            }
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void insert(E e) {
        NodeTS<E> nc = this.root;
        boolean trouve = false;
        while (!trouve) {
            int x = e.compareTo(nc.getElement());
            if (x == 0) {
                return;
            }
            if (x < 0) {
                if (nc.getLeft() != null) {
                    nc = nc.getLeft();
                } else {
                    nc.setLeft(new NodeTS(e, nc, NodeTS.ROUGE));
                    reequilibrerApresInsertion(nc.getLeft());
                    trouve = true;
                }
            } else {
                if (nc.getRight() != null) {
                    nc = nc.getRight();
                } else {
                    nc.setRight(new NodeTS(e, nc, NodeTS.ROUGE));
                    reequilibrerApresInsertion(nc.getRight());
                    trouve = true;
                }
            }

        }
    }

    private NodeTS<E> reequilibrerApresInsertion(NodeTS<E> n) {
        NodeTS<E> parent = n.getParent();
        while (parent != this.root) {
            parent = n.getParent();
            if (n.getColor() == NodeTS.NOIR) {
                return n;
            }
            if (parent == null) {
                // Le noeud est la racine de l'arbre
                return n;
            }
            if (parent.getColor() == NodeTS.NOIR) {
                // Si le parent est noir, l'arbre est toujours valide
                return n;
            }
            // Le parent est rouge
            NodeTS<E> grandParent = parent.getParent();
            NodeTS<E> oncle = parent == grandParent.getLeft() ? grandParent.getRight() : grandParent.getLeft();
            // Cas 1: l'oncle est rouge
            if (oncle != null && oncle.getColor() == NodeTS.ROUGE) {
                parent.setColor(NodeTS.NOIR);
                oncle.setColor(NodeTS.NOIR);
                grandParent.setColor(NodeTS.ROUGE);
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
            parent.setColor(NodeTS.NOIR);
            grandParent.setColor(NodeTS.ROUGE);
        }
        return n;
    }

    private void rotationGauche(NodeTS<E> n) {
        NodeTS<E> parent = n.getParent();
        NodeTS<E> rightChild = n.getRight();
        n.setRight(rightChild.getLeft());
        if (rightChild.getLeft() != null) {
            rightChild.getLeft().setParent(n);
        }
        rightChild.setParent(parent);
        if (parent == null) {
            this.root = rightChild;
        } else if (n == parent.getLeft()) {
            parent.setLeft(rightChild);
        } else {
            parent.setRight(rightChild);
        }
        rightChild.setLeft(n);
        n.setParent(rightChild);
    }

    public void rotationDroite(NodeTS<E> n) {
        NodeTS<E> parent = n.getParent();
        NodeTS<E> leftChild = n.getLeft();
        n.setLeft(leftChild.getRight());
        if (leftChild.getRight() != null) {
            leftChild.getRight().setParent(n);
        }
        leftChild.setParent(parent);
        if (parent == null) {
            this.root = leftChild;
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

    private E ceilingRecursive(E e, NodeTS<E> root) {
        if (root == null) {
            return null;
        }
        if (e.compareTo(root.getElement()) == 0) {
            return root.getElement();
        }
        if (e.compareTo(root.getElement()) > 0) {
            return ceilingRecursive(e, root.getRight());
        }
        if (e.compareTo(root.getElement()) < 0) {
            E tmp = ceilingRecursive(e, root.getLeft());
            return tmp != null ? tmp : root.getElement();
        }
        return null;
    }

    public void clear() {
        this.root = null;
    }

    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        E e = (E) o;
        return containsRecursive(e, this.root);
    }

    private boolean containsRecursive(E e, NodeTS<E> root) {
        if (root == null) {
            return false;
        }
        if (root.getElement().equals(e)) {
            return true;
        }
        if (e.compareTo(root.getElement()) > 0) {
            return containsRecursive(e, root.getRight());
        }
        if (e.compareTo(root.getElement()) < 0) {
            return containsRecursive(e, root.getLeft());
        }
        return false;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean remove(E e) {
        if (!contains(e)) {
            return false;
        }
        this.root = removeRecursive(e, root);
        return true;
    }

    private NodeTS<E> removeRecursive(E e, NodeTS<E> root) {
        if (root == null) {
            return null;
        }
        if (e.compareTo(root.getElement()) > 0) {
            root.setRight(removeRecursive(e, root.getRight()));
        } else if (e.compareTo(root.getElement()) < 0) {
            root.setLeft(removeRecursive(e, root.getLeft()));
        } else {
            // Node to be deleted found
            if (root.getLeft() != null && root.getRight() != null) {
                // Node with two children
                E minValue = findMinValue(root.getRight());
                root.setElement(minValue);
                root.setRight(removeRecursive(minValue, root.getRight()));
            } else {
                // Node with one child or no child
                NodeTS<E> child = root.getLeft() != null ? root.getLeft() : root.getRight();
                if (root.getColor() == NodeTS.NOIR && child != null && child.getColor() == NodeTS.ROUGE) {
                    // Case 1: Deleted node is black, and its child is red
                    child.setColor(NodeTS.NOIR);
                } else {
                    // Case 2: Deleted node is black, and its child is black or null
                    if (root == root.getParent().getLeft()) {
                        root.getParent().setLeft(child);
                    } else {
                        root.getParent().setRight(child);
                    }
                    if (child != null) {
                        child.setParent(root.getParent());
                    }
                    if (root.getColor() == NodeTS.NOIR) {
                        reequilibrerApresSuppression(child);
                    }
                }
                root = child;
            }
        }
        return root;
    }

    private void reequilibrerApresSuppression(NodeTS<E> n) {
        while (n != root && (n == null || n.getColor() == NodeTS.NOIR)) {
            // While n isn't the root and n isn't red
            NodeTS<E> sibling;
            if (n == n.getParent().getLeft()) {
                sibling = n.getParent().getRight();
                if (sibling != null && sibling.getColor() == NodeTS.ROUGE) {
                    // Case 1: Sibling is red
                    sibling.setColor(NodeTS.NOIR);
                    n.getParent().setColor(NodeTS.ROUGE);
                    rotationGauche(n.getParent());
                    sibling = n.getParent().getRight();
                }
                if (sibling != null) {
                    if ((sibling.getLeft() == null || sibling.getLeft().getColor() == NodeTS.NOIR) &&
                            (sibling.getRight() == null || sibling.getRight().getColor() == NodeTS.NOIR)) {
                        // Case 2: Sibling is black, and both its children are black or null
                        sibling.setColor(NodeTS.ROUGE);
                        n = n.getParent();
                    } else {
                        if (sibling.getRight() == null || sibling.getRight().getColor() == NodeTS.NOIR) {
                            // Case 3: Sibling is black, and its right child is black or null so its left
                            // child is red
                            sibling.getLeft().setColor(NodeTS.NOIR);
                            sibling.setColor(NodeTS.ROUGE);
                            rotationDroite(sibling);
                            sibling = n.getParent().getRight();
                        }
                        // Case 4: Sibling is black, and its right child is red
                        sibling.setColor(n.getParent().getColor());
                        n.getParent().setColor(NodeTS.NOIR);
                        sibling.getRight().setColor(NodeTS.NOIR);
                        rotationGauche(n.getParent());
                        n = root; // Exit the loop
                    }
                }
            } else {
                // Mirror cases for right child
                sibling = n.getParent().getLeft();
                if (sibling != null && sibling.getColor() == NodeTS.ROUGE) {
                    // Case 1: Sibling is red
                    sibling.setColor(NodeTS.NOIR);
                    n.getParent().setColor(NodeTS.ROUGE);
                    rotationDroite(n.getParent());
                    sibling = n.getParent().getLeft();
                }
                if (sibling != null) {
                    if ((sibling.getRight() == null || sibling.getRight().getColor() == NodeTS.NOIR) &&
                            (sibling.getLeft() == null || sibling.getLeft().getColor() == NodeTS.NOIR)) {
                        // Case 2: Sibling is black, and both its children are black or null
                        sibling.setColor(NodeTS.ROUGE);
                        n = n.getParent();
                    } else {
                        if (sibling.getLeft() == null || sibling.getLeft().getColor() == NodeTS.NOIR) {
                            // Case 3: Sibling is black, and left child is black or null so its right child
                            // is red
                            sibling.getRight().setColor(NodeTS.NOIR);
                            sibling.setColor(NodeTS.ROUGE);
                            rotationGauche(sibling);
                            sibling = n.getParent().getLeft();
                        }
                        // Case 4: Sibling is black, and its left child is red
                        sibling.setColor(n.getParent().getColor());
                        n.getParent().setColor(NodeTS.NOIR);
                        sibling.getLeft().setColor(NodeTS.NOIR);
                        rotationDroite(n.getParent());
                        n = root; // Exit the loop
                    }
                }
            }
        }
        if (n != null) {
            n.setColor(NodeTS.NOIR);
        }
    }

    private E findMinValue(NodeTS<E> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node.getElement();
    }

    public int size() {
        return sizeRecursive(root);
    }

    private int sizeRecursive(NodeTS<E> root) {
        if (root == null) {
            return 0;
        }
        return 1 + sizeRecursive(root.getLeft()) + sizeRecursive(root.getRight());
    }

    public E first() {
        if (root == null) {
            return null;
        }
        NodeTS<E> current = root;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current.getElement();
    }

    public Iterator<E> iterator() {
        return new TreeSetIterator(root);
    }

    private class TreeSetIterator implements Iterator<E> {
        private NodeTS<E> nextNode;

        public TreeSetIterator(NodeTS<E> root) {
            this.nextNode = root;
            while (nextNode != null && nextNode.getLeft() != null) {
                nextNode = nextNode.getLeft();
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
                NodeTS<E> current = root;
                NodeTS<E> successor = null;
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
    }
}
