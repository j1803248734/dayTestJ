package otherTest.day717;

import java.util.Arrays;
import java.util.Random;

/**
 * 堆排序
 */
public class heap {
    public static void main(String[] args) {
//        int [] num  = new int[]{ 4 , 99 , 88  , 11 , 22 , 33 , 1 , 2 , 3};
        int [] num = getarray(100000000);
//        heapsort(num);
//        insertBy(num , 3 , 100);
//        num = insert(num , 98);
//        num = insert(num , 100);
//        num = insert(num , 5);
        long start = System.currentTimeMillis();
        heapsort(num);
        System.out.println((System.currentTimeMillis() - start)*1.0/1000 +"s");
//        System.out.println(Arrays.toString(num));
    }

    static int[] getarray(int num){
        Random random = new Random();
        int [] temp = new int[num];
        for(int i = 0 ; i < num ; ++i){
            temp[i] = random.nextInt();
        }
        return temp;
    }

    /**
     * 性质
     * @param num
     * @param i
     * @param length
     */
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
            maxheapify(num , largest , length);
        }
    }

    /**
     * 建立最大堆
     * @param num
     */
    static void buildheap(int[] num){
        int length = num.length;
        int start = length >> 1;
        for(; start >=0 ; --start){
            maxheapify(num ,start , length);
        }
    }

    /**
     * 堆排序
     * @param num
     */
    static void heapsort(int[] num){
        buildheap(num);
        int length = num.length;
        int start  = length - 1 ;
        for(;start >= 1 ; --start){
            int temp = num[start];
            num[start] = num[0];
            num[0] =  temp;
            maxheapify(num , 0  ,  --length);
        }
    }

    /**
     * 最大优先队列
     */
    static void insertBy(int [] num , int i , int key){
        buildheap(num);
        if(key < num[i]){
            System.out.println(" less than  ");
            return;
        }
        num[i] = key;
        while(i > 0 && num[i/2] < num[i]){
            int temp = num[i];
            num[i] = num[i/2];
            num[i/2] = temp;
            maxheapify(num , i , num.length);
            i = i/2;
        }
    }
    
    static int[] insert(int [] num ,  int key){
        num = grow(num , 0 , num.length+1);
        insertBy(num , num.length - 1 , key);
        return num;
    }

    /**
     * 数组扩容
     * @param num
     * @param start
     * @param length
     * @return
     */
    static int[] grow(int [] num , int start , int length){
        int [] temp = new int[length];
        System.arraycopy(num , 0 , temp , start , num.length);
        return temp;
    }

}
