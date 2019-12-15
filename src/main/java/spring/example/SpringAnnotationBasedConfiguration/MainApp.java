package spring.example.SpringAnnotationBasedConfiguration;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext(
                "spring/example/SpringAnnotationBasedConfiguration/Beans.xml");
        context.registerShutdownHook();
        System.out.println("=====");

        TextEditor textEditor = (TextEditor) context.getBean("textEditor");
        textEditor.spellCheck();
        System.out.println("=====");

        OuterBean outerBean = (OuterBean) context.getBean("outerBean");
        outerBean.showInnerBean();
        System.out.println("=====");

        ExampleBean exampleBean = (ExampleBean) context.getBean("exampleBean");
        exampleBean.hello();
        System.out.println("=====");

        Profile profile = (Profile) context.getBean("profile");
        System.out.println(profile);
        System.out.println("=====");

        HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
        obj.getMessage();
        System.out.println("=====");
    }

}
