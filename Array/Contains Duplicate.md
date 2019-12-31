## 217. Contains Duplicate
 
> Given an array of integers, find if the array contains any duplicates.     
> Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.    
 

the problem is not clear.

the range of nums

### Sort

**sort the array and find two same value.   
time complexity is O(nlgn)**


```java
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for(int i = 0; i< nums.length-1; i++){
            if(nums[i] == nums[i+1]) return true;
        }
        return false;
    }
}
```

### Hashtable

**due to unclear range of array, we could use set to find if there are duplicates.       
time complexity is O(n), worst is O(n2)**

```java
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int x : nums){
            if(!set.contains(x)){
                set.add(x);
            }else{
                return true;
            }
        }
        return false;
    }
}
```



**due to unclear range of array, we could find max and min of array, then use the becket to filter the duplicate one.       
time complexity is O(n)**

```java
class Solution {
    public boolean containsDuplicate(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int x:nums) {
            min = x<min?x:min;
            max = x>max?x:max;
        }
        boolean[] b = new boolean[max-min+1];
        for(int x: nums) {
            if(b[x-min]) {
                return true;
            }
            b[x-min]=true;
        }
        return false;
    }
}
```

## 219. Contains Duplicate II


> Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.


### Brute force
**time complexity is O(n2)**

### HashMap

**use hashmap to record position to update and compare position difference**
**time complexity is O(n)**

```java
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i< nums.length; i++){
            if(map.containsKey(nums[i])){
                int pos = map.get(nums[i]);
                if(i - pos <= k){
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return false;             
    }
}
```


## 220. Contains Duplicate III

> Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.

### Linear search

**search the limit slide window from each position.**
**time complexity is O(nk)**


```java
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        for(int i = 0; i < nums.length; i++){
            for(int j = 1; j <= k && i+j< nums.length; j++){
                if(Math.abs((long)nums[i] - (long)nums[i+j])<=(long)t){
                    return true;
                }
            }
        }
        return false;
    }
}
```

### bucket - hash table

**use t to divide into bucket, [0, t-1],[t, 2t-1]; use map to record <bucketID, position>**

```java
public class Solution {
    // Get the ID of the bucket from element value x and bucket width w
    // In Java, `-3 / 5 = 0` and but we need `-3 / 5 = -1`.
    private long getID(long x, long w) {
        return x < 0 ? (x + 1) / w - 1 : x / w;
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (t < 0) return false;
        Map<Long, Long> d = new HashMap<>();
        long w = (long)t + 1;
        for (int i = 0; i < nums.length; ++i) {
            long m = getID(nums[i], w);
            // check if bucket m is empty, each bucket may contain at most one element
            if (d.containsKey(m))
                return true;
            // check the neighbor buckets for almost duplicate
            if (d.containsKey(m - 1) && Math.abs(nums[i] - d.get(m - 1)) < w)
                return true;
            if (d.containsKey(m + 1) && Math.abs(nums[i] - d.get(m + 1)) < w)
                return true;
            // now bucket m is empty and no almost duplicate in neighbor buckets
            d.put(m, (long)nums[i]);
            if (i >= k) d.remove(getID(nums[i - k], w));
        }
        return false;
    }
}
```

