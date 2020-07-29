package otherTest.day729;

import otherTest.CommonConstant;

import java.util.Arrays;

public class jssort {
    public static void main(String[] args) {
        int [] arr = CommonConstant.num;
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }


    static void sort(int [] arr){
        int len = Math.max(10 , arr.length);
        int max = Integer.MIN_VALUE;
        for(int v : arr){
            max = Math.max(max , v);
        }
        int n = 1 ;
        int k = 0 ;
        int [][] bucket = new int[10][len];
        int [] order = new int[len];
        while(n < max){
            for(int v : arr){
                int num = (v/n)%10;
                bucket[num][order[num]] = v;
                order[num] += 1;
            }
            for(int i = 0 ; i < 10 ; ++i){
                if(order[i] != 0){
                    for(int j = 0 ; j < order[i] ; ++j){
                        arr[k++] = bucket[i][j];
                    }
                    order[i] = 0;
                }
            }
            n *= 10;
            k = 0;
        }
    }
}
