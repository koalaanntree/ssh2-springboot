package com.sam.ssh2springboot.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: huangxin
 * @Date: Created in 上午11:16 2018/3/19
 * @Description:
 */
@Configuration
@ConfigurationProperties(prefix = "imooc.monitor")
@Data
public class MonitorProperties {

    private HostProperties host;
    private AuthProperties auth;

}
