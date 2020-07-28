package otherTest.day728;

import otherTest.CommonConstant;

import java.util.Arrays;

public class jishusort {
    public static void main(String[] args) {
        int [] arr = CommonConstant.num;
        int [] B = new int[arr.length];
        jishu(arr , B);
        System.out.println(Arrays.toString(B));
    }
    static void jishu(int [] A , int [] B){
        int max = Integer.MIN_VALUE;
        for(int value : A){
            max = Math.max(value , max);
        }
        int k = max + 1;
        int [] C = new int[k];
        for(int value : A){
            C[value] += 1;
        }
        for(int i = 1; i < k ; ++i){
            C[i] += C[i-1];
        }
        for(int value : A){
            B[C[value] - 1] = value;
            C[value] -= 1;
        }

    }


}
