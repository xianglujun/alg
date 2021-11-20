package com.learn.datastructure.st;

import java.util.Iterator;

/**
 * 链表实现符号表
 *
 * @author <a href="mailto:xianglj1991@163.com">xianglujun</a>
 * @since 2021/8/7 20:21
 */
public class LinkedST<Key, Value> implements ST<Key, Value> {

    private int size;
    private Node first;

    @Override
    public void put(Key key, Value value) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
        }
        first = new Node(key, value, first);
        size++;
    }

    @Override
    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (x.key.equals(key)) {
                return x.value;
            }
        }
        return null;
    }

    @Override
    public void delete(Key key) {
        Node prev = null;
        for (Node x = first; x != null; x = x.next) {
            if (x.key.equals(key)) {
                if (prev == null) {
                    first = x.next;
                    x.next = null;
                } else {
                    prev.next = x.next;
                    x.next = null;
                    size--;
                }

                break;
            }
            prev = x;
        }
    }

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<Key> keys() {
        return KeyIterator::new;
    }

    private class KeyIterator implements Iterator<Key> {

        private Node curr = first;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public Key next() {
            Node result = curr;
            curr = curr.next;
            return result.key;
        }

        @Override
        public void remove() {
            Node next = curr.next;
            LinkedST.this.delete(curr.key);
            curr = next;
        }
    }

    private class Node {
        private Key key;
        private Value value;

        private Node next;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        public Node(Key key, Value value, Node next) {
            this.value = value;
            this.key = key;
            this.next = next;
        }

    }
}
