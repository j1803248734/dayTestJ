package otherTest.day803;

public class shellsort {
    public static void main(String[] args) {

    }

    static void sort(int [] arr){
        int len = arr.length;
        int start = len / 2;
        for( ; start > 0 ; start /= 2){
            for(int i = start ; i < len ; ++i){
                int j = i;
                while(j-start >=0 && arr[j-start] > arr[j]){
                    int temp = arr[j-start];
                    arr[j-start] = arr[j];
                    arr[j] = temp;
                    j -= start;
                }
            }
        }
    }

}
