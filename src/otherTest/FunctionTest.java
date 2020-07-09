package otherTest;

import entityTest.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Copyright (C), 2020, 东华厚盾
 * FileName: otherTest.FunctionTest
 * Author:   jiangshuju
 * Date:     2020/5/29 10:53
 * Description: 函数式编程
 */

public class FunctionTest {

    static List<Person> list = getPersonList();
    public static void main(String[] args) {


        Test4();
        System.exit(0);
    }

    public static void test(){
        /**
         * 线程
         */
        new Thread((new Runnable() {
            @Override
            public void run() {
                System.out.println("线程qwq");
            }
        })).start();

        new Thread(()-> System.out.println("线程QAQ")).start();
    }

    public static void test2(){

        //list.forEach(person -> System.out.println(person.toString()));
        Consumer<Person> consumer = e -> e.setAge(e.getAge()+3);
        list.forEach(consumer);
        //list.forEach(e -> System.out.println(e.toString()));
        Predicate<Person> personPredicate = e -> e.getAge() > 4 && e.getName().equals("2");

        list.stream().filter(personPredicate).forEach(e -> System.out.println(e.toString()));

    }

    public static void  test3(){

      Stream<Person> one =   list.stream().sorted((p1, p2) -> (p2.getAge() - p1.getAge()));
      List<Person> personList = one.collect(Collectors.toList());
        System.out.println("----------------------------------------------------------");
        list.stream().sorted(Comparator.comparing(Person::getAge)).forEach(e -> System.out.println(e.toString()));

    }

    public static void Test4(){
      Person person =   list.stream().max(Comparator.comparing(Person::getAge)).orElse(null);
        System.out.println(person.toString());
    }

    private static List<Person> getPersonList(){
        Person p1 = new Person("1",1,"1");
        Person p2 = new Person("2",2,"2");
        Person p3 = new Person("3",3,"3");
        Person p4 = new Person("4",1,"1");
        Person p5 = new Person("5",1,"1");
        Person p6 = new Person("6",1,"1");
        Person p7 = new Person("7",1,"1");
        Person p8 = new Person("8",1,"1");

        List<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);
        list.add(p6);
        list.add(p7);
        list.add(p8);
        return list;

    }
}
