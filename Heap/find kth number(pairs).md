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
        
        //add min to res and get relative larger one/ beacause array in order 
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
#### binary search

```java
  public int kthSmallest(int[][] matrix, int k) {
        int len = matrix.length;
        int lo = matrix[0][0];
        int hi = matrix[len-1][len-1];
      
        while(lo < hi){
            int mid = (lo + hi)/2;
            // same to mid - 1, mid +1
            int [] max_min = new int[]{lo, hi}; 
            int count =  countlessnumber(matrix, mid, max_min);
            if(count == k) return max_min[0];
            if(count < k ){
                lo = max_min[1];
            }else{
                hi = max_min[0];
            }
        }
        return lo;
    }
    
    public int countlessnumber(int[][] matrix, int mid, int [] res){
        int count = 0;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++){
                if(matrix[i][j] <= mid){
                    res[0] = Math.max(res[0], matrix[i][j]);
                    count ++;           
                }else{
                    res[1] = Math.min(res[1], matrix[i][j]);
                    continue;
                }
            }
        }
        return count;
    }
    
 ```



## 719. Find K-th Smallest Pair Distance

> Given an integer array, return the k-th smallest distance among all the pairs. The distance of a pair (A, B) is defined as the absolute difference between A and B.

Example 1:
> Input:
> nums = [1,3,1]
> k = 1
> Output: 0 
 
####  min heap (TLE)
O((k+N)log N) time

```java
class Solution {
    
    class Pair{
        private int a;
        private int b;
        private int dis;
        private int index1;
        private int index2;

        Pair(int num1, int num2, int index1, int index2){
            this.a = num1;
            this.b = num2;
            dis = Math.abs(num1 - num2);
            this.index1 = index1;
            this.index2 = index2;

        }
    }
    
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        
        PriorityQueue<Pair> pq = new PriorityQueue<>(nums.length, new Comparator<Pair>(){
            public int compare(Pair a, Pair b){
                return a.dis - b.dis;
            }
        });
        
        for(int i = 0; i < nums.length -1 ; i++){
            pq.add(new Pair(nums[i], nums[i+1], i, i+1));
        }
    
        while(k-1 > 0 && !pq.isEmpty()){
            Pair p = pq.poll();
            k--;
            if(p.index2 == nums.length-1 ) continue;
            
            pq.add(new Pair(nums[p.index1], nums[p.index2+1], p.index1, p.index2+1));
            
        }
        
        return pq.poll().dis;
    }
}
```


#### binary search

O(Nlog(N) + N^2log(N))
```java
public int smallestDistancePair(int[] nums, int k) {
    Arrays.sort(nums);
    
    int lo = 0; 
    int hi = nums[nums.length-1] - nums[0];
    
    while(lo < hi){
        int mid = (lo + hi) /2;
        int count = 0;
        int [] max_min = new int[]{lo,hi};
        
        
        for(int i = 0; i < nums.length; i++){
            for(int j = i +1; j < nums.length; j ++){
                if(nums[j] -nums[i] <= mid ){
                    count++;
                    max_min[0] = Math.max(max_min[0], nums[j] -nums[i]);
                }else{
                    max_min[1] = Math.min(max_min[1], nums[j] -nums[i]);
                    continue;
                }
            }
        }
        if(count == k) return max_min[0];           
        if(count < k) lo = max_min[1];
        else  hi = max_min[0];
    }
    return lo;
}
```
#### binary search + slide window

O(Nlog(N) + Nlog(W))
slide window size is W
```java
public int smallestDistancePair(int[] nums, int k) {
    Arrays.sort(nums);
    
    int lo = 0; 
    int hi = nums[nums.length-1] - nums[0];
    
	while(lo < hi){
	    int mid = (lo + hi) /2;
	    int count = 0;
	    
	    int left = 0;
	    for(int right = 0; right < nums.length; right++){
	        while(nums[right] - nums[left] > mid) left ++;
	        count  += right - left;
	    }
	    
	    if(count == k) hi = mid;           
	    if(count < k) lo = mid + 1;
	    else  hi = mid;
	}
    return lo;
}
```


## 658. Find K Closest Elements

> Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.


#### two points and get k nums
O(n) time

```java
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int lo = 0; 
        int hi = arr.length-1;

        while(hi-lo + 1 > k){
            if(Math.abs(arr[lo] - x) > Math.abs(arr[hi]-x)){
                lo++;
            }else{
                hi--;
            }
        }
        
        List<Integer> res = new ArrayList<>();
        
        for(int i = lo; i <= hi; i++){
            res.add(arr[i]);
        }
        
        return res;
    }
}
```

#### binary search
O(logn + k) time

