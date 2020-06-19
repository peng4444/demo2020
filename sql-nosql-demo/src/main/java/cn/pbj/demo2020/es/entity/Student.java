package cn.pbj.demo2020.es.entity;


import java.io.Serializable;

/**
 * @ClassName: Student
 * @Author: pbj
 * @Date: 2020/6/10 08:32
 * @Description: TODO
 */

public class Student implements Serializable {
    private Long id;

    private String studentName;

    private String studentNo;

    private String sex;

    private Integer age;

    private String clazz;

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", studentNo='" + studentNo + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", clazz='" + clazz + '\'' +
                '}';
    }
}
