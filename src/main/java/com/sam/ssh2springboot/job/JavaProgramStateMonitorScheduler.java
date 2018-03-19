package com.sam.ssh2springboot.job;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import com.sam.ssh2springboot.dataobject.TopResult;
import com.sam.ssh2springboot.properties.MonitorProperties;
import com.sam.ssh2springboot.rabbit.sender.MQSender;
import com.sam.ssh2springboot.repository.TopResultRepository;
import com.sam.ssh2springboot.util.DataFormatUtil;
import com.sam.ssh2springboot.util.MonitorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @Author: huangxin
 * @Date: Created in 下午1:58 2018/3/19
 * @Description:
 */
//@Configuration
//@EnableScheduling
@Slf4j
public class JavaProgramStateMonitorScheduler {

    @Autowired
    Connection connection;

    @Autowired
    TopResultRepository topResultRepository;

    @Autowired
    MonitorProperties monitorProperties;

    @Autowired
    MQSender mqSender;

    @Scheduled(cron = "0/15 * * * * *")
    public void scanJavaState() throws Exception {
        log.info("java scheduler started");
        //声明session
        Session session = null;
        //声明返回结果
        String result;
        try {
            //通过connection拿到session
            session = connection.openSession();
            //执行命令
            session.execCommand("top -bn1 | grep -w java", "UTF-8");
            //拿到输出结果
            InputStream is = session.getStdout();
            //解析返回结果
            result = MonitorUtil.parseResult(is, "UTF-8");
            List<Map<String, String>> topResultMapList = MonitorUtil.getTopResult(result);
            for (Map<String, String> topResultMap : topResultMapList) {
                if (Double.parseDouble(topResultMap.get("MEMPerUsed")) > 5) {
                    TopResult topResult = DataFormatUtil.formatTopResult(topResultMap, monitorProperties.getHost().getJava());
                    mqSender.sendMonitorTopMessage(topResult);
                }
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        log.info("java scheduler finished");
    }

}
