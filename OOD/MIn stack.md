## 155. Min Stack

> Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.


#### node list
```java
class MinStack {
    class Node{
        private int val;
        private int min;
        Node next;
        
        public Node(int val, int min){
            this.val = val;
            this.min = min;
        }
    }
    
    private Node head;
    /** initialize your data structure here. */
    public MinStack() {
        head = new Node(0, Integer.MAX_VALUE);
        head.next = null;

    }
    
    public void push(int x) {
        Node node;
        if(head.next == null){
            node = new Node(x, Math.min(x, head.min));
        }else{
            node = new Node(x, Math.min(x, head.next.min));
        }
        Node temp = head.next;
        head.next = node;
        node.next = temp;
    }
    
    public void pop() { 
        head.next = head.next.next;
    }
    
    public int top() {
        return head.next.val;
    }
    
    public int getMin() {
        return head.next.min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */ 
```

#### stack twice
```java
class MinStack {

    /** initialize your data structure here. */
    Stack<Integer> minStack = new Stack<Integer>();
    int min = Integer.MAX_VALUE;
    public MinStack() {
        
        
    }
    
    public void push(int x) 
    {
        if(x<=min)
       {
         minStack.push(min);//store a min
         min=x;
       }
      minStack.push(x);
        
    }
    
    public void pop() {
        
        if(min == minStack.pop())
            min = minStack.pop();
        
    }
    
    public int top() 
    {
        int item = minStack.peek();
        return item;
        
    }
    
    public int getMin() 
    {
        
        return min;
    }
}
```