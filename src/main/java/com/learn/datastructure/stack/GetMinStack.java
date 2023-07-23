package com.learn.datastructure.stack;

import java.util.Objects;
import java.util.Stack;

/**
 * 在已有的栈基础上，新增获取栈的最小元素
 */
public class GetMinStack {
    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;

    public void add(Integer v) {
        Integer min = this.getMin();
        if (Objects.isNull(min) || v < min) {
            this.minStack.push(v);
        } else {
            this.minStack.push(min);
        }
        this.dataStack.push(v);
    }

    public Integer pop() {
        this.minStack.pop();
        return this.dataStack.pop();
    }

    public Integer getMin() {
        if (this.minStack.isEmpty()) {
            return null;
        }

        return this.minStack.peek();
    }
}
