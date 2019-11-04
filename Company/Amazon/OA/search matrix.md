## 74. Search a 2D Matrix
> Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

> Integers in each row are sorted from left to right.
> The first integer of each row is greater than the last integer of the previous row.

#### binary search

```java
//binary search
//due to each row sorted and each col sorted
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = 0;
        if(matrix.length == 0) return false;
        int col = matrix[0].length-1;
        while(row < matrix.length && col>= 0){
            if(matrix[row][col] == target) return true;
            
            if(matrix[row][col] < target) row++;
            else col--;
        }
        return false;
    }
}
```

## 240. Search a 2D Matrix II
> Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

> Integers in each row are sorted in ascending from left to right.
> Integers in each column are sorted in ascending from top to bottom.

#### binary search
 \mathcal{O}(n+m)O(n+m)
```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0 || matrix[0].length == 0) return false;
        int row = 0;
        int col = matrix[0].length -1;
        while(row < matrix.length && col >=0){
            if(matrix[row][col] == target) return true;
            if(matrix[row][col] < target) row++;
            else    col--;
        }
        return false;
    }
}
```
