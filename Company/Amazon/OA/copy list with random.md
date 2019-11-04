## 138. Copy List with Random Pointer

> A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

> Return a deep copy of the list.

#### copy in map 

```java
public Node copyRandomList(Node head) {
    if(head == null) return null;
    HashMap<Node, Node> map = new HashMap<>();
    
    Node point = head;
    while(point != null){
        map.put(point, new Node());
        map.get(point).val = point.val;
        point = point.next;
    }
    
    point = head;
    while(point != null){
        map.get(point).next = map.get(point.next);
        map.get(point).random = map.get(point.random);
        point = point.next;
    }
    
    return map.get(head);
}
```


#### copy

A-A' -B-B' -C-C'

```java
public Node copyRandomList(Node head) {
    if(head == null) return null;
    Node point = head;  
    //copy value
    while(point != null){
        Node copy = new Node();
        copy.val = point.val;
        copy.next = point.next;
        point.next = copy;
        point = copy.next;
    }
    //copy random
    point = head;
    while(point != null){
        Node copy = point.next;
        if(point.random != null)
            copy.random = point.random.next; 
        point = copy.next;
    }
    
    Node res = new Node();
    Node p = res;
    point = head;
    while(point != null){
        Node copy = point.next;
        Node next = point.next.next;
        
        p.next = copy;
        p = copy;
        point.next = next;
        point = next;
    } 
    return res.next;
}
```
