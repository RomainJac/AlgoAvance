package fr.pantheonsorbonne.cri;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringHashSetTest {

    @Test
    public void testAddAndContains() {
        StringHashSet set = new StringHashSet();

        set.add("key1", "value1");
        set.add("key2", "value2");
        set.add("key3", "value3");

        assertTrue(set.contains("key1"));
        assertTrue(set.contains("key2"));
        assertTrue(set.contains("key3"));
        assertFalse(set.contains("key4")); 
    }

    @Test
    public void testDuplicateAdd() {
        StringHashSet set = new StringHashSet();

        set.add("key1", "value1");
        set.add("key1", "value2");

        assertEquals(1, set.size);
    }

    @Test
    public void testGrow() {
        StringHashSet set = new StringHashSet();

        for (int i = 0; i < 17; i++) {
            set.add("key" + i, "value" + i);
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

   
}
