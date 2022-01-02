package com.learn.datastructure.tree;

import com.learn.datastructure.st.LinkedST;

import java.util.Iterator;

/**
 * 基于拉链法(链表)散列表的实现方法:
 * <pre>
 *     散列表的实现主要以依赖于对象的hashCode的值，然后将hashCode的值通过计算让对应的索引能够分布在数组长度以内
 *     hashCode的值的解法，一般需要满足三个条件：
 *     <ul>
 *         <li>一致性：等价的键必然产生相等的散列值</li>
 *         <li>高效性：计算简便</li>
 *         <li>均匀性: 均匀地散列所有的键</li>
 *     </ul>
 * </pre>
 */
public class SeparateChainingHashST<K, V> {

    private static final int DEFAULT_CAPACITY = 16;

    /**
     * 键值对的总数
     */
    private int size;

    /**
     * 符号表的大小
     */
    private int capacity;

    /**
     * 符号表的存储
     */
    private LinkedST<K, V>[] data;

    public SeparateChainingHashST() {

    }

    public SeparateChainingHashST(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("散列表大小必须大于0");
        }
        this.capacity = capacity;
        this.data = (LinkedST<K, V>[]) new LinkedST[capacity];
        for (int i = 0; i < capacity; i++) {
            data[i] = new LinkedST<>();
        }
    }

    public V get(K key) {
        return data[hash(key)].get(key);
    }

    public void put(K key, V value) {
        data[hash(key)].put(key, value);
        this.size++;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    private void delete(K key) {
        data[hash(key)].delete(key);
    }

    public Iterator<K> keys() {
        return null;
    }
}
