package otherTest.day728;

import otherTest.CommonConstant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class bucketsort {
    public static void main(String[] args) {
        int [] arr = CommonConstant.num;
        bucket(arr);
        System.out.println(Arrays.toString(arr));
    }

    static void bucket(int [] arr){
        int len = arr.length;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for( int value : arr){
            max = Math.max(max , value);
            min = Math.min(min , value);
        }
        int bucketNum = (max - min) / len + 1;
        List<List<Integer>> valueList = new ArrayList<>();
        for(int i = 0 ; i < bucketNum  ; ++i){
            valueList.add(new ArrayList<>());
        }
        for(int value : arr){
            int num = ( value - min ) / len;
            valueList.get(num).add(value);
        }
        int i = 0;
        for(List<Integer> valueLists : valueList){
            Collections.sort(valueLists);
            for(int value : valueLists){
                arr[i++] = value;
            }
        }
    }
}
