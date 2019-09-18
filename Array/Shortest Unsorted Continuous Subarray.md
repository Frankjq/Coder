## 581. Shortest Unsorted Continuous Subarray

> Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.
> You need to find the shortest such subarray and output its length.
Example 1:
> Input: [2, 6, 4, 8, 10, 9, 15]
> Output: 5
> Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.

#### sort and compare 
O(nlgn)

```java
public class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int[] snums = nums.clone();
        Arrays.sort(snums);
        int start = snums.length, end = 0;
        for (int i = 0; i < snums.length; i++) {
            if (snums[i] != nums[i]) {
                start = Math.min(start, i);
                end = Math.max(end, i);
            }
        }
        return (end - start >= 0 ? end - start + 1 : 0);
    }
}
```

#### finding peak and valley
-> stock

![figures](https://leetcode.com/problems/shortest-unsorted-continuous-subarray/Figures/581/Unsorted_subarray_2.PNG)

O(n)
improved in one pass
```java
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int lo = 0;
        int hi = -1;
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i< nums.length; i++){
            max = Math.max(max, nums[i]);
            if(max != nums[i]) hi = i;
            
            min = Math.min(min, nums[nums.length - 1 -i]);
            if(min != nums[nums.length - 1 -i]) lo = nums.length - 1 -i;

        }
 
        return hi - lo +1;
    }
}
```
