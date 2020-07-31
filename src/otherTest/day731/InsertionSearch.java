package otherTest.day731;

public class InsertionSearch {
    public static void main(String[] args) {

    }
    //你已经一无所有了
    static int search(int [] arr , int i){
        int len = arr.length;
        int ln = 0 , rn = len - 1 , mid;
        while(ln <= rn){
            mid = ln + (i - arr[ln]) / (arr[rn] - arr[ln]) * (rn - ln);
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
