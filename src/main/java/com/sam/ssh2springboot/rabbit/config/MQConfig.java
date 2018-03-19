package com.sam.ssh2springboot.rabbit.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: huangxin
 * @Date: Created in 下午2:43 2018/3/19
 * @Description:
 */
@Configuration
public class MQConfig {

    /**
     * 监控信息，top命令队列名称
     */
    public static final String MONITOR_TOP_QUEUE = "monitor.top.queue";

    /**
     * 接收监控信息，top命令队列,开启持久化操作
     * @return
     */
    @Bean
    public Queue monitorTopQueue() {
        return new Queue(MONITOR_TOP_QUEUE,true);
    }

}