package com.sam.ssh2springboot.config;

import ch.ethz.ssh2.Connection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: huangxin
 * @Date: Created in 上午11:37 2018/3/13
 * @Description:
 */
@Configuration
public class ServerConnection {

    @Bean
    public Connection connection() throws Exception {
        Connection connection = new Connection("10.42.240.81");
        connection.connect();
        connection.authenticateWithPassword("root", "huangxin");
        return connection;
    }

}
