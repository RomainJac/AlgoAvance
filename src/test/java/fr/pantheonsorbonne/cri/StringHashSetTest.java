package fr.pantheonsorbonne.cri;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import fr.pantheonsorbonne.cri.StringHashSet.Couple;

public class StringHashSetTest {

    @Test
    public void testAddAndContains() {
        StringHashSet set = new StringHashSet();

        set.add("key1");
        set.add("key2");
        set.add("key3");

        assertTrue(set.contains("key1"));
        assertTrue(set.contains("key2"));
        assertTrue(set.contains("key3"));
        assertFalse(set.contains("key4"));
    }

    @Test
    public void testDuplicateAdd() {
        StringHashSet set = new StringHashSet();

        set.add("key1");
        set.add("key1");

        assertEquals(1, set.size);
    }


    @Test
    public void testGrow() {
        StringHashSet set = new StringHashSet();

        for (int i = 0; i < 17; i++) {
            set.add("key" + i);
        }

        assertEquals(32, set.buckets.length);
        for (int i = 0; i < 17; i++) {
            assertTrue(set.contains("key" + i));
        }
    }

    @Test
    public void testEmptySet() {
        StringHashSet set = new StringHashSet();

        assertFalse(set.contains("key1"));
    }

    @Test
    public void testCoupleEquals() {
        Couple couple1 = new Couple(123, "value");
        Couple couple2 = new Couple(123, "value");
        Couple couple3 = new Couple(456, "value");

        // Test reflexivity
        assertTrue(couple1.equals(couple1));

        // Test symmetry
        assertTrue(couple1.equals(couple2));
        assertTrue(couple2.equals(couple1));

        // Test transitivity
        assertTrue(couple1.equals(couple2));
        assertTrue(couple2.equals(couple3));
        assertTrue(couple1.equals(couple3));

        // Test for inequality
        assertFalse(couple1.equals(null));
        assertFalse(couple1.equals(new Object()));
        assertFalse(couple1.equals(new Couple(123, "different")));
    }

}
