package otherTest;

import com.sun.deploy.util.ArrayUtil;
import com.sun.deploy.util.StringUtils;
import javafx.scene.input.DataFormat;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestSIX {

    public static int maxSubArray(int[] nums) {
        int max = nums[0];
        int length = nums.length;
        for(int i = 1 ; i < length ; ++i){
            if(nums[i] + nums[i - 1] > nums[i] ){
                nums[i] += nums[i-1];
            }
            if(nums[i] > max){
                max = nums[i];
            }
        }
        return max;
    }
    public static void main(String[] args) {
        double d = 69960.0;
        double d2 = 6551993.448;
        BigDecimal bigDecimal1 = BigDecimal.valueOf(69960);
        BigDecimal bigDecimal2 = BigDecimal.valueOf(6551993.448);
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println(bigDecimal1.divide(bigDecimal2,6,4));
        System.out.println(df.format(d/d2));

//        List<String> one = new ArrayList<>();
//        one.add("jiang");
//        one.add("jiang");
//
//        System.out.println(one.get(1));
//        int i = 12;
//        int[] qwq = new int[12];
//
//        for(int j = 0 ; j < qwq.length ; ++j){
//            System.out.println(qwq[j]);
//        }


//        String [] one = new String[]{"1"};
//        System.out.println(StringUtils.join(Arrays.asList(one), ","));
//        String s = "M_Y2020Q3M7";
//        System.out.println(s.substring(3,7));
//        Pattern pattern = Pattern.compile("\\d+");
//        Matcher matcher = pattern.matcher(s);
//        int[] objects = new int[3];
//        int i = 0;
//        while (matcher.find()) {
//            objects[i] = Integer.parseInt(matcher.group(0));
//            i++;
//        }
//        int year =  objects[0];
//        System.out.println(year);



    }
}
