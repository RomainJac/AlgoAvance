package fr.pantheonsorbonne.cri;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

public class LinkedListeTest {

    @Test
    public void testAddFirst() {
        LinkedListe list = new LinkedListe();
        list.addFirst("Element1");
        assertEquals("[Element1]", list.toString());
        list.addFirst("Element2");
        assertEquals("[Element2, Element1]", list.toString());
    }

    @Test
    public void testadd() {
        LinkedListe list = new LinkedListe();
        list.add("Element1");
        assertEquals("[Element1]", list.toString());
        list.add("Element2");
        assertEquals("[Element1, Element2]", list.toString());
    }

    @Test
    public void testAdd() {
        LinkedListe list = new LinkedListe();
        list.addFirst("Element1");
        list.add("Element3");
        list.add(1, "Element2");
        assertEquals("[Element1, Element2, Element3]", list.toString());
    }

    @Test
    public void testremove() {
        LinkedListe list = new LinkedListe();
        list.add("Element1");
        list.add("Element2");
        list.remove();
        assertEquals("[Element2]", list.toString());
        list.remove();
        assertEquals("[]", list.toString());
    }

    @Test
    public void testRemoveLast() {
        LinkedListe list = new LinkedListe();
        list.add("Element1");
        list.add("Element2");
        assertEquals("Element2", list.removeLast());
        assertEquals("[Element1]", list.toString());
        assertEquals("Element1", list.removeLast());
        assertEquals("[]", list.toString());
    }

    @Test
    public void testGet() {
        LinkedListe list = new LinkedListe();
        list.add("Element1");
        list.add("Element2");
        list.add("Element3");
        assertEquals("Element2", list.get(1));
    }

    @Test
    public void testGetFirst() {
        LinkedListe list = new LinkedListe();
        list.add("Element1");
        list.add("Element2");
        assertEquals("Element1", list.getFirst());
    }

    @Test
    public void testGetLast() {
        LinkedListe list = new LinkedListe();
        list.add("Element1");
        list.add("Element2");
        assertEquals("Element2", list.getLast());
    }

    @Test
    public void testContains() {
        LinkedListe list = new LinkedListe();
        list.add("Element1");
        list.add("Element2");
        assertTrue(list.contains("Element1"));
        assertFalse(list.contains("Element3"));
    }

    @Test
    public void testClear() {
        LinkedListe list = new LinkedListe();
        list.add("Element1");
        list.add("Element2");
        list.clear();
        assertEquals("[]", list.toString());
    }

    @Test
    public void testSize() {
        LinkedListe list = new LinkedListe();
        list.add("Element1");
        list.add("Element2");
        assertEquals(2, list.size());
        list.remove();
        assertEquals(1, list.size());
    }

    @Test
    public void testIterator() {
        LinkedListe list = new LinkedListe();
        list.add("Element1");
        list.add("Element2");

        StringBuilder result = new StringBuilder();
        for (String element : list) {
            result.append(element).append(" ");
        }

        assertEquals("Element1 Element2 ", result.toString());
    }

    @Test
    public void testRemove() {
        LinkedListe list = new LinkedListe();
        list.add("Element1");
        list.add("Element2");
        list.add("Element3");

        assertEquals("Element2", list.remove(1));
        assertEquals("[Element1, Element3]", list.toString());

        assertEquals("Element1", list.remove(0));
        assertEquals("[Element3]", list.toString());

        assertEquals("Element3", list.remove(0));
        assertEquals("[]", list.toString());
    }

    @Test
    public void testAddAtIndex() {
        LinkedListe list = new LinkedListe();
        list.add("Element1");
        list.add("Element3");

        list.add(1, "Element2");
        assertEquals("[Element1, Element2, Element3]", list.toString());

        list.add(0, "NewElement");
        assertEquals("[NewElement, Element1, Element2, Element3]", list.toString());

        list.add(4, "LastElement");
        assertEquals("[NewElement, Element1, Element2, Element3, LastElement]", list.toString());
    }

    @Test
    void testOfferWithEmptyList() {
        LinkedListe list = new LinkedListe();
        assertTrue(list.offer("Element1"));
        assertEquals("Element1", list.getFirst()); // Assurez-vous d'avoir une méthode getFirst() dans votre classe
        assertEquals("Element1", list.getLast()); // Assurez-vous d'avoir une méthode getLast() dans votre classe
    }
    @Test
    void testContainsRec() {
        LinkedListe list = new LinkedListe();
        list.add("Element1");
        list.add("Element2");
        assertTrue(list.containsRecursif("Element1"));
        assertFalse(list.containsRecursif("Element3"));
    }
    @Test
    void testAddRec() {
        LinkedListe list = new LinkedListe();
        list.add("Element1");
        list.add("Element2");
        assertTrue(list.add("Element3"));
        assertTrue(list.containsRecursif("Element3"));
    }
    @Test
    void testOfferWithNonEmptyList() {
        LinkedListe list = new LinkedListe();
        list.offer("Element1");
        assertTrue(list.offer("Element2"));
        assertEquals("Element1", list.getFirst());
        assertEquals("Element2", list.getLast());
    }

    @Test
    void testPollWithEmptyList() {
        LinkedListe list = new LinkedListe();
        assertThrows(NoSuchElementException.class, () -> list.poll());
    }

    @Test
    void testPollWithNonEmptyList() {
        LinkedListe list = new LinkedListe();
        list.add("Element1");
        assertEquals("Element1", list.poll());
        assertTrue(list.isEmpty());
    }

    @Test
    void testSet() {
        LinkedListe list = new LinkedListe();
        list.add("Element1");
        list.add("Element2");
        list.set(0, "NewElement");
        assertEquals("NewElement", list.getFirst());
    }

    @Test
    void testPush() {
        LinkedListe list = new LinkedListe();
        list.push("Element1");
        assertFalse(list.isEmpty());
        assertEquals("Element1", list.getFirst());
    }

    @Test
    void testIsEmptyWithEmptyList() {
        LinkedListe list = new LinkedListe();
        assertTrue(list.isEmpty());
    }

    @Test
    void testIsEmptyWithNonEmptyList() {
        LinkedListe list = new LinkedListe();
        list.add("Element1");
        assertFalse(list.isEmpty());
    }

}
