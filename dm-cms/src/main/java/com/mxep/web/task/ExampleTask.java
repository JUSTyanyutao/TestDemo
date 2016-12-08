package com.mxep.web.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Task - 定时任务
 *
 * @author xingkong1221
 * @date 2015-08-21
 */
public class ExampleTask {

    private Logger logger = LoggerFactory.getLogger(ExampleTask.class);


    @Scheduled(cron = "0 0 1 * * ?")
    public void autoSignedSfOrder() {
        if (logger.isDebugEnabled()) {
            logger.debug("快递订单状态自动更新任务开始了....");
        }
    }
}
