package cn.pbj.demo2020.nginx;

import java.util.*;

/**
 * @ClassName: Loadbalancing
 * @Author: pbj
 * @Date: 2020/6/2 11:02
 * @Description: TODO [一篇有趣的负载均衡算法实现](https://www.cnblogs.com/niumoo/p/13021938.html)
 */
public class Loadbalancing {
    /** 服务器列表 */
    private static List<String> serverList = new ArrayList<>();
    static {
        serverList.add("192.168.1.2");
        serverList.add("192.168.1.3");
        serverList.add("192.168.1.4");
        serverList.add("192.168.1.5");
    }

    /**
     * 随机路由算法
     */
    public static String random() {
        // 复制遍历用的集合，防止操作中集合有变更
        List<String> tempList = new ArrayList<>(serverList.size());
        tempList.addAll(serverList);
        // 随机数随机访问
        int randomInt = new Random().nextInt(tempList.size());
        return tempList.get(randomInt);
    }

    public static void main(String[] args) {
        HashMap<String, Integer> serverMap = new HashMap<>();
        for (int i = 0; i < 20000; i++) {
            String server = random();
            Integer count = serverMap.get(server);
            if (count == null) {
                count = 1;
            } else {
                count++;
            }
            // 记录
            serverMap.put(server, count);
        }
        // 路由总体结果
        for (Map.Entry<String, Integer> entry : serverMap.entrySet()) {
            System.out.println("IP:" + entry.getKey() + "，次数：" + entry.getValue());
        }
    }

    private static Integer index = 0;

    /**
     * 轮训访问
     */
    public static String randomOneByOne() {
        // 复制遍历用的集合，防止操作中集合有变更
        List<String> tempList = new ArrayList<>(serverList.size());
        tempList.addAll(serverList);
        String server = "";
        synchronized (index) {
            index++;
            if (index == tempList.size()) {
                index = 0;
            }
            server = tempList.get(index);;
        }
        return server;
    }

    /** 服务器列表 */
    private static HashMap<String, Integer> serverMap = new HashMap<>();
    static {
        serverMap.put("192.168.1.2", 2);
        serverMap.put("192.168.1.3", 2);
        serverMap.put("192.168.1.4", 2);
        serverMap.put("192.168.1.5", 4);
    }
    private static Integer index1 = 0;

    /**
     * 加权路由算法
     */
    public static String oneByOneWithWeight() {
        List<String> tempList = new ArrayList();
        HashMap<String, Integer> tempMap = new HashMap<>();
        tempMap.putAll(serverMap);
        for (String key : serverMap.keySet()) {
            for (int i = 0; i < serverMap.get(key); i++) {
                tempList.add(key);
            }
        }
        synchronized (index1) {
            index1++;
            if (index1 == tempList.size()) {
                index1 = 0;
            }
            return tempList.get(index1);
        }
    }

    /**
     * 加权路由算法
     */
    public static String randomWithWeight() {
        List<String> tempList = new ArrayList();
        HashMap<String, Integer> tempMap = new HashMap<>();
        tempMap.putAll(serverMap);
        for (String key : serverMap.keySet()) {
            for (int i = 0; i < serverMap.get(key); i++) {
                tempList.add(key);
            }
        }
        int randomInt = new Random().nextInt(tempList.size());
        return tempList.get(randomInt);
    }

    /**
     * ip hash 路由算法
     */
    public static String ipHash(String ip) {
        // 复制遍历用的集合，防止操作中集合有变更
        List<String> tempList = new ArrayList<>(serverList.size());
        tempList.addAll(serverList);
        // 哈希计算请求的服务器
        int index = ip.hashCode() % serverList.size();
        return tempList.get(Math.abs(index));
    }
}
