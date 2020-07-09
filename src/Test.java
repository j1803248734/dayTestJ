
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Copyright (C), 2020, 东华厚盾
 * FileName: Test
 * Author:   jiangshuju
 * Date:     2020/5/28 16:12
 * Description: 测试
 */

public class Test {
    private final static String INITIAL_DATE_STRING = "20-07-03 11:17:22";
    public static void main(String[] args) throws ParseException {
        String uuid = UUID.randomUUID().toString().replace("-","");
        System.out.println(uuid);
//        System.out.println(new SimpleDateFormat("yy-MM-dd hh:mm:ss").format(new SimpleDateFormat("yy-MM-dd hh:mm:ss").parse(INITIAL_DATE_STRING)));
//        List<String> list = new ArrayList<>();
//        list.add("one");
//        list.add("two");
//        Map<String, ?> keyValueMap = new HashMap<String, Object>(){
//            {
//                put("1",list);
//                put("2","list");
//                put("3",1);
//            }
//        };
//        //keyset是循环key   entrySet是循环全部
//        Object[] objects = keyValueMap.keySet().toArray();
//        String [] keys = new String[keyValueMap.size()];
//        for(int i = 0 ; i < objects.length  ; i++){
//            keys[i] = (String)objects[i];
//            System.out.println(objects[i]);
//        }
//
//        int age = 10;
//        new Student(){
//            @Override
//            public int getAge() {
//                return age;
//            }
//        };
//
//        new Thread();
//
////        new Student( ()-> System.out.println("hhh我创建了") ).toString();
//        new HashMap<String , String>().put("","").toCharArray();
//        new Thread(()-> System.out.println("121")).start();
        
    }
}
