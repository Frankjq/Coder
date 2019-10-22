## 207. Course Schedule

> There are a total of n courses you have to take, labeled from 0 to n-1.
> Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
> Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
Example 1:

> Input: 2, [[1,0]] 
> Output: true
> Explanation: There are a total of 2 courses to take.To take course 1 you should have finished course 0. So it is possible.

#### bfs 

```java
//find circle
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer> [] edges = new List [numCourses];
        int [] level = new int [numCourses];
        for(int i = 0; i< numCourses; i++){
            edges[i] = new ArrayList<>();
             
        }
        
        
        for(int [] edge : prerequisites){
            edges[edge[0]].add(edge[1]);
            level[edge[1]]++;
        }
        
        Queue<Integer> q = new LinkedList();
        
        for(int i = 0; i< level.length; i++){
            if(level[i] == 0){
                q.add(i);
                numCourses --;
            }
        }
        
        while(!q.isEmpty()){
            int n = q.poll();
            for(int i : edges[n]){
                level[i] --;
                if(level[i] == 0){
                    q.add(i);
                    numCourses --;
                }
            }
        }
        
        return numCourses == 0;
    }
    
    public int find(int [] parent, int from){
        if(parent[from] != from)
            return find(parent, parent[from]);
        return from;
    }
}
```

#### dfs
check each path find if it exsit circle

```java
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer> [] edges = new ArrayList[numCourses];
        
        for(int i = 0; i < numCourses; i++){
            edges[i] = new ArrayList<>();
        }
        for(int[] x :prerequisites){
            edges[x[0]].add(x[1]);
        }
        boolean res = true;
      
        for(int i = 0; i < numCourses; i ++){
            res = res && dfs(new HashSet<>(), i, edges);
        }
      
        return res;
    }
    
    public boolean dfs(HashSet<Integer> path, int node, List<Integer>[] edges){
        if(path.contains(node)) return false;
        boolean res = true;
        path.add(node);   
        for(int x :  edges[node]){
            res = res && dfs(path, x, edges);
        }
        path.remove(node);
        
        return  res;         
    }
}
```



improve speed, start from root and count all nodes
```java
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Set<Integer> root = new HashSet<>();
        Set<Integer> count = new HashSet<>();

        List<Integer> [] edges = new ArrayList[numCourses];
        
        for(int i = 0; i < numCourses; i++){
            edges[i] = new ArrayList<>();
            root.add(i);
        }
        for(int[] x :prerequisites){
            edges[x[0]].add(x[1]);
            root.remove(x[1]);
        }
        boolean res = true;
        if(!root.isEmpty()){
            for(int x: root){
                res = res && dfs(new HashSet<>(), x, edges, count);
            }
        }
        if(count.size()!=numCourses){
            res = false;
        }
        
        return res;
    }
    
    public boolean dfs(HashSet<Integer> path, int node, List<Integer>[] edges, Set<Integer> count){
        if(path.contains(node)) return false;
        count.add(node);
        boolean res = true;
        path.add(node);   
        for(int x :  edges[node]){
            res = res && dfs(path, x, edges, count);
        }
        path.remove(node);
        
        return  res;         
    }
}
```

## 210. Course Schedule II
