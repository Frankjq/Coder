## 146. LRU Cache

> Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

> get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
> put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

> The cache is initialized with a positive capacity.


#### hashmap + linkedlist node

```java
// linkedlist store data, put recent used data in first place

// get data, if not exist, return -1. if exist, return value, put first place
// put data, less capacity, put in first place, exceed capacity, delete last one, and put in first

// hashmap increase speed of searching data, O(1) better than O(n)

public class LRUCache {

    class DLinkedNode {
        int key;
        int value;
        DLinkedNode pre;
        DLinkedNode post;
        DLinkedNode(int key, int val){
            this.key = key;
            this.value = val;
            pre = null;
            post = null;
        }
    }

    /**
     * Always add the new node right after head;
     */
    private void addNode(DLinkedNode node) {
      node.pre = head;
      node.post = head.post;

      head.post.pre = node;
      head.post = node;
    }

    /**
     * Remove an existing node from the linked list.
     */
    private void removeNode(DLinkedNode node){
      DLinkedNode pre = node.pre;
      DLinkedNode post = node.post;

      pre.post = post;
      post.pre = pre;
    }

    /**
     * Move certain node in between to the head.
     */
    private void moveToHead(DLinkedNode node){
        this.removeNode(node);
        this.addNode(node);
    }


    private HashMap<Integer, DLinkedNode> cache; 
    private int count;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache(int capacity) {
        this.cache = new HashMap<Integer, DLinkedNode>();
        this.count = 0;
        this.capacity = capacity;

        head = new DLinkedNode(0,0);
        tail = new DLinkedNode(0,0);

        head.post = tail;
        tail.pre = head;
    }

    public int get(int key) {

      DLinkedNode node = cache.get(key);
      if(node == null){
        return -1; // should raise exception here.
      }

      // move the accessed node to the head;
      this.moveToHead(node);

      return node.value;
    }


    public void put(int key, int value) {
          DLinkedNode node = cache.get(key);
        
          if(node == null){
                DLinkedNode newNode = new DLinkedNode(key, value);

                this.cache.put(key, newNode);
                this.addNode(newNode);

                ++count;

                if(count > capacity){
                    DLinkedNode last = tail.pre;
                    this.removeNode(last);
                    this.cache.remove(last.key);
                    --count;
                }
          }else{
                // update the value.
                node.value = value;
                this.moveToHead(node);
          }
    }

}
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```