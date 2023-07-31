package com.qf.listen;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitListen {

    /**
     * RabbitListener属性为 监听队列的名称
     *
     * 只要没有抛出异常，则rabbitmq认为该条消息被正确消费，就会从队列中移除。
     * @param msg
     */
    @RabbitListener(queues = "hello")
    public void helloListen(String msg){
        try {
           // int i= 1/0;
            System.out.println(msg);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @RabbitListener(queues = "sms-queue")
    public void smsListen(String msg){
        try {
            // int i= 1/0;
            System.out.println("短信服务："+msg);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @RabbitListener(queues = "email-queue")
    public void emailListen(String msg){
        try {
            // int i= 1/0;
            System.out.println("邮箱服务："+msg);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @RabbitListener(queues = "email-dir-queue")
    public void emaiDirlListen(String msg){
        try {
            // int i= 1/0;
            System.out.println("邮箱服务："+msg);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @RabbitListener(queues = "sms-dir-queue")
    public void smsDirlListen(String msg){
        try {
            // int i= 1/0;
            System.out.println("短信服务："+msg);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @RabbitListener(queues = "sms-topic-queue")
    public void smsTopiclListen(String msg){
        try {
            // int i= 1/0;
            System.out.println("短信服务："+msg);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @RabbitListener(queues = "email-topic-queue")
    public void emailTopiclListen(String msg){
        try {
            // int i= 1/0;
            System.out.println("邮件服务："+msg);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @RabbitListener(queues = "letter-topic-queue")
    public void letterTopiclListen(String msg){
        try {
            // int i= 1/0;
            System.out.println("站内信："+msg);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
