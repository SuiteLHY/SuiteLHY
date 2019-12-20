package spring.example.SpringJDBC;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.example.SpringJDBC.dao.StudentDao;
import spring.example.SpringJDBC.entity.Student;
import spring.example.SpringJDBC.entity.StudentMarks;
import spring.example.SpringJDBC.service.StudentServiceImpl;

import java.util.List;
import java.util.Objects;

public class MainApp {

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring/example/SpringJDBC/Beans.xml");
        StudentDao studentJDBCTemplate = context.getBean("studentJDBCTemplate", StudentDao.class);

        /*System.out.println("===== Records Creation =====");
        studentJDBCTemplate.insert("LiMing", 18, 99, 2000);
        studentJDBCTemplate.insert("ZhangSan", 19, 99, 2000);
        studentJDBCTemplate.insert("LiangEr", 17, 99, 2001);*/

        System.out.println("===== Listing Multiple Records =====");
        List<StudentMarks> students = studentJDBCTemplate.selectAll();
        for (StudentMarks record : students) {
            if (Objects.isNull(record)) continue;
            StudentServiceImpl.show(record);
        }

        /*System.out.println("===== Updating Record with specified ID =====");
        studentJDBCTemplate.update(2, 20);*/

        System.out.println("===== Listing Record with specified ID =====");
        Student student = studentJDBCTemplate.select(41);
        if (Objects.nonNull(student)) {
            StudentServiceImpl.show(student);
        }
        System.out.println("=====");
    }

}
