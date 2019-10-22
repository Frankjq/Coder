## 22. Generate Parentheses

> Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
For example
> given n = 3, a solution set is:
>[
>   "((()))",
>   "(()())",
>   "(())()",
>   "()(())",
>   "()()()"
> ]

#### backTrack
```java
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backTrack(res, "", 0, 0, n);
        return res;
    }
    
    public void backTrack(List<String> res, String str, int left, int right, int num){
        if(str.length()/2 ==  num){
            res.add(str);
            return;
        }
        if(left < num){
            backTrack(res, str + "(", left +1, right, num);
        }
        if(right < left){
            backTrack(res, str + ")", left, right + 1, num);
        }
        
    }
}
```


## 17. Letter Combinations of a Phone Number

> Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
> A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

```java
class Solution {
    public List<String> letterCombinations(String digits) {

        HashMap<Character, String []> map = new HashMap<>();
        map.put('2', new String [] {"a", "b", "c"});
        map.put('3', new String [] {"d", "e", "f"});
        map.put('4', new String [] {"g", "h", "i"});
        map.put('5', new String [] {"j", "k", "l"});
        map.put('6', new String [] {"m", "n", "o"});
        map.put('7', new String [] {"p", "q", "r", "s"});
        map.put('8', new String [] {"t", "u", "v"});
        map.put('9', new String [] {"w", "x", "y", "z"});

        List<String> res = new ArrayList<>();
        
        if(digits.length()==0 ) return res;
        
        backTrack(res, "", digits, map, 0);
        return res;
            
    }
    
    public void backTrack(List<String> res, String str, String digits, HashMap<Character, String []> map, int index){
        if(index == digits.length()){
            res.add(str);
            return;
        }
        
        for(String s: map.get(digits.charAt(index))){
            backTrack(res, str + s, digits, map, index+1);
        }
    }
    
}
```

same but replace hashmap with switch

```java
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if(digits.isEmpty()) {
            return result;
        }
        letterCombinationsRec(digits, 0, "", result);
        return result;
    }
    
    public void letterCombinationsRec(String digits, int index, String combination, List<String> result) {
        if(digits.length() <= index) {
            result.add(combination);
            return;
        } else {
            char current = digits.charAt(index);
            String alpha = convert(current);
            for(int i = 0; i < alpha.length(); i++) {
                letterCombinationsRec(digits, index + 1, combination + alpha.charAt(i), result);
            }
        }
    }
    
    public String convert(char c) {
        switch(c) {
            case '2':
                return "abc";
            case '3':
                return "def";
            case '4':
                return "ghi";
            case '5':
                return "jkl";
            case '6':
                return "mno";
            case '7':
                return "pqrs";
            case '8':
                return "tuv";
            case '9':
                return "wxyz";
        }
        return "";
    }
}
```