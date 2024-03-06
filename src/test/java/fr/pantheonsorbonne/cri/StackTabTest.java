package fr.pantheonsorbonne.cri;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackTabTest {

    @Test
    void testPushAndPop() {
        StackTab<Integer> stack = new StackTab<>(3);
        assertTrue(stack.isEmpty());

        stack.push(1);
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.peek());

        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.peek());

        stack.push(4);
        assertEquals(4, stack.peek());

        assertEquals(4, stack.pop());
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void testPopWithNegativeOne() {
        StackTab<Integer> stack = new StackTab<>(5);

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(-1);
        stack.push(5);

        assertEquals(5, stack.pop());
        assertEquals(-1, stack.pop());
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());

        assertTrue(stack.isEmpty());
    }

    @Test
    void testPeek() {
        StackTab<String> stack = new StackTab<>(2);
        assertNull(stack.peek());

        stack.push("a");
        assertEquals("a", stack.peek());

        stack.push("b");
        assertEquals("b", stack.peek());

        stack.pop();
        assertEquals("a", stack.peek());

        stack.pop();
        assertNull(stack.peek());
    }

    @Test
    void testIsEmpty() {
        StackTab<Double> stack = new StackTab<>(3);
        assertTrue(stack.isEmpty());

        stack.push(3.14);
        assertFalse(stack.isEmpty());

        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    void testIterator() {
        StackTab<Integer> stack = new StackTab<>(5);

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        Iterator<Integer> iterator = stack.Iterator();

        assertTrue(iterator.hasNext());
        assertEquals(5, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(4, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(3, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertFalse(iterator.hasNext()); // No more elements

        // Test NoSuchElementException when trying to access next() beyond the last
        // element
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}
