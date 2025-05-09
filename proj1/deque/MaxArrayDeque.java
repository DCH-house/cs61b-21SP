package deque;

import java.util.Comparator;
import java.util.Iterator;

/**
 * @author Practice
 * @project proj1
 */
public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private Comparator<T> comparator;
    public MaxArrayDeque(Comparator<T> c) {
        this.comparator = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        Iterator<T> it = iterator();
        T max = it.next();
        Comparator<T> comp;
        if (comparator != null) {
            comp = comparator;
        }else {
            comp = (Comparator<T>) Comparator.naturalOrder();
        }
        while (it.hasNext()) {
            T curr = it.next();
            if (comp.compare(curr, max) > 0) {
                max = curr;
            }
        }
        return max;
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        Iterator<T> it = iterator();
        T max = it.next();
        while (it.hasNext()) {
            T curr = it.next();
            if (c.compare(curr, max) > 0) {
                max = curr;
            }
        }
        return max;
    }
}
