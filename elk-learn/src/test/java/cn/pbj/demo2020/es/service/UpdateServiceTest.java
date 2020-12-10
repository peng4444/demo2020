package cn.pbj.demo2020.es.service;

import cn.pbj.demo2020.es.ElasticSearchApplication;
import cn.pbj.demo2020.es.entity.Student;
import cn.pbj.demo2020.es.mapper.StudentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @pClassName: UpdateServiceTest
 * @author: pengbingjiang
 * @create: 2020/12/10 11:26
 * @description: TODO
 */
@SpringBootTest(classes = ElasticSearchApplication.class)
@RunWith(SpringRunner.class)
public class UpdateServiceTest {

    @Resource
    private UpdateService updateService;

    @Resource
    private StudentMapper studentMapper;

    @Test
    public void updateTest() {
        Student student = new Student();
        student.setId(1L);
        student.setStudentName("胡锦强");
        student.setStudentNo("G030510");
        student.setAge(19);
        student.setSex("男");
        student.setClazz("G0305");
        String message = updateService.update(student);
        System.out.println(message);
    }


    /**
     * 参考{@link IndexServiceTest}的 'indexAsyncTest()' 的注释，同理。
     * @throws InterruptedException
     */
    @Test
    public void updateAsyncTest() throws InterruptedException {
        Student student = studentMapper.findOne(1L);
        String message = updateService.updateAsync(student);
        Thread.sleep(2000);
        System.out.println(message);
    }
}
