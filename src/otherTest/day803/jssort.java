package otherTest.day803;

public class jssort {
    public static void main(String[] args) {

    }
    static void sort(int [] arr){
        int max = Integer.MIN_VALUE;
        for(int val : arr){
            max = Math.max(max , val);
        }
        int len = Math.max(arr.length , 10);
        int [][] bucket = new int[10][len];
        int [] order = new int[len];
        int n = 1;
        int k = 0;
        while( n < max ){
            for(int val : arr){
                int num = (val/n) % 10;
                bucket[num][order[num]] = val;
                order[num]+=1;
            }
            for(int i = 0 ; i < bucket.length ; ++i){
                if(order[i]!=0){
                    for(int j = 0 ; j < order[i] ; ++j){
                        arr[k] = bucket[i][j];
                    }
                    order[i] = 0;
                }
            }
            n *= 10;
            k = 0;
        }
    }
}
