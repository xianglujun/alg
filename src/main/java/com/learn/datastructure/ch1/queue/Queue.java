package com.learn.datastructure.ch1.queue;

import java.util.Iterator;

/**
 * queue接口定义，用于定义常用操作形式实现
 * <ul>
 *     <li>1. 队列满足先进先出原则</li>
 *     <li>2. 队列能够实现遍历</li>
 *     <li>3. 当出队之后，该元素应当从队列中删除</li>
 * </ul>
 *
 * @author xianglujun
 * @date 2021/5/26 17:55
 * @since 1.0
 */
public interface Queue<Item> {

    /**
     * 将一个元素入队
     *
     * @param item 需要入队的元素
     */
    void enqueue(Item item);

    /**
     * 对一个队列实现出队操作
     *
     * @return 出队操作
     */
    Item dequeue();

    /**
     * 当前队列长度
     *
     * @return 当前队列长度
     */
    int size();

    /**
     * 判断队列是否为空
     *
     * @return 队列是否为空
     */
    boolean isEmpty();

    /**
     * 实现遍历器
     *
     * @return 迭代器对象
     */
    Iterator<Item> iterator();
}
