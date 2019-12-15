package spring.example.SpringAnnotationBasedConfiguration.JavaBasedConfiguration;

public class Bar extends Foo {

    private String name = "Bar";

    public Bar() {
        super();
    }

    private Bar(Foo foo) {
        super(foo);
    }

    @Override
    public String getName() {
        return name;
    }

}
