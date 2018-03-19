package com.sam.ssh2springboot.util;

import com.sam.ssh2springboot.dataobject.TopResult;

import java.util.Map;
import java.util.UUID;

/**
 * @Author: huangxin
 * @Date: Created in 下午2:09 2018/3/19
 * @Description:
 */
public class DataFormatUtil {
    public static TopResult formatTopResult(Map<String, String> topResultMap,String hostIp){
        TopResult topResult = new TopResult();
        topResult.setAlarmId(UUID.randomUUID().toString().substring(0,8));
        topResult.setProcessId(topResultMap.get("PID"));
        topResult.setProcessName(topResultMap.get("CMD"));
        topResult.setMemPercent(topResultMap.get("MEMPerUsed"));
        topResult.setCpuPercent(topResultMap.get("CPUPerUsed"));
        topResult.setProcessState(topResultMap.get("S"));
        topResult.setHostIP(hostIp);
        return topResult;
    }
}
