package Algorithms;

/**
 * Created by luoxianzhuo on 2018/8/15 17:35
 *
 * @author luoxianzhuo
 * @copyright Copyright 2014-2018 JD.COM All Right Reserved
 */
public class Student {

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                '}';
    }
}