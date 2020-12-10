package cn.pbj.demo2020.es.service;

import cn.pbj.demo2020.es.entity.Student;

/**
 * @pClassName: GetService
 * @author: pengbingjiang
 * @create: 2020/12/10 11:12
 * @description: TODO
 */
public interface GetService {

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

}
