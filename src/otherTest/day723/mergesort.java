package otherTest.day723;

public class mergesort {
    public static void main(String[] args) {

    }

    static void merge(int [] num  , int start , int end){
        if(start == end){
            return;
        }
        int mid = start + ( end - start ) / 2;
        merge(num , start , mid);
        merge(num , mid + 1 , end);
        sort(num , start , mid , end);
    }

    static void sort(int [] num  , int start , int mid , int end){
        int ln1 = start , ln2 = mid + 1;
        int [] temp = new int[end - start + 1];
        int i = 0;
        while(ln1 <= mid && ln2 <= end){
            temp[i++] = num[ln1] <= num[ln2] ? num[ln1++] : num[ln2++];
        }
        while(ln1 <= mid){
            temp[i++] = num[ln1++];
        }
        while(ln2 <= end){
            temp[i++] = num[ln2++];
        }

        for( i  = 0 ; i < temp.length ; ++i){
            num[start + i] = temp[i];
        }
    }
    
    
    static void mergefor(int [] num){
        int step = 1;
        int length = num.length;
        while(step < length){
            mf(num , step , length);
            step *= 2;
        }
    }

    static void mf(int [] num , int step , int length){
        int i = 0 ;
        while(i <= length - step){
            sort(num , i , i + step -1  , Math.min(length - 1 , i + 2 * step - 1));
            i += 2 * step;
        }
    }




}
