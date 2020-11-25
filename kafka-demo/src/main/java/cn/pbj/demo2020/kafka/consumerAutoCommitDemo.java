package cn.pbj.demo2020.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Properties;

/**
 * @pClassName: consumerAutoCommitDemo
 * @author: pengbingjiang
 * @create: 2020/11/25 16:27
 * @description: TODO 自动提交offset实战
 */
public class consumerAutoCommitDemo {

    public static void main(String[] args) {

        //1.配置参数
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.101.206:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put("auto.commit.interval.ms", "1000");


        props.put(ConsumerConfig.GROUP_ID_CONFIG, "1205");

        //2.创建1个消费者
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        //3.订阅主题topic
        consumer.subscribe(Arrays.asList("pbj-topic-1"));

        //5.调用poll输出数据(不用自动提交offset)
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("topic = " + record.topic() + " offset = " + record.offset() + " value = " + record.value());
            }
        }
    }
}
