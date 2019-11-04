## 490. The Maze
> There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
> Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.
> The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

#### bfs

queue to loop
```java
class Solution {   
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean [][] visited = new boolean [maze.length][maze[0].length];
        Queue<int[]> queue = new LinkedList<>();
        int[][] dis  = {{0,1},{1,0},{0,-1},{-1,0}};
        
        queue.add(start);
        visited[start[0]][start[1]] = true;
        
        while(!queue.isEmpty()){
            int [] node = queue.poll();            
            for(int [] x : dis){
                int r = node[0];
                int c = node[1];
                while(r < maze.length && r >=0 && c < maze[0].length && c >=0 && maze[r][c]!=1 ){
                    r += x[0];
                    c += x[1];
                }    
                if(!visited[r-x[0]][c-x[1]]){
                    if(r-x[0]==destination[0] && c-x[1] == destination[1]) return true;
                    queue.add(new int [] {r-x[0], c-x[1]});
                    visited[r-x[0]][c-x[1]] = true;
                }
            }
        }
        return false;
    }
}
```

#### dfs
```java
class Solution {
    class Node{
        private int row;
        private int col;
        Node(int r, int c){
            row = r;
            col = c;
        }
    }
    
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        Node st = new Node(start[0], start[1]);
        Node end = new Node(destination[0], destination[1]);
        
        boolean [][] visited = new boolean [maze.length][maze[0].length];
        return  helper(maze, st, visited, end);
    }
    
    public boolean helper(int[][] maze,Node st, boolean [][] visited, Node des){
        if(visited[st.row][st.col]) return false;
        
        if( st.row == des.row && st.col == des.col) return true;

        visited[st.row][st.col] = true;

        //right
        int col = st.col;
        int r = st.row;
        while(r < maze.length && maze[r][col] != 1 ){
            r++;
        }
        Node p = new Node(r-1, col);
        if(helper(maze, p, visited, des))
           return true;
        
        //left
        r = st.row;
        while( r >= 0 &&maze[r][col] != 1 ){
            r--;
        }
        p = new Node(r+1, col);
        if(helper(maze, p, visited, des))
           return true;
        
        //down
        int c = st.col;
        int row = st.row;
        while( c < maze[0].length && maze[row][c] != 1){
            c++;
        }
         p = new Node(row, c-1);
        if(helper(maze, p, visited, des))
            return true;

        //up
        c = st.col;
        while( c >= 0 && maze[row][c] != 1){
            c--;
        }
        p = new Node(row, c+1);
        if(helper(maze, p, visited, des))
            return true;
        
        return false;
    }
}
```


## 505. The Maze II
> There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
> Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.
> The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.



#### dfs
```java
class Solution {
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;
        
        int [][] distance = new int[m][n];    
        for(int []row : distance)
            Arrays.fill(row, n*m);
        distance[start[0]][start[1]] = 0;
        dfs(distance, maze, start);
        return distance[destination[0]][destination[1]]== n*m ? -1: distance[destination[0]][destination[1]];
    }
    
    public void dfs(int [][] distance, int[][] maze, int [] start){
        int i = start[0];
        int j = start[1];
        int x = i;
        while(x < maze.length && maze[x][j] != 1)
            x++;
        if(distance[x-1][j] > distance[i][j] + x-1-i){
            distance[x-1][j] = distance[i][j] + x-1-i; 
            dfs(distance, maze, new int[]{x-1, j});
        }
       
        x = i;
        while(x >=0 && maze[x][j] != 1)
            x--;
        if(distance[x+1][j] > distance[i][j] + i -x -1){
            distance[x+1][j] = distance[i][j] + i -x -1; 
            dfs(distance, maze, new int[]{x+1, j});
        }
        
        x = j;
        while(x >=0 && maze[i][x] != 1)
            x--;
        if( distance[i][x+1] > distance[i][j] + j -x -1){
            distance[i][x+1]= distance[i][j] + j -x -1; 
            dfs(distance, maze, new int[]{i, x+1});
        }

        x = j;
        while(x < maze[0].length && maze[i][x] != 1)
            x++;
        if(distance[i][x-1] > distance[i][j] + x -1 - j){
            distance[i][x-1] = distance[i][j] + x -1 - j; 
            dfs(distance, maze, new int[]{i, x-1});
        }
    }
}
```


#### bfs
```java
class Solution {
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int [][] distance = new int  [maze.length][maze[0].length];
        Queue<int[]> queue = new LinkedList<>();
        int[][] dis  = {{0,1},{1,0},{0,-1},{-1,0}};
        
        //inital
        queue.add(start);
        for(int[] row : distance){
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        distance[start[0]][start[1]] = 0;
        
        //bfs
        while(!queue.isEmpty()){
            int [] node = queue.poll();            
            for(int [] x : dis){
                int r = node[0];
                int c = node[1];
                int count = 0;
                while(r < maze.length && r >=0 && c < maze[0].length && c >=0 && maze[r][c]!=1 ){
                    r += x[0];
                    c += x[1];
                    count ++;
                }    
                if(distance[node[0]][node[1]] + count -1 < distance[r-x[0]][c-x[1]]){
                    queue.add(new int [] {r-x[0], c-x[1]});
                    distance[r-x[0]][c-x[1]] = distance[node[0]][node[1]] + count -1;
                }
            }
        }
        return distance[destination[0]][destination[1]]== Integer.MAX_VALUE ? -1: distance[destination[0]][destination[1]];
    }
}
```