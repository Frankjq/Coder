## 230. Kth Smallest Element in a BST
> Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.


inorder traversal
#### recursion

```java
	public int kthSmallest(TreeNode root, int k) {
        List<Integer> order = new ArrayList<>();
        inorder(order, root, k);
        return order.get(k-1);
    }
    
    public void inorder(List<Integer> order, TreeNode node, int k){
        if(node == null) return;
        if(k == 0) return;
        inorder(order, node.left, k);
        order.add(node.val);
        k--;
        inorder(order, node.right, k);
    }
```

#### interation
stack

```java
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while(p != null||!stack.isEmpty()){
            while(p != null){
                stack.add(p);
                p = p.left;
            }
            p = stack.pop();
            if(--k == 0) return p.val;
            p = p.right;
        }
        return -1;
    }
```
