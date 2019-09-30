## 79. Word Search

Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.


#### dfs

```java
class Solution {
    public boolean exist(char[][] board, String word) {
        if(word.length() == 0) return true;
        
        for(int i = 0; i < board.length; i++ ){
            for(int j = 0; j < board[0].length; j++){
                if(dfs(board, word, 0, i, j))        
                    return true;
            }
        }
        return false;
    }
    
    public boolean dfs(char[][]board, String word, int index, int row, int col){
        if(index == word.length()) return true;
        
        if(row < 0 || col < 0|| row >= board.length || col >= board[0].length || word.charAt(index) != board[row][col])
            return false;
        
        board[row][col] = '.';
        boolean res = dfs(board, word, index+1, row + 1, col) ||
            dfs(board, word, index+1, row - 1, col) ||
            dfs(board, word, index+1, row , col+1)||
            dfs(board, word, index+1, row , col-1);
        
        board[row][col] = word.charAt(index);
        
        return res;
    }
}
```


## 200. Number of Islands

Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

#### dfs

```java
class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;
        for(int i = 0; i < grid.length; i ++){
            for(int j = 0; j < grid[0].length; j ++){
                if(grid[i][j]== '1'){ 
                    
                    count ++;
                    int c = bfs(grid, i, j);
                }
            }
        }
        
        return count;
    }
    int [] ver = new int [] {-1,1,0,0};
    int [] hor = new int [] {0,0,1,-1};
    public int bfs(char[][] grid, int row, int col){
        
        if(grid[row][col] ==  '0' ) return 0;
        if(grid[row][col] ==  '1') grid[row][col] = '0';
        int tot = 1;
        for(int i = 0; i < ver.length; i++){
            int r = row + ver[i];
            int c = col + hor[i];
            if(r >= 0 && r < grid.length && c >= 0 && c< grid[0].length){
                tot += bfs(grid, r, c) ;
            }      
        }
        
        return tot;
    }
    
}
```