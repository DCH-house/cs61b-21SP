package bstmap;

import java.util.Iterator;
import java.util.Set;

/**
 * @author Practice
 * @project lab7
 */
public class BSTMap<K, V> implements Map61B<K, V> {

    private BSTMap left;
    private BSTMap right;
    private K key;
    private V val;
    private int size;
    public BSTMap() {
        this.left = null;
        this.right = null;
        this.key = null;
        this.val = null;
        this.size = 0;
    }
    private BSTMap(K key, V val) {
        this.left = null;
        this.right = null;
        this.key = key;
        this.val = val;
    }
    @Override
    public void clear() {
        this.left = null;
        this.right = null;
        this.key = null;
        this.val = null;
        this.size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey(key, this);
    }
    private boolean containsKey(K key, BSTMap node) {
        if (node == null) {
            return false;
        }
        if (node.key.equals(key)) {
            return true;
        }
        if (node.key > key) {
            return containsKey(key, node.left);
        }
        if (node.key < key) {
            return containsKey(key, node.right);
        }

        return false;
    }
    @Override
    public V get(K key) {
        return get(key, this);
    }

    private V get(K key, BSTMap node) {
        if (node == null) {
            return null;
        }
        if (node.key.equals(key)) {
            return (V) node.val;
        }
        if (node.key > key) {
            return get(key, node.left);
        }
        if (node.key < key) {
            return get(key, node.right);
        }

        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void put(K key, V value) {
        put(key, value, this);
        this.size += 1;
    }

    private BSTMap put(K key, V value, BSTMap node){
        if (node == null) {
            return new BSTMap(key, value);
        }
        if (node.key == key) {
            node.val = value;
        }
        if (node.key < key) {
            node.right = put(key, value, node.right);
        }
        if (node.key > key) {
            node.left = put(key, value, node.right);
        }
        return node;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("Unsupport this operation");
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("Unsupport this operation");
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("Unsupport this operation");
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("Unsupport this operation");
    }

    public void printInOrder(){
        if (this == null) {
            return;
        }
        this.left.printInOrder();
        System.out.print(this.key + " ");
        this.right.printInOrder();
    }
}
