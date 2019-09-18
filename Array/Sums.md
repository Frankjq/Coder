## 1. Two Sum

> Given an array of integers, return indices of the two numbers such that they add up to a specific target.
> You may assume that each input would have exactly one solution, and you may not use the same element twice.
Example:
> Given nums = [2, 7, 11, 15], target = 9,
> Because nums[0] + nums[1] = 2 + 7 = 9,
> return [0, 1].

#### Brute force
O(n2) time
O(1) space
```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        for(int i = 0 ; i < nums.length;  i++){
            for(int j = i+1; j < nums.length; j++){
                if(nums[i] + nums[j]  == target){
                    return new int[]{i,j};
                }
            }
        }
        return new int []{-1,-1};
    }
}
```

#### two pass hash table 
O(n) time
O(n) space

#### improved one pass
O(n) time
O(n) space

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i ++){
            if(map.containsKey(target - nums[i])){
                return new int []{map.get(target - nums[i]), i};
            }
            map.put(nums[i],i);
        }
        return new int []{-1,-1};
    }
}
```
#### two points(due to find index -> X)
need to sort


***
## 15. 3Sum

> Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

>Note:
>The solution set must not contain duplicate triplets.

Example:

> Given array nums = [-1, 0, 1, 2, -1, -4],
> A solution set is:
> [
>   [-1, 0, 1],
>  [-1, -1, 2]
> ]



#### Brute force
O(n3) time
O(1) space

#### two points + loop
O(n2) time

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length -2; i ++){
            if(i != 0 && nums[i] == nums[i-1]) continue; // avoid duplicate 
            // find target - nums[i] with wto points
            int lo = i+1; 
            int hi = nums.length-1;
            while(lo < hi){
                if(nums[lo] + nums[hi] == 0 - nums[i]){
                    res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                    while (lo < hi && nums[lo] == nums[lo+1]) lo ++;
                    while (lo < hi && nums[hi] == nums[hi-1]) hi --;
                    lo ++; hi --;
                }else if(nums[lo] + nums[hi] < 0 - nums[i]){
                    lo ++;
                }else{
                    hi --;
                }
            }
        }
        return res;
    }
}
```

***
## 18. 4Sum

> Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
> Note:
> The solution set must not contain duplicate quadruplets.

Example:
>  Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
>  A solution set is:
>  [
>    [-1,  0, 0, 1],
>    [-2, -1, 1, 2],
>    [-2,  0, 0, 2]
>  ]

using two sum and three sum to decrease nums
O(n3) time


***
Kth sum to target

```java
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return Kthsum(nums, 5, 0, target);
    }
    
    public List<List<Integer>> Kthsum(int[] nums, int k, int start, int target){
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(start >= nums.length-1) return res;
        
        if(k == 2){
            int lo = start;
            int hi = nums.length -1;
            
            while(lo < hi){
                if(nums[lo] + nums[hi] == target){
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[lo]);
                    temp.add(nums[hi]);
                    res.add(temp);
                    while(lo < hi && nums[lo] == nums[lo+1]) lo++;
                    while(lo < hi && nums[hi-1] == nums[hi]) hi--;
                    lo ++;
                    hi --;
                }else if(nums[lo] + nums[hi] < target) lo ++;
                else hi --;        
            }
            
        }else{
            for(int i = start; i< nums.length-k+1; i++){
                if(i != start && nums[i] == nums[i-1]) continue;
                List<List<Integer>> temp = Kthsum(nums, k-1, i + 1, target-nums[i]);
                for(List<Integer> x : temp){
                    x.add(nums[i]);
                }
                res.addAll(temp);
            }
        }
        return res;
    }
}
```

