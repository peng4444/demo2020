package cn.pbj.demo2020.es.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @pClassName: Student
 * @author: pengbingjiang
 * @create: 2020/12/10 11:05
 * @description: TODO
 */
@ToString
@Getter
@Setter
public class Student implements Serializable {

    private Long id;

    private String studentName;

    private String studentNo;

    private String sex;

    private Integer age;

    private String clazz;

}
