package otherTest.day730;

import otherTest.CommonConstant;

import java.util.Arrays;

public class jishusort {
    public static void main(String[] args) {
        int [] arr = CommonConstant.num;
        int [] B = new int[arr.length];
        sort(arr , B);
        System.out.println(Arrays.toString(B));
    }

    static void sort(int [] A , int [] B ){
        int max = Integer.MIN_VALUE;
        for(int v : A){
            max = Math.max(v , max);
        }
        int k = max + 1;
        int [] C = new int[k];
        for(int v : A){
            C[v] += 1;
        }
        for(int i = 1 ; i < k ; ++i){
            C[i] += C[i-1];
        }
        for(int v : A){
            B[C[v] - 1] = v;
            C[v] -= 1;
        }
    }
}
