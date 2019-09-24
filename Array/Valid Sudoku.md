## 36. Valid Sudoku
> Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

> Each row must contain the digits 1-9 without repetition.
> Each column must contain the digits 1-9 without repetition.
> Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.


#### check each row , col and square

map--
set--
array-> '1'-'1' locate index

```java
class Solution{
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rowCheck = new boolean[9][9];
        boolean[][] colCheck = new boolean[9][9];
        boolean[][][] boxCheck = new boolean[3][3][9];
        for (int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){                
                if(board[i][j]!='.'){
                    if(rowCheck[i][board[i][j] - '1'] || colCheck[j][board[i][j] - '1'] || boxCheck[i/3][j/3][board[i][j]-'1']){
                        return false;
                    }else{
                        rowCheck[i][board[i][j] - '1'] = true;
                        colCheck[j][board[i][j] - '1'] = true;
                        boxCheck[i/3][j/3][board[i][j]-'1'] = true;
                    }
                       
                }
            }
        }
        
        return true;
    }
}
```


## 37. Sudoku Solver

Write a program to solve a Sudoku puzzle by filling the empty cells.

#### similar backtrack
search for validate num to decrease time
```java
class Solution {
    public void solveSudoku(char[][] board) {
        solve(board, 0);    
    }
    
    public boolean solve(char[][] board, int finished){
        if(finished == 81) return true;
        // better than two val, 
        int row = finished /9;
        int col = finished %9;
        
        if(board[row][col] != '.') return solve(board, finished+1);
        
        boolean [] notFilledNum = new boolean [9];
        //find all validate num
        search(board, notFilledNum, row, col);
        for(int i = 0 ; i < 9; i ++){
            if(notFilledNum[i]){
                board[row][col] =(char)('1' + i );  
                if(solve(board, finished+1)) return true;
            }
        }
        //if not track back
        board[row][col] = '.';
        return false;
    }
    
    public void  search(char [][]board, boolean [] notFilled, int row, int col){
        Arrays.fill(notFilled, true);
        int rowSquare = row /3;
        int colSquare = col /3;
        for(int i = 0; i < 9; i++){
            if(board[row][i] != '.') notFilled[board[row][i]-'1'] = false;
            if(board[i][col] != '.') notFilled[board[i][col]-'1'] = false;
            if(board[rowSquare*3 + i/3][colSquare*3 + i%3] != '.') notFilled[board[rowSquare*3 + i/3][colSquare*3 + i%3] - '1']= false;   
        }
    }
}
```

