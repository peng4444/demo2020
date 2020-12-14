package cn.pbj.demo2020.ssm.service.impl;

import cn.pbj.demo2020.ssm.dao.SysCodeDao;
import cn.pbj.demo2020.ssm.entity.SysCode;
import cn.pbj.demo2020.ssm.service.SysCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @pClassName: SysCodeServiceImpl
 * @author: pengbingjiang
 * @create: 2020/12/14 10:23
 * @description: TODO
 */
@Service
public class SysCodeServiceImpl implements SysCodeService {

    private Logger logger = LoggerFactory.getLogger(SysCodeService.class);

    @Autowired
    private SysCodeDao sysCodeDao;

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 查询所有数字字典
     * @return
     */
    @Override
    public List<SysCode> queryCodeAll() {
        logger.info("先从缓存中查找，如果没有则去数据进行查询");
        List<SysCode> codeList = (List<SysCode>)redisTemplate.opsForList().leftPop("codeList");
        if (codeList == null) {
            logger.info("说明缓存中没有数据，则到数据库中查询");
            codeList = sysCodeDao.queryCodeAll();

            logger.info("将数据库获取的数据存入缓存");
            redisTemplate.opsForList().leftPush("codeList", codeList);
        } else {
            logger.info("则说明缓存中存在，直接从缓存中获取数据");
        }
        return codeList;
    }
}
