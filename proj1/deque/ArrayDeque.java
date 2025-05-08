package deque;

import java.util.Iterator;

/**
 * @author Practice
 * @project proj1
 */
public class ArrayDeque<T> implements Iterable<T>, Deque<T>{
    private T[] items;
    private int size;
    /** the position point to the head of ArrayDeque and has element at this position */
    private int first;
    /** the position point to the tail of ArrayDeque and has no element at this position */
    private int last;
    public ArrayDeque(){
        items = (T[])new Object[8];
        first = 0;
        last = 0;
        size = 0;
    }
    @Override
    public void addFirst(T item){
        if(item == null){
            throw new IllegalArgumentException("can not add null!");
        }
        if(size == items.length){
            resize(items.length + items.length >> 1);
        }
        first = Math.floorMod(first - 1, items.length);
        items[first] = item;
        size += 1;
    }
    @Override
    public void addLast(T item){
        if(item == null){
            throw new IllegalArgumentException("can not add null!");
        }
        if(size == items.length){
            resize(items.length + (items.length >> 1));
        }
        items[last] = item;
        last = Math.floorMod(last + 1, items.length);
        size += 1;
    }
    private void resize(int capacity){
        T[] newArray = (T[]) new Object[capacity];
        for(int i = 0; i < size; i ++){
            newArray[i] = items[Math.floorMod(first, items.length)];
            first += 1;
        }
        first = 0;
        last = size;
        items = newArray;
    }
/*    @Override
    public boolean isEmpty(){
        return size == 0;
    }*/
    @Override
    public int size(){
        return size;
    }
    @Override
    public void printDeque(){
        int temp = size;
        int cur = first;
        while(temp > 0){
            System.out.print(items[Math.floorMod(cur, items.length)] + " ");
            cur += 1;
            temp -= 1;
        }
        System.out.println();
    }
    @Override
    public T removeFirst(){
        if(size == 0){
            return null;
        }
        if(size < items.length / 4 && items.length >= 16){
            resize(items.length / 4);
        }
        T res = items[Math.floorMod(first, items.length)];
        items[Math.floorMod(first,items.length)] = null;
        first += 1;
        size -= 1;
        return res;
    }
    @Override
    public T removeLast(){
        if(size == 0){
            return null;
        }
        if(size < items.length / 4 && items.length >= 16){
            resize(items.length / 4);
        }
        last -= 1;
        T res = items[Math.floorMod(last, items.length)];
        items[Math.floorMod(last,items.length)] = null;
        size -= 1;
        return res;
    }
    @Override
    public T get(int index){
        if(index < 0 || index >= size){
            return null;
        }
        int cur = first;
        while(index > 0){
            cur += 1;
            index -= 1;
        }

        return items[Math.floorMod(cur, items.length)];
    }

    public T getRecursive(int index){
        int cur = first;
        return getRecursive(cur, index);
    }

    private T getRecursive(int cur, int index){
        if(index < 0 || index >= size){
            return null;
        }
        if(index == 0){
            return items[Math.floorMod(cur, items.length)];
        }

        return getRecursive(cur + 1, index - 1);
    }
    @Override
    public Iterator<T> iterator(){
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator{
        int cur;
        int recordSize;
        ArrayIterator(){
            cur = first;
            recordSize = 0;
        }
        @Override
        public boolean hasNext() {
            return recordSize < size;
        }

        @Override
        public T next() {
            T res = items[Math.floorMod(cur, items.length)];
            cur += 1;
            recordSize += 1;
            return res;
        }
    }

    @Override
    public boolean equals(Object o){
        if(o == null || !(o instanceof ArrayDeque)){
            return false;
        }
        ArrayDeque comparedArrayDeque = (ArrayDeque) o;
        if(this.size != comparedArrayDeque.size){
            return false;
        }
        int i = 0;
        int cur1 = first;
        int cur2 = comparedArrayDeque.first;
        while(i < size){
            int thisIndex = Math.floorMod(cur1, items.length);
            int comIndex = Math.floorMod(cur2, comparedArrayDeque.items.length);
            if(!items[thisIndex].equals(comparedArrayDeque.items[comIndex])){
                return false;
            }
            i += 1;
            cur1 += 1;
            cur2 += 1;
        }

        return true;
    }

    public T[] getItems(){
        return this.items;
    }
}
