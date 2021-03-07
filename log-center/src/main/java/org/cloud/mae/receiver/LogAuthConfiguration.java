package org.cloud.mae.receiver;

import org.cloud.mae.api.log.constants.LogQueue;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * @Author: Mae
 * @Date: 2021/2/23 12:18 上午
 */
public class LogAuthConfiguration {
    /**
     * declaration queue
     */
    @Bean
    public Queue logQueue() {
        return new Queue(LogQueue.LOG_QUEUE);
    }

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * declare LogMqClient as bean.
     */
    @Bean
    public LogMqClient logMqClient() {
        return new LogMqClient(amqpTemplate);
    }
}
