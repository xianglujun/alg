package com.learn.alg.offer.stack;

import java.util.Stack;

/**
 * 通过双栈的方式实现队列
 * @author xianglujun
 */
public class DoubleStackForQueueResulation {

    private Stack<Integer> stack1 = new Stack<Integer>();
    private Stack<Integer> stack2 = new Stack<Integer>();


    public void push(int ele) {
        stack1.push(ele);
    }

    public int pop() {
        if (!stack2.isEmpty()) {
            return stack2.pop();
        }

        // 这里在多线程环境下会存在问题, 因此, 这里有一做同步处理
        // 这样在stack1做数据拷贝的时候, 其他的线程暂时不能push元素。
        synchronized (stack1) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }

        return stack2.pop();
    }

    public static void main(String[] args) {
        DoubleStackForQueueResulation resulation = new DoubleStackForQueueResulation();
        resulation.push(1);
        resulation.push(2);
        resulation.push(3);
        resulation.push(4);

        System.out.print(resulation.pop());
        System.out.print(resulation.pop());
        System.out.print(resulation.pop());
        System.out.print(resulation.pop());
    }

}
