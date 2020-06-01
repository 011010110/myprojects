package org.jiang.myrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author li.linhua
 * @Date 2020/5/29
 * @Version 1.0
 */
@Configuration
public class RabbitmqConfig {

    public static final String HELLO_QUEUE = "hello_queue";
    public static final String HELLO_EXCHANGE = "hello_exchange";
    public static final String HELLO_ROUTINGKEY = "hello_key";

    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("192.168.232.131");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("root");
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate(){
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue queue(){
        return new Queue(RabbitmqConfig.HELLO_QUEUE);
    }

    @Bean
    public Exchange exchange(){
        return new DirectExchange(RabbitmqConfig.HELLO_EXCHANGE);
    }

    @Bean
    public Binding binding(){
        Map<String,Object> params = new HashMap<>();
        return new Binding(RabbitmqConfig.HELLO_QUEUE, Binding.DestinationType.QUEUE, RabbitmqConfig.HELLO_EXCHANGE, RabbitmqConfig.HELLO_ROUTINGKEY, params);
    }

    @RabbitListener(queues = RabbitmqConfig.HELLO_QUEUE)
    public void rabbitmqListener(String message){
        System.out.println("收到了"+RabbitmqConfig.HELLO_QUEUE+"队列消息："+message);
    }

}
