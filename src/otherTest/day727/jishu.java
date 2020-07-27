package otherTest.day727;

import otherTest.CommonConstant;

import java.util.Arrays;

/**
 * 羞辱
 */
public class jishu {
    public static void main(String[] args) {
        int [] arr = CommonConstant.num;
        int [] B = new int[arr.length];
        jishusort(arr , B , getMaxValue(arr)+1);
        System.out.println(Arrays.toString(B));
    }

    private static void jishusort(int[] arr, int[] b, int maxValue) {
        int [] C = new int[maxValue];
        for(int value : arr){
            C[value] = C[value] + 1;
        }
        for(int i = 1 ; i < C.length ; ++i){
            C[i] = C[i -1] +C[i];
        }
        for(int value : arr){
            b[C[value] - 1] = value;
            C[value] -= 1;
        }
    }

    static int getMaxValue(int [] arr){
        int max = Integer.MIN_VALUE;
        for(int value : arr){
            if(value > max){
                max = value;
            }
        }
        return max;
    }
    
}
