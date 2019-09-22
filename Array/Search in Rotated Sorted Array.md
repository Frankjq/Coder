## 33. Search in Rotated Sorted Array

> Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
> (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
> You are given a target value to search. If found in the array return its index, otherwise return -1.
> You may assume no duplicate exists in the array.
> Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:
> Input: nums = [4,5,6,7,0,1,2], target = 0
> Output: 4



### binary search 

O(log n) time

#### 1.

我们可以先找到哪一段是有序的 (只要判断端点即可)，然后看 target 在不在这一段里，如果在，那么就把另一半丢弃。如果不在，那么就把这一段丢弃。

```java
class Solution {
    public int search(int[] nums, int target) {
        
        int lo = 0; 
        int hi = nums.length -1;
        
        while(lo <= hi){
            
            
            int mid = (lo + hi) /2;
            
            if(nums[mid] == target) return mid;
            
            if(nums[mid] >= nums[lo]){
                if(nums[mid] > target && target >= nums[lo]) hi = mid -1;
                else lo = mid +1;
            }
            
            else{
                
                if(nums[mid] < target && target <= nums[hi]) lo = mid +1;
                else hi = mid -1;
            }
            
        }
        
        return -1;
        
    }
}
```


#### 2.

[12, 13, 14, 15, 16, 17, 18, 19, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]

compare with nums[0] and check if they are in same sides

nums[mid] - nums[0]
target - nums[0]
基于两个有序数组
同号为正 同侧 正常即可
异号为负 两侧 使得数组伪有序 正负无穷

```java
public int search(int[] nums, int target) {
    int lo = 0, hi = nums.length - 1;
    while (lo <= hi) {
        int mid = lo + (hi - lo) / 2;
        int num = nums[mid]; 
        //nums [ mid ] 和 target 在同一段
        if ((nums[mid] < nums[0]) == (target < nums[0])) {
            num = nums[mid];
        //nums [ mid ] 和 target 不在同一段，同时还要考虑下变成 -inf 还是 inf。
        } else {
            num = target < nums[0] ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }

        if (num < target)
            lo = mid + 1;
        else if (num > target)
            hi = mid - 1;
        else
            return mid;
    }
    return -1;
}
```


## 81. Search in Rotated Sorted Array II

when array contain duplicates

actually O(n) time

```java
class Solution {
    public boolean search(int[] nums, int target) {
        int lo = 0, 
        hi = nums.length - 1;
        // make fisrt one differ last one
        while(lo < hi && nums[hi] == nums[lo]) lo ++;
      	while(lo <= hi){
            int mid = (lo + hi) /2;
            
            if(nums[mid] == target) return true;
            
            if(nums[mid] >= nums[lo]){
                if(nums[mid] > target && target >= nums[lo]) hi = mid -1;
                else lo = mid +1;
            }
            
            else{
                if(nums[mid] < target && target <= nums[hi]) lo = mid +1;
                else hi = mid -1;
            }
        }    
        return false;
    }
}
```


