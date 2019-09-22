## Leetcode 21. Merge Two Sorted Lists

> Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

Example:
> Input: 1->2->4, 1->3->4
> Output: 1->1->2->3->4->4

#### create new node list
 new a node and make a loop of all nodes and connect all

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        
        ListNode head = new ListNode(0);
        ListNode cur = head;
        
        while(l1!=null && l2!= null){
            if (l1.val > l2.val){
                cur.next = l2;
                l2 = l2.next;
            }else{
                cur.next =l1;
                l1 = l1.next;
            }
            
            cur = cur.next;
        }
        
        if(l1==null) cur.next = l2;
        if(l2==null) cur.next = l1;

        return head.next;
    }
}
```

********

#### recursion (better)
find smaller value and connect to next node


```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;  
        if(l1.val < l2.val){
            ListNode node = l1;
            l1 = l1.next;
            node.next = mergeTwoLists(l1, l2);
            return node;
        }else{
            ListNode node = l2;
            l2 = l2.next;
            node.next = mergeTwoLists(l1, l2);
            return node;
        }
    }
}
```
