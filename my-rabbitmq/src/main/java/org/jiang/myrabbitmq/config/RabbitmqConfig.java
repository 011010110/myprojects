package org.jiang.myrabbitmq.config;

import org.jiang.myrabbitmq.utils.DateUtils;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    /**
     * 队列
     */
    public static final String HELLO_QUEUE = "hello_queue";
    public static final String HI_QUEUE = "hi_queue";
    public static final String NORMAL_QUEUE = "normal_queue";
    public static final String DLX_QUEUE = "dlx_queue";
    public static final String ALTERNATE_QUEUE = "alternate_queue";
    /**
     * 交换机
     */
    public static final String DIRECT_EXCHANGE = "direct_exchange";
    public static final String FANOUT_EXCHANGE = "fanout_exchange";
    public static final String TOPIC_EXCHANGE = "topic_exchange";
    public static final String ALTERNATE_EXCHANGE = "alternate_exchange";//备份交换机
    public static final String NORMAL_EXCHANGE = "normal_exchange";//正常交换机
    public static final String DLX_EXCHANGE = "dlx_exchange";//死信交换机
    /**
     * 路由键
     */
    public static final String HELLO_KEY = "hello.key";
    public static final String HI_KEY = "hi.key";
    public static final String NORMAL_KEY = "normal.key";
    public static final String ALTERNATE_KEY = "alternate.key";

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("192.168.232.130");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("root");
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
        rabbitAdmin.setIgnoreDeclarationExceptions(true);
        return rabbitAdmin;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue alternateQueue() {
        return new Queue(RabbitmqConfig.ALTERNATE_QUEUE);
    }

    @Bean
    public Queue queue() {
        return new Queue(RabbitmqConfig.HELLO_QUEUE);
    }

    @Bean
    public Queue hiQueue() {
        return new Queue(RabbitmqConfig.HI_QUEUE);
    }





    @Bean
    public Exchange directExchange() {
        Map<String, Object> arguments = new HashMap<>();
        //添加备份交换机
//        arguments.put("alternate-exchange", RabbitmqConfig.ALTERNATE_EXCHANGE);
        return new DirectExchange(RabbitmqConfig.DIRECT_EXCHANGE, true, false, arguments);
    }



    @Bean
    public Exchange fanoutExchange() {
        return new FanoutExchange(RabbitmqConfig.FANOUT_EXCHANGE);
    }

    @Bean
    public Exchange topicExchange() {
        return new TopicExchange(RabbitmqConfig.TOPIC_EXCHANGE);
    }

    @Bean
    public Exchange alternateExchange() {
        return new FanoutExchange(RabbitmqConfig.ALTERNATE_EXCHANGE);
    }

    @Bean
    public Binding bindingHelloDirect() {
        Map<String, Object> params = new HashMap<>();
        return new Binding(RabbitmqConfig.HELLO_QUEUE, Binding.DestinationType.QUEUE, RabbitmqConfig.DIRECT_EXCHANGE, RabbitmqConfig.HELLO_KEY, params);
    }

    @Bean
    public Binding bindingHiDirect() {
        Map<String, Object> params = new HashMap<>();
        return new Binding(RabbitmqConfig.HI_QUEUE, Binding.DestinationType.QUEUE, RabbitmqConfig.DIRECT_EXCHANGE, RabbitmqConfig.HI_KEY, params);
    }

    @Bean
    public Binding bindingHelloFanout() {
        Map<String, Object> params = new HashMap<>();
        return new Binding(RabbitmqConfig.HELLO_QUEUE, Binding.DestinationType.QUEUE, RabbitmqConfig.FANOUT_EXCHANGE, RabbitmqConfig.HELLO_KEY, params);
    }

    @Bean
    public Binding bindingHiFanout() {
        Map<String, Object> params = new HashMap<>();
        return new Binding(RabbitmqConfig.HI_QUEUE, Binding.DestinationType.QUEUE, RabbitmqConfig.FANOUT_EXCHANGE, RabbitmqConfig.HI_KEY, params);
    }

    @Bean
    public Binding bindingHelloTopic() {
        Map<String, Object> params = new HashMap<>();
        return new Binding(RabbitmqConfig.HELLO_QUEUE, Binding.DestinationType.QUEUE, RabbitmqConfig.TOPIC_EXCHANGE, RabbitmqConfig.HELLO_KEY, params);
    }

    @Bean
    public Binding bindingHiTopic() {
        Map<String, Object> params = new HashMap<>();
        return new Binding(RabbitmqConfig.HI_QUEUE, Binding.DestinationType.QUEUE, RabbitmqConfig.TOPIC_EXCHANGE, RabbitmqConfig.HI_KEY, params);
    }


    @RabbitListener(queues = RabbitmqConfig.HELLO_QUEUE)
    public void rabbitmqListener(String message) {
        System.out.println(DateUtils.getNow()+"收到了" + RabbitmqConfig.HELLO_QUEUE + "队列消息：" + message);
    }

    @RabbitListener(queues = RabbitmqConfig.HI_QUEUE)
    public void rabbitmqHiQueueListener(String message) {
        System.out.println(DateUtils.getNow()+"收到了" + RabbitmqConfig.HI_QUEUE + "队列消息：" + message);
    }

    /*@RabbitListener(queues = RabbitmqConfig.NORMAL_QUEUE)
    public void rabbitmqNormalQueueListener(String message) {
        System.out.println("收到了" + RabbitmqConfig.NORMAL_QUEUE + "队列消息：" + message);
    }*/

    @RabbitListener(queues = RabbitmqConfig.ALTERNATE_QUEUE)
    public void rabbitmqAlterNateQueueListener(String message) {
        System.out.println(DateUtils.getNow()+"收到了" + RabbitmqConfig.ALTERNATE_QUEUE + "队列消息：" + message);
    }

    @RabbitListener(queues = RabbitmqConfig.DLX_QUEUE)
    public void rabbitmqDlxQueueListener(String message) {
        System.out.println(DateUtils.getNow()+"收到了" + RabbitmqConfig.DLX_QUEUE + "队列消息：" + message);
    }
}
