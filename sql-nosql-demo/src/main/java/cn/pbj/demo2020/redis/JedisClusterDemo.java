package cn.pbj.demo2020.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @pClassName: JedisDemo
 * @author: pengbingjiang
 * @create: 2020/11/10 15:07
 * @description: TODO Jedis直接连接服务器进行操作
 */
public class JedisClusterDemo {
    public static void main(String[] args) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大连接数
        poolConfig.setMaxTotal(30);
        // 最大空闲数
        poolConfig.setMaxIdle(2);
        // 最大允许等待时间，如果超过这个时间还未获取到连接，则会报JedisException异常：
        // Could not get a resource from the pool
        poolConfig.setMaxWaitMillis(1000);
        Set<HostAndPort> nodes = new LinkedHashSet<HostAndPort>();
        nodes.add(new HostAndPort("192.168.101.206", 6371));
        nodes.add(new HostAndPort("192.168.101.206", 6372));
        nodes.add(new HostAndPort("192.168.101.206", 6373));
        nodes.add(new HostAndPort("192.168.101.206", 6374));
        nodes.add(new HostAndPort("192.168.101.206", 6375));
        nodes.add(new HostAndPort("192.168.101.206", 6376));
        JedisCluster cluster = new JedisCluster(nodes, poolConfig);
        String name = cluster.get("test");
        System.out.println(name);
        cluster.set("age", "18");
        System.out.println(cluster.get("age"));
        try {
            cluster.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
