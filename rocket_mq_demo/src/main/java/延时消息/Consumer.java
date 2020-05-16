package 延时消息;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Consumer {
    public static void main(String[] args) throws InterruptedException, MQClientException, MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("s3");
        consumer.setNamesrvAddr("192.168.0.113:9876");
        consumer.subscribe("test1", "*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for(int i=0; i<msgs.size(); i++){
                    MessageExt msg = msgs.get(i);
                    System.out.println(msg.getTopic() + " " + msg.getTags() + " " + new String(msg.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();

    }
}
