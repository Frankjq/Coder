## 252. Meeting Rooms
> Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.


#### sort and find

```java
class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>(){
            public int compare(int[]a, int []b){
                return a[0] - b[0];
            }
        });
        
        for(int i = 1; i< intervals.length; i++){
            if(intervals[i][0] < intervals[i-1][1])
                return false;
        }
        return true;   
    }
}
```


## 253. Meeting Rooms II
> Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:
> Input: [[0, 30],[5, 10],[15, 20]]
> Output: 2


>> consider max number of room used at same time

#### pq
```java
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>(){
            public int compare(int[]a, int[]b){
                return a[0] - b[0];
            }
        });
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[]a, int[]b){
                return a[1]- b[1];
            }                    
        });
        int res = 0;
        for(int i = 0; i< intervals.length; i++){
            if(pq.isEmpty())
                pq.add(intervals[i]);
            else{
                int [] p = pq.peek();
                if(p[1] <= intervals[i][0])
                    pq.poll();
                pq.add(intervals[i]);
            }
            res = Math.max(res, pq.size());
        }
        
        return res;
    }
}
```

#### two points

op
strat - - - - - 
end   - - - - - 
op

return max diff between start and end

```java
    public int minMeetingRooms(int[][] intervals) {
        int len =  intervals.length;
        int [] start = new int [len];
        int [] end = new int [len];
        
        for(int i = 0; i < len ; i ++){
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        
        Arrays.sort(start);
        Arrays.sort(end);
        
        int res = 0;
        int sp = 0;
        int ep = 0;
        for(; sp < len; sp++){
            res = Math.max(res, sp - ep);
            if(start[sp] >= end[ep]){
                ep ++;
            }
        }
        return Math.max(res, sp - ep);
    }
```