## favorite genres

> Given a map Map<String, List<String>> userSongs with user names as keys and a list of all the songs that the user has listened to as values.
> Also given a map Map<String, List<String>> songGenres, with song genre as keys and a list of all the songs within that genre as values. The song can only belong to only one genre.
> The task is to return a map Map<String, List<String>>, where the key is a user name and the value is a list of the user's favorite genre(s). Favorite genre is the most listened to genre. A user can have more than one favorite genre if he/she has listened to the same number of songs per each of the genres.

Example
```
Input:
userSongs = {  
   "David": ["song1", "song2", "song3", "song4", "song8"],
   "Emma":  ["song5", "song6", "song7"]
},
songGenres = {  
   "Rock":    ["song1", "song3"],
   "Dubstep": ["song7"],
   "Techno":  ["song2", "song4"],
   "Pop":     ["song5", "song6"],
   "Jazz":    ["song8", "song9"]
}

Output: {  
   "David": ["Rock", "Techno"],
   "Emma":  ["Pop"]
}
```


#### hashmap

```java
public static Map<String, List<String>> favoriteGenres(Map<String, List<String>> user, Map<String, List<String>> song){
    HashMap<String, List<String>> res = new HashMap<>();
    HashMap<String, String> songtoG = new HashMap<>();

    for(Map.Entry<String, List<String>> entry: song.entrySet()){
        if(entry.getValue() == null) continue; //corner 
        for(String s : entry.getValue()){
            songtoG.put(s, entry.getKey());
        }
    }

    for(String u : user.keySet()){
        if(user.get(u) == null) continue;//corner 
        HashMap<String, Integer> countG = new HashMap<>();

        int max = 0;
        for(String s: user.get(u)){
            if(!songtoG.containsKey(s)) continue;
            String genres = songtoG.get(s);
            if(!countG.containsKey(genres))
                countG.put(genres, 0);
            countG.put(genres, countG.get(genres)+1);
            max = Math.max(max, countG.get(genres));
        }

        res.put(u, new ArrayList<>());
        for(Map.Entry<String, Integer> e: countG.entrySet())
        {
            if(e.getValue() == max) res.get(u).add(e.getKey());
        }
    }
    return res;
}
```