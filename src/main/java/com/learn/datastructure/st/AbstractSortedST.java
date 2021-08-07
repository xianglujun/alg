package com.learn.datastructure.st;

/**
 * TODO
 *
 * @author <a href="mailto:xianglj1991@163.com">xianglujun</a>
 * @since 2021/8/7 21:06
 */
public abstract class AbstractSortedST<Key extends Comparable, Value> implements SortedST<Key, Value> {

    @Override
    public void deleteMin() {
        // 删除最小位置索引
        delete(min());
    }

    @Override
    public void deleteMax() {
        delete(max());
    }

    @Override
    public int size(Key lo, Key hi) {
        if (hi.compareTo(hi) < 0) {
            return 0;
        } else if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }

    @Override
    public Iterable<Key> keys() {
        return keys(min(), max());
    }
}
