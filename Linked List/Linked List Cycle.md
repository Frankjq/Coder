## 141.Linked List Cycle I
Given a linked list, determine if it has a cycle in it.

To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. 
If pos is -1, then there is no cycle in the linked list.


Example 1:

> Input: head = [3,2,0,-4], pos = 1
> Output: true

### save data in set
  O(n) time
  O(n) sapce
### two points
  
```java
  /**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;
        
        ListNode slow = head;
        ListNode fast = head.next;
        while(slow != fast){
            if(fast == null || fast.next == null) return false;
            
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return true;
    }
}
```


## 142. Linked List Cycle II
follow up in #141, need return the fist node of circle

```java
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) return null;
        
        ListNode slow = head;
        ListNode fast = head;
        do{
            if(fast.next == null || fast.next.next == null) return null; //avoid no circle
            slow = slow.next;
            fast = fast.next.next;
        }while(slow != fast);
        
        //find entry
        slow = head;
        while(slow!=fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
            
    }
}
```
