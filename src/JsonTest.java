import com.alibaba.fastjson.JSON;

/**
 * Copyright (C), 2020, 东华厚盾
 * FileName: JsonTest
 * Author:   jiangshuju
 * Date:     2020/5/29 17:29
 * Description: 测试json转化为map
 */
import  java.util.*;
public class JsonTest {

    public static void main(String[] args) {
        String str = "{\"0\":\"zhangsan\",\"1\":\"lisi\",\"2\":\"wangwu\",\"3\":\"maliu\"}";
        Map<String , String> maps = (Map) JSON.parse(str);
        for(String key: maps.keySet()){
            System.out.println(key);
            System.out.println(maps.get(key));
        }
    }
}
