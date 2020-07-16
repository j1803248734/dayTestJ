package otherTest.day716;

import java.util.Arrays;

public class heap {
    public static void main(String[] args) {
        int [] num = new int[]{ 88 , 11 , 22 , 33 , 44 , 89};
        heapsort(num);
        System.out.println(Arrays.toString(num));
    }

    static void maxheapity(int [] num , int i , int length){
        int ln = 2 * i + 1;
        int rn = 2 * i + 2;
        int largest = 0;
        if( ln < length && num[ln] > num[i]){
            largest = ln;
        }else{
            largest = i;
        }
        if( rn < length && num[rn] > num[largest]){
            largest = rn;
        }
        if(largest != i){
            int temp = num[i];
            num[i] = num[largest];
            num[largest] = temp;
            maxheapity(num , largest , length);
        }
    }

    /**
     * 建立最大堆
     */
    static void buildmaxheap(int [] num){
        int length = num.length;
        int start = length >> 1;
        for(;start >=0 ; start --){
            maxheapity(num , start , length);
        }
    }

    /**
     * 堆排序
     */

    static void heapsort(int [] num){
        buildmaxheap(num);
        int length = num.length;
        int start = length -1;
        for(;start >=1 ; start --){
            int temp = num[start];
            num[start] = num[0];
            num[0] = temp;
            maxheapity(num , 0 , --length);
        }
    }
}
