package com.learn.alg.sort.bucket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 桶排序
 */
public class BucketSort {

    /**
     * 桶排序的而实现
     *
     * @param elements  需要排序的列表
     * @param bucketCap 每个桶的容量
     */
    public void BucketSort(List<Integer> elements, int bucketCap) {
        if (elements == null || elements.size() == 0) {
            return;
        }

        // 列表中最大与最小元素
        Integer min = 0, max = 0;
        for (int i = 0, len = elements.size(); i < len; i++) {
            Integer e = elements.get(i);
            if (e.compareTo(min) < 0) {
                min = e;
            }

            if (e.compareTo(max) > 0) {
                max = e;
            }
        }

        // 根据最大最小值决定间隙, 然后通过间隙获取需要桶的数量
        int bucketCount = (max - min) / bucketCap + 1;
        // 定义桶的数据
        List<List<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0, len = bucketCount; i < len; i++) {
            buckets.add(new ArrayList<>());
        }
        // 将元素对应到各自的桶中
        for (Integer e : elements) {
            buckets.get((e - min) / bucketCap).add(e);
        }

        // 查看桶中的元素数量
        int i = 0;
        for (List<Integer> bucket : buckets) {
            System.out.println("第" + i + "个bucket中元素数量为: " + bucket.size());
            i++;
        }

        // 对bucket中的元素执行排序
        int index = 0;
        for (List<Integer> bucket : buckets) {
            if (bucket.size() > 0) {
                sort(bucket);

                // 将现有书序重写写入到原集合中
                for (Integer e : bucket) {
                    elements.set(index++, e);
                }
            }
        }
    }

    /**
     * 插入排序
     *
     * @param bucket
     */
    private void sort(List<Integer> bucket) {
        // 采用插入排序实现
        for (int i = 0, len = bucket.size(); i < len; i++) {
            for (int j = i; j > 0; j--) {
                // 判断大小
                if (bucket.get(j) < bucket.get(j - 1)) {
                    Integer temp = bucket.get(j);
                    bucket.set(j, bucket.get(j - 1));
                    bucket.set(j - 1, temp);
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> elements = Arrays.asList(1, 5, 6, 2, 1, 3, 2, 1, 23, 6, 9, 23, 48, 89);
        new BucketSort().BucketSort(elements, 25);
        System.out.println(Arrays.toString(elements.toArray()));
    }
}
