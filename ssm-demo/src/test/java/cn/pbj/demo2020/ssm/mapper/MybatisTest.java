package cn.pbj.demo2020.ssm.mapper;

import cn.pbj.demo2020.ssm.entity.SysUser;
import cn.pbj.demo2020.ssm.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @pClassName: MybatisTest
 * @author: pengbingjiang
 * @create: 2020/12/13 22:16
 * @description: TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisTest {
    private final static Logger logger = LoggerFactory.getLogger(MybatisTest.class);

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void queryUserInfo() throws Exception {
        SysUser userEntity = new SysUser();
        userEntity.setUserId(1L);
        List<SysUser> list = sysUserService.queryUserInfo(userEntity.getUserId());
        logger.info("list:" + list);
    }
}
