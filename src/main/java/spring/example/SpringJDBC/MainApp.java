package spring.example.SpringJDBC;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.example.SpringJDBC.dao.StudentJDBCTemplate;
import spring.example.SpringJDBC.model.Student;
import spring.example.SpringJDBC.service.StudentService;

import java.util.List;
import java.util.Objects;

public class MainApp {

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring/example/SpringJDBC/Beans.xml");
        StudentJDBCTemplate studentJDBCTemplate =
                (StudentJDBCTemplate) context.getBean("studentJDBCTemplate");

        System.out.println("------ Records Creation --------");
        studentJDBCTemplate.create("Zara", 11);
        studentJDBCTemplate.create("Nuha", 2);
        studentJDBCTemplate.create("Ayan", 15);

        System.out.println("------ Listing Multiple Records --------");
        List<Student> students = studentJDBCTemplate.listStudents();
        for (Student record : students) {
            if (Objects.isNull(record)) continue;
            StudentService.show(record);
        }

        System.out.println("---- Updating Record with ID = 2 -----");
        studentJDBCTemplate.update(2, 20);

        System.out.println("----Listing Record with ID = 2 -----");
        Student student = studentJDBCTemplate.getStudent(2);
        if (Objects.nonNull(student)) {
            StudentService.show(student);
        }
        System.out.println("=====");
    }

}
