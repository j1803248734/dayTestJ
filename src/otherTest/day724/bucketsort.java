package otherTest.day724;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class bucketsort {
    /**
     * 桶排序
     * @param args
     */
    public static void main(String[] args) {
        int [] arr = new int[]{55 , 33 , 88 , 11 , 2 , 3 ,1};
        int [] B = new int[arr.length];
//        bucket(arr);
        jishu(arr , B);
        System.out.println(Arrays.toString(B));
    }

    static void bucket(int[] arr){
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int item : arr) {
            max = Math.max(max, item);
            min = Math.min(min, item);
        }
        //按照arr的长度分桶
        int bucketNum = (max - min) / arr.length + 1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>();
        for(int i = 0; i < bucketNum; ++i){
            bucketArr.add(new ArrayList<Integer>());
        }
        for (int value : arr) {
            int num = (value - min) / arr.length;
            bucketArr.get(num).add(value);
        }
        for (ArrayList<Integer> integers : bucketArr) {
            Collections.sort(integers);
        }
        int index = 0;

        for (ArrayList<Integer> integers : bucketArr) {
            for (int item : integers) {
                arr[index++] = item;
            }
        }

    }


    static void jishu(int [] A , int [] B ){
        int max = Integer.MIN_VALUE;
        for (int value : A) {
            max = Math.max(value, max);
        }
        int k = max + 1;
        int [] C = new int[k];
        for(int i = 0 ; i < k ; ++i){
            C[i] = 0;
        }

        for (int value : A) {
            C[value] = C[value] + 1;
        }

        for(int i = 1 ; i < k ; ++i){
             C[i] = C[i-1]+C[i];
        }

        for (int value : A) {
            B[C[value]-1] = value;
            C[value] = C[value] - 1;
        }


    }
}
