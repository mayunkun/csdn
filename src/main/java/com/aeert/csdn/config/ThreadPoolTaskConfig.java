package com.aeert.csdn.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


@Configuration
@Slf4j
public class ThreadPoolTaskConfig {

    @Value("${task.core_pool_size:5}")
    private Integer corePoolSize;
    @Value("${task.max_pool_size:50}")
    private Integer maxPoolSize;
    @Value("${task.queue_capacity:1000}")
    private Integer queueCapacity;
    @Value("${task.keep_alive_seconds:60}")
    private Integer keepAliveSeconds;


    @Bean(name="taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
        //线程池维护线程的最少数量
        poolTaskExecutor.setCorePoolSize(corePoolSize);
        //线程池维护线程的最大数量
        poolTaskExecutor.setMaxPoolSize(maxPoolSize);
        //线程池所使用的缓冲队列
        poolTaskExecutor.setQueueCapacity(queueCapacity);
        //线程池维护线程所允许的空闲时间
        poolTaskExecutor.setKeepAliveSeconds(keepAliveSeconds);
        poolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        return poolTaskExecutor;
    }

}