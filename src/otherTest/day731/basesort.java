package otherTest.day731;

public class basesort {
    public static void main(String[] args) {

    }

    static void sort(int [] arr){
        int max = Integer.MIN_VALUE;
        for(int v : arr){
            max = Math.max(max , v);
        }
        int n = 1;
        int k = 0;
        int len = Math.max(arr.length , 10);
        int [][] bucket = new int[10][len];
        int [] order = new int[len];
        while( n < max ){
            for(int v : arr){
                int num = (v/n)%10;
                bucket[num][order[num]] = v;
                order[num] += 1;
            }
            for(int i = 0 ; i < bucket.length ; ++i){
                if(order[i] != 0){
                    for(int j = 0 ; j < order[i] ; ++j){
                        arr[k++] = bucket[i][j];
                    }
                    order[i] = 0;
                }
            }
            n *= 10;
            k = 0;
        }
    }
}
