package project5;

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Arrays;
public class SuffixArray {
  
  public ArrayList<Integer> construct(String S) {
    ArrayList<Integer> suffix_array = new ArrayList<>();
    int n = S.length();
    int[][] suffixes = new int[n][3];
    
    // store index,rank (Ascii value at charAt(0)) 
      for(int i=0;i<n;i++){
      suffixes[i][0] = i;  // index
      suffixes[i][1] = S.charAt(i)-'a'; // rank
    }

    // store the next rank
    for(int i=0;i<n;i++){
      suffixes[i][2] = (i+1)<n ? suffixes[i+1][1]:-1;
    }

    // sort based on rank and next rank
   sortsuffixes(suffixes);

    int[] index = new int[n];

    for(int k=4;k<2*n;k=k*2){
      int rank = 0,prev_rank = suffixes[0][1];
      suffixes[0][1] = rank ; // first suffix assigned rank 0
      index[suffixes[0][0]] = 0;

      for(int i=1;i<n;i++){
        // if rank and prev rank equal don't increment the rank else increment by 1
        if(suffixes[i][1] == prev_rank && suffixes[i][2]==suffixes[i-1][2]){
          prev_rank = suffixes[i][1];
          suffixes[i][1] = rank;
        }else{
          prev_rank = suffixes[i][1];
          suffixes[i][1]=++rank;
        }
        index[suffixes[i][0]] = i;
      }
      // updating the next rank based on suffixies[i+2] if exists

      for(int i=0;i<n;i++){
        int next_rank = suffixes[i][0] + k/2;
        if(next_rank< n){
          int temp = index[next_rank];
          suffixes[i][2] = suffixes[temp][1];
        }else{
          suffixes[i][2] = -1;
        }
      }

      sortsuffixes(suffixes);
    }
    for(int i=0;i<n;i++){
      suffix_array.add(suffixes[i][0]);
    }
    return suffix_array;
  }
  private void sortsuffixes(int[][] suffixes){
     // sort based on rank and next rank
     Arrays.sort(suffixes,new Comparator<int[]>() {
      @Override
      public int compare(int[] rank1,int[] rank2){
        if(rank1[1]==rank2[1]) return rank1[2]-rank2[2];
        return rank1[1]-rank2[1];
      }
    });
  }
}
