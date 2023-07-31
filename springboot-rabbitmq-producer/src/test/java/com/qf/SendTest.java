package com.qf;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SendTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void testSend(){
        /**
         * 1.交换机名称
         * 2.路由键、当交换机为“”时，为队列的名称
         * 3.消息内容
         */
        rabbitTemplate.convertAndSend("","hello","这是一条测试消息");
    }

    @Test
    public void testSendMore(){
        /**
         * 1.交换机名称
         * 2.路由键、当交换机为“”时，为队列的名称
         * 3.消息内容
         */
        //当由多台消费者时，消息会轮询进行消费。
        //1.当产生消息积压时，解决方案是什么？
        //启动多台消费者进行消费
        //2.消费是否会出现多次消费的情况？rabbit是如何防止多次消费的？
        //每条消息都已一条唯一标识。

        for (int i=0;i<200;i++){
            rabbitTemplate.convertAndSend("","hello","这是一条测试消息"+i);

        }
    }

    /**
     * 发送到交换机，由交换机将该条消息转发到绑定的所有的队列中。又称之为 广播模式。
     * 通知类型的消息。
     */
    @Test
    public void testSendSmsOrEmail(){
        rabbitTemplate.convertAndSend("sms-email-exchange","","发送短信以及邮件");
    }

    @Test
    public void testSendSms(){
        rabbitTemplate.convertAndSend("dir-sms-email-exchange","message","短信邮件服务接受");
    }

    @Test
    public void testSendTopicSms(){
        /**
         * 这这种通配符的模式下,相关的要求全部满足,素以一次会输出三个结果
         * sms/email和站内信
         *
         */
        rabbitTemplate.convertAndSend("topic-sms-email-exchange","sms.letter.email","短信邮件服务接受");
    }
}
