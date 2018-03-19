package com.sam.ssh2springboot.aop;

import com.sam.ssh2springboot.dataobject.TopResult;
import com.sam.ssh2springboot.repository.TopResultRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * @Author: huangxin
 * @Date: Created in 下午6:32 2018/3/19
 * @Description:
 */
@Component
@Aspect
@Slf4j
public class MonitorAspect {

    @Autowired
    TopResultRepository topResultRepository;

    @Around("execution(* com.sam.ssh2springboot.Ssh2SpringbootApplication.ssh(..))")
    public List<TopResult> handleMonitorData(ProceedingJoinPoint pjp) throws Throwable{
        log.info("切面开始");


        List<TopResult> topResultList = (List<TopResult>) pjp.proceed();

        for (TopResult topResult : topResultList) {
            if (Double.parseDouble(topResult.getMemPercent()) > 3) {
                topResult.setAlarmId(UUID.randomUUID().toString().substring(0,8));
                topResultRepository.save(topResult);
            }
        }


        log.info("切面结束");
        return topResultList;
    }
}
