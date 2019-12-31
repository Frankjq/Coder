## 289. Game of Life

> According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."    
> Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):    
> Any live cell with fewer than two live neighbors dies, as if caused by under-population.    
Any live cell with two or three live neighbors lives on to the next generation.    
Any live cell with more than three live neighbors dies, as if by over-population.        
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.    
> Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.    



### Brute force

record new status for each element      
**Time Complexity is O(N2), Space is O(NM)**

```java
class Solution {
    public void gameOfLife(int[][] board) {
        if(board.length == 0) return;
        int [][] state= new int [board.length][board[0].length];
        for(int i = 0;  i < board.length ; i++){
            for(int j = 0; j < board[0].length ; j++){
                int num = check(board, i, j);
                if(board[i][j] == 1){
                    if(num <= 3 && num >=2) state[i][j] = 1;
                }else{
                    if(num == 3) state[i][j] = 1;
                }
            }
        }
        
		for(int i = 0;  i < board.length ; i++){
			for(int j = 0; j < board[0].length ; j++){
				board[i][j] = state[i][j];
			}
		}
    }
    
    public int check (int [][] board, int row, int col){
        int count = 0;
        int [] index = {0,-1, 1};
        
        for(int i = 0; i < 3 ; i++){
            for(int j = 0; j < 3 ; j++){
                if(!(index[i] ==0 && index[j]==0)){
                    int r = row + index[i];
                    int c = col + index[j];
                    if( (r >= 0 && r < board.length) &&  (c >= 0 && c < board[0].length)){
                        if(board[r][c] == 1) {
                            count ++;
                        }                       
                    }
                    
                }
            }
        }
        return count;
    }
}
```

### improved
**Time Complexity is O(N2), Space is O(1)**


```java


```




