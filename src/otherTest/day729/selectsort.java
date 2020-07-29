package otherTest.day729;

import otherTest.CommonConstant;

import java.util.Arrays;

public class selectsort {
    public static void main(String[] args) {
        int [] arr = CommonConstant.num;
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    static void sort(int [] arr){
        int len = arr.length;
        for(int i = 0 ; i < len - 1 ; ++i){
            int tmp = i;
            for(int j = i + 1 ; j < len ; ++j){
                if(arr[j] < arr[tmp]){
                    tmp = j;
                }
            }
            if(tmp != i){
                int temp = arr[tmp];
                arr[tmp] = arr[i];
                arr[i] = temp;
            }
        }
    }

}
