package otherTest.day728;

import otherTest.CommonConstant;

import java.util.Arrays;

public class straightsort {
    public static void main(String[] args) {
        int [] arr = CommonConstant.num;
        straight(arr);
        System.out.println(Arrays.toString(arr));
    }
    static void straight(int [] arr){
        int len = arr.length;
        int i;
        int j;
        int tmp;
        for(i = 1 ; i < len ; ++i){
            tmp = arr[i];
            for(j = i - 1 ; j >= 0 && arr[j] > tmp; --j){
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = tmp;
        }
    }
}
