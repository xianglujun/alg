package com.learn.datastructure.ch1.queue;

import java.util.Iterator;

public class RingArrayQueue<V> implements Queue<V> {

    private Object[] arr;
    private int size;
    private int pollIndex;
    private int pushIndex;
    private int limit;

    public RingArrayQueue(int limit) {
        this.limit = limit;
        this.arr = new Object[limit];
    }

    @Override
    public void enqueue(V v) {
        if (this.size == limit) {
            throw new IllegalArgumentException("队列已经满了");
        }

        pushIndex = pushIndex < this.size - 1 ? pushIndex + 1 : 0;
        this.arr[pushIndex] = v;
        size++;
    }

    @Override
    public V dequeue() {
        if (this.size == 0) {
            return null;
        }
        pollIndex = pollIndex < this.size - 1 ? pollIndex + 1 : 0;
        size--;
        return (V) this.arr[pollIndex];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<V> iterator() {
        return null;
    }
}
