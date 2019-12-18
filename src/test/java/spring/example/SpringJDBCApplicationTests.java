package spring.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;
import spring.example.SpringJDBC.dao.StudentDao;
import spring.example.SpringJDBC.entity.Student;
import spring.example.SpringJDBC.entity.StudentMarks;
import spring.example.SpringJDBC.service.StudentServiceImpl;

import java.util.List;
import java.util.Objects;

@SpringBootTest
public class SpringJDBCApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Test
    void studentJDBC() {
        StudentDao studentJDBCTemplate;
        Assert.notNull(studentJDBCTemplate = context.getBean(StudentDao.class)
                , "获取测试单元失败");

        System.out.println("===== Records Creation =====");
        studentJDBCTemplate.insert("LiMing", 18, 99, 2000);
        studentJDBCTemplate.insert("ZhangSan", 19, 99, 2000);
        studentJDBCTemplate.insert("LiangEr", 17, 99, 2001);

        System.out.println("===== Listing Multiple Records =====");
        List<StudentMarks> students = studentJDBCTemplate.selectAll();
        for (Student record : students) {
            if (Objects.isNull(record)) continue;
            StudentServiceImpl.show(record);
        }

        /*System.out.println("===== Updating Record with specified ID =====");
        studentJDBCTemplate.update(2, 20);*/

        System.out.println("===== Listing Record with specified ID =====");
        Student student = studentJDBCTemplate.select(20);
        if (Objects.nonNull(student)) {
            StudentServiceImpl.show(student);
        }
        System.out.println("=====");
    }

}
