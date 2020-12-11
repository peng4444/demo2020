package cn.pbj.demo2020.redis;

import redis.clients.jedis.Jedis;

/**
 * @pClassName: JedisDemo
 * @author: pengbingjiang
 * @create: 2020/11/10 15:07
 * @description: TODO Jedis直接连接服务器进行操作
 */
public class JedisDemo {
    public static void main(String[] args) {
        //查看访问服务器防火墙是否开启，开启则需要开启访问的6379端口
        Jedis jedis = null;
        try {
            //1. 生成一个Jedis对象，这个对象负责和指定Redis实例进行通信
            jedis = new Jedis("192.168.101.206", 6379);
            //Jedis jedis = new Jedis("192.168.101.222", 6379,客户端连接超时时间,客户端读写超时时间);
            //2. jedis执行set操作
            String set = jedis.set("hello1", "world1");
            //输出结果：ok
            System.out.println(set);
            //休眠30秒
            //TimeUnit.SECONDS.sleep(30);
            System.out.println("------");
            //3. jedis执行get操作, value="world"
            //输出结果：world
            String ans1 = jedis.get("hello1");
            System.out.println("get key=hello1,value="+ans1);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
