## 198. House Robber
> You are a professional robber planning to rob houses along a street. 
> Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

> Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.


#### Dynamic Program 
O(n) time
O(1) space

OPT(i)  the current biggest number
OPT(i) = MAX (OPT(i-1) , OPT(i-2) + num(i) );


```java
class Solution {
    public int rob(int[] nums) {
        if(nums.length == 0 ) return 0;
        if(nums.length == 1 ) return nums[0];
        
        int pre1 ;
        int pre2 =  nums[0];
        if(nums[1] < nums[0]) 
            pre1 = nums[0];
        else
            pre1 = nums[1];
        
        int res = pre1;
        
        for( int i = 2 ; i< nums.length ;  i ++){
            
            if(nums[i] + pre2 > pre1)
                res = nums[i] + pre2;
            else
                res = pre1;
 
            pre2 = pre1;
            pre1 = res;
            
        }        
        return res;  
    }
}
```

## 213. House Robber II

> You are a professional robber planning to rob houses along a street. 
Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. 
> That means the first house is the neighbor of the last one. 
> Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

#### dp
circle --> 首尾只有一个在list中
O(n) time 


```java

// rob[i] =  Max(nrob[i-1], rob[i-1])
// nrob[i] = rob[i-1] + num[i] 
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0],nums[1]);
        return Math.max(robHelp(nums,0,nums.length-2) , robHelp(nums,1,nums.length-1) );   
    }
    //1的helper function
    public int robHelp(int []nums, int low , int high){
        
        int  first = nums[low];
        int  second = Math.max(nums[low+1],first);
        int res = second;
        for (int i = low+2; i <= high; i++){
            res = Math.max(nums[i] + first, second);
            first = second;
            second = res;
        }
        
        return res;
    }
}
```

## 740. Delete and Earn
> Given an array nums of integers, you can perform operations on the array.

> In each operation, you pick any nums[i] and delete it to earn nums[i] points. After, you must delete every element equal to nums[i] - 1 or nums[i] + 1.
> You start with 0 points. Return the maximum number of points you can earn by applying such operations.

####dp more space

```java
//dp[i] = max num to i
//dp[i] = Max(dp[i-2],dp[i-3]) + all i
class Solution {
    public int deleteAndEarn(int[] nums) {
        int [] count = new int [10001];
        for(int x : nums){
            count[x] += x;
        }
        
        int [] dp =  new int [count.length];
        dp[0] = 0;
        dp[1] = count[1];
        dp[2] = count[2];
        
        for(int i = 3; i <  count.length; i++){
            dp[i] = Math.max(dp[i-2], dp[i-3]) + count[i];
        }
        
        return Math.max(dp[count.length-1], dp[count.length-2]);
    }
}

```

#### dp less spave

```java
// take[i] = skip[i-1] + values[i];
// skip[i] = Math.max(skip[i-1], take[i-1]);

class Solution {
    public int deleteAndEarn(int[] nums) {
        int [] count = new int [10001];
        for(int x : nums){
            count[x] += x;
        }
        
        int take = 0;
        int skip = 0;
        
        for(int i = 0 ; i< count.length ; i++)
        {
            int takei = skip + count[i];
            int skipi = Math.max(skip, take);
            
            take = takei;
            skip = skipi;
        }
        
        return Math.max(take, skip);
    }
}
```




## 337. House Robber III
> The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

form a binary tree

#### recursion

```java
//res[0] rob self
//res[1] not rob self
//memory reduce load time
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int rob(TreeNode root) {
        int [] res = helper(root);
        return Math.max(res[0],res[1]);
    }
    
    public int [] helper(TreeNode node){
        if(node == null) return new int[2];
        int [] left = helper(node.left);
        int [] right = helper(node.right);
        int [] res = new int [2];
        
        res[0] = node.val + left[1] + right[1];
        res[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return res;
        
    }
    
}
```
