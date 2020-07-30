package otherTest.day730;

import otherTest.CommonConstant;

import java.util.Arrays;

public class quicksort {
    public static void main(String[] args) {
        int [] arr = CommonConstant.num;
        quick(arr , 0 , arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    static void quick(int [] arr , int start , int end){
        if(start >= end){
            return;
        }
        int mid = pr(arr , start , end);
        quick(arr , start , mid - 1);
        quick(arr , mid + 1 , end);
    }

    private static int pr(int[] arr, int start, int end) {
        int rn = arr[end];
        int ln = start - 1;

        for(int i = start ; i < end ; ++i){
            if(arr[i] < rn){
                int temp = arr[i];
                ++ln;
                arr[i] = arr[ln];
                arr[ln] = temp;
            }
        }
        int temp = arr[end];
        ++ln;
        arr[end] = arr[ln];
        arr[ln] = temp;
        return ln;
    }
}
