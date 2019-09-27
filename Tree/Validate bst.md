## 98. Validate Binary Search Tree

> Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:
1. The left subtree of a node contains only nodes with keys less than the node's key.
2. The right subtree of a node contains only nodes with keys greater than the node's key.
3. Both the left and right subtrees must also be binary search trees.


#### recursion 

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
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        return Valid(root.left, null, root.val) && Valid(root.right, root.val, null);
    }
    
    public boolean Valid(TreeNode node, Integer loval, Integer hival){
        if(node == null) return true;
        
        if(loval != null && node.val <= loval) return false;
        if(hival != null && node.val >= hival) return false;
        return Valid(node.left, loval, node.val) && Valid(node.right, node.val, hival);
    }
    
}
```