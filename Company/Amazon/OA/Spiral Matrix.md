## 54. Spiral Matrix
> Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

#### for loop

```java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        
        int lowR = 0;
        int lowC = 0;
        int highR = matrix.length;
        if(highR == 0) return res;
        int highC = matrix[0].length;
        
        while(lowR<highR && lowC < highC){
            for(int c = lowC; c < highC; c++) res.add(matrix[lowR][c]);
            lowR++;
            
            if(!(lowR<highR && lowC < highC)) break;
            for(int r = lowR; r < highR; r++) res.add(matrix[r][highC-1]);
            highC--;
            
            if(!(lowR<highR && lowC < highC)) break;
            for(int c = highC-1; c >= lowC; c--) res.add(matrix[highR-1][c]);
            highR--;
            
            if(!(lowR<highR && lowC < highC)) break;
            for(int r = highR-1; r >= lowR; r--) res.add(matrix[r][lowC]);
            lowC++;
        }
        return res;
    }
}
```

## 59. Spiral Matrix II
> Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

Example:
> Input: 3
> Output:
> [
>  [ 1, 2, 3 ],
>  [ 8, 9, 4 ],
>  [ 7, 6, 5 ]
> ]

#### for loop

```java
class Solution {
    public int[][] generateMatrix(int n) {
        int [][]res = new int [n][n];
        
        int start = 1;
        int lowC = 0 , highC = n;
        int lowR = 0 , highR = n;
        int currentR , currentC;
        while(lowC < highC && lowR < highR){
            for(currentC = lowC; currentC < highC ; currentC++) res[lowR][currentC] = start ++;
            lowR++;
            
            if(!(lowC < highC && lowR < highR))break;
            for(currentR = lowR; currentR < highR ; currentR++) res[currentR][highC-1] = start ++;
            highC--;
            
            if(!(lowC < highC && lowR < highR))break;
            for(currentC = highC-1; currentC >= lowC ; currentC--) res[highR-1][currentC] = start ++;
            highR--;
            
            if(!(lowC < highC && lowR < highR))break;
            for(currentR = highR-1; currentR >= lowR ; currentR--) res[currentR][lowC] = start ++;
            lowC++;
        
        }
        return res;
    }
}
```