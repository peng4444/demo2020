package cn.pbj.demo2020.rocketmq.consumer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @pClassName: OrderSource
 * @author: pengbingjiang
 * @create: 2020/11/18 10:53
 * @description: TODO
 */
public interface OrderSource {

    String OUTPUT = "orderOutput";

    @Output(OrderSource.OUTPUT)
    MessageChannel output();
}
