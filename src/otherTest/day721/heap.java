package otherTest.day721;

import java.util.Arrays;

public class heap {
    public static void main(String[] args) {
        int [] num = new int[]{ 55 , 66 , 88 , 1 , 6 , 3, 4, 2 , 22};
//        heapsort(num);
        insertkey(num , 3 , 77);
        System.out.println(Arrays.toString(num));
    }

    static void maxheapify(int [] num , int i , int length){
//        int length = num.length;
        int ln = 2 * i + 1;
        int rn = 2 * i + 2;
        int largest = 0 ;

        if(ln < length && num[ln] > num[i]){
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

    static void buildheap(int [] num){
        int length = num.length;
        int start = length / 2;
        for(; start >= 0  ; --start){
            maxheapify(num , start , length);
        }
    }

    static void heapsort(int [] num){
        buildheap(num);
        int length  = num.length;
        int start  = length - 1;
        for( ; start >=1 ; --start){
            int temp = num[0];
            num[0] = num[start];
            num[start] = temp;
            maxheapify(num , 0 , --length);
        }
    }

    static void insertkey(int [] num , int i , int key){
        buildheap(num);
        if(key < num[i]){
            System.out.println("爬");
            return;
        }
        num[i] = key;
        while(i > 0 && num[i/2] < num[i] ){
            int temp = num[i];
            num[i] = num[i/2];
            num[i/2] = temp;
            maxheapify(num , i ,num.length );
            i /= 2;
        }

    }
}
