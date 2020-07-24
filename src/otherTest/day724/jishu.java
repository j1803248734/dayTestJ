package otherTest.day724;

import java.util.Arrays;
import java.util.Random;

/**
 * 计数排序
 */
public class jishu {
    public static void main(String[] args) {
        int [] num = getarray(100000000);
//        int [] num = new int[]{ 55 , 22 ,22, 33 , 11 , 44 , 88};
        int [] B = new int[num.length];
        long start = System.currentTimeMillis();
        countingSort(num , B  , findMax(num));
        System.out.println((System.currentTimeMillis() - start)*1.0/1000 +"s");
//        System.out.println(Arrays.toString(B));
    }

    static int findMax(int [] num){
        int max = num[0];
        for(int i = 0 ; i < num.length ; ++i){
            if(num[i] > max){
                max = num[i];
            }
        }
        return max+1;
    }

    static void countingSort(int [] A , int [] B , int k){
        int [] C = new int[k];
        for(int i=0;i<k;i++){
            C[i]=0;
        }
        for(int j = 0 ; j < A.length ; ++j){
            C[A[j]] = C[A[j]] + 1;
        }
        for(int i = 1 ; i < k ; ++i){
            C[i] = C[i - 1] + C[i];
        }
        for(int i = A.length - 1  ; i >= 0 ; --i){
            B[C[A[i]] - 1] = A[i];
            C[A[i]] = C[A[i]] - 1;
        }

    }

    static int[] getarray(int num){
        Random random = new Random();
        int [] temp = new int[num];
        for(int i = 0 ; i < num ; ++i){
            temp[i] = random.nextInt(1000);
        }
        return temp;
    }

}
