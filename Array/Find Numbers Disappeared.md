## 41. First Missing Positive
> Given an unsorted integer array, find the smallest missing positive integer.
Example 1:
> Input: [1,2,0]
> Output: 3


#### sort in place
O(n) time
need consider number < 0 and number > length


```java
class Solution {
    public int firstMissingPositive(int[] nums) {
        for(int i = 0; i< nums.length; i++){
            while(nums[i]!= i+1 && nums[i]>0 && nums[i] <= nums.length && nums[i] != nums[nums[i]-1]) swap(nums, i, nums[i]-1);
        }
        for(int i = 0 ;i< nums.length; i++){
            if(nums[i] != i+1) return i+1;
        }
        return nums.length+1;
    }
    
    public void swap(int [] num, int a, int b){
        int temp = num[a];
        num[a] = num[b];
        num[b] = temp;
    }
}
```


## 448. Find All Numbers Disappeared in an Array

> Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
> Find all the elements of [1, n] inclusive that do not appear in this array.
> Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:
>  Input:
>  [4,3,2,7,8,2,3,1]
>  Output:
>  [5,6]

number is *unique*


#### Sort in place
put each number in its own place
O(n) time 

```java
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i< nums.length; i++){
            while(nums[i] != i+1 && nums[i] != nums[nums[i]-1]) swap(nums, i,nums[i]-1);
        }
        for(int i = 0; i< nums.length; i++){
            if(nums[i] != i+1) res.add(i+1);
        }
        return res;
    }
    
    public void swap(int [] num, int a, int b){
        int temp = num[a];
        num[a] = num[b];
        num[b] = temp;
    }
}
```
***
#### mark as negative

mark number existed as negative number, then add positive into list

```java
 public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ret = new ArrayList<Integer>();
        
        for(int i = 0; i < nums.length; i++) {
            int val = Math.abs(nums[i]) - 1;
            if(nums[val] > 0) {
                nums[val] = -nums[val];
            }
        }
        
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] > 0) {
                ret.add(i+1);
            }
        }
        return ret;
    }
```

## 442. Find All Duplicates in an Array
> Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
> Find all the elements that appear twice in this array.
> Could you do it without extra space and in O(n) runtime?

Example:
> Input:
> [4,3,2,7,8,2,3,1]
> Output:
> [2,3]

#### sort in place
numbers limited in 1-n

```java 
class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i< nums.length; i++){
            while(nums[i] != i+1 && nums[i] != nums[nums[i]-1]) swap(nums, i,nums[i]-1);
        }
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != i+1) res.add(nums[i]);
        }
        return res;
    }
    public void swap(int [] num, int a, int b){
        int temp = num[a];
        num[a] = num[b];
        num[b] = temp;
    }
}
```

#### mark as positve

```java
class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) {
            int val = Math.abs(nums[i]) - 1;
            if(nums[val] < 0) 
                res.add(val + 1);
            else
                nums[val] = -nums[val];
        }
        return res;
    }
}
```
