import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Copyright (C), 2020, 东华厚盾
 * FileName: ClassTest
 * Author:   jiangshuju
 * Date:     2020/5/29 8:54
 * Description: 反射
 */

public class ClassTest {
    public static void main(String[] args) {
        try {

            Class clazz = Class.forName("Student");
//            /**
//             * 获取所有的公共构造方法
//             */
//            Constructor[] constructor = clazz.getConstructors();
////            for(Constructor constructor1 : constructor){
////                System.out.println(constructor1);
////            }
//            /**
//             * 获取所有的（私有 受保护 ...）构造方法
//             */
//            Constructor[] constructors = clazz.getDeclaredConstructors();
////            for(Constructor c : constructors){
////                System.out.println(c);
////            }
//            Constructor constructor1 = clazz.getDeclaredConstructor(int.class,int.class);
//            //System.out.println("constructor1="+constructor1);
//            /**
//             * 调用构造方法
//             */
//           // Object o = constructor1.newInstance(1,2);
//            //System.out.println("o=="+o);
//           // Student student = (Student) o;
//            //System.out.println(student.getGrade());

            /**
             * 通过构造方法创立新对象
             */
            Object obj = clazz.getDeclaredConstructor().newInstance();
            /**
             * 公有变量
             */
            //Field[] field1 = clazz.getFields();
//            for(Field field : field1){
//                System.out.println(field);
//            }
//            Field[] field2 = clazz.getDeclaredFields();
//            for(Field field : field2){
//                System.out.println(field);
//            }
//
//            Field field = clazz.getField("age");
//            field.set(obj ,13 );
//
//            Student student = (Student) obj;
//            System.out.println("age====="+student.getAge());
//
//            Field field3 = clazz.getDeclaredField("grade");
//            field3.setAccessible(true);
//            field3.set(obj,111111);
//            System.out.println(student.getGrade());

//            Method [] methods = clazz.getMethods();
//            for(Method method : methods){
//                System.out.println(method);
//            }
            Field[] field = clazz.getDeclaredFields();
            for(Field field2 : field){
                Method method = clazz.getMethod("get"
                        + field2.getName().substring(0, 0 + 1).toUpperCase()
                        + field2.getName().substring(0 + 1),new Class[]{});
                System.out.println(method);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
