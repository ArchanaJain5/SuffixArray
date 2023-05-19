package project5;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;

public class SuffixArray {
  
  public ArrayList<Integer> construct(String S) {
    // Your code goes here
    // The function should return a vector of integers storing the integer IDs of the suffix array
    TreeMap<String,Integer> map = new TreeMap<>();
    int n = S.length();
    for(int i=0;i<n-1;i++){
      map.put(S.substring(i,n),i+1);
    }
    ArrayList<Integer> res = new ArrayList<>();
    res.add(n);
    for (Map.Entry<String, Integer> entry : map.entrySet()){
      // System.out.print(entry.getKey()+"   ");
      res.add(entry.getValue());
    }
    return res;
  }

}
