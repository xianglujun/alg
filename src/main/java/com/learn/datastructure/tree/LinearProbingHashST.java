package com.learn.datastructure.tree;

/**
 * 线性探测法的散列表
 *
 * @param <K> key值
 * @param <V> value值
 */
public class LinearProbingHashST<K, V> {

    private static final int DEFAULT_CAPACITY = 16;

    private int size;

    private int capacity;
    private K[] keys;
    private V[] values;

    public LinearProbingHashST() {

    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    public LinearProbingHashST(int capacity) {
        this.capacity = capacity;
        this.keys = (K[]) new Object[capacity];
        this.values = (V[]) new Object[capacity];
    }

    public void put(K key, V value) {

        // 扩容
        if (size >= capacity / 2) {
            resize(2 * capacity);
        }

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % capacity) {
            // 判断key和value是否相等
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
        }
        keys[i] = key;
        values[i] = value;
        this.size++;
    }

    public void delete(K key) {
        // 判断是否包含key
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % capacity;
        }

        keys[i] = null;
        values[i] = null;

        // 开始处理右侧的空隙
        i = (i + 1) % capacity;
        while (keys[i] != null) {
            // 获取当前的key
            K nK = keys[i];
            V nV = values[i];

            keys[i] = null;
            values[i] = null;
            put(nK, nV);
            size--;

            // 寻找下一个
            i = (i + 1) % capacity;
        }
        size--;
        if (size > 0 && size <= capacity / 8) {
            resize(capacity / 2);
        }
    }

    public V get(K key) {
        if (size == 0) {
            return null;
        }

        int i = hash(key);
        for (; keys[i] != null; i = (i + 1) % capacity) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private void resize(int newCapacity) {
        K[] oldKeys = this.keys;
        V[] oldValues = this.values;

        K[] newKeys = (K[]) new Object[newCapacity];
        V[] newValues = (V[]) new Object[newCapacity];

        for (int i = 0; i < oldKeys.length; i++) {
            // 计算hash值
            int index = (oldKeys[i].hashCode() & 0x7fffffff) % newCapacity;
            while (newKeys[index] != null) {
                index = (index + 1) % newCapacity;
            }
            newKeys[index] = oldKeys[i];
            newValues[index] = oldValues[i];
        }
        this.capacity = newCapacity;
        this.keys = newKeys;
        this.values = newValues;
    }
}
