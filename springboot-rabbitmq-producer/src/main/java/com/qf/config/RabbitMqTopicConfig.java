package com.qf.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 路由键模式：
 * 随着队列的增多，与只绑定的规则越来越多。
 */
@Configuration
public class RabbitMqTopicConfig {

    //声明队列
    @Bean("TopicSmsQueue")
    public Queue smsQueue(){
        return new Queue("sms-topic-queue");
    }
    @Bean("TopicEmailQueue")
    public Queue EmailQueue(){
        return new Queue("email-topic-queue");
    }
    @Bean("TopicletterQueue")
    public Queue letterQueue(){
        return new Queue("letter-topic-queue");
    }
    //声明交换机
    //通配符的交换机
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topic-sms-email-exchange");
    }

    @Bean
    Binding bindTopicSmsToExchange(@Qualifier("TopicSmsQueue")Queue queue,TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("sms.*.*");
    }
    @Bean
    Binding bindTopicSmsEmailToExchange(@Qualifier("TopicEmailQueue")Queue queue,TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("*.*.email");
    }

    @Bean
    Binding bindTopicletterToExchange(@Qualifier("TopicletterQueue")Queue queue,TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("*.letter.*");
    }

}
