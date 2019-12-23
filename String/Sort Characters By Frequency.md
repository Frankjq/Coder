## 451. Sort Characters By Frequency

> Given a string, sort it in decreasing order based on the frequency of characters.  
>  Example 1:  
>   Input:  
>   "tree"  
>   Output:  
>   "eert"  
>  Explanation:  
>   'e' appears twice while 'r' and 't' both appear once.  
>   So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.  
 

### hashMap + PriorityQueue

**hashMap used to record each character and its frequency    
priorityQueue used to sort the entry by its frequency    
StringBuilder used to build the final result
time complexity is O(n + nlgn + n) -- n is the length of String s**

```java
class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        //record hashMap
        for(int i = 0; i< s.length(); i++){
            if(!map.containsKey(s.charAt(i))){
                 map.put(s.charAt(i), 0);
            }
           map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
        }
        
        //sort
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<Map.Entry<Character, Integer>>(new Comparator<Map.Entry<Character, Integer>>(){
            public int compare(Map.Entry<Character, Integer> a, Map.Entry<Character, Integer> b){
                return b.getValue() - a.getValue();
            } 
        });
        pq.addAll(map.entrySet());
        
        //build result
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            Map.Entry<Character, Integer> a = pq.poll();
            int i = 0;
            while(i < a.getValue()){
                sb.append(a.getKey());
                i ++;
            }
        }
        return sb.toString();
    }
}
```

### hashMap + bucket sort

***improved***
**bucket sort used to sort, but the sort has limits that the size of bucket or object is fixed.   
time complexity is O(n + n + n)**

```java
class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        //record hashMap
        for(int i = 0; i< s.length(); i++){
            if(!map.containsKey(s.charAt(i))){
                 map.put(s.charAt(i), 0);
            }
           map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
        }
        
        //sort
        List<Character> [] bucket = new ArrayList [s.length()+1];
        for(char key : map.keySet()){
           if(bucket[map.get(key)] == null ) bucket[map.get(key)]= new ArrayList<>();
           bucket[map.get(key)].add(key);
        }

        //build result
        StringBuilder sb = new StringBuilder();
        for(int i = bucket.length -1 ; i >= 0; i --){
            if(bucket[i]!= null){
                for(char ch : bucket[i]){
                    for(int j = 0; j < i; j ++){
                         sb.append(ch);
                    }
                }
            }
        }
        return sb.toString();
    }
}
```




