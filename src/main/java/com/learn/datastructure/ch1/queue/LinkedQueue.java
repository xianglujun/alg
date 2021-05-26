package com.learn.datastructure.ch1.queue;

import java.util.Iterator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 通过链表的数据结果实现队列
 *
 * @author xianglujun
 * @date 2021/5/26 17:53
 * @since 1.0
 */
public class LinkedQueue<Item> implements Queue<Item> {

    /**
     * 节点的第一个节点
     */
    private Node first;

    private int size;

    /**
     * 链表的节点定义
     */
    @Setter
    @Getter
    @ToString
    @AllArgsConstructor
    private class Node {

        private Item item;
        private Node next;
    }
    

    @Override
    public void enqueue(Item item) {
        // 将一个元素执行入队
        Node next = new Node(item, null);
        if (first == null) {
            first = next;
        } else {
            first.next = next;
        }
        size++;
    }

    @Override
    public Item dequeue() {
        if (first == null) {
            return null;
        }

        Node node = first;
        first = node.next;

        return node.item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }
}
