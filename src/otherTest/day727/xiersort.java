package otherTest.day727;

import otherTest.CommonConstant;

import java.util.Arrays;

/**
 * 夜来非
 */
public class xiersort {
    public static void main(String[] args) {
        int [] arr = CommonConstant.num;
        xier(arr);
        System.out.println(Arrays.toString(arr));
    }
    static void xier(int [] arr){
        int len = arr.length;
        for (int gap = len/2;gap > 0;gap = gap/2)
        {
            for (int i = gap;i < len;i++)
            {
                int j = i;
                while (j - gap >= 0 && arr[j] < arr[j - gap])
                {
                    Swap(arr,j,j - gap);
                    j = j - gap;
                }
            }
        }

    }

    static void xier2(int [] arr){
        int len = arr.length;
        int start = len / 2;
        for(; start > 0 ; start/=2 ){
            for(int i = start ; i < len ; i++){
                int j = i;
                while(j - start >= 0 && arr[j] < arr[j-start]){
                    Swap(arr,j,j - start);
                    j = j - start;
                }
            }
        }
    }

    private static void Swap(int[] arr, int j, int i) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
    static void xier3(int [] arr){
        int len = arr.length;
        int start = len / 2;
        for(;start > 0 ; start /= 2){
            for(int i = start ; i < len ;i++){
                int j = i;
                while(j - start >= 0 && arr[j] < arr[j-start]){
                    Swap(arr , j , j-start);
                    j = j - start;
                }
            }
        }
    }

    static void xier4(int [] arr){
        int len = arr.length;
        int start = len / 2;
        for( ; start > 0 ; start /= 2){
            for(int i = start ; i < len ; ++i){
                int j = i;
                while( j - start >= 0 && arr[j] < arr[j - start]){

                }
            }
        }
    }


}
