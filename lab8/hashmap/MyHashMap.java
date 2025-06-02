package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> , Iterable<K>{

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private Integer initialSize;
    private Double loadFactor;
    private Integer size;
    private Set<K> keySet;
    /** Constructors */
    public MyHashMap() {
        this.initialSize = 16;
        this.loadFactor = 0.75;
        this.buckets = new Collection[initialSize];
        this.size = 0;
        this.keySet = new HashSet<>();
    }

    public MyHashMap(int initialSize) {
        this.initialSize = initialSize;
        this.loadFactor = 0.75;
        this.buckets = new Collection[initialSize];
        this.size = 0;
        this.keySet = new HashSet<>();
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.initialSize = initialSize;
        this.loadFactor = maxLoad;
        this.buckets = new Collection[initialSize];
        this.size = 0;
        this.keySet = new HashSet<>();
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new ArrayList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!
    @Override
    public void clear() {
        this.buckets = new Collection[initialSize];
        this.size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (buckets == null) {
            return false;
        }
        int index = hash(key, buckets.length);
        if (buckets[index] == null) {
            return false;
        }
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        if (buckets == null) {
            return null;
        }
        int index = hash(key, buckets.length);
        if (buckets[index] == null) {
            return null;
        }
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        int index = hash(key, buckets.length);
        if (containsKey(key)) {
            for (Node node : buckets[index]) {
                if (node.key.equals(key)) {
                    node.value = value;
                    return;
                }
            }
        }
        /* check if the actual load factor is greater than the specified load factor */
        if ((size + 1) / buckets.length >= loadFactor) {
            /* resize */
            Collection<Node> newBuckets[] = createTable(buckets.length * 2);
            for (int i = 0; i < buckets.length; i++) {
                if (buckets[i] != null) {
                    for (Node node : buckets[i]) {
                        index = hash(node.key, newBuckets.length);
                        if (newBuckets[index] == null) {
                            newBuckets[index] = createBucket();
                        }
                        newBuckets[index].add(node);
                    }
                }
            }
            buckets = newBuckets;
        }
        index = hash(key, buckets.length);
        if (buckets[index] == null){
            buckets[index] = createBucket();
        }
        buckets[index].add(createNode(key, value));
        size += 1;
        keySet.add(key);
    }

    @Override
    public Set<K> keySet() {
        return this.keySet;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("Unsuport remove operation");
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("Unsupport remove operation");
    }

    @Override
    public Iterator<K> iterator() {
        return keySet.iterator();
    }

    private int hash(K key, int tableSize) {
        int hash = key.hashCode();
        return  Math.floorMod(hash, tableSize);
    }
}
