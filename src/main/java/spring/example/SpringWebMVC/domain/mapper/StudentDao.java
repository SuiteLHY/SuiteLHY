package spring.example.SpringWebMVC.domain.mapper;

import spring.example.SpringWebMVC.domain.entity.Student;
import spring.example.SpringWebMVC.domain.entity.StudentMarks;

import java.util.List;

/**
 * Student 基础数据交互业务接口
 */
public interface StudentDao {

    /**
     * 新增一条 Student 数据
     * @param name
     * @param age
     */
    void insert(String name, Integer age);

    /**
     * This method is used to create a record in the Student table
     * with related data processing business.
     * @param name
     * @param age
     * @param marks
     * @param year
     */
    void insert(String name, Integer age, Integer marks, Integer year);

    /**
     * 查询指定的 Student 数据
     * @param id
     * @return
     */
    Student select(Integer id);

    /**
     * 查询所有的 Student 数据
     * @return
     */
    List<StudentMarks> selectAll();

    /**
     * 删除指定的 Student 数据
     * @param id
     */
    void delete(Integer id);

    /**
     * 更新指定的 Student 数据
     * @param id
     * @param age
     */
    void update(Integer id, Integer age);

}
