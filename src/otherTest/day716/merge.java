package otherTest.day716;

import java.util.Arrays;

public class merge {
    public static void main(String[] args) {
        int [] num = new int[]{ 99 , 22 , 11 , 33, 44,1 , 2, 3};
//        mergeSort(num , 0 , num.length - 1);
//        System.out.println(Arrays.toString(num));
        mergeFor(num);
        System.out.println(Arrays.toString(num));
    }

    /**
     * 归并排序 递归版本
     * @param num
     * @param ln
     * @param rn
     */
    static void mergeSort(int [] num , int ln , int rn){
        if(ln == rn){
            return;
        }
        int mid = ln + ( (rn - ln) >>1 );
        mergeSort(num , ln , mid);
        mergeSort(num , mid + 1 , rn);
        sort(num , ln , mid , rn);
    }
    static void sort( int [] num , int ln , int mid , int rn){
        int start1 = ln;
        int start2 = mid + 1;
        int [] temp = new int[rn - ln + 1];
        int i = 0;
        while(start1 <= mid && start2 <= rn){
            temp[i++] = num[start1] <= num[start2] ? num[start1++] : num[start2++];
        }
        while(start1 <= mid){
            temp[i++] = num[start1++];
        }
        while(start2 <= rn){
            temp[i++] = num[start2++];
        }
        for(i = 0 ; i < temp.length ; ++i){
            num[ln+i] = temp[i];
        }
    }

    /**
     * 归并排序 非递归版本
     * @param num
     */
    static void mergeFor(int [] num){
        int step = 1;
        int length = num.length;
        while(step < length){
            mergefor_child(num , step , length);
            step = 2 * step;
        }
    }

    static void mergefor_child(int [] num , int step , int length){
        int start = 0;
        while(start <= length - 2*step){
            sort(num , start , start + step -1 , Math.min(start + 2 * step - 1 , length - 1));
            start += 2*step;
        }
    }
}
