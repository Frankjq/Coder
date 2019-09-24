## 	256	Paint House
> The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.


#### dp
dp[i][] += min( dp[i-1][other two] )

min (dp[][]) => choose min one from three 

```java
public int minCost(int[][] costs) {
    if(costs==null||costs.length==0)
        return 0;
 
    for(int i=1; i<costs.length; i++){
        costs[i][0] += Math.min(costs[i-1][1], costs[i-1][2]);
        costs[i][1] += Math.min(costs[i-1][0], costs[i-1][2]);
        costs[i][2] += Math.min(costs[i-1][0], costs[i-1][1]);
    }
 
    int m = costs.length-1;
    return Math.min(Math.min(costs[m][0], costs[m][1]), costs[m][2]);
}
```

## 265. Paint House II
extend to k colors 

decrease the time complexity, choose two min colors from all color cost 

```
for from 0 to last one house
    from  first to last color
        choose the min cost from previous color
 
```



## 276. Paint Fence

> There is a fence with n posts, each post can be painted with one of the k colors.You have to paint all the posts such that no more than two adjacent fence posts have the same color.

> Return the total number of ways you can paint the fence.
//最多有两个相同颜色

#### DP

if n = 0, 0
if n = 1, k
if n = 2, k * k-1 + k
if n = 3, k * k-1 + (k * k-1 + k) * k-1

dp[i] = dp[i-1] * k + dp[i-2] * (k-1)

