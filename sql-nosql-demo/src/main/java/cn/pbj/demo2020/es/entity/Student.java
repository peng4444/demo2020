package cn.pbj.demo2020.es.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName: Student
 * @Author: pbj
 * @Date: 2020/6/10 08:32
 * @Description: TODO
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
