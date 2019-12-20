package spring.example.SpringAOP.annotationBasedAOP;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.example.SpringAOP.annotationBasedAOP.config.AppConfig;
import spring.example.SpringAOP.model.Student;

public class Application {

    public static void main(String[] args) {
        /*AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);*/
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring/example/SpringAOP/annotationBasedAOP/Beans.xml");
        System.out.println("=====");

        Student student = context.getBean("studentInfo", Student.class);
        student.getName();
        student.getAge();
        try {
            student.printThrowException();
        } catch (IllegalArgumentException e) {
            System.err.println("An exception of " + e.getClass() + " raised!");
        }
        System.out.println("=====");
    }

}
