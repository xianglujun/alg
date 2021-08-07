package com.learn.datastructure;

import com.learn.datastructure.ch1.queue.LinkedQueue;
import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;
import org.junit.Test;

/**
 * 链表实现的队列测试
 *
 * @author xianglujun
 * @date 2021/5/26 18:06
 * @since 1.0
 */
public class LinkedQueueTest {

    @Test
    public void testMultiOperation() {
        long currentMillis = System.currentTimeMillis();
        LinkedQueue<String> linkedQueue = new LinkedQueue<>();
        for (int i = 0; i < 1000000; i++) {
            linkedQueue.enqueue(UUID.randomUUID().toString());
        }

        long duration = System.currentTimeMillis() - currentMillis;
        System.out.println("测试用例耗时:" + duration + " ms");
    }

    @Test
    public void testJdkMultiOperation() {
        long currentMillis = System.currentTimeMillis();
        Queue<String> linkedQueue = new LinkedList<>();
        for (int i = 0; i < 1000000; i++) {
            linkedQueue.add(UUID.randomUUID().toString());
        }

        long duration = System.currentTimeMillis() - currentMillis;
        System.out.println("测试用例耗时:" + duration + " ms");
    }

    public static void main(String[] args) {
        System.out.println("测试");
    }

}
