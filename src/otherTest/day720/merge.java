package otherTest.day720;

import java.util.Arrays;

public class merge {
    public static void main(String[] args) {
        int [] num = new int[]{66 , 88 , 99 , 1 , 4 ,  5 , 6 , 2 , 10};
//        mergefor(num);
//        mergesort(num , 0 , num.length-1);
        insertkey(num , 11 , 3);
        System.out.println(Arrays.toString(num));
    }


    static void mergesort(int[] num , int start , int end){
        if(start == end){
            return;
        }
        int mid = start + (end - start)/2;
        mergesort(num , start , mid);
        mergesort(num , mid + 1 , end);
        sort(num , start , mid , end);
    }
    static void sort(int [] num , int start , int mid , int end){
        int ln1 = start;
        int ln2 = mid + 1;
        int [] temp = new int[end - start + 1];
        int i = 0;
        while(ln1 <= mid && ln2 <=end){
            temp[i++] = num[ln1] <= num[ln2] ? num[ln1++] : num[ln2++];
        }
        while(ln1 <= mid){
            temp[i++] = num[ln1++];
        }
        while(ln2 <= end){
            temp[i++] = num[ln2++];
        }
        for( i = 0 ; i < temp.length ; ++i){
            num[start+i] = temp[i];
        }

    }

    static void mergefor(int [] num){
        int step = 1;
        int length = num.length;
        while(step < length){
            mfor(num , step , length);
            step *= 2;
        }
    }

    static void mfor(int [] num , int step , int length){
        int i = 0;
        while( i <= length - step){
            sort(num , i  ,  i + step -1 , Math.min(length - 1 , i + 2 * step -1));
            i += 2*step;
        }
    }

    //--------------------------------------------------堆

    static void maxheapify(int [] num , int i , int length){
       int ln = 2 * i + 1;
       int rn = 2 * i + 2;
//       int length = num.length;
       int largest = 0;
       if( ln < length && num[ln] > num[i] ){
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

    static void buildheap(int [] num){
        int length = num.length;
        int start  = length / 2;
        for(;start >=0 ; --start){
            maxheapify(num , start , length);
        }
    }
    //堆排序
    static void heapsort(int [] num){
        int length = num.length;
        buildheap(num);
        int start = length - 1;
        for(;start >=1 ; --start){
            int temp = num[0];
            num[0] = num[start];
            num[start] = temp;
            maxheapify(num , 0 , --length);
        }
    }
    //---------------------------------最大优先队列

    //插入
    static void insertkey(int [] num , int key , int i){
        if(key < num[i])
            return;
        buildheap(num);
        num[i] = key;
        int parent = i != 0 ? i/2 : 0;
        while(i > 0 && num[parent] < num[i]){
            int temp = num[i];
            num[i] = num[parent];
            num[parent] = temp;
            parent = i  /  2;
            maxheapify(num , parent , num.length);
        }
    }


    //---------------------------------快排

}
