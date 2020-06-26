package cn.pbj.demo2020.springboot.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.nio.ByteBuffer;

/**
 * @pClassName: WebSocketTest
 * @author: pengbingjiang
 * @create: 2020/6/26 19:32
 * @description: TODO  [spring boot 使用WebSocket与前端进行byte字节数组交互](https://www.cnblogs.com/joey-java/p/10640675.html)
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint(value="/websocket")
@Component
public class WebSocketTest {
    @OnOpen
    public void onOpen(Session session){ System.out.println("加入连接");  }

    @OnClose
    public void onClose(){ System.out.println("关闭连接");  }

    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 收到客户端消息后调用的方法
     * @param messages 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(byte[] messages, Session session) {
        try {
            System.out.println("接收到消息:"+new String(messages,"utf-8"));
            //返回信息
            String resultStr="{name:\"张三\",age:18,addr:\"上海浦东\"}";
            //发送字符串信息的 byte数组
            ByteBuffer bf= ByteBuffer.wrap(resultStr.getBytes("utf-8"));
            session.getBasicRemote().sendBinary(bf);
            //发送字符串
            //session.getBasicRemote().sendText("测试");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
