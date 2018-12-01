package com.rocketmq.test;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @Author suosong
 * @Date 2018/11/25
 */
public class Producer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("producerGroup01");
        producer.setNamesrvAddr("47.98.237.224:9876");
        producer.start();
        //producer.createTopic("topic01","topic01",8);
        for (int i = 0; i < 100; i++) {
            Message message = new Message();
            message.setTopic("topic01");
            message.setTags("tags01");
            message.setBody(("world "+i).getBytes());
            producer.send(message);
        }
        producer.shutdown();
    }
}
