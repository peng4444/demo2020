package cn.pbj.demo2020.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @pClassName: JedisDemo
 * @author: pengbingjiang
 * @create: 2020/11/10 15:07
 * @description: TODO Jedis直接连接Redis集群进行操作
 */
public class JedisClusterDemoTests {
    public static void main(String[] args) {
        Set<HostAndPort> nodes = new LinkedHashSet<HostAndPort>();
        nodes.add(new HostAndPort("192.168.101.206", 7000));
        nodes.add(new HostAndPort("192.168.101.206", 7001));
        nodes.add(new HostAndPort("192.168.101.206", 7002));
        nodes.add(new HostAndPort("192.168.101.206", 7003));
        nodes.add(new HostAndPort("192.168.101.206", 7004));
        nodes.add(new HostAndPort("192.168.101.206", 7005));
        JedisCluster cluster = new JedisCluster(nodes, 5000);
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
