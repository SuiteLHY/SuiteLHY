package spring.example.SpringWebMVC.domain.mapper;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import spring.example.SpringWebMVC.domain.entity.Student;
import spring.example.SpringWebMVC.domain.entity.StudentMarks;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Student 基础数据交互业务实现
 *
 * @Desprition Student 持久层接口实现类
 */
// @Transactional : 该类/方法需要事务支持。
//-> 方法级别注释：被注解的方法在被调用时，Spring 开启一个新的事务，
//-> 当方法无异常运行结束后，Spring 会提交这个事务。
//-> 类级别注释：被注解的类的所有 public 方法都是开启事务的。
/*@Repository
@Transactional*/
public class StudentDaoImpl implements StudentDao {

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private JdbcTemplate singleJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    public void insert(String name, Integer age) {
        String SQL = "insert into student (name, age) values (?, ?)";
        try {
            singleJdbcTemplate().update(SQL, name, age);
            System.out.println("Created Record {name = " + name + ", age = " + age + "}");
        } catch (DataAccessException e) {
            System.out.println("An Error in creating record, rolling back!");
            throw e;
        }
    }

    @Override
    public void insert(String name, Integer age, Integer marks, Integer year) {
        try {
            String SQL1 = "insert into Student (name, age) values (?, ?)";
            singleJdbcTemplate().update(SQL1, name, age);
            // Get the latest studentInfo id to be used in Marks table
            String SQL2 = "select max(id) from Student";
            Integer sid = singleJdbcTemplate().queryForObject(SQL2, Integer.class);
            String SQL3 = "insert into Marks(sid, marks, year) "
                    + " values (?, ?, ?)";
            singleJdbcTemplate().update(SQL3, sid, marks, year);
            System.out.println("Created Record {name = " + name + ", age = " + age + "}");
            // to simulate the exception.
            throw new RuntimeException("simulate Error condition!");
        } catch (DataAccessException e) {
            System.out.println("An Error in creating record, rolling back!");
            throw e;
        }
    }

    @Override
    public Student select(Integer id) {
        Student result;
        try {
            String SQL = "select * from student where id = ?";
            result = singleJdbcTemplate().queryForObject(SQL
                    , new Object[]{id}
                    , new RowMapper<Student>() {
                        @Override
                        public Student mapRow(ResultSet rs, int i) throws SQLException {
                            Student student = new Student();
                            student.setId(rs.getInt("id"));
                            student.setName(rs.getString("name"));
                            student.setAge(rs.getInt("age"));
                            return student;
                        }
                    });
        } catch (DataAccessException e) {
            System.out.println("An Error in creating record, rolling back!");
            e.printStackTrace();
            result = new Student();
        }
        return result;
    }

    @Override
    public List<StudentMarks> selectAll() {
        List<StudentMarks> result;
        try {
            String SQL = "select * from student, marks where student.id=marks.sid";
            result = singleJdbcTemplate().query(SQL, new RowMapper<StudentMarks>() {
                        @Override
                        public StudentMarks mapRow(ResultSet rs, int i) throws SQLException {
                            StudentMarks studentMarks = new StudentMarks();
                            studentMarks.setId(rs.getInt("id"));
                            studentMarks.setName(rs.getString("name"));
                            studentMarks.setAge(rs.getInt("age"));
                            studentMarks.setSid(rs.getInt("sid"));
                            studentMarks.setMarks(rs.getInt("marks"));
                            studentMarks.setYear(rs.getInt("year"));
                            return studentMarks;
                        }
                    });
        } catch (DataAccessException e) {
            System.out.println("An Error in creating record, rolling back!");
            e.printStackTrace();
            result = new ArrayList<>();
        }
        return result;
    }

    @Override
    public void delete(Integer id) {
        String SQL = "delete from student where id = ?";
        singleJdbcTemplate().update(SQL, id);
        System.out.println("Deleted Record with ID = " + id);
    }

    @Override
    public void update(Integer id, Integer age){
        String SQL = "update student set age = ? where id = ?";
        singleJdbcTemplate().update(SQL, age, id);
        System.out.println("Updated Record with ID = " + id);
    }

}
