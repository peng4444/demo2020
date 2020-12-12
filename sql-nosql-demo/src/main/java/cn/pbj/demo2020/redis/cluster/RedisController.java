package cn.pbj.demo2020.redis.cluster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @pClassName: RedisController
 * @author: pengbingjiang
 * @create: 2020/12/12 22:21
 * @description: TODO
 */
@RestController
public class RedisController {
    @Autowired
    private RedisProperties redisProperties;

    @Autowired
    private RedisConfig redisConfig;

    @Autowired
    private JedisClientCluster jedisClientCluster;

    @RequestMapping(value = "getRedisValue")
    public String getRedisValue(){
        System.out.println(redisProperties.toString());
        //RedisProperties{expireSeconds=120, nodes='192.168.101.206:7000,192.168.101.206:7001,192.168.101.206:7002,192.168.101.206:7003,192.168.101.206:7004,192.168.101.206:7005', commandTimeout=5000}
        System.out.println(redisConfig.getJedisCluster().getClusterNodes());
        // {192.168.101.206:7000=redis.clients.jedis.JedisPool@705e40ce, 192.168.101.206:7001=redis.clients.jedis.JedisPool@652382aa, 192.168.101.206:7002=redis.clients.jedis.JedisPool@4f84edf1, 192.168.101.206:7003=redis.clients.jedis.JedisPool@1eb62a28, 192.168.101.206:7004=redis.clients.jedis.JedisPool@1c6c5100, 192.168.101.206:7005=redis.clients.jedis.JedisPool@6523b6f5}
        System.out.println(jedisClientCluster.get("test"));// 1234
        System.out.println(jedisClientCluster.get("yp"));// null
        jedisClientCluster.set("12","12");
        System.out.println(jedisClientCluster.get("12"));// 12
        return jedisClientCluster.get("12");
    }
}

