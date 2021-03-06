package otherTest.day727;

import otherTest.CommonConstant;

import java.util.Arrays;

public class heapsort {
    public static void main(String[] args) {
        int [] arr = CommonConstant.num;
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    static void maxheapify(int [] arr , int i , int length){
        int ln = 2 * i + 1;
        int rn = 2 * i + 2;
        int largest = 0;
        if(ln < length && arr[ln] > arr[i]){
            largest = ln;
        }else{
            largest = i;
        }
        if(rn < length && arr[rn] > arr[largest]){
            largest = rn;
        }

        if(largest != i){
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            maxheapify(arr , largest , length);
        }
    }

    static void buildheap(int [] arr){
        int length = arr.length;
        int start = length / 2;
        for( ; start >= 0 ; --start){
            maxheapify(arr , start , length);
        }
    }

    static void sort(int [] arr){
        buildheap(arr);
        int length = arr.length;
        int start = length - 1;
        for(; start >= 1 ; --start){
            int temp = arr[0];
            arr[0] = arr[start];
            arr[start] = temp;
            maxheapify(arr , 0 , --length);
        }
    }
}
