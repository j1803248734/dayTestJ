package otherTest.day728;

public class heapsort {
    public static void main(String[] args) {

    }

    static void maxheapify(int [] arr , int i , int len){
        int ln = 2 * i + 1;
        int rn = 2 * i + 2;
        int largest = 0;
        if(ln < len && arr[ln] > arr[i]){
            largest = ln;
        }else {
            largest = i;
        }
        if(rn < len && arr[rn] > arr[largest]){
            largest = rn;
        }
        if(largest != i){
            int temp = arr[largest];
            arr[largest] = arr[i];
            arr[i] = temp;
            maxheapify(arr , largest , len);
        }
    }

    static void buildheap(int [] arr){
        int len = arr.length;
        int start = len / 2;
        for( ; start >= 0 ; --start){
            maxheapify(arr , start , len);
        }
    }

    static void sort(int [] arr){
        buildheap(arr);
        int len = arr.length;
        int start = len - 1;
        for( ; start >= 1 ; --start){
            int tmp = arr[0];
            arr[0] = arr[start];
            arr[start] = tmp;
            maxheapify(arr , 0 , --len);
        }
    }
}
