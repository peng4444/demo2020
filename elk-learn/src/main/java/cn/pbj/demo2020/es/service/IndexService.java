package cn.pbj.demo2020.es.service;

import cn.pbj.demo2020.es.entity.Student;

/**
 * @pClassName: IndexService
 * @author: pengbingjiang
 * @create: 2020/12/10 11:07
 * @description: TODO
 */
public interface IndexService {

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

}
