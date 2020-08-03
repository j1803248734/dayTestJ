package otherTest.day803;

public class mergesort {
    public static void main(String[] args) {

    }

    static void merge(int [] arr , int start , int end){
        if(start >= end){
            return;
        }
        int mid = start + ( end - start ) / 2;
        merge(arr , start , mid);
        merge(arr , mid + 1 , end);
        sort(arr , start , mid , end);
    }

    private static void sort(int[] arr, int start, int mid, int end) {
        int ln1 = start;
        int ln2 = mid + 1;
        int [] temp = new int[end - start + 1];
        int i = 0;
        while(ln1 <= mid && ln2 <= end){
            temp[i++] = arr[ln1] <= arr[ln2] ? arr[ln1++] : arr[ln2++];
        }
        while(ln1 <= mid){
            temp[i++] = arr[ln1++];
        }
        while(ln2 <= end){
            temp[i++] = arr[ln2++];
        }
        for(i = 0 ; i < temp.length ; ++i){
            arr[start + i] = temp[i];
        }
    }


    static void mergef(int [] arr){
        int step = 1;
        int len = arr.length;
        while(step < len){
            mf(arr , step , len);
            step *= 2;
        }
    }

    private static void mf(int[] arr, int step, int len) {
        int i = 0;
        while(i <= len - step){
            sort(arr , i , i + step - 1 , Math.min(i + 2 * step - 1 , len - 1));
            i += 2 * step;
        }
    }

}
