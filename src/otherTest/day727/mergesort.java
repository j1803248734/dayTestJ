package otherTest.day727;

import otherTest.CommonConstant;

import java.util.Arrays;

public class mergesort {
    public static void main(String[] args) {
        int [] arr = CommonConstant.num;
//        merge(arr , 0 , arr.length-1);
        mergefor(arr);
        System.out.println(Arrays.toString(arr));
    }

    static void merge(int [] arr , int start , int end){
        if(start >= end){
            return;
        }
        int mid = start + ( end - start ) / 2;
        merge(arr , start , mid);
        merge(arr , mid + 1 , end);
        sort(arr , start , mid , end);
    }

    static void sort(int[] arr, int start, int mid, int end) {
        int ln1 = start;
        int ln2 = mid + 1;
        int [] temp = new int[end - start + 1];
        int i = 0;

        while(ln1 <= mid && ln2 <= end){
            temp[i++] = arr[ln1] <= arr[ln2] ? arr[ln1++] : arr[ln2++];
        }
        while(ln1 <= mid){
            temp[i++] = arr[ln1++];
        }
        while(ln2 <= end){
            temp[i++] = arr[ln2++];
        }

        for(i = 0 ; i < temp.length ; ++i){
            arr[start + i] = temp[i];
        }
    }

    static void mergefor(int [] arr){
        int step = 1;
        int length = arr.length;
        while(step < length){
            mf(arr , step , length);
            step *= 2;
        }
    }

    private static void mf(int[] arr, int step, int length) {
        int i = 0;
        while(i <= length - step){
            sort(arr , i , i + step - 1  , Math.min(length-1,i+2*step-1));
            i += 2*step;
        }
    }


}
