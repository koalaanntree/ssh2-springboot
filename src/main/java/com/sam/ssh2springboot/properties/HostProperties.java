package com.sam.ssh2springboot.properties;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: huangxin
 * @Date: Created in 上午11:18 2018/3/19
 * @Description:
 */
@Data
@PropertySource("classpath:host.conf")
@Configuration
public class HostProperties {

    private String java;

}
