package otherTest.day721;

import java.util.Arrays;

public class merge {
    public static void main(String[] args) {
        int [] num = new int[]{ 55 , 66 , 88 , 1 , 6 , 3, 4, 2 , 22};
//        mergesort(num , 0 , num.length - 1);
        mergefor(num);
        System.out.println(Arrays.toString(num));
    }


    static void mergesort(int [] num , int start , int end){
        if(start == end){
            return;
        }
        int mid = start + (end - start) / 2;
        mergesort(num , start , mid);
        mergesort(num , mid + 1 , end);
        sort(num , start , mid , end);
    }

    static void sort(int [] num , int start , int mid , int end){
        int ln1 = start;
        int ln2 = mid + 1;
        int [] temp = new int[end - start + 1];
        int i = 0;

        while(ln1 <= mid && ln2 <= end){
            temp[i++] = num[ln1] <= num[ln2] ? num[ln1++] :num[ln2++];
        }
        while(ln1 <= mid){
            temp[i++] = num[ln1++];
        }
        while(ln2 <= end){
            temp[i++] = num[ln2++];
        }
        for(i = 0 ; i < temp.length ; ++i){
            num[start + i] = temp[i];
        }
    }



    static void mergefor(int [] num){
        int length = num.length;
        int step = 1;
        while(step < length){
            mfor(num , step , length);
            step *= 2;
        }
    }
    static void mfor(int [] num , int step , int length){
        //开始下标
        int i = 0;
        while(i < length - step){
            sort(num , i , i + step - 1 , Math.min(length - 1 , i + 2 * step - 1));
            i += 2 * step;
        }
    }
}
