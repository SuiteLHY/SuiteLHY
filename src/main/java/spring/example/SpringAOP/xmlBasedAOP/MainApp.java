package spring.example.SpringAOP.xmlBasedAOP;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.example.SpringAOP.model.Student;

public class MainApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "spring/example/SpringAOP/xmlBasedAOP/Beans.xml");
        System.out.println("=====");

        Student student = context.getBean("student", Student.class);
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
