package otherTest.day731;

public class insertsort {
    public static void main(String[] args) {

    }
    static void sort(int [] arr){
        int len = arr.length;
        for(int i = 1; i < len ; ++i){
            int tmp = arr[i];
            int j = i - 1;
            for(;j >= 0 && arr[j] > tmp; ++j ){
                arr[j+1]=arr[j];
            }
            arr[j + 1] = tmp;
        }
    }
}
