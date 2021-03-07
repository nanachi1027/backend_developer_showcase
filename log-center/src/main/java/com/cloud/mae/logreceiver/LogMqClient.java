package com.cloud.mae.logreceiver;

import org.apache.commons.lang3.StringUtils;
import org.cloud.mae.model.log.Log;
import org.cloud.mae.model.log.constants.LogQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

/**
 * @Author: Mae
 * @Date: 2021/2/23 12:11 上午
 */
public class LogMqClient {

    private static final Logger logger = LoggerFactory.getLogger(LogMqClient.class);

    private AmqpTemplate amqpTemplate;

    public LogMqClient(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendLogMsg(String module, String username, String params, String remark, boolean flag) {
        CompletableFuture.runAsync(() -> {
            try {
                Log log = new Log();
                log.setCreateTime(new Date());
                if (StringUtils.isNotBlank(username)) {
                    log.setUsername(username);
                } else {
                    // todo: get login app user instance
                    // AppUserUtil to get current login app user and refre user's back info
                }
                log.setFlag(flag);
                log.setModule(module);
                log.setParams(params);
                log.setRemark(remark);

                amqpTemplate.convertAndSend(LogQueue.LOG_QUEUE, log);
                logger.info("send log to log queue: {}", log);
            } catch (Exception e) {
                logger.error("failed to send log to queue ", e);
            }
        });
    }
}
