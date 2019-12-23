## 242. Valid Anagram

> Given two strings s and t , write a function to determine if t is an anagram of s.

**Assumed that the string is unicode**

### sort check

**sort the string twice and chech the sorted string if they are samethe    
time complexity is O(nlgn)**

```java
public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) {
        return false;
    }
    char[] str1 = s.toCharArray();
    char[] str2 = t.toCharArray();
    Arrays.sort(str1);
    Arrays.sort(str2);
    return Arrays.equals(str1, str2);
}
```

### improved bucket sort
**one string used to sum the frequency of each character, other one used to minus the frequency of each character. Finally check.     
the time complexity is O(n)**

```java
class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length()!= t.length()) return false;
        
        int [] ch = new int [256];
        
        for(int i = 0 ; i< s.length(); i++){
            ch[s.charAt(i)]++;
        }
        for(int i = 0 ; i< t.length(); i++){
            ch[t.charAt(i)]--;
        }

        for(int x: ch){
            if(x != 0) return false;            
        } 
        return true;   
    }
}
```

## 266. Palindrome Permutation

> Given a string, determine if a permutation of the string could form a palindrome.   

### hash table and check if it is palindrome

**time complexity is O(n)**

```java
class Solution {
    public boolean canPermutePalindrome(String s) {
        int [] ch = new int [256];
        for(int i = 0; i< s.length(); i++){
            ch[s.charAt(i)] ++;
        }
        boolean single = false;
        for(int freq: ch){
            if(freq %2 != 0){
                if(!single) single = true;
                else    return false;
            } 
        }
        return true;  
    }
}
```

## 49. Group Anagrams

> Given an array of strings, group anagrams together.  
> Example:  
> Input: ["eat", "tea", "tan", "ate", "nat", "bat"],  
> Output:  
 [  
   ["ate","eat","tea"],   
   ["nat","tan"],  
   ["bat"]   
> ]  

### group string by counts

**each string could be translated to a unique string, such as abc -> 11100-0**
**time complexity is O(NK), N is string group size, K is the maximun length of string**

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> count = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        
        for(String str : strs){
            //count 
            int [] c = new int [26];
            for(int i = 0; i < str.length(); i++){
                char ch = str.charAt(i);
                c[ch-'a']++;
            }
            // build unique str
            StringBuilder sb = new StringBuilder();
            for(int i : c){
                sb.append((char)(i+'0'));
            }
            
            //save and check
            String st = sb.toString();
            if(count.containsKey(st)){
                count.get(st).add(str);
            }else{
                count.put(st, new ArrayList<>());
                count.get(st).add(str);
            }
        }
        
        for(Map.Entry<String, List<String>> s : count.entrySet()){
            res.add(s.getValue());
        }
        
        return res;
    }
}
```

## 760. Find Anagram Mappings

> Given two lists Aand B, and B is an anagram of A. B is an anagram of A means B is made by randomizing the order of the elements in A.  
> We want to find an index mapping P, from A to B. A mapping P[i] = j means the ith element in A appears in B at index j.  
> These lists A and B may contain duplicates. **If there are multiple answers, output any of them.**    

For example, given
> A = [12, 28, 46, 32, 50]    
> B = [50, 12, 32, 46, 28]      

We should return
> [1, 4, 3, 2, 0]      
> as P[0] = 1 because the 0th element of A appears at B[1], and P[1] = 4 because the 1st element of A appears at B[4], and so on.


### hahsMap to locate the position

**time complexity is O(n)**

```java
class Solution {
    public int[] anagramMappings(int[] A, int[] B) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for(int i = 0; i < B.length; i++){
            map.put(B[i] ,i);
        }
        
        for(int i = 0; i< A.length; i++){
            A[i] = map.get(A[i]);
        }
        return A;   
    }
}
```


## 438. Find All Anagrams in a String

> Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.    
> Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.    
> The order of output does not matter.    


### sliding window

**time complexity is O(n)**

```java
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0) return list;
        int[] hash = new int[256]; 
            //character hash
            //record each character in p to hash
        for (char c : p.toCharArray()) {
            hash[c]++;
        }
        //two points, initialize count to p's length
        int left = 0, right = 0, count = p.length();
        while (right < s.length()) {
            //move right everytime, if the character exists in p's hash, decrease the count
            //current hash value >= 1 means the character is existing in p
            if (hash[s.charAt(right++)]-- >= 1) count--; 

            //when the count is down to 0, means we found the right anagram
            //then add window's left to result list
            if (count == 0) list.add(left);

            //if we find the window's size equals to p, then we have to move left (narrow the window) to find the new match window
            //++ to reset the hash because we kicked out the left
            //only increase the count if the character is in p
            //the count >= 0 indicate it was original in the hash, cuz it won't go below 0
            if (right - left == p.length() && hash[s.charAt(left++)]++ >= 0) count++;
        }
        return list;
    }
}
```





