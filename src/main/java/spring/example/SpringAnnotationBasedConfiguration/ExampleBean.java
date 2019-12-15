package spring.example.SpringAnnotationBasedConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

public class ExampleBean {

    private String name;

    // 属性中的 @Autowired
    @Autowired(required = false)
    private int num;

    private ExampleBean(String name, @Nullable int num) {
        this.name = name;
        this.num = num;
        System.out.println("ExampleBean: {arg1=" + name + ", arg2=" + num + "}");
    }

    public void hello() {
        System.out.println("ExampleBean: hello, " + name + "!");
    }

    //===== getter and setter =====//
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

}
