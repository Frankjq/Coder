## 11. Container With Most Water
> Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
> Note: You may not slant the container and n is at least 2.

Example:
> Input: [1,8,6,2,5,4,8,3,7]
> Output: 49


#### two points
shorter move to the longer one
```java
//two points

class Solution {
    public int maxArea(int[] height) {
        int lo = 0;
        int hi = height.length-1;
        int max = 0;
        
        while(lo < hi){
            int h = Math.min(height[lo], height[hi]);
            max = Math.max(h * (hi -lo), max);
            if(height[lo] > height[hi]){
                hi --;
            }else{
                lo ++;
            }
        }
        return max;
    }
}
```

## 42. Trapping Rain Water
> Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

![image](https://assets.leetcode.com/uploads/2018/10/22/rainwatertrap.png)

Example:
> Input: [0,1,0,2,1,0,1,3,2,1,2,1]
> Output: 6

#### two points
lower move to higher
caculate area
```java
//two points
public int trap(int[] height) {
    int res = 0;
    int lo = 0; 
    int hi = height.length-1;

    int lmax = 0;
    int rmax = 0;
    while(lo < hi){
        if(height[lo] < height[hi]){
            lmax = Math.max(height[lo], lmax);
            res += lmax - height[lo];
            lo++;
        }else{
            rmax = Math.max(rmax, height[hi]);
            res += rmax -height[hi];
            hi --;
        }    
    }
    return res;

}
```
    

#### two dp pass

```java
public int trap(int[] height) {
    if(height.length < 3) return 0;

    int [] dpleft = new int [height.length];
    int max = 0;
    for(int i = 0; i < height.length; i++){
        max = Math.max(max, height[i]);
        dpleft[i] = max - height[i];
    }

    int [] dpright = new int [height.length];
    max = 0;
    for(int i = height.length-1; i >= 0; i--){
        max = Math.max(max, height[i]);
        dpright[i] = max - height[i];
    }
    
    choose the min dp value
    int res = 0;
    for(int i =0; i< height.length; i++){
        res += Math.min(dpleft[i],dpright[i]);
    }

    return res;
}
```


