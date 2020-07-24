package otherTest.day723;

import java.util.Arrays;
import java.util.Random;

public class quicksort {
    public static void main(String[] args) {
        int [] num = new int[]{ 55 , 22 , 33 , 11 , 44 , 88};
        quick(num , 0 , num.length - 1);
        System.out.println(Arrays.toString(num));
    }

    static void quick(int [] num , int start , int end){
        if(start >= end){
            return;
        }
        int mid = randomkey(num , start , end);
        quick(num , start , mid - 1 );
        quick(num , mid + 1 , end);
    }

    static void we(int [] num , int start , int end){
        int temp = num[start];
        num[start] = num[end];
        num[end] = temp;
    }

    private static Random random = new Random();
    static int randomkey(int [] num , int start , int end){
        int rkey = random.nextInt(end - start) + start;
        we(num , rkey , end);
        return par(num , start , end);
    }


    static int par(int [] num , int start , int end){
        int rn = num[end];
        int ln = start - 1;

        for(int i = start ; i < end ; ++i){
            if(num[i] < rn){
                ++ln;
                we(num , ln , i);
            }
        }
        ++ln;
        we(num , ln , end);
        return ln;
    }
}
