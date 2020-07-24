package otherTest.day724;

import java.util.Random;

public class mergesort {
    /**
     * 归并
     */
    public static void main(String[] args) {
        int [] num = getarray(100000000);
//        int [] num = new int[]{ 55 , 22 ,22, 33 , 11 , 44 , 88};
        long start = System.currentTimeMillis();
//        merge(num , 0 , num.length - 1 );
        mergefor(num);
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

    static void merge(int [] num , int start , int end){
        if(start >= end){
            return;
        }
        int mid = start + (end - start)/2;
        merge(num , start , mid);
        merge(num , mid + 1 , end);
        sort(num , start , mid , end);
    }

    //分组排序
    static void sort(int [] num , int start , int mid , int end){
        int ln1 = start ;
        int ln2 = mid + 1;
        int i = 0;
        int [] temp = new int[end - start + 1];

        while(ln1 <= mid && ln2 <= end){
            temp[i++] = num[ln1] <= num[ln2] ? num[ln1++] : num[ln2++];
        }
        while(ln1 <= mid){
            temp[i++] = num[ln1++];
        }
        while(ln2 <= end){
            temp[i++] = num[ln2++];
        }

        for( i = 0 ; i < temp.length ; ++i){
            num[start + i] = temp[i];
        }
    }


    static void mergefor(int [] num){
        int step = 1;
        int length  = num.length;
        while(step < length){
            mf(num , step , length);
            step *= 2;
        }
    }

    static void mf(int [] num , int step , int length){
        int i = 0;
        while(i <= length - step){
            sort(num , i , i + step - 1 , Math.min(i + 2 * step , length - 1));
            i += 2 * step;
        }
    }

}
