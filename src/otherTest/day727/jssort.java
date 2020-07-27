package otherTest.day727;

import otherTest.CommonConstant;

import java.util.Arrays;

/**
 * dead
 */
public class jssort {
    public static void main(String[] args) {
        int [] arr = CommonConstant.num;
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    static void sort(int [] arr){
        int n = 1;
        int k = 0;
        int max = Integer.MIN_VALUE;
        for(int value : arr){
            if(max < value){
                max = value;
            }
        }
        int length = Math.max(10 , arr.length);
        int [] order = new int[length];
        int [][] bucket = new int[10][length];
        while( n < max ){
            for(int num:arr) //将数组array里的每个数字放在相应的桶里
            {
                int digit=(num/n)%10;
                bucket[digit][order[digit]]=num;
                order[digit]++;
            }
            for(int i = 0 ; i < length ; ++i){
                if(order[i]!=0){
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
