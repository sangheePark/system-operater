package com.novles.system;

import java.awt.print.Printable;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
@Getter
@Slf4j
public class SystemInfo {
    private String cpu;
    private String memory;
    private String memoryFree;
    private String memoryTotal;
    
    @Builder
    public SystemInfo(String cpu, String memory, String memoryFree, String memoryTotal) {
        this.cpu = cpu;
        this.memory = memory;
        this.memoryFree = memoryFree;
        this.memoryTotal = memoryTotal;
    }
    
    public SystemInfo print() {
        log.debug("------------------------------------------------------");
        log.debug("Cpu Usage: " + this.cpu + "%");
        log.debug("Memory Usage: " + this.memory + "%");
        log.debug("Memory Free: " + this.memoryFree + "GB");
        log.debug("Memory Total: " + this.memoryTotal + "GB");
        log.debug("------------------------------------------------------");
        return this;
    }
}
