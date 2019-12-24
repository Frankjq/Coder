## 26. Remove Duplicates from Sorted Array

> Given a **sorted** array nums, remove the duplicates in-place such that each element appear **only once** and return the new length.    
> Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.    


#### two pointers

Since the array is already sorted, we can keep two pointers i and j, where i is the slow-runner while j is the fast-runner. As long as nums[i] == nums[j], we increment j to skip the duplicate.

**time complexity is O(n)**


```java
class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length < 2) return nums.length;
        int p = 0;
        for(int i = 1; i< nums.length; i++){
            if(nums[p] != nums[i]){
                p++;
                nums[p] = nums[i];
            }
        }
        return p+1;
    }
}
```

## 80. Remove Duplicates from Sorted Array II

> Given a **sorted** array nums, remove the duplicates in-place such that duplicates appeared at **most twice** and return the new length.  
> Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.  


#### two pointer, based on #26

need to compare times with 2

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length < 3) return nums.length;
        int p = 0;
        int count = 1;
        for(int i = 1; i< nums.length; i++){
            if(nums[p] != nums[i] ){
                p++;
                count = 1;
                nums[p] = nums[i];
                
            }else{
                count ++;
                if(count == 2){
                    p++;
                    nums[p] = nums[i];
                }
            }
        }
        return p+1;
    }
}
```

## 27. Remove Element
 
> Given an array nums and a value val, remove all instances of that value in-place and return the new length.    
> Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.   

#### two pointer

**for point i, if it is target val, move to next, otherwise exchange and move to next**


```java
class Solution {
    public int removeElement(int[] nums, int val) {
        if(nums.length == 0) return 0;
        int p = 0;
        for(int i = 0; i< nums.length; i++){
            if(nums[i] != val){
                nums[p++] = nums[i];
            }
        }
        return p;
    }
}
```


## 283. Move Zeroes

> Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.   

Example:

> Input: [0,1,0,3,12]       
> Output: [1,3,12,0,0]      

#### two pointer

```java
class Solution {
    public void moveZeroes(int[] nums) {
        int j  = 0 ;
        for(int i  = 0 ; i < nums.length; i++ ){
            if(nums[i] != 0){
                nums[j++] =  nums[i];
            }
        }
        for(; j < nums.length; j++){
            nums[j] = 0;
        }
    }
}
```
