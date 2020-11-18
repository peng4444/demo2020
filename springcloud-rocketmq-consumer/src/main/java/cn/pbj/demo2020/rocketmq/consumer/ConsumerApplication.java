package cn.pbj.demo2020.rocketmq.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @pClassName: ConsumerApplication
 * @author: pengbingjiang
 * @create: 2020/11/18 10:50
 * @description: TODO
 */
@EnableBinding({ Sink.class, InputChannel.class})
@SpringBootApplication
public class ConsumerApplication {
    @StreamListener(value = InputChannel.ORDER_INPUT)
    public void receive(String receiveMsg) {
        System.out.println("receive: " + receiveMsg);
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
