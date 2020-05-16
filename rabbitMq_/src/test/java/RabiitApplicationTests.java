import cn.hutool.poi.excel.ExcelUtil;
import com.App;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {App.class})
public class RabiitApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;


    @Test
    public void contextLoads() {

        //给死信队列发送消息  超过的数量转移到  死信队列中 死信队列 转发给 下面的 队列
        for (int i = 0; i < 1000; i++) {
            rabbitTemplate.convertAndSend("jiaohuanji2","mail","aaa");
        }



    }


    @Test
    public void contextLoadss() {

        //给死信队列发送消息  超过的数量转移到  死信交换机中 然后 转发给 下面的 队列
        for (int i = 0; i < 1000; i++) {
            rabbitTemplate.convertAndSend("queue3","aaa");
        }



    }
}
