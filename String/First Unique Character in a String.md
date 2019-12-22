## 387. First Unique Character in a String
>> Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.   

>> Examples:     
 s = "leetcode"   
 return 0.   
 s = "loveleetcode",   
>> return 2.   


#### linear search

***based on the size of character set, we decide the size of array.    
if it is ASCII, the size is 256.   
if it is only lowercase character, the size is 26.    
The time complexity is O(n).    
*** 

```java
class Solution {
    public int firstUniqChar(String s) {
        int [] ch = new int [256];
        for(int i = 0; i< s.length(); i++){
            ch[s.charAt(i)]++;
        }
        for(int i = 0; i< s.length(); i++){
            if (ch[s.charAt(i)]==1) return i;
        }
        return -1;
    }
}
```

#### two pointers search

*** two pointers - slow and fast, if slow pointer is not unique, move to next. otherwise, the fast pointer move and slow keep check.     
***

```java
public class Solution {
    public int firstUniqChar(String s) {
        if (s==null || s.length()==0) return -1;
        int len = s.length();
        if (len==1) return 0;
        char[] cc = s.toCharArray();
        int slow =0, fast=1;
        int[] count = new int[256];
        count[cc[slow]]++;
        while (fast < len) {
            count[cc[fast]]++;
            // if slow pointer is not a unique character anymore, move to the next unique one
            while (slow < len && count[cc[slow]] > 1) slow++;  
            if (slow >= len) return -1; // no unique character exist
            if (count[cc[slow]]==0) { // not yet visited by the fast pointer
                count[cc[slow]]++; 
                fast=slow; // reset the fast pointer
            }
            fast++;
        }
        return slow;
    }
}
```