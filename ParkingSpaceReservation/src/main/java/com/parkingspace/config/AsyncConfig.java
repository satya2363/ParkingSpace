package com.parkingspace.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {
    int    corePoolSize;
    int    maxPoolSize;
    int    queueCapacity;
    String threadNamePrefix;

    //    public AsyncConfig(int corePoolSize, int maxPoolSize, int queueCapacity, String threadNamePrefix) {
    //        this.corePoolSize = corePoolSize;
    //        this.maxPoolSize = maxPoolSize;
    //        this.queueCapacity = queueCapacity;
    //        this.threadNamePrefix = threadNamePrefix;
    //    }

    @Bean(
            name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("Parking Thread");
        executor.initialize();
        return executor;

    }
}
