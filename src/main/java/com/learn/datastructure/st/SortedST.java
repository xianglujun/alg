package com.learn.datastructure.st;

/**
 * 有序符号表实现
 *
 * @author <a href="mailto:xianglj1991@163.com">xianglujun</a>
 * @since 2021/8/7 17:21
 */
public interface SortedST<Key extends Comparable, Value> extends ST<Key, Value> {

    Key min();

    Key max();

    Key floor(Key key);

    Key ceiling(Key key);

    /**
     * 小于Key键的数量
     *
     * @param key key信息
     * @return 小于Key键的数量
     */
    int rank(Key key);

    /**
     * 排名为K的元素
     *
     * @param k 排名
     * @return {@value Key} 信息
     */
    Key select(int k);

    void deleteMin();

    void deleteMax();

    int size(Key lo, Key hi);

    Iterable<Key> keys(Key lo, Key hi);
}
