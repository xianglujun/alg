package com.learn.datastructure.st;

/**
 * 符号表具有的特性：
 * <li>1. 符号表中Key不能为null</li>
 * <li>2. 符号表中Value不能为null</li>
 * <li>3. 删除操作包含了延时删除以及即时删除两种形式</li>
 * <li>2. 符号表中Value不能为null</li>
 *
 * @author <a href="mailto:xianglj1991@163.com">xianglujun</a>
 * @since 2021/8/7 17:17
 */
public interface ST<Key, Value> {

    void put(Key key, Value value);

    Value get(Key key);

    void delete(Key key);

    boolean contains(Key key);

    boolean isEmpty();

    int size();

    Iterable<Key> keys();
}
