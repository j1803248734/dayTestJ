package otherTest.day724;

import java.util.Arrays;
import java.util.Random;

public class heapsort {
    public static void main(String[] args) {
        int [] num = getarray(100000000);
//        int [] num = new int[]{ 55 , 22 ,22, 33 , 11 , 44 , 88};
        long start = System.currentTimeMillis();
//        merge(num , 0 , num.length - 1 );
        sort(num);
//        System.out.println(Arrays.toString(num));
        System.out.println((System.currentTimeMillis() - start)*1.0/1000 +"s");
    }

    static int[] getarray(int num){
        Random random = new Random();
        int [] temp = new int[num];
        for(int i = 0 ; i < num ; ++i){
            temp[i] = random.nextInt();
        }
        return temp;
    }

    static void we(int [] num , int start , int end){
        int temp = num[start];
        num[start] = num[end];
        num[end] = temp;
    }

    static void maxheapify(int [] num , int i , int length){
        int ln = 2 * i + 1;
        int rn = 2 * i + 2;
        int largest = 0;
        if(ln < length && num[ln] > num[i]){
            largest = ln;
        }else{
            largest = i;
        }
        if(rn < length && num[rn] > num[largest]){
            largest = rn;
        }
        if(largest != i){
            int temp = num[i];
            num[i] = num[largest];
            num[largest] = temp;
            maxheapify(num , largest,length);
        }
    }

    static void buildheap(int [] num){
        int length = num.length;
        int start = length / 2;

        for( ; start  >= 0 ; --start){
            maxheapify(num , start,length);
        }
    }


    static void sort(int [] num){
        if(num.length <= 0){
            return;
        }
        buildheap(num);
        int length = num.length;
        int start = length -1 ;
        for( ; start >= 1 ; --start){
            we(num , 0 , start);
            maxheapify(num , 0 , --length);
        }
    }
}
