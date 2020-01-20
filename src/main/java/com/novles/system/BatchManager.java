package com.novles.system;

import javax.annotation.PostConstruct;

import static org.quartz.JobBuilder.newJob;

import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;

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

@Component
@Slf4j
public class BatchManager {
    
    @Autowired
    private Scheduler scheduler;
    
    @PostConstruct
    public void start() {
        log.debug("=>>>>>>>>>>>>");
        try {
            JobDetail defaultJobDetail = buildJobDetail(SystemOperaterJob.class, "system", "operater", Maps.newHashMap());
            scheduler.scheduleJob(defaultJobDetail, buildJobTrigger("0 * * * * ?"));
        } catch (SchedulerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        log.debug("<<<<<<<<<<<<=");
    }
    
    public Trigger buildJobTrigger(String exp) {
        return TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(exp)).build();
    }
    
    public JobDetail buildJobDetail(Class<SystemOperaterJob> class1, String name, String group, Map<String, Object> param) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.putAll(param);
        return newJob(class1).withIdentity(name, group).usingJobData(jobDataMap).build();
    }
}
