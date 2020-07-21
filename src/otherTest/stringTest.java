package otherTest;

import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class stringTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String  one= "11121jiangJ½¯";
        String name = one.replaceAll("[^\\u4e00-\\u9fa5]", "");
        String id = one.replace(name , "");
        System.out.println(id);
        System.out.println(name);
//        Pattern pattern = Pattern.compile("[^\u4E00-\u9FA5]");
//        //[\u4E00-\u9FA5]?unicode2?????
//        Matcher matcher = pattern.matcher(one);
//        System.out.println(matcher.replaceAll(""));
//        char [] c = one.toCharArray();
//        char [] newId = new char[1024];
//        List<Character> newId = new ArrayList<Character>();
//        int length = one.toCharArray().length;
//        for(int i = 0 ; i < length ; ++i){
//            if(!isChinese(c[i]))
//            {
//                newId.add(c[i]);
//            }else {
//                break;
//            }
//        }
//        String id = StringUtils.join(newId , "");
//        String name = one.replace(id ,"");
//        String name  = new String(one.replace(id , "").getBytes("gb2312") , StandardCharsets.UTF_8);
//        System.out.println(one);
//        System.out.println(id);
//        System.out.println(name);
//        System.out.println("½¯Ê÷é·");
    }

    private static final boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    protected Map<String , String> separateIdOrName(String idOrName){
        char [] chars = idOrName.toCharArray();
        List<Character> Ids = new ArrayList<Character>();
        int length = chars.length;
        for(int i = 0 ; i < length ; ++i){
            if(!isChinese(chars[i]))
            {
                Ids.add(chars[i]);
            }else {
                break;
            }
        }
        String id = StringUtils.join(Ids , "");
        String name = idOrName.replace(id ,"");
        return new HashMap<String , String>(){
            {
                put("id",id);
                put("name",name);
            }
        };
    }


}
