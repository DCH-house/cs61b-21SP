package deque;

import org.junit.Test;


import java.util.Deque;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

/**
 * @author Practice
 * @project proj1
 */
public class ArrayDequeTest {
    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        ArrayDeque<String> a = new ArrayDeque<>();

        assertTrue("A newly initialized ArrayDeque should be empty", a.isEmpty());
        a.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, a.size());
        assertFalse("a should now contain 1 item", a.isEmpty());

        a.addLast("middle");
        assertEquals(2, a.size());

        a.addLast("back");
        assertEquals(3, a.size());

        System.out.println("Printing out deque: ");
        a.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        ArrayDeque<Integer> a = new ArrayDeque<Integer>();
        // should be empty
        assertTrue("a should be empty upon initialization", a.isEmpty());

        a.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", a.isEmpty());

        a.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", a.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addFirst(3);

        a.removeLast();
        a.removeFirst();
        a.removeLast();
        a.removeFirst();

        int size = a.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create ArrayDeque with different parameterized types*/
    public void multipleParamTest() {

        ArrayDeque<String>  a1 = new ArrayDeque<String>();
        ArrayDeque<Double>  a2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> a3 = new ArrayDeque<Boolean>();

        a1.addFirst("string");
        a2.addFirst(3.14159);
        a3.addFirst(true);

        String s = a1.removeFirst();
        double d = a2.removeFirst();
        boolean b = a3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty ArrayDeque. */
    public void emptyNullReturnTest() {

        ArrayDeque<Integer> a = new ArrayDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, a.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, a.removeLast());

    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        ArrayDeque<Integer> a = new ArrayDeque<>();
        for (int i = 0; i < 1000000; i++) {
            a.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) a.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) a.removeLast(), 0.0);
        }

    }

    @Test
    /** check the addFirst method with two element */
    public void addFirstWithTwoEleTest(){
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addFirst(1);
        a.addFirst(2);
    }

    @Test
    /** check the addLast method with two elements */
    public void addLastWithTwoEleTest(){
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addLast(1);
        a.addLast(2);
    }

    @Test
    /** check the get and getRecursive method with two elements */
    public void getAndGetReWithTwoEleTest(){
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        lld.addLast(1);
        lld.addLast(2);

        assertEquals(Integer.valueOf(1), lld.get(0));
        assertEquals(Integer.valueOf(1), lld.getRecursive(0));

        assertEquals(Integer.valueOf(2), lld.get(1));
        assertEquals(Integer.valueOf(2), lld.getRecursive(1));
    }

    @Test
    /** check the equals method with two same deque */
    public void equalsWithSameTest(){
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        LinkedListDeque<Integer> lld2 = new LinkedListDeque<>();

        lld1.addLast(1);
        lld1.addLast(2);
        lld2.addLast(1);
        lld2.addLast(2);

        assertTrue(lld1.equals(lld2));
    }

    @Test
    /** check the equals method with two different deque */
    public void equalsWithDiffTest(){
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        LinkedListDeque<Integer> lld2 = new LinkedListDeque<>();

        lld1.addLast(1);
        lld1.addLast(2);
        lld2.addLast(1);
        lld2.addLast(3);

        assertFalse(lld1.equals(lld2));
    }

    @Test
    /** check the iterator method use strong for-loop */
    public void IteratorTest(){
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addLast(1);
        a.addLast(2);
        a.addLast(3);

        for(Integer i : a){
            System.out.println(i);
        }
    }
}
