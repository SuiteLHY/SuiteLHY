package spring.example.SpringDI;

public class ExampleBean {

    private String name;

    private int num;

    private ExampleBean(String name, int num) {
        this.name = name;
        this.num = num;
        System.out.println("ExampleBean: {arg1=" + name + ", arg2=" + num + "}");
    }

    public void hello() {
        System.out.println("ExampleBean: hello, " + name + "!");
    }

}
