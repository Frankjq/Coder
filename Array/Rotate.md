## 48. Rotate Image

>  You are given an n x n 2D matrix representing an image.
>  Rotate the image by 90 degrees (clockwise).
>  Note:
>  You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

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
***
### anticlockwise

1. swap two times 
2. anticlockwise = > left to right -> symmetry

---
## 189. Rotate Array

>  Given an array, rotate the array to the right by k steps, where k is non-negative.

  Example 1:
>  Input: [1,2,3,4,5,6,7] and k = 3
>  Output: [5,6,7,1,2,3,4]
>  Explanation:
>  rotate 1 steps to the right: [7,1,2,3,4,5,6]
>  rotate 2 steps to the right: [6,7,1,2,3,4,5]
>  rotate 3 steps to the right: [5,6,7,1,2,3,4]



1. create a new array is easy but more space O(n) space O(n) time
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





