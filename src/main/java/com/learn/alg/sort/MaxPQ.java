package com.learn.alg.sort;

/**
 * @author 自来也
 */
public class MaxPQ<Item extends Comparable> {

    private Item[] items;
    /**
     * 0的位置没有使用
     */
    private int size = 0;

    public MaxPQ(int capacity) {
        this.items = (Item[]) new Comparable[capacity];
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return size;
    }

    /**
     * 向优先对垒中插入元素，将元素插入到队尾之中
     * @param item
     */
    public void insert(Item item) {
        items[++size] = item;
        // 上浮元素
        swim(size);
    }

    public Item deleteMax() {
        // 当前实现的是最大优先队列，因此元素中第一个元素就代表了最大元素
        if (isEmpty()) {
            return null;
        }
        Item max = items[1];
        // 将最底下的元素放入到根节点
        exch(1, size--);
        // 回收末尾的地方
        items[size + 1] = null;
        sink(1);
        return max;
    }

    public boolean less(int i, int j) {
        return items[i].compareTo(items[j]) < 0;
    }

    public void exch(int i, int j) {
        Item item = items[j];
        items[j] = items[i];
        items[i] = item;
    }

    public void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(k/2, k);
            k = k/2;
        }
    }

    /**
     * 下沉元素，下沉元素要求满足当前节点的元素小于两个叶子节点的元素
     * @param k 当前元素所在位置
     */
    public void sink(int k) {
        while (2*k <= size) {
            int j = 2*k;
            // 下沉时，需要比较两个节点的元素
            if (j < size && less(j, j+1)) j++;
            if (!less(k, j)) break;

            // 说明当前节点小于两个节点中的一个
            exch(k, j);
            k = j;
        }
    }

    public static void main(String[] args) {
        MaxPQ<Integer> maxPQ = new MaxPQ<Integer>(100);
        for (int i = 0; i < 99; i++) {
            maxPQ.insert(i);
        }

        for (int i = 0; i < 99; i++) {
            System.out.println(maxPQ.deleteMax());
        }
    }
}
