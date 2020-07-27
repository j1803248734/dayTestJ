package otherTest.day727;

import otherTest.CommonConstant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class bucketsort {
    public static void main(String[] args) {
        int  [] arr = CommonConstant.num;
        bucket(arr);
        System.out.println(Arrays.toString(arr));
    }

    static void bucket(int [] arr){
        int length = arr.length;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int value : arr){
            max = Math.max(max , value);
            min = Math.min(min , value);
        }
        int bucketNum = (max - min) / length + 1;
        List<List<Integer>>  valueList  = new ArrayList<>();
        for(int i = 0 ; i < bucketNum ; ++i){
            valueList.add(new ArrayList<>());
        }
        for(int value : arr){
            int num = (value - min) / length;
            valueList.get(num).add(value);
        }
        int i = 0;
        for(List<Integer> value : valueList){
            Collections.sort(value);
            if(!value.isEmpty()){
                for(int v : value){
                    arr[i++] = v;
                }
            }
        }
    }
}
