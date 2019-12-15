package spring.example.SpringAnnotationBasedConfiguration.JavaBasedConfiguration;

/**
 * (使用了自限定类型)
 *
 * @param <E>
 */
public class Foo<E extends Foo<E>>  {

    private String name;

    protected Foo() {}

    public Foo(E foo) {
        this.name = foo.getName();
    }

    //===== getter =====//
    public String getName() {
        return name;
    }

    //===== initialization and destruction =====//
    public void init() {
        // initialization logic
        System.out.println("class Foo : initialization logic.");
    }

    public void cleanup() {
        // destruction logic
        System.out.println("class Foo : destruction logic.");
    }

}
