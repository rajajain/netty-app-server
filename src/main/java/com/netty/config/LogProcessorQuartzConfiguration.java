package com.netty.config;

import com.netty.log.schedular.LogProcessorScheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Created by raja .
 */
@Configuration
@ComponentScan("com.netty")
public class LogProcessorQuartzConfiguration {

    @Bean
    public JobDetailFactoryBean logProcessorJobDetailFactoryBean() {
        JobDetailFactoryBean factory = new JobDetailFactoryBean();
        factory.setJobClass(LogProcessorScheduler.class);
        factory.setGroup("LogProcessor");
        factory.setName("LogProcessor");
        return factory;
    }

    //Job is scheduled after every 2 min (0 0/2 * 1/1 * ? *)
    @Bean
    public CronTriggerFactoryBean logProcessorCronTriggerFactoryBean() {
        CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
        stFactory.setJobDetail(logProcessorJobDetailFactoryBean().getObject());
        stFactory.setStartDelay(3000);
        stFactory.setName("LogProcessorTrigger");
        stFactory.setGroup("LogProcessor");
        stFactory.setCronExpression("0 0/2 * 1/1 * ? *");
        return stFactory;
    }


    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setTriggers(logProcessorCronTriggerFactoryBean().getObject());
        return scheduler;
    }

}
