package otherTest.day724;

import java.util.Random;

public class quicksort {
    public static void main(String[] args) {
        int [] num = getarray(100000000);
//        int [] num = new int[]{ 55 , 22 ,22, 33 , 11 , 44 , 88};
        long start = System.currentTimeMillis();
        quick(num , 0 , num.length - 1 );
        System.out.println((System.currentTimeMillis() - start)*1.0/1000 +"s");
    }

    static void quick(int [] num , int start , int end ){
        if(start >= end){
            return;
        }
        int mid = ranpri(num , start , end);
        quick(num , start , mid - 1);
        quick(num , mid + 1 , end);
    }
    private static Random random = new Random();
    static int ranpri(int [] num , int start , int end){
        int ran = random.nextInt(end - start) + start;
        we(num , ran , end);
        return pri(num , start , end);
    }

    static int pri(int [] num , int start , int end){
        int rn = num[end];
        int ln = start - 1;

        for(int i = start ; i < end ; ++i){
            if(num[i]< rn){
                ++ln ;
                we(num , i , ln);
            }
        }
        ++ln;
        we(num , end , ln);
        return ln;
    }


    static void we(int [] num , int start , int end){
        int temp = num[start];
        num[start] = num[end];
        num[end] = temp;
    }

    static int[] getarray(int num){
        Random random = new Random();
        int [] temp = new int[num];
        for(int i = 0 ; i < num ; ++i){
            temp[i] = random.nextInt();
        }
        return temp;
    }
}
