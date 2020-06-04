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
    public static final String HI_QUEUE = "hi_queue";
    public static final String DIRECT_EXCHANGE = "direct_exchange";
    public static final String FANOUT_EXCHANGE = "fanout_exchange";
    public static final String TOPIC_EXCHANGE = "topic_exchange";
    public static final String HELLO_KEY = "hello.key";
    public static final String HI_KEY = "hi.key";

    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("192.168.232.130");
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
    public Queue hiQueue(){
        return new Queue(RabbitmqConfig.HI_QUEUE);
    }

    @Bean
    public Exchange directExchange(){
        return new DirectExchange(RabbitmqConfig.DIRECT_EXCHANGE);
    }
    /*@Bean
    public Exchange fanoutExchange(){
        return new FanoutExchange(RabbitmqConfig.FANOUT_EXCHANGE,true,false);
    }*/
    @Bean
    public Exchange fanoutExchange(){
        return new FanoutExchange(RabbitmqConfig.FANOUT_EXCHANGE);
    }
    @Bean
    public Exchange topicExchange(){
        return new TopicExchange(RabbitmqConfig.TOPIC_EXCHANGE);
    }

    @Bean
    public Binding bindingHelloDirect(){
        Map<String,Object> params = new HashMap<>();
        return new Binding(RabbitmqConfig.HELLO_QUEUE, Binding.DestinationType.QUEUE, RabbitmqConfig.DIRECT_EXCHANGE, RabbitmqConfig.HELLO_KEY, params);
    }
    @Bean
    public Binding bindingHiDirect(){
        Map<String,Object> params = new HashMap<>();
        return new Binding(RabbitmqConfig.HI_QUEUE, Binding.DestinationType.QUEUE, RabbitmqConfig.DIRECT_EXCHANGE, RabbitmqConfig.HI_KEY, params);
    }

    @Bean
    public Binding bindingHelloFanout(){
        Map<String,Object> params = new HashMap<>();
        return new Binding(RabbitmqConfig.HELLO_QUEUE, Binding.DestinationType.QUEUE, RabbitmqConfig.FANOUT_EXCHANGE, RabbitmqConfig.HELLO_KEY, params);
    }
    @Bean
    public Binding bindingHiFanout(){
        Map<String,Object> params = new HashMap<>();
        return new Binding(RabbitmqConfig.HI_QUEUE, Binding.DestinationType.QUEUE, RabbitmqConfig.FANOUT_EXCHANGE, RabbitmqConfig.HI_KEY, params);
    }

    @Bean
    public Binding bindingHelloTopic(){
        Map<String,Object> params = new HashMap<>();
        return new Binding(RabbitmqConfig.HELLO_QUEUE, Binding.DestinationType.QUEUE, RabbitmqConfig.TOPIC_EXCHANGE, RabbitmqConfig.HELLO_KEY, params);
    }
    @Bean
    public Binding bindingHiTopic(){
        Map<String,Object> params = new HashMap<>();
        return new Binding(RabbitmqConfig.HI_QUEUE, Binding.DestinationType.QUEUE, RabbitmqConfig.TOPIC_EXCHANGE, RabbitmqConfig.HI_KEY, params);
    }

    @RabbitListener(queues = RabbitmqConfig.HELLO_QUEUE)
    public void rabbitmqListener(String message){
        System.out.println("收到了"+RabbitmqConfig.HELLO_QUEUE+"队列消息："+message);
    }

    @RabbitListener(queues = RabbitmqConfig.HI_QUEUE)
    public void rabbitmqHiQueueListener(String message){
        System.out.println("收到了"+RabbitmqConfig.HI_QUEUE+"队列消息："+message);
    }
}
