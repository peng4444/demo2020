package cn.pbj.demo2020.ssm.entity;

/**
 * @pClassName: Person
 * @author: pengbingjiang
 * @create: 2020/12/15 11:16
 * @description: TODO
 */

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 实体类字段加上javax.validation.constraints定义的注解
 * @author Summerday
 */

@Data
@ToString
public class Person {
    private Integer id;

    @NotNull
    @Size(min = 6,max = 12)
    private String name;

    @NotNull
    @Min(20)
    private Integer age;
}

