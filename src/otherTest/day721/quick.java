package otherTest.day721;

import java.util.Arrays;

public class quick {
    //  快排
    public static void main(String[] args) {
        int [] num = new int[]{ 55 , 66 , 88 , 1 , 6 , 3, 4, 2 , 22};
        quicksort(num , 0 , num.length - 1);
        System.out.println(Arrays.toString(num));
    }
    static void  quicksort( int [] num , int start , int end ){
        if(start >= end){
            return;
        }
        int mid = sort(num , start , end);
        quicksort(num , start , mid - 1);
        quicksort(num , mid + 1 , end);
    }
    static int sort(int [] num , int start , int end ){
        int rn = num[end];
        int ln = start - 1;

        for(int i = start ; i < end ; ++i){
            if(num[i] < rn){
                ++ln;
                int temp = num[i];
                num[i] = num[ln];
                num[ln] = temp;
            }
        }
        ++ln;
        num[end] = num[ln];
        num[ln] = rn;
        return ln;
    }
}
