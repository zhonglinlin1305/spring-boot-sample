package cn.kiiwii.framework.schedule;

import cn.kiiwii.framework.service.ITestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;

/**
 * Created by zhong on 2016/11/10.
 */
@Configuration
@EnableScheduling
public class Scheduling {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ITestService testService;

    @Scheduled(cron = "0/20 * * * * ?") // 每20秒执行一次
    public void scheduler() {
        testService.test();
        logger.info("excute Schedule");
    }
}
