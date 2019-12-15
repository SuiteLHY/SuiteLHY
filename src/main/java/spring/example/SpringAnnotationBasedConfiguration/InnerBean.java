package spring.example.SpringAnnotationBasedConfiguration;

public class InnerBean {

    private InnerBean() {
        System.out.println("Inside InnerBean constructor.");
    }

    public void show() {
        System.out.println("This is " + this.getClass().getName() + "!");
    }

}
