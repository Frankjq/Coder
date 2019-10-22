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

## 212. Word Search II

> Given a 2D board and a list of words from the dictionary, find all words in the board.
> Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

#### backtrack to dfs
```java
class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        for(int i = 0; i < board.length; i ++){
            for(int j = 0; j < board[0].length; j ++){
                for(int k=0; k < words.length; k ++){
                    findW(board, i, j, words[k], 0, res);   
                }
            }     
        }
         return res;
    }
    
    public void findW(char [][]board, int r, int c, String w, int index, List<String> res){
        
        if(r < 0 || c < 0 || r >= board.length || c>= board[0].length || w.charAt(index) != board[r][c] ) return;
        
        if(index == w.length()-1){
            if(!res.contains(w))
                res.add(w);
            return;
        }
        
        board[r][c] = '.';
        findW(board, r+1, c, w, index+1, res);
        findW(board, r-1, c, w, index+1, res);
        findW(board, r, c+1, w, index+1, res);
        findW(board, r, c-1, w, index+1, res);

        board[r][c] = w.charAt(index); 
    }
}
```


#### better, backtrack + trietree

trietree is a tree saved letter and point to linked other letter


```java

class Solution {
    class TrieNode{
        private Map<Character, TrieNode> map;
        private boolean endWord;
        private String word;
        TrieNode(){
            map = new HashMap<>();
            endWord = false;
            word = null;
        }
    }
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        
        TrieNode root = new TrieNode();
                
        for(String word: words){
            TrieNode node = root;
            for(char letter: word.toCharArray()){
                if(node.map.containsKey(letter)){
                    node = node.map.get(letter);
                }else{
                    node.map.put(letter, new TrieNode());
                    node = node.map.get(letter);
                }
            }
            node.endWord = true;
            node.word = word;
        }
        
        for(int i = 0; i < board.length; i ++){
            for(int j = 0; j < board[0].length; j ++){
                if(root.map.containsKey(board[i][j])){
                    helper(board, i, j, root, res);   
                }
            }
        }
        return res;
    }
    
    public void helper(char[][] board, int row, int col, TrieNode node, List<String> res){
        node = node.map.get(board[row][col]);
        char letter = board[row][col];
        
        if(node.endWord){
            res.add(node.word);
            node.endWord = false;
        }
        
        
        int []offsetr = new int []{-1,1,0,0};
        int []offsetc = new int []{0,0,-1,1};
       
        board[row][col] = '.';
        for(int k = 0; k < 4 ; k++){
            int nr = row + offsetr[k];
            int nc = col + offsetc[k];

            if(nr < board.length && nc < board[0].length && nr >=0 && nc>= 0){
                if(node.map.containsKey(board[nr][nc]))
                    helper(board, nr, nc, node, res);
            }
        } 
        board[row][col] = letter;   
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