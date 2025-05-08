package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;

/**
 * @author Practice
 * @project proj1
 */
public class MaxArrayDequeTest {
    @Test
    /** check the max method */
    public void maxTest(){
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>();
        mad.addLast(1);
        mad.addLast(2);
        mad.addLast(3);

        assertEquals(Integer.valueOf(3), mad.max());
    }

    @Test
    /** check the max(Comparator<T> c) method */
    public void maxWithComTest(){
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>();
        mad.addLast(1);
        mad.addLast(2);
        mad.addLast(3);

        assertEquals(Integer.valueOf(1), mad.max((a, b) -> b - a));
    }
}
