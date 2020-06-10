package cn.pbj.demo2020.es.service;

import cn.pbj.demo2020.es.entity.Student;

import java.util.List;

/**
 * @ClassName: StudentService
 * @Author: pbj
 * @Date: 2020/6/10 08:41
 * @Description: TODO
 */
public interface StudentService {
    /**
     * 简单同步索引
     * @param student：学生实体
     * @return String
     */
    public String index(Student student);


    /**
     * 简单异步索引
     * @param student：学生实体
     * @return String
     */
    public String indexAsync(Student student);

    /**
     * 同步搜索区间查询
     * List<Student>
     * @param from: 左区间，默认包含左边界
     * @param to ： 右区间，默认包含右边界
     * @param field : 字段名
     * @param index : 索引库名称
     * @return List<Student>
     */
    public List<Student> searchRange(Object from, Object to, String field, String index);


    /**
     * 同步搜索查询年龄在某区间的学生
     * @param from：左区间，默认包含左边界
     * @param to：右区间，默认包含右边界
     * @param index：索引
     * @return List<Student>
     */
    public List<Student> searchAgeRange(Integer from, Integer to, String index);


    /**
     * 组合查询
     * @return List<Student>
     */
    public List<Student> searchBool();


    /**
     * 组合查询 + 聚合查询
     */
    public void searchBoolAndAggregation();


    /**
     * 分词查询
     * @param match：分词词值
     * @return List<Student>
     */
    public List<Student> searchMatch(String match);

    /**
     * 同步获取索引
     * @param id：文档id
     * @return Student
     */
    public Student get(String id);


    /**
     * 异步获取索引
     * @param id：文档id
     * @return Student
     */
    public Student getAsync(String id);


    /**
     * 同步获取数据源
     * @param id：文档id
     * @return Student
     */
    public Student getSource(String id);


    /**
     * 异步获取数据源
     * @param id：文档id
     * @return Student
     */
    public Student getSourceAsync(String id);


    /**
     * 判断一个对象存不存在
     * @param id：文档id
     * @return Boolean
     */
    public Boolean exists(String id);

    /**
     * 同步修改一个学生信息
     * @param student：学生实体
     * @return String
     */
    public String update(Student student);


    /**
     * 异步修改一个学生信息
     * @param student：学生实体
     * @return String
     */
    public String updateAsync(Student student);

    /**
     * 同步删除一个文档
     * @param id：文档id
     * @return String
     */
    public String delete(String id);


    /**
     * 异步删除一个文档
     * @param id：文档id
     * @return String
     */
    public String deleteAsync(String id);

}
