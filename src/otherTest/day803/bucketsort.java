package otherTest.day803;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class bucketsort {
    public static void main(String[] args) {
    }
    static void sort(int [] arr){
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int val : arr){
            max = Math.max(max , val);
            min = Math.min(min , val);
        }
        int len = arr.length;
        int bucketNum = ( max - min ) / len;
        List<List<Integer>> bucket = new ArrayList<>();
        for(int i = 0 ; i < bucketNum ; ++i){
            bucket.add(new ArrayList<>());
        }
        for(int val : arr){
            int num = ( val - min ) / len;
            bucket.get(num).add(val);
        }
        int k = 0;
        for(List<Integer> valueList : bucket){
            Collections.sort(valueList);
            for(int val : valueList){
                arr[k++] = val;
            }
        }
    }
}
