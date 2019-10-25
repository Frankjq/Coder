## 102  Binary Tree Level Order Traversal

> Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
For example:
> Given binary tree [3,9,20,null,null,15,7],
>     3
>    / \
>   9  20
>    /  \
>   15   7
return its level order traversal as:
>[
>  [3],
>  [9,20],
>  [15,7]
>]

#### bfs
Queue
```java
	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();        
		if(root == null) return res;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()){
		  int count = queue.size();
		  List<Integer> level  = new ArrayList<>();
		  while(count > 0){
		      TreeNode node = queue.poll();
		      level.add(node.val);
		      if(node.left != null) queue.offer(node.left);
		      if(node.right != null) queue.offer(node.right);
		      count --;
		  }
		  res.add(level);
		}
		return res;
	}
```

#### dfs
Recursion
```java
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        helper(root, res, 0);
        return res;
    }
    
    public void helper(TreeNode node, List<List<Integer>> res, int level){
        if(node == null) return;
        if(level >= res.size()) res.add(new LinkedList<Integer>());
        
        res.get(level).add(node.val);
        helper(node.left, res, level+1);
        helper(node.right, res, level+1); 
    }
```


## 103. Binary Tree Zigzag Level Order Traversal
> Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
>Given binary tree [3,9,20,null,null,15,7],
>    3
>   / \
>  9  20
>   /  \
>  15   7

return its zigzag level order traversal as:
>[
>  [3],
>  [20,9],
>  [15,7]
>]

O(m+n)
#### bfs 
Queue
```java
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> level = new LinkedList<>();
        if(root == null) return res;
        
        level.add(root);
       

        while(!level.isEmpty()){
            List<Integer> sub = new ArrayList<>();
            int size = level.size(); // count size of each level, the level  0 is size 1
            for(int i = 0; i < size; i ++){
                TreeNode p = level.poll();
                if(res.size() %2 == 0){
                    sub.add(p.val);                
                }else{
                    sub.add(0,p.val);
                }
                if(p.left != null) {
                    level.add(p.left); 
                }
                if(p.right != null) {
                    level.add(p.right);
                }
            }               
            res.add(sub);
        }
        return res;
    }
}
```

#### dfs
count levels
```java
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        helper(root, res, 0);
        return res;
    }
    
    public void helper(TreeNode node, List<List<Integer>> res, int level){
        if(node == null) return;
        while(res.size() <= level){
            res.add(new ArrayList<>());
        }
        
        if(level % 2 == 0){
            res.get(level).add(node.val);
        }else{
            res.get(level).add(0, node.val);
        }
        helper(node.left, res, level+1);
        helper(node.right, res, level+1);
    }
}
```


## 107. Binary Tree Level Order Traversal II
>Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
> Given binary tree [3,9,20,null,null,15,7],
>    3
>   / \
>  9  20
>   /  \
>   15   7
return its bottom-up level order traversal as:
>[
>  [15,7],
>  [9,20],
>  [3]
>]

#### dfs and reverse
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        helper(root, res, 0);
        Collections.reverse(res);
        return res;
    }
    
    public void helper(TreeNode root,  List<List<Integer>> list , int level){
        if(root == null) return;
        if(level >= list.size()) list.add(new LinkedList<Integer>());
        list.get(level).add(root.val);
        helper(root.left, list, level+1);
        helper(root.right, list, level+1);
    }
}
```

##### remove reverse dfs
```java
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
	    List<List<Integer>> res = new ArrayList<>();
	    helper(root, res, 0);
	    return res;
	}

	public void helper(TreeNode root,  List<List<Integer>> list , int level){
	    if(root == null) return;
	    if(level >= list.size()) list.add(0, new LinkedList<Integer>());
	    list.get(list.size() - level -1).add(root.val);
	    helper(root.left, list, level+1);
	    helper(root.right, list, level+1);
	}
```

#### bfs
add list 0 index
Queue
```java
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> level = new LinkedList<>();
        if(root == null) return res;
    
        level.add(root);    
        while(!level.isEmpty()){
            List<Integer> sub = new ArrayList<>();
            int size = level.size();
            for(int i = 0; i < size; i ++){
                TreeNode p = level.poll();
                sub.add(p.val);
                if(p.left != null) {
                    level.add(p.left); 
                }
                if(p.right != null) {
                    level.add(p.right);
                }
            }               
            res.add(0, sub);
        }
        return res;
    }
}
```