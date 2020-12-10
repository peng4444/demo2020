package cn.pbj.demo2020.es.service;

import cn.pbj.demo2020.es.entity.Student;

/**
 * @pClassName: UpdateService
 * @author: pengbingjiang
 * @create: 2020/12/10 11:13
 * @description: TODO
 */
public interface UpdateService {

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

}
