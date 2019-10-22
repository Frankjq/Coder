## 55. Jump Game
> Given an array of non-negative integers, you are initially positioned at the first index of the array.
> Each element in the array represents your maximum jump length at that position.
> Determine if you are able to reach the last index.


#### try until last one
```java 
class Solution {
    public boolean canJump(int[] nums) {
        int maxindex = 0;
        for(int i = 0; i <= maxindex && i < nums.length; i++){
            maxindex = Math.max(maxindex, i + nums[i]);
            if(maxindex >= nums.length-1) return true;
            
        }
        return false;
    }
}
```


## 45. Jump Game II

find min number of jumb

#### dp
O(n2)
```java
//dp
// opt[i] current jump number
// opt[i] = for( min(opt[i+1]+1, opt[i]) - opt[i+nums[i]])
 	public int jump(int[] nums) {
        int [] dp = new int [nums.length];
        Arrays.fill(dp, nums.length);
        dp[0] = 0;
        for(int i = 0; i < dp.length; i ++){
            for(int j = i+1; j <= i + nums[i] && j < nums.length; j++){
                dp[j] = Math.min(dp[i]+1, dp[j]);
            }
        }
        return dp[nums.length-1];
    }
```

#### bfs

```java
// bfs levels
// 2 - 0
// 3,1 - 1
// 1, 4, -2
// 2 
class Solution {
    public int jump(int[] nums) {
 int res = 0;
        int curmax = 0;
        int curlevel = 0;
        for(int i = 0; i < nums.length; i++){
            if(i == nums.length-1){
                return res;
            }
            
            curmax = Math.max(curmax, i + nums[i]);
                        
            if(i == curlevel){
                curlevel = curmax;
                res++;
            }
        }
        
        return res;
    }
}
```