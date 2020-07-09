import EntityTest.Person;

import java.util.Date;

/**
 * Copyright (C), 2020, 东华厚盾
 * FileName: Student
 * Author:   jiangshuju
 * Date:     2020/5/29 8:54
 * Description: 实体
 */

public class Student extends Person {
    private Long Id;
    public int Age;
    private int Grade;
    private Date Brithday;


    protected Student(){
        System.out.println("qwq");
    }

    public Student(int Age, int Grade) {
        this.Age = Age;
        this.Grade = Grade;
        System.out.println("Age = "+Age +",Grade = "+Grade+"，Brithday = "+Brithday);
    }

    public Student(Long Id, int Age, int Grade, Date Brithday) {
        this.Id = Id;
        this.Age = Age;
        this.Grade = Grade;
        this.Brithday = Brithday;
        System.out.println("Id = "+Id+",Age = "+Age +",Grade = "+Grade+"，Brithday = "+Brithday);
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public int getGrade() {
        return Grade;
    }

    public void setGrade(int Grade) {
        this.Grade = Grade;
    }

    public Date getBrithday() {
        return Brithday;
    }

    public void setBrithday(Date Brithday) {
        this.Brithday = Brithday;
    }
}
