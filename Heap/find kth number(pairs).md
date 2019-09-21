## 373. Find K Pairs with Smallest Sums
> You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
> Define a pair (u,v) which consists of one element from the first array and one element from the second array.
> Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

Example 1:
> Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
> Output: [[1,2],[1,4],[1,6]] 
> Explanation: The first 3 pairs are returned from the sequence: 
>             [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]


#### heap

```java
class Solution {
    class pair{
        private int num1;
        private int num2;
        private int sum;
        private int index;
        public pair(int a, int b, int i){
            this.num1 = a;
            this.num2 = b;
            sum = a + b;
            this.index = i;
        }
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
      // construct a min heap
        PriorityQueue<pair> pq = new PriorityQueue<>(k, new Comparator<pair>(){
            public int compare(pair a, pair b){
                return a.sum - b.sum;
            }
        });
        
        List<List<Integer>> res = new ArrayList<>();
        if(nums1.length == 0|| nums2.length == 0 || k ==0) return res;
        
        //add first k pairs
        for(int i = 0; i < nums1.length && i < k; i++){
            pq.offer(new pair(nums1[i], nums2[0], 0));
        }
        
        //add minimus to res and get relative larger one/ beacause array in order 
        while(res.size() < k && !pq.isEmpty()){
            pair p = pq.poll();
            List<Integer> s = new ArrayList<>();
            s.add(p.num1);
            s.add(p.num2);
            res.add(s);
            
            //touch last one
            if(p.index == nums2.length-1) continue;
            
            pq.offer(new pair(p.num1, nums2[p.index +1], p.index+1));
 
        }
        return res;
    }
}
```


## 378. Kth Smallest Element in a Sorted Matrix


#### heap

```java
class Solution {
    
    class Number{
        private int value;
        private int row;
        private int col;
        
        public Number(int row, int col, int value){
            this.value = value;
            this.row = row;
            this.col = col;
        }
        
    }
    
    
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Number> pq = new PriorityQueue<>(k, new Comparator<Number>(){
            public int compare (Number a , Number b){
                return a.value - b.value;
            }
        }); 
        
        for(int i  = 0 ; i < matrix.length && i < k; i ++) pq.offer(new Number(0, i, matrix[0][i]));
        
        for(int i = 0; i < k-1 ; i++){
            Number num = pq.poll();
            
            if(num.row == matrix.length -1) continue;
            
            pq.offer(new Number(num.row +1,num.col, matrix[num.row+1][num.col]));
            
        }
        
        return pq.poll().value;
            
    }
}
```
