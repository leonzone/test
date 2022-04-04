package com.reiser.test;

import org.junit.Assert;

public class MyQueueTest {

    MyQueue queue;

    @org.junit.Before
    public void setUp() throws Exception {

        queue = new MyQueue();
    }

    @org.junit.Test
    public void test1() {


        queue.push(1);
        queue.push(2);
        Assert.assertEquals(1, queue.peek());
        Assert.assertEquals(1, queue.pop());
        Assert.assertFalse(queue.empty());
    }

}