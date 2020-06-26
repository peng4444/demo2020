package cn.pbj.demo2020.book.springinaction4;

import cn.pbj.demo2020.book.springinaction4.chpater2.CDPlayerConfig;
import cn.pbj.demo2020.book.springinaction4.chpater2.CompactDisc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * @pClassName: CDPlayerTest
 * @author: pengbingjiang
 * @create: 2020/6/25 23:07
 * @description: TODO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= CDPlayerConfig.class)
public class CDPlayerTest {

    @Autowired
    private CompactDisc cd;

    @Test
    public void cdShouldNotBeNull() {
        assertNotNull(cd);
    }

}
