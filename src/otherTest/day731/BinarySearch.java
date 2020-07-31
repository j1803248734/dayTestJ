package otherTest.day731;

public class BinarySearch {
    public static void main(String[] args) {

    }
    static int search(int [] arr , int i){
        int len = arr.length;
        int ln = 0 , rn = len - 1 , mid ;
        while(ln <= rn){
            mid = ln + ( rn - ln ) / 2;
            if(arr[mid] == i){
                return mid;
            }
            if(arr[mid] > i){
                rn = mid - 1;
            }
            if(arr[mid] < i){
                ln = mid + 1;
            }
        }
        return -1;
    }
}
