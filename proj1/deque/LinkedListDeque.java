package deque;

import org.hamcrest.core.IsInstanceOf;

import java.util.Iterator;

/**
 * @author Practice
 * @project proj1
 */
public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    private class Node {
        Node previous;
        T data;
        Node next;
    }
    private Node sentinel;
    private int size;
    private Node temp;
    public LinkedListDeque() {
        this.sentinel = new Node();
        this.sentinel.previous = this.sentinel;
        this.sentinel.next = this.sentinel;
        this.size = 0;
        this.temp = sentinel;
    }
    @Override
    public void addFirst(T item) {
        if(item == null) {
            throw new IllegalArgumentException("can not add null!");
        }
        Node newNode = new Node();
        newNode.data = item;
        Node nextNode = this.sentinel.next;
        newNode.next = nextNode;
        nextNode.previous = newNode;
        this.sentinel.next = newNode;
        newNode.previous = this.sentinel;
        this.size += 1;
    }
    @Override
    public void addLast(T item) {
        if(item == null) {
            throw new IllegalArgumentException("can not add null!");
        }
        Node newNode = new Node();
        newNode.data = item;
        Node preNode = this.sentinel.previous;
        preNode.next = newNode;
        newNode.previous = preNode;
        newNode.next = this.sentinel;
        this.sentinel.previous = newNode;
        this.size += 1;
    }
/*    @Override
    public boolean isEmpty(){
        return this.size == 0;
    }*/
    @Override
    public int size() {
        return this.size;
    }
    @Override
    public void printDeque() {
        Node cur = this.sentinel.next;
        while(cur != this.sentinel) {
            System.out.print(cur.data + " ");
            cur = cur.next;
        }
        System.out.println();
    }
    @Override
    public T removeFirst() {
        if(this.size == 0) {
            return null;
        }
        Node removeNode = this.sentinel.next;
        removeNode.next.previous = this.sentinel;
        this.sentinel.next = removeNode.next;
        T res = removeNode.data;
        removeNode = null;
        this.size -= 1;
        return  res;
    }
    @Override
    public T removeLast() {
        if(this.size == 0) {
            return null;
        }
        Node removeNode = this.sentinel.previous;
        removeNode.previous.next = this.sentinel;
        this.sentinel.previous = removeNode.previous;
        T res = removeNode.data;
        removeNode = null;
        this.size -= 1;
        return  res;
    }
    @Override
    public T get(int index) {
        if(index < 0 || index >= this.size) {
            return null;
        }
        Node cur = this.sentinel.next;
        while(index > 0) {
            cur = cur.next;
            index -= 1;
        }

        return cur.data;
    }

    public T getRecursive(int index) {
        this.temp = sentinel.next;
        return getRecursive(temp, index);
    }

    private T getRecursive(Node cur, int index) {
        if(index < 0 || index >= size) {
            return null;
        }
        if(index == 0) {
            return cur.data;
        }
        return getRecursive(cur.next, index - 1);
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator {
        Node cur;
        LinkedListDequeIterator() {
            cur = sentinel.next;
        }
        @Override
        public boolean hasNext() {
            return cur != sentinel;
        }

        @Override
        public T next() {
            T res = cur.data;
            cur = cur.next;
            return res;
        }
    }

    @Override
    public boolean equals(Object o) {
        if(o == null || !(o instanceof Deque)) {
            return false;
        }
        Deque lld = (Deque)o;
        if(this.size != lld.size()) {
            return false;
        }
        for(int i = 0; i < this.size; i ++) {
            if(!get(i).equals(lld.get(i))) {
                return false;
            }
        }
        return true;
    }
}
