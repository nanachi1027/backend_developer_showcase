package com.cloud.mae.logreceiver;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.cloud.mae.model.log.Log;

import java.util.Date;

/**
 * @Author: Mae
 * @Date: 2021/2/22 11:54 下午
 * <p>
 * aop log implementation class.
 */
@Aspect
public class LogAop {

    private static final Logger logger = LoggerFactory.getLogger(LogAop.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * around  annotation with @LogAnnotation AOP
     */
    public Object logSave(ProceedingJoinPoint joinPoint) throws Throwable {
        Log log = new Log();
        log.setCreateTime(new Date());
        // todo: when add user
        return null;
    }
}
