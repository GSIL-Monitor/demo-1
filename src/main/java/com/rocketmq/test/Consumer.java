package com.rocketmq.test;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @Author suosong
 * @Date 2018/11/14
 */
public class Consumer {
    public static void main(String[] args) throws MQClientException {
        long now = System.currentTimeMillis();
        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer("xx12");
        pushConsumer.setNamesrvAddr("47.98.237.224:9876");
        pushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        pushConsumer.subscribe("topic01", "*");

        pushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for(MessageExt messageExt : msgs){
                    try {
                        if(now < messageExt.getBornTimestamp()){
                            String body = new String(messageExt.getBody(),"utf-8");
                            System.out.println(body);
                        }

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        pushConsumer.start();
    }
}
