package otherTest.day731;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class bucketsort {
    public static void main(String[] args) {

    }

    static void sort(int [] arr){
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int v : arr){
            max = Math.max(v , max);
            min = Math.min(v , min);
        }
        int len = arr.length;
        int bucketNum = ( max - min ) / len + 1;
        List<List<Integer>> bucket = new ArrayList<>();
        for(int i = 0 ; i < bucketNum ; ++i){
            bucket.add(new ArrayList<>());
        }
        for(int v : arr){
            int num = ( v - min ) / len;
            bucket.get(num).add(v);
        }
        int i = 0;
        for(List<Integer> valueList : bucket){
            Collections.sort(valueList);
            for(int v : valueList){
                arr[i++] = v;
            }
        }
    }
}
