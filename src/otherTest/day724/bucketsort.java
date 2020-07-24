package otherTest.day724;

import java.util.*;

public class bucketsort {
    /**
     * 桶排序
     * @param args
     */
    public static void main(String[] args) {
        int [] arr = new int[]{55 , 33 , 88 , 11 , 2 , 3 ,1  , 111};
        int [] B = new int[arr.length];
//        bucket(arr);
//        jishu(arr , B);
        re(arr);
        System.out.println(Arrays.toString(arr));
    }

    static void bucket(int[] arr){
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int item : arr) {
            max = Math.max(max, item);
            min = Math.min(min, item);
        }
        //按照arr的长度分桶
        int bucketNum = (max - min) / arr.length + 1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>();
        for(int i = 0; i < bucketNum; ++i){
            bucketArr.add(new ArrayList<Integer>());
        }
        for (int value : arr) {
            int num = (value - min) / arr.length;
            bucketArr.get(num).add(value);
        }
        for (ArrayList<Integer> integers : bucketArr) {
            Collections.sort(integers);
        }
        int index = 0;

        for (ArrayList<Integer> integers : bucketArr) {
            for (int item : integers) {
                arr[index++] = item;
            }
        }

    }

    static void bucket1(int [] arr){
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int value : arr) {
            max = Math.max(max, value);
            min = Math.min(min, value);
        }
        int bucketNum = (max - min) / arr.length + 1;
        List<List<Integer>> bucketArr = new ArrayList<>();
        for(int i = 0 ; i < bucketNum ; ++i){
            bucketArr.add(new ArrayList<>());
        }

        for (int value : arr) {
            int num = (value - min) / arr.length;
            bucketArr.get(num).add(value);
        }
        int index = 0;
        for (List<Integer> integers : bucketArr) {
            Collections.sort(integers);
            for(int item : integers){
                arr[index++] = item;
            }
        }

    }

    static void jishu(int [] A , int [] B ){
        int max = Integer.MIN_VALUE;
        for (int value : A) {
            max = Math.max(value, max);
        }
        int k = max + 1;
        int [] C = new int[k];
        for(int i = 0 ; i < k ; ++i){
            C[i] = 0;
        }

        for (int value : A) {
            C[value] = C[value] + 1;
        }

        for(int i = 1 ; i < k ; ++i){
             C[i] = C[i-1]+C[i];
        }

        for (int value : A) {
            B[C[value]-1] = value;
            C[value] = C[value] - 1;
        }


    }


    static void radixsort(int [] arr ){
        int max = getMaxValue(arr);
        int n = 1;  //位数
        int k = 0; // 保存每一位排序后的结果
        int length = 10;
        int [][] bucket = new int[10][length];
        int [] order = new int[length];
        while(n < max){
            for(int num:arr) //将数组array里的每个数字放在相应的桶里
            {
                int digit=(num/n)%10;
                bucket[digit][order[digit]]=num;
                order[digit]++;
            }
            System.out.println("order++===="+Arrays.toString(order));
            for(int i=0;i<length;i++)//将前一个循环生成的桶里的数据覆盖到原数组中用于保存这一位的排序结果
            {
                if(order[i]!=0)//这个桶里有数据，从上到下遍历这个桶并将数据保存到原数组中
                {
                    for(int j=0;j<order[i];j++)
                    {
                        arr[k]=bucket[i][j];
                        k++;
                    }
                }
                order[i]=0;//将桶里计数器置0，用于下一次位排序
            }
            n*=10;
            k=0;//将k置0，用于下一轮保存位排序结果
            System.out.println(Arrays.toString(arr));
        }
    }



    static int getTimes(int value){
        int times = 0 ;
        while(value > 0){
            value /= 10 ;
            times++;
        }
        return times;
    }
    static int getMaxValue(int [] A){
        int max = Integer.MIN_VALUE;
        for (int value : A) {
            max = Math.max(value, max);
        }
        return max;
    }

    static void re(int [] arr){
        int max = getMaxValue(arr);
        int n = 1;
        int k = 0;
        int length = Math.max(arr.length, 10);
        int [][]  bucket = new int[10][length];
        int [] order = new int[length];

        while( n < max ){
            for(int num : arr){
                int d = (num/n) % 10;
                bucket[d][order[d]] = num;
                order[d]++;
            }
            for(int i = 0 ; i < length ; ++i){
                if(order[i]!=0){
                    for(int j = 0 ; j < order[i] ; ++j){
                        arr[k++] = bucket[i][j];
                    }
                }
                order[i] = 0;
            }
            n *= 10;
            k = 0;
        }
    }

}
