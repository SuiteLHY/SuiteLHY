package spring.example.SpringJDBC.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import spring.example.SpringJDBC.mapper.StudentMapper;
import spring.example.SpringJDBC.model.Student;

import javax.sql.DataSource;
import java.util.List;

/**
 * DAO 接口 StudentDAO 的实现类
 */
public class StudentJDBCTemplate implements StudentDAO {

    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(String name, Integer age) {
        String SQL = "insert into student (name, age) values (?, ?)";
        jdbcTemplate.update( SQL, name, age);
        System.out.println("Created Record {Name = " + name + ", Age = " + age + "}");
    }

    public Student getStudent(Integer id) {
        String SQL = "select * from student where id = ?";
        Student student = jdbcTemplate.queryForObject(SQL
                , new Object[]{id}
                , new StudentMapper());
        return student;
    }

    public List<Student> listStudents() {
        String SQL = "select * from student";
        List <Student> students = jdbcTemplate.query(SQL
                , new StudentMapper());
        return students;
    }

    public void delete(Integer id) {
        String SQL = "delete from student where id = ?";
        jdbcTemplate.update(SQL, id);
        System.out.println("Deleted Record with ID = " + id);
    }

    public void update(Integer id, Integer age){
        String SQL = "update student set age = ? where id = ?";
        jdbcTemplate.update(SQL, age, id);
        System.out.println("Updated Record with ID = " + id);
    }

}
