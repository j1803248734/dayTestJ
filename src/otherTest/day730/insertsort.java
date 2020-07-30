package otherTest.day730;

import otherTest.CommonConstant;

import java.util.Arrays;

public class insertsort {
    public static void main(String[] args) {
        int [] arr = CommonConstant.num;
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    static void sort(int [] arr){
        int len  = arr.length;
        for(int i = 1 ; i < len ; ++i){
            int j = i - 1;
            int tmp = arr[i];
            for( ; j >= 0 && arr[j] > tmp; --j){
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = tmp;
        }
    }
}
