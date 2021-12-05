package com.learn.datastructure.st;

/**
 * 二分查找法
 *
 * @author <a href="mailto:xianglj1991@163.com">xianglujun</a>
 * @since 2021/8/7 21:06
 */
public class BinarySearchST<Key extends Comparable, Value> extends AbstractSortedST<Key, Value> {

    private Key[] keys;
    private Value[] values;

    private int size;

    public BinarySearchST(int capacity) {
        this.keys = (Key[]) new Comparable[capacity];
        this.values = (Value[]) new Object[capacity];
    }

    @Override
    public Key min() {
        if (isEmpty()) {
            return null;
        }

        return keys[0];
    }

    @Override
    public Key max() {
        if (isEmpty()) {
            return null;
        }
        return keys[size - 1];
    }

    /**
     * 小于等于key的最大值
     *
     * @param key key值信息
     * @return 小于等于key的最大值
     */
    @Override
    public Key floor(Key key) {
        // 获取当前位置的key的值
        int i = rank(key);
        return keys[i];
    }

    /**
     * 大于等于{@code key}的最小key
     *
     * @param key 对应的key值
     * @return 大于等于key的最小值
     */
    @Override
    public Key ceiling(Key key) {
        int i = rank(key);
        return keys[i];
    }

    @Override
    public int rank(Key key) {
        int lo = 0, hi = size - 1;
        while (lo <= hi) {
            // 判断对应
            int mid = (hi - lo) / 2 + lo;
            // 和中间的位置
            int comp = keys[mid].compareTo(key);
            if (comp < 0) {
                lo = mid + 1;
            } else if (comp > 0) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }
        return 0;
    }

    @Override
    public Key select(int k) {
        return keys[k];
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        return null;
    }

    @Override
    public void put(Key key, Value value) {
        int i = rank(key);
        if (i < size && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }

        // 向后移动
        for (int j = size; j >= i; j--) {
            values[size] = values[j];
            keys[size] = keys[j];
        }

        keys[i] = key;
        values[i] = value;
        size++;
    }

    @Override
    public Value get(Key key) {

        if (isEmpty()) {
            return null;
        }

        // 判断对应的key是否存在
        int i = rank(key);
        if (i < size && keys[i].compareTo(key) == 0) {
            return values[i];
        }

        return null;
    }

    @Override
    public void delete(Key key) {
        int i = rank(key);
        if (i < size && keys[i].compareTo(key) == 0) {
            for (int j = i; j < size; j++) {
                keys[j] = keys[j + 1];
                values[j] = values[j + 1];
            }
            size--;
        }
    }

    @Override
    public boolean contains(Key key) {
        int i = rank(key);
        return i < size && keys[i].compareTo(key) == 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }
}
