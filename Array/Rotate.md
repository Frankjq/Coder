## [48. Rotate Image](https://leetcode.com/problems/rotate-image/)
  
>  You are given an n x n 2D matrix representing an image.    
>  Rotate the image by 90 degrees (clockwise).   
>  Note: You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.    

  Example 1:
>  Given input matrix =   
>  [
>    [1,2,3],  
>    [4,5,6],  
>    [7,8,9]
>  ],  
>  rotate the input matrix in-place such that it becomes:  
>  [
>    [7,4,1],   
>    [8,5,2],   
>    [9,6,3]
>  ]   

### clockwise 90 degrees 

1. swap two times 
2. clockwise = > up to down -> symmetry
```java
class Solution {
    public void rotate(int[][] matrix) {
        for(int i = 0; i < matrix.length /2; i++){
            int [] temp = matrix[i];
            matrix[i] = matrix[matrix.length - 1 -i];
            matrix[matrix.length - 1 -i] =  temp;
        }        
        
        for(int i = 0; i < matrix.length ; i++){
            for(int j = i +1; j < matrix.length; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        
    }
}
```

### anticlockwise

1. swap two times 
2. anticlockwise = > left to right -> symmetry


## [796. Rotate String](https://leetcode.com/problems/rotate-string/)

> A shift on A consists of taking string A and moving the leftmost character to the rightmost position. For example, if A = 'abcde', then it will be 'bcdea' after one shift on A. Return True if and only if A can become B after some number of shifts on A.     

### Brute force

**Time Complexity is O(n2)**

```java
class Solution {
    public boolean rotateString(String A, String B) {
        if (A.length() != B.length())
            return false;
        if (A.length() == 0)
            return true;

        search:
            for (int s = 0; s < A.length(); ++s) {
                for (int i = 0; i < A.length(); ++i) {
                    if (A.charAt((s+i) % A.length()) != B.charAt(i))
                        continue search;
                }
                return true;
            }
        return false;
    }
}
```

### KMP (Knuth-Morris-Pratt)

forming a string with double A and find if new string has B.    
**Time Complexity is O(n)**

```java
class Solution {
    public boolean rotateString(String A, String B) {
        if(A.length() == B.length() && A.length() == 0) return true;
        if(A.length() != B.length()) return false;
        
        String newA = A + A;
        for(int i = 0; i < A.length(); i ++){
            if(isSubstring(newA.substring(i,i+B.length()), B))
                return true;
        }
        return false;
    }
    
    boolean isSubstring(String a, String b){
        return a.equals(b);
    }
}
```

## [189. Rotate Array](https://leetcode.com/problems/rotate-array/)

>  Given an array, rotate the array to the right by k steps, where k is non-negative.

  Example 1:
>  Input: [1,2,3,4,5,6,7] and k = 3    
>  Output: [5,6,7,1,2,3,4]     
>  Explanation:     
>  rotate 1 steps to the right: [7,1,2,3,4,5,6]    
>  rotate 2 steps to the right: [6,7,1,2,3,4,5]    
>  rotate 3 steps to the right: [5,6,7,1,2,3,4]      



1. create a new array is easy but more space => O(n) space O(n) time
2. O(nk) time but O(1) space

reverse three times:
* reversing all numbers     
* reversing first k numbers 
* revering last n-k numbers 

```java
class Solution {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        
        reverse(nums, 0, nums.length-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, nums.length-1);
           
    }
    
    public void reverse(int[] nums, int lo, int hi){
        while(lo < hi){
            int temp = nums[lo];
            nums[lo] = nums[hi];
            nums[hi] = temp;
            lo ++;
            hi --;
        }
    }
}
```

## [61. Rotate List](https://leetcode.com/problems/rotate-list/)

> Given a linked list, rotate the list to the right by k places, where k is non-negative.

Example:

> Input: 1->2->3->4->5->NULL, k = 2    
> Output: 4->5->1->2->3->NULL    
Explanation:     
> rotate 1 steps to the right: 5->1->2->3->4->NULL   
> rotate 2 steps to the right: 4->5->1->2->3->NULL    


### find node to split list

1. count the total number of nodes
2. find the new tail and new head
3. reconstruct

**Time Complexity is O(N)**

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
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null) return head;
        
        int length = 1;
        ListNode point = head;
        while(point.next != null){
            length ++;
            point = point.next;
        }
        point.next = head;
        
        int index = length - k % length;
        point = head;
        for(int i = 0; i < index-1; i++){
            point = point.next;
        }
        
        ListNode res = point.next;
        point.next = null;
        return res;
    }
}
```

## [788. Rotated Digits](https://leetcode.com/problems/rotated-digits/)

> X is a good number if after rotating each digit individually by 180 degrees, we get a valid number that is different from X.  Each digit must be rotated - we cannot choose to leave it alone.
> A number is valid if each digit remains a digit after rotation. 0, 1, and 8 rotate to themselves; 2 and 5 rotate to each other; 6 and 9 rotate to each other, and the rest of the numbers do not rotate to any other number and become invalid.
> Now given a positive number N, how many numbers X from 1 to N are good?


Example:
> Input: 10      
Output: 4      
Explanation:       
There are four good numbers in the range [1, 10] : 2, 5, 6, 9.      
> Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.      

0, 1, 8 can rotate by themselves;      
2 <=> 5;
6 <=> 9;

#### 









