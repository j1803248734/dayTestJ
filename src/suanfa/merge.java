package suanfa;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

public class merge {
    private final static int [] num = new int[]{11,2,3,22,44,54,21,34};
    public static void main(String[] args) {
        int nums[] = num;
        mergeF(nums);
        System.out.println(Arrays.toString(nums));
    }


    /**
     * 递归 归并排序
     * @param num
     * @param start
     * @param end
     */
    public static void merge_sort(int [] num , int start , int end){
        if(start == end) {
            return;
        }
        int mid = start + ((end - start) >> 1);
        merge_sort(num, start, mid);
        merge_sort(num, mid + 1, end);
        merge(num, start, mid, end);
    }


    static void merge(int[] num , int  start , int mid , int end){
        int [] temp = new int[ end - start +1 ];
        int i = 0 ;
        int s1 = start;
        int s2 = mid + 1;

        while(s1 <= mid && s2 <= end){
            temp[i++] = num[s1] < num[s2] ? num[s1++] : num[s2++];
        }
        while(s1 <= mid){
            temp[i++] = num[s1++];
        }
        while(s2 <= end){
            temp[i++] = num[s2++];
        }
        for(i = 0 ; i < temp.length ; i++){
            num[start + i] = temp[i];
        }

    }

    /**
     * 非递归版本归并  二叉堆排序的概念，自底向上，分层排序
     *
     */
    static void mergeF(int[] num){
        /**
         * 倍数
         */
        int step = 1 ;
        int length = num.length;
        int[] temp = new int[length];
        while(step < length){
            Merge(num,temp,step,length);
            step *= 2;
        }
    }

    static void Merge(int num[] , int temp[] , int step , int length){
        int i = 0 ;
        /**
         * 这一步操作  i<= length - 2*step
         */
        while(i <= length - 2 * step ){
            merge1(num , i , i + step - 1 , Math.min(i + 2 * step - 1, length - 1));
            i = i + step * 2;
        }
    }

    static void merge1(int[] num , int  start , int mid , int end){
        int [] temp = new int[ end - start +1 ];
        int i = 0 ;
        int s1 = start;
        int s2 = mid + 1;

        while(s1 <= mid && s2 <= end){
            temp[i++] = num[s1] < num[s2] ? num[s1++] : num[s2++];
        }
        while(s1 <= mid){
            temp[i++] = num[s1++];
        }
        while(s2 <= end){
            temp[i++] = num[s2++];
        }
        for(i = 0 ; i < temp.length ; i++){
            num[start + i] = temp[i];
        }

    }







}
