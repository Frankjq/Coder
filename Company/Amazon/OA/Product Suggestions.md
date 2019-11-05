## Product Suggestions

> Implement a function to return product suggestions using products from a product repository after each character is typed by the customer in the search bar.If there are more than THREE acceptable products, return the product name that is first in the alphabetical order.Only return product suggestions after the customer has entered two characters.Product suggestions must start with the characters already typed.
> Both the repository and the customer query should be compared in a CASE-INSENSITIVE way.

#### trienode
```java
class TrieNode{
    HashMap<Character, TrieNode> map;
    List<String> prefixList;
    TrieNode(){
        map = new HashMap<>();
        prefixList = new ArrayList<>();
    }
}    
public static List<List<String>> productSuggestion(int numProduct, List<String> products, String customer){
    List<List<String>> res = new ArrayList<>();
    
    TrieNode root = new TrieNode();
    for(String p: products){
        char [] word = p.toLowerCase().toCharArray();
        TrieNode point = root;
        for(char ch : word){
            if(!point.map.containsKey(ch)){
                point.map.put(ch, new TrieNode());
            }
            point = point.map.get(ch);
            point.prefixList.add(p);
        }
    }
    
    char [] cusCharArray = customer.toLowerCase().toCharArray();
    if(cusCharArray.length < 2) return res;
    
    if(!root.map.containsKey(cusCharArray[0])) return res;
    root = root.map.get(cusCharArray[0]);
    
    for(int i = 1; i < cusCharArray.length; i++){
        
        if(!root.map.containsKey(cusCharArray[i])){
            break;
        }
        root = root.map.get(cusCharArray[i]);
        
        List<String> list = root.prefixList;
        Collections.sort(list);
        while(list.size() > 3)
            list.remove(list.size()-1);
        res.add(list);
    }
    
    return res;
}
```

