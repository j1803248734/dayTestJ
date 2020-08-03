package otherTest.day803;

public class selectsort {
    public static void main(String[] args) {

    }
    static void sort(int [] arr){
        int len = arr.length;
        for(int i = 0 ; i < len-1 ; ++i){
            int tmp = i;
            for(int j = i + 1 ; i < len ; ++j){
                if(arr[i] < arr[tmp]){
                    tmp = i;
                }
            }
            int temp = arr[tmp];
            arr[tmp] = arr[i];
            arr[i] = temp;
        }
    }
}
