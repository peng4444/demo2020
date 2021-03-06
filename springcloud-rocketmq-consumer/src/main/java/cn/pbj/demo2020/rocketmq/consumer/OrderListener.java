package cn.pbj.demo2020.rocketmq.consumer;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @pClassName: OrderListener
 * @author: pengbingjiang
 * @create: 2020/11/18 10:53
 * @description: TODO
 */
@Component
@RocketMQMessageListener(topic = "TopicTest", consumerGroup = "CONSUMER_GROUP_DEMO", consumeMode = ConsumeMode.ORDERLY)
public class OrderListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String order) {
        System.out.println("TopicTest receive: " + order + ", receiveTime = " + System.currentTimeMillis());
    }
}



//@Component
//@RocketMQMessageListener(topic = "TopicOrder", consumerGroup = "CONSUMER_GROUP_ORDER")
//public class OrderListener implements RocketMQListener<Order> {
//
//    @Override
//    public void onMessage(Order order) {
//        // 调用数据库 update 库存表
//    }
//}
