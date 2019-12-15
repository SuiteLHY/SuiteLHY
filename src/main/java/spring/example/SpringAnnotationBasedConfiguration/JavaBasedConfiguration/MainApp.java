package spring.example.SpringAnnotationBasedConfiguration.JavaBasedConfiguration;

import spring.example.SpringAnnotationBasedConfiguration.JavaBasedConfiguration.config.AppConfig;
import spring.example.SpringAnnotationBasedConfiguration.JavaBasedConfiguration.config.EventHandlerConfig;
/*import com.example.SpringAnnotationBasedConfiguration.JavaBasedConfiguration.config.HelloWorldConfig;
import com.example.SpringAnnotationBasedConfiguration.JavaBasedConfiguration.config.TextEditorConfig;*/
/*import org.springframework.context.ApplicationContext;*/
import spring.example.SpringAnnotationBasedConfiguration.JavaBasedConfiguration.event.CustomEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {

    public static void main(String[] args) {
        /*ApplicationContext ctx =
                new AnnotationConfigApplicationContext(HelloWorldConfig.class);*/
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        // AppConfig.class 通过 @Import 引入了其他所需的注解配置类
        ctx.register(AppConfig.class/*
                , HelloWorldConfig.class
                , TextEditorConfig.class*/
                , EventHandlerConfig.class);
        ctx.refresh();
        System.out.println("=====");

        HelloWorld helloWorld = ctx.getBean(HelloWorld.class);
        helloWorld.getMessage();
        System.out.println("=====");

        // Let us raise a start event.
        ctx.start();
        System.out.println("-----");

        Foo foo = (Foo) ctx.getBean("foo");
        System.out.println("Foo : " + foo.getName());
        System.out.println("=====");

        Bar bar = (Bar) ctx.getBean("bar");
        System.out.println("Bar : " + bar.getName());
        System.out.println("=====");

        // Let us raise a stop event.
        ctx.stop();
        System.out.println("-----");

        TextEditor textEditor = ctx.getBean(TextEditor.class);
        textEditor.spellCheck();
        System.out.println("=====");

        CustomEventPublisher publisher = ctx.getBean(CustomEventPublisher.class);
        publisher.publish();
        publisher.publish();
        System.out.println("=====");
    }

}
