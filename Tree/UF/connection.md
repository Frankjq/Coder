## 684. Redundant Connection

In this problem, a tree is an undirected graph that is connected and has no cycles.

The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.

Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.


Example 1:
> Input: [[1,2], [1,3], [2,3]]
> Output: [2,3]
> Explanation: The given undirected graph will be like this:
>   1
>  / \
> 2 - 3


#### UF
```java
class Solution {
    class UF{
        int [] parent;
        
        public UF(int size){
            parent = new int [size+1];
            for(int i = 0; i< size+1; i++){
                parent[i] = i;
            }
        }
        
        public boolean union(int u, int v){
            int findu = find(u);
            int findv = find(v);
            
            if(findu == findv){
                return false;
            }
            if(findu < findv){
                parent[findu] = findv;
            }else{
                parent[findv] = findu;
            }
            return true;
        }
        
        public int find(int u){
            if(u == parent[u])  return parent[u];
            return find(parent[u]);
        }
    }
    
    
    public int[] findRedundantConnection(int[][] edges) {
        UF uf = new UF(edges.length);
        
        for (int i = 0; i <edges.length; i++){
            boolean ch = uf.union(edges[i][0], edges[i][1]);
            if(!ch){
                return edges[i];
            }
        }
        return new int[]{-1,-1};
        
    }
}
```

##### normal
```java
class Solution {
    
    class UF{
        private HashMap<Integer, Set<Integer>> map = new HashMap<>();
        private int index = 0;
        private boolean circle = false;
        public UF(){
                        
        }
        
        public int find(int u){
            for(Map.Entry<Integer, Set<Integer>> entry : map.entrySet()){
                if(entry.getValue().contains(u))
                    return entry.getKey();
            }
            return -1;
        }
        
        public void union(int u, int v){
            int uset = find(u);
            int vset = find(v);
            
            if (uset == -1 && vset == -1){
                map.put(index, new HashSet<Integer>());
                map.get(index).add(u);
                map.get(index).add(v);
                index ++;
            }
            
            else if (uset == -1 || vset == -1){
                int i = Math.max(uset, vset);
                map.get(i).add(u);
                map.get(i).add(v);
            }else{
                if(uset != vset){
                    map.put(index, new HashSet<Integer>());
                    map.get(index).addAll(map.get(uset));
                    map.get(index).addAll(map.get(vset));
                    map.remove(uset);
                    map.remove(vset);
                    index++;
                }else{
                    circle = true;
                }
            }
        }
        
        public boolean checkC(){
            return circle;
        }
    }
    
    public int[] findRedundantConnection(int[][] edges) {
       
        UF uf = new UF();
        
        for (int i = 0; i <edges.length; i++){
            uf.union(edges[i][0], edges[i][1]);
            if(uf.checkC()){
                return new int []{edges[i][0], edges[i][1]};
            }
            
        }
        
        return new int []{-1,-1};
        
        
    }
}
```


## 658. Redundant Connection II

In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.

The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 1, 2, ..., N), with one additional directed edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that represents a directed edge connecting nodes u and v, where u is a parent of child v.

Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array.


directed graph
#### UF

```java
class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
      
        List<Integer> [] rgraph = new ArrayList [edges.length+1];
        for(int i = 0; i<rgraph.length; i++){
            rgraph[i] = new ArrayList<>();
        }
        UF uf = new UF(edges.length);
        
        List<int []>  parent = new ArrayList<>();   
        
        
        for(int [] edge : edges){    
            rgraph[edge[1]].add(edge[0]);
            if(rgraph[edge[1]].size() > 1) {
                parent.add(new int [] {rgraph[edge[1]].get(0), edge[1]});
                parent.add(new int [] {rgraph[edge[1]].get(1), edge[1]});
                break;
            }
        }
        int i = 0;
        for(int [] edge : edges){
            
            if(parent.size()==0){
                boolean ch = uf.union(edge[0], edge[1]);
                if(!ch) return edge;
            }else{
                if(edge[0]!= parent.get(1)[0] || edge[1]!= parent.get(1)[1]){
                    boolean ch = uf.union(edge[0], edge[1]);
                  

                    if(!ch) return parent.get(0);
                }
            }
        }
        return parent.get(1);

    }
    
    class UF{
        private int[] parent;
        
        public UF(int size){
            parent  = new int [size+1];
            for(int i = 0; i<= size; i++){
                parent[i] =i;
            }
        }   
        
        public boolean union(int u, int v){
            int findu =find(u);
            int findv =find(v);
            
            if(findu == findv) return false;
            
            if(findu < findv){
                parent[findv] = findu;
            }else{
                parent[findu] = findv;
            }
            
            return true;
        
        } 
        
        public int find(int u){
            if(u!= parent[u]){
                return find(parent[u]);
            }
            return u;
        }
        
    }
    
}
```

