package spring.example.SpringWebMVC.domain.entity;

import java.io.Serializable;

public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer age;

    private String name;

    private Integer id;

    public Student() {}

    public Student(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public boolean isEmpty() {
        return null == id || null == age || null == name;
    }

    @Override
    public String toString() {
        return isEmpty() ? "{}" : "{id=" + id
                + ", name=" + name
                + ", age=" + age + "}";
    }

    //===== setter and getter =====//
    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

}
