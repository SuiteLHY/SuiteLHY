package spring.example.SpringJDBC.service;

import com.sun.istack.internal.NotNull;
import spring.example.SpringJDBC.model.Student;

public class StudentService {

    public static void show(@NotNull Student student) {
        System.out.println(student.toString());
    }

}
