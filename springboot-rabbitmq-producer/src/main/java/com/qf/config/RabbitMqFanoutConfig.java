package com.qf.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqFanoutConfig {

    //声明队列
    @Bean("SmsQueue")
    public Queue smsQueue(){
        return new Queue("sms-queue");
    }
    @Bean("EmailQueue")
    public Queue EmailQueue(){
        return new Queue("email-queue");
    }
    //声明交换机
    //发布订阅交换机
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("sms-email-exchange");
    }

    @Bean
    Binding bindSmsToExchange(@Qualifier("SmsQueue")Queue queue,FanoutExchange exchange){
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    Binding bindEmailToExchange(@Qualifier("EmailQueue")Queue queue,FanoutExchange exchange){
        return BindingBuilder.bind(queue).to(exchange);
    }
}
