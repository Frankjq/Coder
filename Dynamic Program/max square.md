## 221. Maximal Square

> Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

#### dp
find each cell the largest length of square

grid[i-1][j-1]  grid[i-1][j]	
grid[i][j-1] 	grid[i][j] = min number of three around cells plus 1 if and only if it is 1

```java
class Solution {
    public int maximalSquare(char[][] matrix) {
        if(matrix.length ==0 || matrix[0].length == 0) return 0;
        int width = matrix.length;
        int heigth = matrix[0].length;
        int [][] grid = new int [width][heigth];
        int maxLen = 0;
        for(int i = 0; i < width; i ++){
            if(matrix[i][0] == '1') {
                grid[i][0] = 1;
                maxLen = 1;
            }
        }
        
        
        for(int i = 0; i < heigth; i ++){
            if(matrix[0][i] == '1'){
                grid[0][i] = 1;
                maxLen = 1;
            }
        }
        
        for(int i = 1; i < width; i ++){
            for(int j = 1; j < heigth; j++){
                if(matrix[i][j] == '1'){
                    grid[i][j] = Math.min(Math.min(grid[i-1][j], grid[i][j-1]), grid[i-1][j-1]) + 1;
                    maxLen = Math.max(grid[i][j], maxLen);
                }
            }
        }
        return maxLen * maxLen;
    }
}
```


## 85 max rectangle histogram

#### dp

dp[i][j] record ith row from left to jth width
	and compare each area from ith row to 0th row
```java
class Solution {
    public int maximalRectangle(char[][] matrix) {
        int res = 0;
        if(matrix.length ==0 || matrix[0].length == 0) return 0;

        int m = matrix.length;
        int n = matrix[0].length;
        
        int [][]dp = new int [m][n];
        for(int i = 0; i < m; i ++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == '1')
                    dp[i][j] =  (j == 0 ? 0 : dp[i][j-1]) + 1;
                int minWidth = dp[i][j];
                for(int k = i; k >=0 ;k --){
                    minWidth = Math.min(minWidth, dp[k][j]);
                    if(minWidth == 0) break;
                    res = Math.max(res, minWidth * (i-k+1));
                }
            }
            
        }
        return res;
    }
}
```


O(nm)
#### stack
each row look as a histogram 
combine record heights and stack computing max area

```java
class Solution {
    public int maximalRectangle(char[][] matrix) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        if(matrix.length ==0 || matrix[0].length == 0) return 0;

        int m = matrix.length;
        int n = matrix[0].length;
        int [][] grid = new int [m][n];
        for(int i = 0; i< n; i ++){
            if(matrix[0][i] == '1')
                grid[0][i] = 1;
            
            while(!stack.isEmpty() && grid[0][i] < grid[0][stack.peek()]){
                int top = stack.pop();
                res = Math.max(res, grid[0][top]*(i - (stack.isEmpty() ? 0 : stack.peek()+1) ));
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            int top = stack.pop();
            res = Math.max(res, grid[0][top] *(n - (stack.isEmpty() ? 0 : stack.peek()+1)));
        }
        
        for(int i = 1; i< m; i++){
            for(int j = 0; j< n; j++){
                if(matrix[i][j] == '1'){
                    grid[i][j] = 1 + grid[i-1][j];
                }
                
                while(!stack.isEmpty() && grid[i][j] < grid[i][stack.peek()]){
                    int top = stack.pop();
                    res = Math.max(res, grid[i][top]*(j - (stack.isEmpty() ? 0 : stack.peek()+1) ));
                }
                stack.push(j);
            }
            
            while(!stack.isEmpty()){
                int top = stack.pop();
                res = Math.max(res, grid[i][top] *(n - (stack.isEmpty() ? 0 : stack.peek()+1)));
            }
        }
        
        return res;
    }
}
```


## 84 Largest Rectangle in Histogram 
> Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

[https://assets.leetcode.com/uploads/2018/10/12/histogram.png]

O(n2)
#### brute force
```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        int max = 0;
        for(int i = 0; i < heights.length ; i ++){
            int minH = heights[i];
            for(int j = i; j >= 0 ; j--){
                minH = Math.min(minH, heights[j]);
                if(minH == 0) break;
                max = Math.max(max, minH*(i-j+1));
            }
        }
        return max;
    }
}
```

O(nlgn)
#### dc -> divide and conqure
```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        return divide(heights, 0, heights.length-1);
    }
    
    public int divide(int[] heights, int low, int high){
        if(high < low) return 0;
        int mid = low;
        
        for(int i = mid; i <= high; i++){
            if(heights[i] < heights[mid])
                mid = i;
        }
        int res = Math.max(divide(heights, low, mid-1), divide(heights, mid +1, high));
        return Math.max(res, heights[mid]*(high - low +1));
    }
}
```

#### impoved divide and conqure + sort
```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        return divide(heights, 0, heights.length-1);
    }
    
    public int divide(int[] heights, int low, int high){
        if(high < low) return 0;
        int mid = low;
        boolean sort = true;
        for(int i = mid+1; i <= high; i++){
            if(heights[i] < heights[i - 1]) sort = false;
            if(heights[i] < heights[mid]) mid = i;
        }
        if(sort){
            int m = 0;
            for(int i = low; i <= high; i ++){
                m = Math.max(heights[i]*(high-i+1) , m);
            }
            return m;
        }
        else
        {
            int res = Math.max(divide(heights, low, mid-1), divide(heights, mid +1, high));
            return Math.max(res, heights[mid]*(high - low +1));
        }
    }
}
```



O(n)
#### stack

push one until the height less than top one
make ascending order

otherwise pop and calculate the area 
>isEmpty()? 0 : stack.peek()+1 

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int resArea = 0;
        for(int i = 0; i < heights.length; i ++){
            while(!stack.isEmpty() && heights[i] < heights[stack.peek()]){
                int top = stack.pop();
                resArea = Math.max(resArea, heights[top]*(i - (stack.isEmpty() ? 0 : stack.peek()+1) ));
            }
            stack.push(i);
        }
        
        while(!stack.isEmpty()){
            int top = stack.pop();
            resArea = Math.max(resArea, heights[top] *(heights.length - (stack.isEmpty() ? 0 : stack.peek()+1)));
        }
        return resArea;
    }
}
```
