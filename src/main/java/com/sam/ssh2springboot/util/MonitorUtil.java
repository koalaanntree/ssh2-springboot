package com.sam.ssh2springboot.util;

import ch.ethz.ssh2.StreamGobbler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: huangxin
 * @Date: Created in 下午2:01 2018/3/19
 * @Description:
 */
public class MonitorUtil {
    /**
     * 解析脚本执行返回的结果集
     *
     * @param in      输入流对象
     * @param charset 编码
     * @return 以纯文本的格式返回
     */
    public static String parseResult(InputStream in, String charset) throws Exception {
        //用stringbuilder来操作字符串java.lang.autocloseable
        StringBuilder sb = new StringBuilder();
        try (
                //基于jdk1.7以后的特性，在try关键字后声明已经实现java.lang.AutoCloseable的对象
                InputStream stdout = new StreamGobbler(in);
                //声明读取器来读取输入流，可以操作字节流的读取器来读取stdout
                //因为在try()语句块中，我们可以为所欲为地声明已经实现了AutoCloseable接口的对象，让jdk自动为我们关闭读写流
                //不用显示调用它们的close()方法
                BufferedReader br = new BufferedReader(new InputStreamReader(stdout, charset))
        ) {
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
        }
        return sb.toString();
    }

    /**
     * 封装top命令返回的数据
     * @param originalData
     * @return
     */
    public static List<Map<String, String>> getTopResult(String originalData) {
        String[] lineData = originalData.split("\n");
        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 0; i < lineData.length; i++) {
            Map<String, String> map = new HashMap<>();
            String[] unitData = lineData[i].trim().split("\\s{1,}");
            map.put("PID", unitData[0]);
            map.put("USER", unitData[1]);
            map.put("PR", unitData[2]);
            map.put("NI", unitData[3]);
            map.put("VIRT", unitData[4]);
            map.put("RES", unitData[5]);
            map.put("SHR", unitData[6]);
            map.put("S", unitData[7]);
            map.put("CPUPerUsed", unitData[8]);
            map.put("MEMPerUsed", unitData[9]);
            map.put("TIMETill", unitData[10]);
            map.put("CMD", unitData[11]);
            list.add(map);
        }
        return list;
    }
}
