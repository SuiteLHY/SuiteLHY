package spring.example.SpringJDBC.service;

import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.example.SpringJDBC.dao.StudentDao;
import spring.example.SpringJDBC.entity.Student;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    public static void show(@NotNull Student student) {
        System.out.println(student.toString());
    }

}
