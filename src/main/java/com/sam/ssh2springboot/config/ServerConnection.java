package com.sam.ssh2springboot.config;

import ch.ethz.ssh2.Connection;
import com.sam.ssh2springboot.properties.MonitorProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: huangxin
 * @Date: Created in 上午11:37 2018/3/13
 * @Description:
 */
@Configuration
@Slf4j
public class ServerConnection {

    @Autowired
    MonitorProperties monitorProperties;

    @Bean
    public Connection connection() throws Exception {
        log.info(monitorProperties.toString());
        Connection connection = new Connection(monitorProperties.getHost().getJava());
        connection.connect();
        connection.authenticateWithPassword(monitorProperties.getAuth().getJava().getUsername(), monitorProperties.getAuth().getJava().getPassword());
        return connection;
    }

}
