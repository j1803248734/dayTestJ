package otherTest.day819;

import otherTest.CommonConstant;

import java.util.Arrays;

public class heapsort {
    public static void main(String[] args) {
        int [] arr = CommonConstant.num;
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    static void maxheapify(int [] arr , int i , int len){
        int ln = 2 * i + 1 ;
        int rn = 2 * i + 2;
        int max = 0;
        if(ln < len && arr[i] < arr[ln]){
            max = ln;
        }else{
            max = i;
        }
        if(rn < len && arr[max] < arr[rn]){
            max = rn ;
        }
        if(max != i){
            int temp =  arr[i];
            arr[i] = arr[max];
            arr[max] = temp;
        }
    }

    static void buildMaxHeap(int [] arr){
        int len = arr.length;
        int start = len / 2;
        for(;start >= 0 ; start--){
            maxheapify(arr , start , len);
        }
    }

    static void sort(int [] arr){
        buildMaxHeap(arr);
        int len = arr.length;
        int start = arr.length - 1;
        for( ; start >= 1 ; start--){
            int temp = arr[start];
            arr[start] = arr[0];
            arr[0] = temp;
            maxheapify(arr , 0 , --len);
        }
    }
}
