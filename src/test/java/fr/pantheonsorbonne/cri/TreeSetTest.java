package fr.pantheonsorbonne.cri;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeSetTest {

    @Test
    public void testAdd() {
        TreeSetImpl<Integer> treeSet = new TreeSetImpl<>();
        assertTrue(treeSet.add(5));
        assertTrue(treeSet.add(3));
        assertFalse(treeSet.add(5)); 
    }

    @Test
    public void testCeiling() {
        TreeSetImpl<Integer> treeSet = new TreeSetImpl<>();
        treeSet.add(5);
        treeSet.add(10);
        treeSet.add(15);

        assertEquals(Integer.valueOf(10), treeSet.ceiling(8));
        assertEquals(Integer.valueOf(15), treeSet.ceiling(15));
        assertNull(treeSet.ceiling(20));
    }

    @Test
    public void testClear() {
        TreeSetImpl<Integer> treeSet = new TreeSetImpl<>();
        treeSet.add(5);
        treeSet.add(10);

        assertFalse(treeSet.isEmpty());
        treeSet.clear();
        assertTrue(treeSet.isEmpty());
    }

    @Test
    public void testContains() {
        TreeSetImpl<Integer> treeSet = new TreeSetImpl<>();
        treeSet.add(5);

        assertTrue(treeSet.contains(5));
        assertFalse(treeSet.contains(10));
    }

    @Test
    public void testRemove() {
        TreeSetImpl<Integer> treeSet = new TreeSetImpl<>();
        treeSet.add(5);
        treeSet.add(10);

        assertTrue(treeSet.remove(5));
        assertFalse(treeSet.contains(5));
        assertFalse(treeSet.remove(8));
    }

    @Test
    public void testRemoveRecursive() {
        TreeSetImpl<Integer> treeSet = new TreeSetImpl<>();
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
        TreeSetImpl<Integer> treeSet = new TreeSetImpl<>();
        treeSet.add(5);
        treeSet.add(10);
        treeSet.add(15);

        assertEquals(3, treeSet.size());
    }

    @Test
    public void testFirst() {
        TreeSetImpl<Integer> treeSet = new TreeSetImpl<>();
        treeSet.add(5);
        treeSet.add(10);
        treeSet.add(15);

        assertEquals(Integer.valueOf(5), treeSet.first());
    }

    @Test
    public void testIterator() {
        TreeSetImpl<Integer> treeSet = new TreeSetImpl<>();
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
}
