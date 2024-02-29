package fr.pantheonsorbonne.cri;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeSetTest {

    @Test
    public void testCeiling() {
        ETreeSet<Integer> treeSet = new ETreeSet<>();
        treeSet.add(5);
        treeSet.add(10);
        treeSet.add(15);

        assertEquals(Integer.valueOf(10), treeSet.ceiling(8));
        assertEquals(Integer.valueOf(15), treeSet.ceiling(15));
        assertNull(treeSet.ceiling(20));
    }

    @Test
    public void testClear() {
        ETreeSet<Integer> treeSet = new ETreeSet<>();
        treeSet.add(5);
        treeSet.add(10);

        assertFalse(treeSet.isEmpty());
        treeSet.clear();
        assertTrue(treeSet.isEmpty());
    }

    @Test
    public void testContains() {
        ETreeSet<Integer> treeSet = new ETreeSet<>();
        treeSet.add(5);

        assertTrue(treeSet.contains(5));
        assertFalse(treeSet.contains(10));
    }

    @Test
    public void testRemove() {
        ETreeSet<Integer> treeSet = new ETreeSet<>();
        treeSet.add(5);
        treeSet.add(10);

        assertTrue(treeSet.remove(5));
        assertFalse(treeSet.contains(5));
        assertFalse(treeSet.remove(8));
    }

    @Test
    public void testRemoveRecursive() {
        ETreeSet<Integer> treeSet = new ETreeSet<>();
        treeSet.add(10);
        treeSet.add(5);
        treeSet.add(15);
        treeSet.add(3);
        treeSet.add(7);
        treeSet.add(12);
        treeSet.add(17);

        assertTrue(treeSet.contains(10));
        assertTrue(treeSet.contains(5));
        assertTrue(treeSet.contains(15));
        assertTrue(treeSet.contains(3));
        assertTrue(treeSet.contains(7));
        assertTrue(treeSet.contains(12));
        assertTrue(treeSet.contains(17));

        treeSet.remove(3);
        assertFalse(treeSet.contains(3));
        assertTrue(treeSet.contains(10));
        assertTrue(treeSet.contains(5));
        assertTrue(treeSet.contains(15));
        assertTrue(treeSet.contains(7));
        assertTrue(treeSet.contains(12));
        assertTrue(treeSet.contains(17));

        treeSet.remove(15);
        assertFalse(treeSet.contains(15));
        assertTrue(treeSet.contains(10));
        assertTrue(treeSet.contains(5));
        assertTrue(treeSet.contains(7));
        assertTrue(treeSet.contains(12));
        assertTrue(treeSet.contains(17));

        treeSet.remove(10);
        assertFalse(treeSet.contains(10));
        assertTrue(treeSet.contains(7));
        assertTrue(treeSet.contains(5));
        assertTrue(treeSet.contains(12));
        assertTrue(treeSet.contains(17));
    }

    @Test
    public void testSize() {
        ETreeSet<Integer> treeSet = new ETreeSet<>();
        treeSet.add(5);
        treeSet.add(10);
        treeSet.add(15);

        assertEquals(3, treeSet.size());
    }

    @Test
    public void testFirst() {
        ETreeSet<Integer> treeSet = new ETreeSet<>();
        treeSet.add(5);
        treeSet.add(10);
        treeSet.add(15);

        assertEquals(Integer.valueOf(5), treeSet.first());
    }

    @Test
    public void testIterator() {
        ETreeSet<Integer> treeSet = new ETreeSet<>();
        treeSet.add(5);
        treeSet.add(10);
        treeSet.add(15);

        Iterator<Integer> iterator = treeSet.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(5), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(10), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(15), iterator.next());
        assertFalse(iterator.hasNext());

        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void testCompareTo() {
        NodeTS<Integer> node1 = new NodeTS<>(5);
        NodeTS<Integer> node2 = new NodeTS<>(10);
        NodeTS<Integer> node3 = new NodeTS<>(5);

        assertTrue(node1.compareTo(node2) < 0);
        assertTrue(node2.compareTo(node1) > 0);
        assertEquals(0, node1.compareTo(node3));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    void testEquals() {
        NodeTS<String> node1 = new NodeTS<>("apple");
        NodeTS<String> node2 = new NodeTS<>("banana");
        NodeTS<String> node3 = new NodeTS<>("apple");
        NodeTS<String> node4 = null;
        String notNode = "apple";

        assertTrue(node1.equals(node3));
        assertFalse(node1.equals(node2));
        assertFalse(node1.equals(node4));
        assertFalse(node1.equals(notNode));
    }


    @Test
    public void testReequilibrerApresSuppression() {
        // Création d'un arbre rouge-noir pour tester la rééquilibrage après suppression
        NodeTS<Integer> root = new NodeTS<>(10);
        NodeTS<Integer> leftChild = new NodeTS<>(5);
        NodeTS<Integer> rightChild = new NodeTS<>(15);
        root.setLeft(leftChild);
        root.setRight(rightChild);

        leftChild.setColor(NodeTS.NOIR);
        rightChild.setColor(NodeTS.NOIR);

        NodeTS<Integer> leftGrandChild = new NodeTS<>(3);
        leftChild.setLeft(leftGrandChild);
        leftGrandChild.setColor(NodeTS.NOIR);

        // Vérifier si l'arbre est encore rouge-noir après la suppression
        assertTrue(isRedBlackTree(root));
    }

    // Méthode pour vérifier si un arbre binaire est un arbre rouge-noir
    private boolean isRedBlackTree(NodeTS<Integer> root) {
        if (root == null)
            return true;

        // Vérifier les propriétés de l'arbre rouge-noir
        if (root.getColor() == NodeTS.ROUGE) {
            if ((root.getLeft() != null && root.getLeft().getColor() == NodeTS.ROUGE) ||
                    (root.getRight() != null && root.getRight().getColor() == NodeTS.ROUGE)) {
                return false; // Si un nœud rouge a un enfant rouge, l'arbre n'est pas valide
            }
        }

        // Vérifier récursivement pour les sous-arbres
        return isRedBlackTree(root.getLeft()) && isRedBlackTree(root.getRight());
    }
}

