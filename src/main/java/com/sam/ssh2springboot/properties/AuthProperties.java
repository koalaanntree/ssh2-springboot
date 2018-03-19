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
@PropertySource("classpath:auth.conf")
//先不加Configuration测试，然后再加Configuration测试
@Configuration
public class AuthProperties {

    private JavaAuthProperties java;

}
