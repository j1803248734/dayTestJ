package otherTest.day731;

public class FibonacciSearch {
    public static void main(String[] args) {

    }
    private static final int maxSizeLen = 20;
    static void Fibonacci(int [] farr)
    {
        int len = farr.length;
        farr[0]=0;
        farr[1]=1;
        for(int i=2;i<len ;++i)
            farr[i]=farr[i-1]+farr[i-2];
    }

    static int search(int [] arr , int n , int key){
        int ln = 0;
        int rn = n-1;
        int [] farr = new int[maxSizeLen];
        Fibonacci(farr);
        int k=0;
        while(n>farr[k]-1){
            ++k;
        }
        int [] temp = new int[farr[k] -1];
        System.arraycopy(arr , 0 , temp , 0 , arr.length);
        for(int i=n;i<farr[k]-1;++i){
            temp[i]=arr[n-1];
        }
        while(ln<=rn)
        {
            int mid=ln+farr[k-1]-1;
            if(key<temp[mid])
            {
                rn=mid-1;
                k-=1;
            }
            else if(key>temp[mid])
            {
                ln=mid+1;
                k-=2;
            }
            else
            {
                if(mid<n)
                    return mid; //若相等则说明mid即为查找到的位置
                else
                    return n-1; //若mid>=n则说明是扩展的数值,返回n-1
            }
        }

        return -1;
    }
    static void fc(int [] farr){
        int len = farr.length;
        farr[0]=0;
        farr[1]=1;
        for(int i = 2 ; i < len ; ++i){
            farr[i] = farr[i-1]+farr[i-2];
        }
    }

    static int search1(int [] arr , int key){
        int len = arr.length;
        int ln = 0;
        int rn = len-1;
        int [] farr = new int[maxSizeLen];
        fc(farr);
        int k=0;
        while(len>farr[k]-1){
            ++k;
        }
        int [] temp = new int[farr[k] -1];
        System.arraycopy(arr , 0 , temp , 0 , arr.length);
        for(int i = len ; len < farr[k] - 1 ; ++i){
            temp[i]=arr[len-1];
        }
        while(ln <= rn){
            int mid=ln+farr[k-1]-1;
            if(temp[mid] > key){
                rn=mid-1;
                k-=1;
            }else if(temp[mid] < key){
                ln=mid+1;
                k-=2;
            }else{
                if(mid<len)
                    return mid; //若相等则说明mid即为查找到的位置
                else
                    return len-1; //若mid>=n则说明是扩展的数值,返回n-1
            }
        }
        return -1;
    }

    //
    static int search2(int [] arr , int key){
        int len = arr.length;
        int ln = 0 , rn = len - 1;
        int[] farr = new int[maxSizeLen];
        Fibonacci(arr);
        int k = 0;
        while(farr[k] - 1 < len){
            ++k;
        }
        int [] temp = new int[farr[k] -1];
        System.arraycopy(arr, 0 , temp , 0 , len);
        for(int i = len ; i < temp.length ; ++i){
            temp[i] = arr[len - 1];
        }
        while(ln <= rn){
            int mid = ln + farr[k - 1] - 1;
            if(temp[mid] > key){
                k -= 1;
                rn=mid-1;
            }else if(temp[mid] < key){
                k -= 2;
                ln = mid + 1;
            }else{
                if(mid<len)
                    return mid; //若相等则说明mid即为查找到的位置
                else
                    return len-1; //若mid>=n则说明是扩展的数值,返回n-1
            }
        }
        return -1;
    }

}
