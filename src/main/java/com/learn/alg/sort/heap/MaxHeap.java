package com.learn.alg.sort.heap;

/**
 * 堆排序实现: 实现大顶堆
 * <pre>
 *  该实现中，会使用数组的0位置元素，因此：
 *  1. 左子树定位公式位: 2 * i + 1
 *  2. 右子树定位公式为: 2 * i + 2
 *  3. 父节点定位方式为: (i - 1)/2
 * </pre>
 */
public class MaxHeap {
    private int heapSize;
    private int[] heap;
    private int limit;

    public MaxHeap(int limit) {
        this.limit = limit;
        this.heap = new int[limit];
    }

    /**
     * 弹出大顶堆中最大的元素
     *
     * @return 最大的元素
     */
    public int pop() {
        return 0;
    }

    /**
     * 向堆中加入元素
     *
     * @param i 需要加入的元素
     */
    public void offer(int i) {
    }

    /**
     * 元素下沉操作
     *
     * @param index
     */
    public void siftDown(int index) {

    }

    /**
     * 元素上浮操作
     *
     * @param index
     */
    public void siftUp(int index) {

    }

    public int size() {
        return this.heapSize;
    }

}
