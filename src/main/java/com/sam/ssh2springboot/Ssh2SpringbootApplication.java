package com.sam.ssh2springboot;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.sam.ssh2springboot.dataobject.TopResult;
import com.sam.ssh2springboot.properties.MonitorProperties;
import com.sam.ssh2springboot.util.MonitorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController("/monitor")
public class Ssh2SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ssh2SpringbootApplication.class, args);
    }

    @Autowired
    Connection connection;

    @Autowired
    MonitorProperties monitorProperties;

    @PostMapping
    public List<TopResult> ssh(@RequestParam("query") String cmd) throws Exception {
        //声明session
        Session session = null;
        //声明返回结果
        String result;
        List<TopResult> topResultList = new ArrayList<>();
        try {
            //通过connection拿到session
            session = connection.openSession();
            //执行命令
            session.execCommand(cmd, "UTF-8");
            //拿到输出结果
            InputStream is = session.getStdout();
            //解析返回结果
            result = MonitorUtil.parseResult(is, "UTF-8");
            List<Map<String, String>> topResultMapList = MonitorUtil.getTopResult(result);
            for (Map<String, String> topResultMap : topResultMapList) {
                TopResult topResult = new TopResult();
                topResult.setHostIP(monitorProperties.getHost().getJava());
                topResult.setProcessId(topResultMap.get("PID"));
                topResult.setProcessName(topResultMap.get("CMD"));
                topResult.setMemPercent(topResultMap.get("MEMPerUsed"));
                topResult.setCpuPercent(topResultMap.get("CPUPerUsed"));
                topResult.setProcessState(topResultMap.get("S"));
                topResultList.add(topResult);
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return topResultList;
    }



}
