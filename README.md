## 第一题
> 确实没看懂题意，但并不想在网上搜答案，期待进一步交流

## 第二题
### 思路
需要两个 Stack 去实现
Stack s1 负责队尾
Stack s2 负责队头

主要是 Queue peek() 时，需要从 s2 中取，这时我们需要将 s1 的数据转移到 s2

### 代码
```java
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
```

## 第三题

### 思路
lru 就是给定一个固定容量的缓冲池,一旦满了就淘汰最早未使用的数据

1. 使用 HashMap 存储和查找数据，使得时间复杂度是 O(1)
2. 使用 双向链表 记录使用使用情况，最近使用的放队尾、最早使用的放队头
3. 每次查询 HashMap 都将节点更新到 双向链表 的末端
4. 每次插入时，检查容量是否已满，若已满先删除 双向链表 的头节点


LinkedHashMap 本质上就是双向链表 + HashMap
```java
public class LRUCache {
    private int capacity;
    private Map<Integer, Node> map;
    private DoubleList cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        makeRecently(key);
        return map.get(key).val;
    }

    public void put(int key, int val) {
        if (map.containsKey(key)) {
            // 删除旧的数据
            deleteKey(key);
            // 新插入的数据为最近使用的数据
            addRecently(key, val);
            return;
        }

        if (cache.size() == capacity) {
            removeLeastRecently();
        }

        addRecently(key, val);
    }

    /**
     * 实现 DoubleList、Map 删除和增加同步
     */
    /* 将某个 key 提升为最近使用的 */
    private void makeRecently(int key) {
        Node node = map.get(key);
        cache.remove(node);
        cache.addLast(node);
    }

    /* 添加最近使用的元素 */
    private void addRecently(int key, int val) {
        Node node = new Node(key, val);
        cache.addLast(node);
        map.put(key, node);
    }

    /* 删除某一个 key */
    private void deleteKey(int key) {
        Node node = map.get(key);
        cache.remove(node);
        map.remove(key);
    }

    /* 删除最久未使用的元素 */
    private void removeLeastRecently() {
        Node node = cache.removeFirst();
        if (node != null) {
            map.remove(node.key);
        }
    }

    /**
     * 自定义实现双向链表
     */
    class Node {
        public int key, val;
        public Node next;
        public Node prev;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    class DoubleList {

        public Node head;
        public Node tail;
        public int size;

        public DoubleList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        public void addLast(Node x) {
            x.prev = tail.prev;
            x.next = tail;
            tail.prev.next = x;
            tail.prev = x;
            size++;
        }

        public void remove(Node x) {
            x.prev.next = x.next;
            x.next.prev = x.prev;
            size--;
        }

        public Node removeFirst() {
            if (head.next == tail) {
                return null;
            }
            Node first = head.next;
            remove(first);
            return first;
        }

        public int size() {
            return size;
        }
    }

}
```


