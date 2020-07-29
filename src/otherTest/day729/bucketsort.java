package otherTest.day729;

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
            max = Math.max(max , v);
            min = Math.min(min , v);
        }
        int len = arr.length;
        //分桶
        int bucketNum = (max - min) / len + 1;
        List<List<Integer>> valueList = new ArrayList<>();
        for(int i = 0 ; i < bucketNum ; ++i){
            valueList.add(new ArrayList<>());
        }
        //数据分流
        for(int v : arr){
            int num = (v - min) / len;
            valueList.get(num).add(v);
        }
        //桶内排序 拿出放置
        int i = 0;
        for(List<Integer> values : valueList){
            Collections.sort(values);
            for(int value : values){
                arr[i++] = value;
            }
        }
    }
}
