package com.sam.ssh2springboot.dataobject;

import lombok.Data;

/**
 * @Author: huangxin
 * @Date: Created in 上午10:42 2018/3/19
 * @Description:
 */
@Data
public class TopCMD {
    /**
     * 进程号
     */
    private String processId;
    /**
     * 进程属性
     */
    private String processName;
    /**
     * 进程状态
     * D=不可中断的睡眠状态 R=运行 S=睡眠 T=跟踪/停止 Z=僵尸进程
     */
    private String processState;
    /**
     * 进程cpu占用率
     */
    private String cpuPercent;
    /**
     * 进程内存占用率
     */
    private String memPercent;
    /**
     * 主机地址
     */
    private String hostIP;
}
