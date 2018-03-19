package com.sam.ssh2springboot.repository;

import com.sam.ssh2springboot.dataobject.TopResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @Author: huangxin
 * @Date: Created in 下午1:23 2018/3/19
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TopResultRepositoryTest {

    @Autowired
    TopResultRepository topResultRepository;

    @Test
    public void saveTest() {
        TopResult topResult = new TopResult();
        topResult.setAlarmId(UUID.randomUUID().toString().substring(0,8));
        topResult.setProcessId("test");
        topResult.setProcessName("test");
        topResult.setProcessState("S");
        topResult.setCpuPercent("test");
        topResult.setMemPercent("test");
        topResult.setHostIP("test");
        TopResult save = topResultRepository.save(topResult);
        Assert.assertNotEquals(save,null);
        log.info(save.toString());
    }
}