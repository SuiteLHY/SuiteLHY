package spring.example.SpringJDBC.entity;

public class Student {

    private Integer age;

    private String name;

    private Integer id;

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
