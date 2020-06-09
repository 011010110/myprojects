package org.jiang.myrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author li.linhua
 * @Date 2020/6/5
 * @Version 1.0
 */
@Configuration
public class DxlQueueExchange {

    @Bean
    public Queue dlxQueue() {
        return new Queue(RabbitmqConfig.DLX_QUEUE);
    }

    @Bean
    public Exchange dlxExchange() {
        return new FanoutExchange(RabbitmqConfig.DLX_EXCHANGE);
    }

    @Bean
    public Binding bindingDlxToDlx() {
        Map<String, Object> params = new HashMap<>();
        return new Binding(RabbitmqConfig.DLX_QUEUE, Binding.DestinationType.QUEUE, RabbitmqConfig.DLX_EXCHANGE, RabbitmqConfig.NORMAL_KEY, params);
    }

    /**
     * 正常交换机绑定死信交换机
     *
     * @return
     */
    @Bean
    public Exchange normalExchange() {
        Map<String, Object> arguments = new HashMap<>();
        return new DirectExchange(RabbitmqConfig.NORMAL_EXCHANGE, true, true, arguments);
    }

    @Bean
    public Queue normalQueue() {
        Map<String, Object> arguments = new HashMap<>();
        //添加死信交换机
        arguments.put("x-dead-letter-exchange", RabbitmqConfig.DLX_EXCHANGE);
        //死信路由键
        arguments.put("x-dead-letter-routing-key", RabbitmqConfig.NORMAL_KEY);
        return new Queue(RabbitmqConfig.NORMAL_QUEUE, true, false, false, arguments);
    }

    @Bean
    public Binding bindingNormalToNormal() {
        Map<String, Object> params = new HashMap<>();
        return new Binding(RabbitmqConfig.NORMAL_QUEUE, Binding.DestinationType.QUEUE, RabbitmqConfig.NORMAL_EXCHANGE, RabbitmqConfig.NORMAL_KEY, params);
    }
}
