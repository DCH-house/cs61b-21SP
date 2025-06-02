package bstmap;

import java.util.Iterator;
import java.util.Set;

/**
 * @author Practice
 * @project lab7
 */
public class BSTMap<K extends Comparable<K>,V> implements Map61B<K, V> {
    private class Entry {
        K key;
        V val;
        Entry left;
        Entry right;

        Entry(K k, V v) {
            this.key = k;
            this.val = v;
            this.left = null;
            this.right = null;
        }
    }
    private int size;
    private Entry root;
    public BSTMap() {
        this.root = null;
        this.size = 0;
    }
    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey(key, this.root);
    }
    /**
     * 判断以node为根的二叉搜索树中是否包含指定的键
     * 此方法是递归实现的，根据二叉搜索树的性质，比较给定键与节点键的大小，
     * 从而决定向左子树或右子树递归搜索
     *
     * @param key 要搜索的键，用于查找其是否存在于树中
     * @param node 当前考察的树的根节点，起始调用时应为整个树的根节点
     * @return 如果树中包含指定的键，则返回true；否则返回false
     */
    private boolean containsKey(K key, Entry node) {
        // 如果当前节点为空，说明已经到达树的底部，没有找到指定的键
        if (node == null) {
            return false;
        }

        int cmp = node.key.compareTo(key);

        // 如果比较结果为0，说明找到了匹配的键
        if (cmp == 0) {
            return true;
        }
        // 如果当前节点的键大于给定键，则向左子树递归搜索
        else if (cmp > 0) {
            return containsKey(key, node.left);
        }
        // 如果当前节点的键小于给定键，则向右子树递归搜索
        else {
            return containsKey(key, node.right);
        }
    }


    @Override
    public V get(K key) {
        return get(key, this.root);
    }

    private V get(K key, Entry node) {
        if (node == null) {
            return null;
        }
        int cmp = node.key.compareTo(key);
        if (cmp == 0) {
            return (V) node.val;
        } else if (cmp == 1) {
            return get(key, node.left);
        } else {
            return get(key, node.right);
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void put(K key, V value) {
        if (this.root == null) {
            this.root = new Entry(key, value);
            this.size += 1;
            return;
        }
        put(key, value, this.root);
        this.size += 1;
    }

    private Entry put(K key, V value, Entry node){
        if (node == null) {
            return new Entry(key, value);
        }
        int cmp = node.key.compareTo(key);

        if (cmp == 0) {
            node.val = value;
        } else if (cmp == 1) {
            node.left = put(key, value, node.left);
        } else {
            node.right = put(key, value, node.right);
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
        printInOrder(this.root);
    }
    private void printInOrder(Entry node){
        if (node == null) {
            return;
        }
        printInOrder(node.left);
        System.out.println(node.key + " " + node.val);
        printInOrder(node.right);
    }
}
