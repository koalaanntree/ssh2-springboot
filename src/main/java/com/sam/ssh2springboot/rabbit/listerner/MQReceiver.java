package com.sam.ssh2springboot.rabbit.listerner;

import com.sam.ssh2springboot.dataobject.TopResult;
import com.sam.ssh2springboot.rabbit.config.MQConfig;
import com.sam.ssh2springboot.repository.TopResultRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: huangxin
 * @Date: Created in 下午2:54 2018/3/19
 * @Description:
 */
@Component
@Slf4j
public class MQReceiver {

    @Autowired
    private TopResultRepository topResultRepository;
    /**
     * Direct模式 接收监控top报警消息
     *
     * @param topResult
     */
    @RabbitListener(queues = MQConfig.MONITOR_TOP_QUEUE)
    public void receiveMonitorTopMessage(TopResult topResult) {
        log.info("received : "+topResult.toString());
        topResultRepository.save(topResult);
    }

}