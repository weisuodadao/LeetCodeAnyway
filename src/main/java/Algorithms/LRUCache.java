package Algorithms;
import java.util.HashMap;

/**
 * Created by luoxianzhuo on 2018/8/9 16:34
 *
 * @author luoxianzhuo
 * @copyright Copyright 2014-2018 JD.COM All Right Reserved
 */

/**
 * 1、汇总概要
 以下思路涵盖了哈希与双向链表的结合使用、缓存设计等知识点
 2、题目
 Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 Follow up:
 Could you do both operations in O(1) time complexity?
 Example:
 LRUCache cache = new LRUCache( 2 /* capacity */

/**
 cache.put(1, 1);
 cache.put(2, 2);
 cache.get(1);       // returns 1
 cache.put(3, 3);    // evicts key 2
 cache.get(2);       // returns -1 (not found)
 cache.put(4, 4);    // evicts key 1
 cache.get(1);       // returns -1 (not found)
 cache.get(3);       // returns 3
 cache.get(4);       // returns 4
 3、审题
 设计一个简单版的最近使用缓存模型。缓存空间有容量限制，时间复杂度要求是O(1)。
 其中“最近使用”是指最近被访问过(被cache.get调用过）。
 4、解题思路
 以上对cache的操作有：添加(put)、查找(get)、替换(put)，因有容量限制，还需有删除，每次当容量满时，将最久未使用的节点删除。

 为快速添加和删除，我们可以用双向链表来设计cache，链表中从头到尾的数据顺序依次是，(最近访问)->...(最旧访问)：

 1）添加节点：新节点插入到表头即可，时间复杂度O(1)；

 2）查找节点：每次节点被查询到时，将节点移动到链表头部，时间复杂度O(n)

 3)  替换节点：查找到后替换(更新节点value)，将节点移动到链表头部；

 可见在查找节点时，因对链表需遍历，时间复杂度O(n)，为达到O(1)，可以考虑数据结构中加入哈希(hash)。

 我们需要用两种数据结构来解题：双向链表、哈希表
 */
public class LRUCache {
    class Node {
        int key;
        int value;
        Node next;
        Node pre;

        public Node(int key, int value, Node pre, Node next) {
            this.key = key;
            this.value = value;
            this.pre = pre;
            this.next = next;
        }
    }

    int capacity;
    int count;//cache size
    Node head;
    Node tail;
    HashMap<Integer, Node> hm;

    public LRUCache(int capacity) { //only initial 2 Node is enough, head/tail
        this.capacity = capacity;
        this.count = 2;
        this.head = new Node(-1, -1, null, null);
        this.tail = new Node(-2, -2, this.head, null);
        this.head.next = this.tail;
        hm = new HashMap<Integer, Node>();
        hm.put(this.head.key, this.head);
        hm.put(this.tail.key, this.tail);
    }

    public int get(int key) {
        int value = -1;
        if (hm.containsKey(key)) {
            Node nd = hm.get(key);
            value = nd.value;
            detachNode(nd); //detach nd from current place
            insertToHead(nd); //insert nd into head
        }
        return value;
    }

    public void put(int key, int value) {
        if (hm.containsKey(key)) { //update
            Node nd = hm.get(key);
            nd.value = value;
            //move to head
            detachNode(nd); //detach nd from current place
            insertToHead(nd); //insert nd into head
        } else { //add
            Node newNd = new Node(key, value, null, this.head);
            this.head.pre = newNd; //insert into head
            this.head = newNd;
            hm.put(key, newNd); //add into hashMap
            this.count++;
            if (this.count > capacity) { //need delete node
                removeNode();
            }
        }
    }

    //common func
    public void insertToHead(Node nd) {
        this.head.pre = nd;
        nd.next = this.head;
        nd.pre = null;
        this.head = nd;
    }

    public void detachNode(Node nd) {
        nd.pre.next = nd.next;
        if (nd.next != null) {
            nd.next.pre = nd.pre;
        } else {
            this.tail = nd.pre;
        }
    }

    public void removeNode() { //remove from tail
        int tailKey = this.tail.key;
        this.tail = this.tail.pre;
        this.tail.next = null;
        hm.remove(tailKey);
        this.count--;
    }

    public void printCache() {
        System.out.println("\nPRINT CACHE ------ ");
        System.out.println("count: " + count);
        System.out.println("From head:");
        Node p = this.head;
        while (p != null) {
            System.out.println("key: " + p.key + " value: " + p.value);
            p = p.next;
        }
        System.out.println("From tail:");
        p = this.tail;
        while (p != null) {
            System.out.println("key: " + p.key + " value: " + p.value);
            p = p.pre;
        }

    }

    public static void main(String[] args) {
        LRUCache lc = new LRUCache(3);
        lc.printCache();

        lc.put(1, 1);
        lc.put(2, 2);
        lc.put(3, 3);
        lc.printCache();

        lc.get(2);
        lc.printCache();

        lc.put(4, 4);
        lc.printCache();

        lc.get(1);
        lc.printCache();

        lc.put(3, 33);
        lc.printCache();
    }


}