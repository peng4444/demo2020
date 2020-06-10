package cn.pbj.demo2020.es.mapper;

import cn.pbj.demo2020.es.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    /**
     * 查询所有学生信息
     * @return List<Student>
     */
    List<Student> findAll();

    /**
     * 通过id查询学生信息
     * @param id：学生id
     * @return Student
     */
    Student findOne(Long id);

    /**
     * 通过学号查询学生信息
     * @param studentNo：学生学号
     * @return Student
     */
    Student findByStudentNo(String studentNo);
}
