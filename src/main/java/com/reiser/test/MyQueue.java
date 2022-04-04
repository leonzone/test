package com.reiser.test;

import java.util.Stack;

/**
 * @author: reiserx
 * Date:2022/4/4
 * 需要两个 Stack 去实现
 * Stack s1 负责队尾
 * Stack s2 负责队头
 *
 * 主要是 Queue peek() 时，需要从 s2 中取，这时我们需要将 s1 的数据转移到 s2
 */
public class MyQueue {

    Stack<Integer> s1;
    Stack<Integer> s2;

    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    public void push(int x) {
        s1.push(x);
    }

    public int pop() {
        peek();
        return s2.pop();
    }

    public int peek() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.add(s1.pop());
            }
        }
        return s2.peek();
    }

    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}
