package demo;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.List;


/***
 *   在rocketmq中 可以把  Topic 理解为 同一业务类型的消息。
 *   而  tags 相当于二级标签。  比如 支付类型的消息 分为支付完成 支付关闭 支付退款等等
 *
 *   类似于 rabbitmq中的 交换机  下的多个队列 然后用 rotingKey 来关联。来区分不同类型的业务场景
 *
 *      NameServer 得作用 存放 Topic 提供者 Broker 得信息  跟dubbo zookeeper中 生产者将自己主从到 zk 消费者向zk拉取 服务提供者信息一致
 *
 *      生产者 发送消息 时 先从 NameServer 中获取  Topic 对应得 Broker 然后建立长连接 每次发送消息 直接发到broker
 *      如果没有 则随机选取一个broker 分配这个Topic给 broker
 *      然后消费者也是。从 NameServer获取Topic 对应得 BrokerServer得信息 然后拉取 message
 *        //"1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h"
 *
 *
 *      对于 消息的顺序 包括 发送的顺序 和存储的顺序【rocketMq】 消费的顺序
 *      发送 和 消费必须是单线程 才能保证
 *      对同一个 订单号【比如】 通过取模 来分发到 同一个 broker 对于的 MessagQueue中 就保证了这个订单 不同时期 的顺序 消费。
 *      比如一个订单 需要先 执行操作1 然后执行操作2. 路由到 对于的分区就可以保证 存储的顺序性
 *
 *
 *
 *
 *      不同的消费者组  监听同一个Topic  被同一个 group 监听 那么这个组下 会被平均分配
 *
 *      不同组 监听 那么会是广播的效果
 */
public class product {


    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("producer1");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();

        for (int i = 0; i < 100; i++) {
            Message msg = new Message();
            msg.setTopic("test1");
            msg.setTags("tag1");
            msg.setBody(("mybody"+i).getBytes());
            producer.send(msg);

        }


        //Shut down once the producer instance is not longer in use.
//        producer.shutdown();
    }
}
