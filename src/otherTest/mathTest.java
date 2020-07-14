package otherTest;

import java.util.Arrays;

public class mathTest {
    public static void main(String[] args) {
        byte[] bytes = new byte[]{'.','t','x','t'};
        System.out.println(bytesToInt(bytes));
    }

    public static void collT(){
        int [] num  = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13};
        int [] num2 = new int[num.length-3];
        System.arraycopy(num , 3 , num2 ,
                0 , 2);
        System.out.println(Arrays.toString(num2));
    }

    public static void mathT(){
        double k = 5*6%7;
        System.out.println(k);
        double l = 2d/8;
        System.out.println(l);
        double j = 5*6%7/8;
        System.out.println(j);
        double i = 1+2+(3+4)*((5*6%7/8)-9)*10;
        System.out.println(i);
    }


    public static int bytesToInt(byte[] b){
        return b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }

}
