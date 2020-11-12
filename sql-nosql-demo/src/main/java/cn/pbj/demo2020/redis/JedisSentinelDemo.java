package cn.pbj.demo2020.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * @pClassName: JedisSentinelDemo
 * @author: pengbingjiang
 * @create: 2020/11/11 14:19
 * @description: TODO Redis Sentinel
 */
public class JedisSentinelDemo {
    public static void main(String[] args) {
        Set<String> sentinelSet = new HashSet<>();
        sentinelSet.add("sentinel1");
        sentinelSet.add("sentinel2");
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(100);
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool("masterName", sentinelSet, poolConfig, 100);
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            // jdeis command
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
