package spring.example.SpringIoC;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext(
                "spring/example/SpringIoC/Beans.xml");
        context.registerShutdownHook();

        HelloWorld objA = (HelloWorld) context.getBean("helloWorld");
        objA.setMessage1("This is objA!");
        objA.showMessage1();
        objA.showMessage2();

        HelloWorld objB = (HelloWorld) context.getBean("helloWorld");
        objB.showMessage1();
        objB.showMessage2();

        HelloChina objC = (HelloChina) context.getBean("helloChina");
        objC.showMessage1();
        objC.showMessage2();
        objC.showMessage3();
    }

}
