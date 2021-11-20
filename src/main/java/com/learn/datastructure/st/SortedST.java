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

    /**
     * 小于等于key的最大值
     *
     * @param key key值信息
     * @return 小于等于key的最大值
     */
    Key floor(Key key);

    /**
     * 大于等于{@code key}的最小key
     *
     * @param key 对应的key值
     * @return 大于等于key的最小值
     */
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
