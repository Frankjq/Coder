## 134. Gas Station

> There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
> You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
> Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.




#### brute force
```java
// sum gas > sum cost
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sumgas = 0;
        int sumcost = 0;

        for(int i = 0; i < gas.length; i++){
            sumgas+= gas[i];
            sumcost+= cost[i];
            gas[i] -= cost[i];
        }
        
        if(sumgas < sumcost)return -1;
        
        for(int i = 0; i < gas.length; i++){
            if(gas[i] >= 0){
                if(check(gas, i)) return i;
            }
        }
        
        return -1;
    }
    
    public boolean check(int []gas, int index){
        int g = 0;
        for(int i = index; i < gas.length; i++){
            g += gas[i];
            if(g< 0) return false;
        }
        for(int i = 0; i < index; i++){
            g += gas[i];
            if(g < 0) return false;
        }
        return true;
        
    }
    
    
}
```


#### greedy

sum gas > sum cost => the next station of the least 
sum gas < sum cost => -1

```java
   public int canCompleteCircuit(int[] gas, int[] cost) {
    
        int start = 0;
        int min = 0;
        int total = 0;
        for(int i = 0; i <gas.length; i++){
            total += gas[i] - cost[i];
            if(total < min){
                start = (i + 1) % gas.length;
                min = total;
            }
        }
        if(total < 0) return -1;
        
        return start;
    } 
```



