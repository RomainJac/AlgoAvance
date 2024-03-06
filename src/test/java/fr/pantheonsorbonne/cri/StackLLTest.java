package fr.pantheonsorbonne.cri;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

public class StackLLTest {

    @Test
    public void testPushPeek() {
        StackLL<Integer> stack = new StackLL<>();
        assertEquals(null, stack.peek());
        stack.push(5);
        assertEquals(5, stack.peek());
        stack.push(10);
        assertEquals(10, stack.peek());
        stack.push(15);
        assertEquals(15, stack.peek());
    }
    @Test
    public void testPop() {
        StackLL<Integer> stack = new StackLL<>();
        assertEquals(null, stack.pop());    
        stack.push(5);
        stack.push(10);
        stack.push(15);
        assertEquals(15, stack.pop());
        assertEquals(10, stack.pop());
        assertEquals(5, stack.pop());
    }

    @Test
    public void testEmpy() {
        StackLL<Integer> stack = new StackLL<>();
        assertEquals(true, stack.isEmpty());
        stack.push(5);
        assertEquals(false, stack.isEmpty());
        stack.pop();
        assertEquals(true, stack.isEmpty());
        StackLL<Integer> stack2 = new StackLL<>(5);
        assertEquals(false, stack2.isEmpty());
        assertEquals(5, stack2.pop());

    
    }

    @Test
    public void testIterator() {
        StackLL<Integer> stack = new StackLL<>();
        stack.push(5);
        stack.push(10);
        stack.push(15);
        stack.push(20);
        stack.push(25);
        stack.push(30);
        stack.push(35);
        stack.push(40);
        stack.push(45);
        stack.push(50);
        stack.push(55);
        stack.push(60);
        stack.push(65);
        stack.push(70);
        stack.push(75);
        stack.push(80);
        stack.push(85);
        stack.push(90);
        stack.push(95);
        stack.push(100);
        int i = 100;
        Iterator<Integer> it = stack.iterator();
        while (it.hasNext()) {
            assertEquals(i, it.next());
            i -= 5;
        }
    }
}
