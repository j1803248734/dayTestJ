package otherTest.day729;

import otherTest.CommonConstant;

import java.util.Arrays;

/**
 * 插入排序
 */
public class straightsort {
    public static void main(String[] args) {
        int[] arr = CommonConstant.num;
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    static void sort(int [] arr){
        int len = arr.length;
        int i , j , tmp;
        for(i = 1 ; i < len ; ++i){
            tmp = arr[i];
            for(j = i - 1 ; j >= 0 && arr[j] > tmp ; --j){
                arr[j+1] = arr[j];
            }
            arr[j+1] = tmp;
        }
    }
}
