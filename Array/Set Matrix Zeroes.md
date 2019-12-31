## 73. Set Matrix Zeroes

> Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.

Example 1:      
> Input:       
[      
  [1,1,1],      
  [1,0,1],      
  [1,1,1]      
]      
Output:       
[      
  [1,0,1],      
  [0,0,0],      
  [1,0,1]      
> ]      


### record location of zero

use two array to record which row and col should be set zero      


**Time Complexity is O(N2), Space is O(N+M)**


```java
class Solution {
    public void setZeroes(int[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return;
        boolean []row = new boolean [matrix.length];
        boolean []col = new boolean [matrix[0].length]; 
        
        for(int i = 0; i < row.length; i++){
            for(int j = 0; j < col.length; j++){
                if(matrix[i][j] == 0){
                    row[i] = true;
                    col[j] = true;
                }
            }
        }
        for(int i = 0; i < row.length; i++){
            for(int j = 0; j < col.length; j++){
               if(row[i] || col[j]) matrix[i][j] = 0;
            }
        }
    }
}
```


