package otherTest.day727;

import otherTest.CommonConstant;

import java.util.Arrays;

/**
 * ~ 再也不喝酒了
 */
public class straightSort {
    /**
     * 普通插入排序
     */
    public static void main(String[] args) {
        int [] arr = CommonConstant.num;
        straight(arr);
        System.out.println(Arrays.toString(arr));
    }

    static void straight(int [] arr){
        int i;
        int j;
        int tmp;
        int len = arr.length;
        for (i = 1;i < len;i++)
        {
            tmp = arr[i];
            for (j = i - 1;j >= 0 && arr[j] > arr[i];j--)
            {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = tmp;
        }

    }


    static  void sort(int [] arr){
        int i ;
        int j ;
        int tmp ;
        int len = arr.length;
        for(i = 1 ; i < len ; ++i){
            tmp = arr[i];
            for(j = i - 1 ; j >= 0 && arr[j] > tmp ; j--){
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = tmp;
        }
    }


}
