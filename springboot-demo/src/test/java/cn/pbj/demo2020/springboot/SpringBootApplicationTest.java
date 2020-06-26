package cn.pbj.demo2020.springboot;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest //@ContextConfiguration(classes = DemoConf.class) @ContextConfiguration注解只加载所需配置，这样可以加速测试
public class SpringBootApplicationTest {

    @Test
    public void main() {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void main1() {
    }
}
