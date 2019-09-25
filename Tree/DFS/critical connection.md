## 1192. Critical Connections in a Network
> There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network where connections[i] = [a, b] represents a connection between servers a and b. Any server can reach any other server directly or indirectly through the network.

> A critical connection is a connection that, if removed, will make some server unable to reach some other server.
> Return all critical connections in the network in any order.


#### dps
find all bridges in graph
> tarjan

```java 
class Solution {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<Integer> [] edges = new ArrayList [n];
        for(int i = 0; i < n ;i++){
            edges[i] = new ArrayList<>();
        }
        
        for(List<Integer> edge: connections){
            int from = edge.get(0);
            int to = edge.get(1);
            edges[from].add(to);
            edges[to].add(from);
        }
        
        boolean [] visited = new boolean[n];
        int [] disc = new int [n];
        int [] low = new int [n];
        
        List<List<Integer>> res = new ArrayList<>();
        
        for(int i = 0 ; i < n; i++){
            if(!visited[i]){
            	// root node 
                dfs(i, visited, disc, low, edges, res, 0);
            }
        }
        return res;
    }
    int time = 0;
    public void dfs(int u, boolean [] visited, int [] disc, int [] low,  List<Integer> [] graph, List<List<Integer>> res, int pre){
        visited[u] = true;
        disc[u] = low[u] = ++time;
        for(int i : graph[u]){
            int v = i;
            if(pre == v) continue;
            if(!visited[i]){
                dfs(v, visited, disc, low, graph, res, u);
                low[u] = Math.min(low[u],low[v]);
                if(low[v] > disc[u]){// check if it is bridge
                    if(u < v)                     
                        res.add(Arrays.asList(u,v));
                    else
                        res.add(Arrays.asList(v,u));
                }
            }else{
                low[u] = Math.min(low[u], low[v]);
            }
        }
    }
}
```
