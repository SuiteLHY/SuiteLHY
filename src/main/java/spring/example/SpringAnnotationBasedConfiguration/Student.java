package spring.example.SpringAnnotationBasedConfiguration;

public class Student {

    private Integer age;

    private String name;

    @Override
    public String toString() {
        return "{name=" + name + ", age=" + age + "}";
    }

    //===== getter and setter =====//
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //===== other methods =====//
    public void printThrowException()
            throws IllegalArgumentException {
        System.out.println("An exception raised!");
        throw new IllegalArgumentException();
    }

}
