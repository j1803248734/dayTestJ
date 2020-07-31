package otherTest.day731;

public class quicksort {
    public static void main(String[] args) {

    }

    static void sort(int [] arr , int start , int end){
        if(start >= end){
            return;
        }
        int mid = pr(arr , start , end);
        sort(arr , start , mid - 1);
        sort(arr , mid + 1 , end);
    }
    // 我 这种人
    private static int pr(int[] arr, int start, int end) {
        int rn = arr[end];
        int ln = start - 1;
        for(int i = start ; i < end ; ++i){
            if(arr[i] < rn){
                ++ln;
                int temp = arr[ln];
                arr[ln] = arr[i];
                arr[i] = temp;
            }
        }
        ++ln;
        int temp = arr[ln];
        arr[ln] = arr[end];
        arr[end] = temp;

        return ln;
    }
}
