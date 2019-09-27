## 144. Binary Tree Preorder Traversal
> Given a binary tree, return the preorder traversal of its nodes' values.

Example:
> Input: [1,null,2,3]
>   1
>    \
>     2
>    /
>   3
>Output: [1,2,3]


前序遍历
中左右

#### Recursive:
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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res  = new ArrayList<Integer>();       
        helper(root, res);
        return res;    
    }
    
    public void helper(TreeNode root, List<Integer> res){
        if(root == null) return;
        res.add(root.val);
        if(root.left != null) helper(root.left, res);
        if(root.right != null) helper(root.right, res);
    }
}
```

#### Iteration
```java
public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> res  = new ArrayList<Integer>();
    Stack<TreeNode> stack = new Stack<>();
    TreeNode cur = root;

    while(cur != null || !stack.isEmpty()){
        if(cur != null){
            res.add(cur.val);
            stack.push(cur.right);
            stack.push(cur.left);
        }    
        cur = stack.pop();
    }
    return res;
}
```


## 94. Binary Tree Inorder Traversal

> Given a binary tree, return the inorder traversal of its nodes' values.

Example:
> Input: [1,null,2,3]
>   1
>    \
>     2
>    /
>   3
> Output: [1,3,2]


中序遍历
左中右

#### recursion:
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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        helper(root, list);
        return list;
    }
    public void helper(TreeNode root, List<Integer> res){
        if(root == null) return;
        if(root.left != null){
            helper(root.left, res);
        } 
        res.add(root.val);
        if(root.right != null){
            helper(root.right, res);
        }
    }
}
```

#### iteration:
```java
    public List<Integer> inorderTraversal(TreeNode root) {
        List <Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()){
            while(cur != null){ 
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            res.add(cur.val);
            cur = cur.right; 
        }
        return res;
    }
```

## 145. Binary Tree Postorder Traversal
> Given a binary tree, return the postorder traversal of its nodes' values.

Example:
> Input: [1,null,2,3]
>   1
>    \
>     2
>    /
>   3
> Output: [3,2,1]


后序遍历
前后中

#### recursion:

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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        helper(root, res);
        return res;
    }
    
    public void helper(TreeNode root, List<Integer> res){
        if(root == null) return;
        if(root.left != null) helper(root.left, res);
        if(root.right != null) helper(root.right, res);
        res.add(root.val);
    }
}
```