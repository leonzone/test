package com.reiser.test;

import org.junit.Assert;

public class LRUCacheTest {
    LRUCache lruCache;

    @org.junit.Before
    public void setUp() throws Exception {
        lruCache = new LRUCache(2);
    }

    @org.junit.Test
    public void test1() {
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        Assert.assertEquals(1, lruCache.get(1));
        lruCache.put(3, 3);
        Assert.assertEquals(-1, lruCache.get(2));
        lruCache.put(4, 4);
        Assert.assertEquals(-1, lruCache.get(1));
        Assert.assertEquals(3, lruCache.get(3));
        Assert.assertEquals(4, lruCache.get(4));
    }

}