package otherTest.day819;

import otherTest.CommonConstant;

import java.util.Arrays;

public class jssort {
    public static void main(String[] args) {
        int [] arr = CommonConstant.num;
        int [] B = new int[arr.length];

        sort(arr , B);
        System.out.println(Arrays.toString(B));
    }

    private static void sort(int[] arr, int[] b) {
        int max = Integer.MIN_VALUE;
        for(int val : arr){
            max = Math.max(val , max);
        }
        int k = max + 1;
        int [] C = new int[k];
        for(int val : arr){
            C[val] += 1;
        }
        for(int i = 1 ; i < k ; ++i){
             C[i] += C[i-1] ;
        }

        for(int val : arr){
            b[C[val] - 1] = val;
            C[val]--;
        }
    }


}

