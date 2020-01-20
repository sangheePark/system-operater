package com.novles.system;

import java.lang.management.ManagementFactory;

import org.springframework.stereotype.Component;

import com.sun.management.OperatingSystemMXBean;

/**
 * 
 * <b></b>
 * <pre>
 * <b>Description:</b>
 * </pre>
 *
 * <pre>
 * <b>History:</b>
 * - 2020.01.20, snack: 최초작성 
 * </pre>
 * @author snack (novles@naver.com)
 * @Version 1.0, 2020.01.20
 */
@Component
public class SystemOperator {
    
    public SystemInfo operating() {
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        String cpu = String.format("%.2f", osBean.getSystemCpuLoad() * 100);
        long freeSize = osBean.getFreePhysicalMemorySize();
        long totalSize = osBean.getTotalPhysicalMemorySize();
        String memory = String.format("%.2f", Double.valueOf(Math.round( (Double.valueOf(totalSize - freeSize)) / Double.valueOf(totalSize) * 100)));
        String memoryFree = String.format("%.2f", Double.valueOf(freeSize)/1024/1024/1024);
        String memoryTotal = String.format("%.2f", Double.valueOf(totalSize)/1024/1024/1024);
        return SystemInfo.builder().cpu(cpu).memoryTotal(memoryTotal).memoryFree(memoryFree).memory(memory).build().print();
    }
}
