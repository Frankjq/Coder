## 496. Next Greater Element I
> You are given two arrays *(without duplicates)* nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.

> The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.


#### stack
O(n) time

```java
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack();
        HashMap<Integer, Integer> map =new HashMap<>();
        
        for(int num : nums2){
            while(!stack.isEmpty() && stack.peek() < num){
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }
        
        int [] res = new int[nums1.length];
        
        for(int i = 0; i< res.length; i++){
            res[i] = map.getOrDefault(nums1[i], -1);
        }
        return res;
    }
}
```

## 739. Daily Temperatures
> Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.

> For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

> Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].

find the higher temp and get dif between indexs

#### stack

no map

```java
class Solution {
    class Pair{
        private int val;
        private int index;
        public Pair(int val, int index){
            this.val = val;
            this.index = index;
        }
        
    }
    
    public int[] dailyTemperatures(int[] T) {
        Stack<Pair> stack = new Stack();        
        int [] res = new int [T.length];
        
        for(int i = 0; i < T.length; i++){
            while(!stack.isEmpty() && stack.peek().val < T[i]){
                res[stack.peek().index] = i - stack.peek().index;
                stack.pop();
            }
            stack.push(new Pair(T[i], i));
        }
        
        // while(!stack.isEmpty()){
        //     res[stack.pop().index] = 0;
        // }
        
        return res;
    }
}
```

##### improve but still in stack mind

```java
class Solution {
    public int[] dailyTemperatures(int[] T) {
 		int [] stack = new int [T.length];
 		int [] res = new int [T.length];

 		int stack_index = -1;

 		for (int i = 0; i <T.length; i++) {
 			while(stack_index >=0 && T[stack[stack_index]] < T[i] ){
 				res[stack[stack_index]] = i - stack[stack_index];
 				stack_index--;	//stack.pop()
 			}
 			stack[++stack_index] =  i; //stack.push()
 		}
 		return res;
    }
}
```

## 503. Next Greater Element II
> Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.

form a circle

#### stack
make two loops for circle

```java
class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int [] res = new int [nums.length];
        // sava index 
        int [] stack = new int [2*nums.length];
        
        int stack_index = -1;
        
        Arrays.fill(res, -1);
        
        for(int i = 0; i < 2 * nums.length; i++){
            while(stack_index >=0 && nums[stack[stack_index]] < nums[i% nums.length]){
                res[stack[stack_index--]] = nums[i% nums.length];//pop
            }
            stack[++stack_index] = i% nums.length;//push
        }
        return res;
    }
}
```

