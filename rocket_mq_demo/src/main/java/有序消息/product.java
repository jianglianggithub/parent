package 有序消息;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
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
 *  keys :
 *      每个消息在业务层面的唯一标识码，要设置到 keys 字段，方便将来定位消息丢失问题。服务器会为每个消
 * 息创建索引（哈希索引），应用可以通过 Topic，key 来查询返条消息内容，以及消息被谁消费。由于是哈希索引，请务必保证 key 尽可能唯一，这样可以避免潜在的哈希冲突
 *

 */
public class product {


    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("producer1");
       producer.setNamesrvAddr("localhost:9876");
        producer.start();

        for (int i = 0; i < 100; i++) {
            Message msg = new Message( );
            if (i==0)
                msg.setKeys("qwerty");//设置key的作用就是 可以在控制台查询这个消息。这个key应该是一个唯一值 此外。还有一个id 是hash值
                                                            // 这个值最好设置 方便后续 排查消息丢失 等等问题
            msg.setTopic("test1");
            msg.setTags("tag1");
            msg.setBody(("订单"+i).getBytes());
            producer.send(msg,new MessageQueueSelector(){

                @Override//MessageQueue = 一个brocker下的队列 一个Topic 会将消息 分布在多个brocker的队列中 默认4个
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    return mqs.get((int)arg % mqs.size());  //将同一个订单号 分散到同一个队列。 前提是这个订单的 消息是单线程 send的不然保证不了顺序性
                }
            },1);//这个参数 可以理解为订单号 并不是一个唯一的 同一个订单号按着发送的顺序 然后在中间件中顺序存储

        }


        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }
}
