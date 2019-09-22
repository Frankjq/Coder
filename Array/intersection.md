## 349. Intersection of Two Arrays
## 350. Intersection of Two Arrays II
similar

> Given two arrays, write a function to compute their intersection.

I in the result, should be unique
II in the result, should list all

#### set or set built-in method

set.intersection()

```java
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for(int x : nums1){
            set.add(x);
        }
        Set<Integer> res = new HashSet<>();
        for(int c : nums2){
            if(set.contains(c)) res.add(c);
        }
        
        int [] arr = new int [res.size()];
        int i = 0;
        for(int x : res){
            arr[i] = x;
            i++;
        }
        
        return arr;
        
    }
```


#### sort and find same value

the difference is check same 
```java 
 public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        Set<Integer> res = new HashSet<>();
        int i =0;
        int j = 0;
        while(i < nums1.length && j < nums2.length){
            if(nums1[i] == nums2[j]){
                res.add(nums1[i]);
                i++;j++;
           //     while(nums1[i] == nums1[i-1] && i < nums1.length) i++;
           //     while(nums1[j] == nums1[j-1] && j < nums2.length) j++;
            }
            else if(nums1[i] < nums2[j]){
                i++;
            }
            else if(nums1[i] > nums2[j]){
                j++;
            }
        }
        
        int [] arr = new int [res.size()];
         i = 0;
        for(int x : res){
            arr[i] = x;
            i++;
        }
        
        return arr;

    }
```

#### binary search
sort one array and make a loop of other array, check each number if exist in sorted array