package IntList;

import static org.junit.Assert.*;

import jh61b.junit.In;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    /** this test is check when list has no prime element */
    @Test
    public void testSquarePrimesNoPrime(){
        IntList lst = IntList.of(1, 4, 6, 8);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("1 -> 4 -> 6 -> 8", lst.toString());
        assertFalse(changed);
    }

    /** this test is check when list has 2 primes */
    @Test
    public void testSquarePrimesTwo(){
        IntList lst = IntList.of(2, 3, 4, 6);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("4 -> 9 -> 4 -> 6", lst.toString());
        assertTrue(changed);
    }
}
