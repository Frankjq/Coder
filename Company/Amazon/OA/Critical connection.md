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
        for(int i = 0; i < n ;i++){ // init
            edges[i] = new ArrayList<>();
        }
        
        for(List<Integer> edge: connections){// bidirection 
            int from = edge.get(0);
            int to = edge.get(1);
            edges[from].add(to);
            edges[to].add(from);
        }
        
        boolean [] visited = new boolean[n];
        int [] nodeId = new int [n];
        int [] low = new int [n];
        
        List<List<Integer>> res = new ArrayList<>();
        
        for(int i = 0 ; i < n; i++){
            if(!visited[i]){
            	// root node 
                dfs(i, visited, nodeId, low, edges, res, 0);
            }
        }
        return res;
    }
    int time = 0;
    public void dfs(int u, boolean [] visited, int [] disc, int [] low,  List<Integer> [] graph, List<List<Integer>> res, int pre){
        visited[u] = true;
        nodeId[u] = low[u] = ++time;
        for(int i : graph[u]){
            int v = i;
            if(pre == v) continue;
            if(!visited[v]){
                dfs(v, visited, nodeId, low, graph, res, u);
                low[u] = Math.min(low[u],low[v]); 
                if(low[v] > nodeId[u]){// check if it is bridge // if from(u) < to(v), it is bridge
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


## Critical Routers

> AWS wants to increase the reliability of their network by upgrading crucial data center routers. Each data center has a single router that is connected to every other data center through a direct link or some other data center.
> To increase the fault tolerance of the network, AWS wants to identify routers which would result in a disconnected network if they went down and replace then with upgraded versions.
> Write an algorithm to identify all such routers.

Example:
```
Input:
numRouters = 7
numLinks = 7
Links = [[0,1], [0, 2], [1, 3], [2, 3], [2, 5], [5, 6], [3,4]]

Output:
[2, 3, 5]
```

#### 
find all nodes
