package otherTest.day804;

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
        if(rn < len && arr[rn] > arr[largest] ){
            largest = rn;
        }
        if( largest != i ){
            int temp = arr[largest];
            arr[largest] = arr[i];
            arr[i] = temp;
            maxheapify(arr , largest , len);
        }
    }

    static void buildheap(){

    }

}
