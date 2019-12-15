package spring.example.SpringAOP.model;

import org.springframework.stereotype.Component;

@Component
public class Student {

    private Integer age = 0;

    private String name = "";

    public void printThrowException()
            throws IllegalArgumentException {
        System.out.println("Exception raised");
        throw new IllegalArgumentException();
    }

    //===== setter and getter =====//
    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        System.out.println("Age : " + age);
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        System.out.println("Name : " + name);
        return name;
    }

}
