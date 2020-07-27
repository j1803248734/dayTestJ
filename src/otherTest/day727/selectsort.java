package otherTest.day727;

import otherTest.CommonConstant;

import java.util.Arrays;

public class selectsort {
    public static void main(String[] args) {
        int [] arr = CommonConstant.num;
        select(arr);
        System.out.println(Arrays.toString(arr));
    }

    static void select(int [] arr){
        int len = arr.length;
        for(int i = 0 ; i < len - 1 ; ++i){
            int index = i;
            int j;
            for(j = i + 1 ; j < len; ++j){
                if(arr[j] < arr[index]){
                    index = j;
                }
            }
            int temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
    }
}
