package fr.pantheonsorbonne.cri;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

public class ArrayListeTest {
    private ArrayListe myArrayListe;

    @BeforeEach
    public void setUp() {
        myArrayListe = new ArrayListe();
    }

    @Test
    public void testAddAndGet() {
        myArrayListe.add("Apple");
        myArrayListe.add("Banana");
        myArrayListe.add("Cherry");

        assertEquals("Apple", myArrayListe.get(0));
        assertEquals("Banana", myArrayListe.get(1));
        assertEquals("Cherry", myArrayListe.get(2));
    }

    @Test
    public void testAddAtIndex() {
        myArrayListe.add("Apple");
        myArrayListe.add("Cherry");
        myArrayListe.add(1, "Banana");

        assertEquals("Apple", myArrayListe.get(0));
        assertEquals("Banana", myArrayListe.get(1));
        assertEquals("Cherry", myArrayListe.get(2));
    }

    @Test
    public void testAddAtIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            myArrayListe.add("Apple");
            myArrayListe.add(2, "Banana");
        });
    }

    @Test
    public void testContains() {
        myArrayListe.add("Apple");
        myArrayListe.add("Banana");

        assertTrue(myArrayListe.contains("Apple"));
        assertTrue(myArrayListe.contains("Banana"));
        assertFalse(myArrayListe.contains("Cherry"));
    }

    @Test
    public void testEnsureCapacity() {
        for (int i = 0; i < 15; i++) {
            myArrayListe.add("Element" + i);
        }

        assertEquals(15, myArrayListe.size);
        assertTrue(myArrayListe.data.length >= 15);
    }

    @Test
    public void testIndexOf() {
        myArrayListe.add("Apple");
        myArrayListe.add("Banana");

        assertEquals(0, myArrayListe.indexOf("Apple"));
        assertEquals(1, myArrayListe.indexOf("Banana"));
        assertEquals(-1, myArrayListe.indexOf("Cherry"));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(myArrayListe.isEmpty());

        myArrayListe.add("Apple");
        assertFalse(myArrayListe.isEmpty());
    }

    @Test
    public void testRemove() {
        myArrayListe.add("Apple");
        myArrayListe.add("Banana");
        myArrayListe.add("Cherry");

        assertEquals("Banana", myArrayListe.remove(1));
        assertEquals(2, myArrayListe.size);
        assertEquals("Cherry", myArrayListe.get(1));
    }

    @Test
    public void testRemoveOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            myArrayListe.remove(0);
        });
    }

    @Test
    public void testSet() {
        myArrayListe.add("Apple");
        myArrayListe.add("Banana");

        assertEquals("Apple", myArrayListe.set(0, "Orange"));
        assertEquals("Orange", myArrayListe.get(0));
    }

    @Test
    public void testSetOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            myArrayListe.set(0, "Orange");
        });
    }

    @Test
    public void testClear() {
        myArrayListe.add("Apple");
        myArrayListe.add("Banana");
        myArrayListe.clear();

        assertTrue(myArrayListe.isEmpty());
        assertEquals(0, myArrayListe.size);
        assertEquals(5, myArrayListe.data.length);
    }

    @Test
    public void testIterator() {
        myArrayListe.add("Apple");
        myArrayListe.add("Banana");
        myArrayListe.add("Cherry");

        Iterator<String> iterator = myArrayListe.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Apple", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("Banana", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("Cherry", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testToStringEmptyList() {
        assertEquals("[]", myArrayListe.toString());
    }

    @Test
    public void testToStringNonEmptyList() {
        myArrayListe.add("Apple");
        myArrayListe.add("Banana");
        myArrayListe.add("Cherry");

        assertEquals("[Apple, Banana, Cherry]", myArrayListe.toString());
    }

    @Test
    public void testToStringSingleElementList() {
        myArrayListe.add("Apple");

        assertEquals("[Apple]", myArrayListe.toString());
    }

    @Test
    public void testToStringWithNumbers() {
        myArrayListe.add("One");
        myArrayListe.add("Two");
        myArrayListe.add("Three");

        assertEquals("[One, Two, Three]", myArrayListe.toString());
    }

}