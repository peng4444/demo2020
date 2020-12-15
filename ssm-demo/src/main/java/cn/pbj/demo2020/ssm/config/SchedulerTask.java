package cn.pbj.demo2020.ssm.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @pClassName: SchedulerTask
 * @author: pengbingjiang
 * @create: 2020/12/15 10:47
 * @description: TODO
 * [Spring Boot2(十三)：整合定时任务发送邮件](https://www.cnblogs.com/niaobulashi/p/schedule-mail.html)
 */
@Component
public class SchedulerTask {

    private int count=0;

    @Scheduled(cron="*/8 * * * * ?")
    private void process(){
        System.out.println("定时任务开启，以跑：  "+(count++));
    }

}
