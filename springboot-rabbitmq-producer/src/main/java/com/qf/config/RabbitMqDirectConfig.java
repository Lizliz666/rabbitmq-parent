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
public class RabbitMqDirectConfig {

    //声明队列
    @Bean("DirectSmsQueue")
    public Queue smsQueue(){
        return new Queue("sms-dir-queue");
    }
    @Bean("DirectEmailQueue")
    public Queue EmailQueue(){
        return new Queue("email-dir-queue");
    }
    //声明交换机
    //发布订阅交换机
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("dir-sms-email-exchange");
    }

    @Bean
    Binding bindDirSmsToExchange(@Qualifier("DirectSmsQueue")Queue queue,DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("sms");
    }
    @Bean
    Binding bindDirSmsEmailToExchange(@Qualifier("DirectSmsQueue")Queue queue,DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("message");
    }

    @Bean
    Binding bindDirEmailToExchange(@Qualifier("DirectEmailQueue")Queue queue,DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("email");
    }
    @Bean
    Binding bindDirEmailSmsToExchange(@Qualifier("DirectEmailQueue")Queue queue,DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("message");
    }
}
