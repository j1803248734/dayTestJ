package otherTest.day723;

import java.util.Arrays;

public class heapsort {
    public static void main(String[] args) {
        int [] num = new int[]{ 55 , 22 , 33 , 11 , 44 , 88};
        sort(num);
        System.out.println(Arrays.toString(num));
    }

    static void maxheapify(int [] num , int i , int length){
        int ln = 2 * i + 1;
        int rn = 2 * i + 2;
        int largest = 0;

        if(ln < length && num[ln] > num[i] ){
            largest = ln;
        }else {
            largest = i;
        }
        if(rn < length && num[rn] > num[largest]){
            largest = rn;
        }
        if(largest != i){
            int temp = num[i];
            num[i] = num[largest];
            num[largest] = temp;
            maxheapify(num , largest , length);
        }

    }
    static void buildmaxheap(int [] num){
        int length = num.length;
        int start = length / 2;
        for(;start >= 0 ; --start){
            maxheapify(num , start , length);
        }
    }

    static void sort(int [] num){
        buildmaxheap(num);
        int length = num.length;
        int start = length - 1;
        for(;start >= 1 ; --start){
            we(num , 0 , start);
            maxheapify(num , 0 , --length);
        }
    }

    static void we(int [] num , int start , int end){
        int temp = num[start];
        num[start] = num[end];
        num[end] = temp;
    }

    static void insertkey(int [] num , int i , int key){
        if(key < num[i]){
            return;
        }
        num[i] = key;
        while(i > 0  && num[i/2] < num[i]){
            we(num , i ,i/2);
            i = i / 2;
            maxheapify(num , i ,num.length);
        }
    }


}
