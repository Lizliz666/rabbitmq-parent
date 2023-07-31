package com.qf;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsumerExample {

        private static final String QUEUE_NAME="hello";

        public static void main(String[] args) {
            //1.建立连接
            //1.设置连接工厂，设置 连接的rabbitmq服务的Ip,port,用户名，密码，以及主机
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("10.35.186.65");
            connectionFactory.setPort(5672);
            connectionFactory.setUsername("root");
            connectionFactory.setPassword("root");
            connectionFactory.setVirtualHost("xa-2304");

            try {
                //2.基于连接工厂创建连接
                Connection connection = connectionFactory.newConnection();
                //2.建立信道
                Channel channel = connection.createChannel();
                //3.通过信道发送消息
                //1.通过信道发送到交换机，如果为hello-world模式，使用的默认交换机，但是需要声明队列。
                //通过信道声明队列时需填写5个参数
                /**
                 * String queue,  队列的名称
                 * boolean durable, 是否持久化
                 * boolean exclusive, 是否排外的 如果设置为true.代表在一个连接中才能消费该条消息。
                 * boolean autoDelete, 是否自动删除
                 * Map<String, Object> arguments 额外属性的设置。11个参数。
                 */
                channel.queueDeclare(QUEUE_NAME,true,false,false,null);
                //3.监听某一个队列
                //声明回调方法
                DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                    //consumerTag 消费的唯一标识。delivery 消息内容
                    String message = new String(delivery.getBody(), "UTF-8");
                    System.out.println(" [x] Received '" + message + "'");
                };
                /**
                 * String queue,  监听队列的名称
                 * boolean autoAck, 是否自动的删除
                 * DeliverCallback deliverCallback,  消息监听的回调方法
                 * CancelCallback cancelCallback  取消回调
                 */
                channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }

        }

}
