## 53. Maximum Subarray

> Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:
> Input: [-2,1,-3,4,-1,2,1,-5,4],
> Output: 6
> Explanation: [4,-1,2,1] has the largest sum = 6.


#### linear method or DP
O(n)

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int curMax = nums[0];
        for(int i = 1; i< nums.length; i ++){
            if(curMax < 0){
                curMax = nums[i];
            }else{
                curMax += nums[i];
            }
            max = Math.max(max, curMax);
        }
        return max;
    }
}
```



## 152. Maximum Product Subarray

> Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:
> Input: [2,3,-2,4]
> Output: 6
> Explanation: [2,3] has the largest product 6.

#### linear method or DP
due to product, we store two var, min and max

```java
// dp[i] = max product subarray ending with i 
// dp[i] = dp[i-1]*nums[i]

class Solution {
    public int maxProduct(int[] nums) {
        int res = nums[0];
        int max = nums[0];
        int min = nums[0];
        for(int i = 1; i < nums.length; i++){
            int temp = max;
            //speed up divide into two phase
            max = Math.max(Math.max(max*nums[i], min*nums[i]), nums[i]);
            min = Math.min(Math.min(temp*nums[i], min*nums[i]), nums[i]);
            res = Math.max(max, res);
        }
        return res;
        
    }
}
```


## 628. Maximum Product of Three Numbers

> Given an integer array, find three numbers whose product is maximum and output the maximum product.

### pick three nums
max(max1*max2*max3, max1*min1*min2)

#### linear pick O(n)
#### sort pick O(lgn)
```java
class Solution {
    public int maximumProduct(int[] nums) {
        
        Arrays.sort(nums);
        int pro = nums[0] * nums[1] * nums[nums.length - 1];
        int res = pro;
        pro = nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3];
        return  Math.max(pro, res);
    }
}
```