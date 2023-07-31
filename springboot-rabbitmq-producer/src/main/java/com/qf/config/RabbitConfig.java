package com.qf.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    //声明队列
    @Bean
    public Queue queue(){
        return new Queue("hello");
    }
}
