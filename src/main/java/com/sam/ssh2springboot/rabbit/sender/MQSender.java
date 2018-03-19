package com.sam.ssh2springboot.rabbit.sender;

import com.sam.ssh2springboot.dataobject.TopResult;
import com.sam.ssh2springboot.rabbit.config.MQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: huangxin
 * @Date: Created in 下午2:52 2018/3/19
 * @Description:
 */
@Component
@Slf4j
public class MQSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 进入报警监控top命令队列
     * @param topResult
     */
    public boolean sendMonitorTopMessage(TopResult topResult) {
        log.info("send message:" + topResult.toString());
        amqpTemplate.convertAndSend(MQConfig.MONITOR_TOP_QUEUE, topResult);
        return true;
    }

}
