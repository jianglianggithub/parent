package 有序消息;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Consumer {
    public static void main(String[] args) throws InterruptedException, MQClientException, MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("s3");
        consumer.setNamesrvAddr("192.168.0.113:9876");
        consumer.subscribe("test1", "tag1");
        consumer.registerMessageListener(new MessageListenerOrderly() {

            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                msgs.forEach(item-> System.out.println(new String(item.getBody())+item.getKeys()));
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        consumer.start();

    }
}
