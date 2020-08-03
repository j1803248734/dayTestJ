package otherTest.day803;

public class countsort {
    public static void main(String[] args) {

    }

    static void sort(int [] A , int [] B){
        int max = Integer.MIN_VALUE;
        for(int val : A){
            max = Math.max(max , val);
        }
        int k = max + 1;
        int [] C = new int[k];
        for(int val : A){
            C[val] += 1;
        }
        for(int i = 1 ; i < k ; ++i){
            C[i] += C[i-1];
        }
        for(int val : A){
            B[C[val]- 1] = val;
            C[val] -= 1;
        }
    }
}
