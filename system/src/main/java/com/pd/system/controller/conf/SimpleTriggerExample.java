package com.pd.system.controller.conf;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.JobKey.jobKey;

import java.util.Date;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

@Component
public class SimpleTriggerExample {

    public void run() throws Exception{
        System.out.println("--------Initializing--------");

        // First we must get a reference to a scheduler
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        System.out.println("--------Initialization Complete--------");

        System.out.println("--------Scheduling Jobs---------");

        // jobs can be scheduled before sched.start() has been called

        // 下一个第15秒 例:
        //           当前 10秒,则 执行时间为15秒
        //           当前 16秒,则 执行时间为30秒
        //           当前 33秒,则 执行时间为45秒
        //           当前 48秒,则 执行时间为00秒
        Date startTime1 = (Date) DateBuilder.nextGivenSecondDate(null, 15);
        // job1 将只会执行一次
        JobDetail job = newJob(SimpleJob1.class).withIdentity("job1", "group1").build();

        SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startAt(startTime1).build();
        // schedule it to run!
        sched.start();
        Date ft = sched.scheduleJob(job, trigger);
        System.out.println(job.getKey() + " will run at: " + ft + " and repeat: "
                        + trigger.getRepeatCount() + " times, every "
                        + trigger.getRepeatInterval() / 1000 + " seconds");

        Date startTime2 = (Date) DateBuilder.nextGivenSecondDate(null, 20);
        // job2 将执行11次(执行1次,重复10次) ,每10秒重复一次
        job = JobBuilder.newJob(SimpleJob2.class).withIdentity("job2", "group1").build();
        trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger2", "group1")
                .startAt(startTime2)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10)// 重复间隔
                                .withRepeatCount(10))     // 重复次数
                .build();
        ft = sched.scheduleJob(job, trigger);
        System.out.println(job.getKey() + " will run at: " + ft + " and repeat: "
                + trigger.getRepeatCount() + " times, every "
                + trigger.getRepeatInterval() / 1000 + " seconds");

        Date startTime3 = (Date) DateBuilder.nextGivenSecondDate(null, 7);
        // job3  每40s运行一次,没有指定重复次数,则无下限的重复
        job = JobBuilder.newJob(SimpleJob3.class).withIdentity("job3", "group1").build();
        trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger3", "group1")
                .startAt(startTime3)
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(40)
                                .repeatForever()).build();
        ft = sched.scheduleJob(job, trigger);
        System.out.println(job.getKey() + " will run at: " + ft + " and repeat: "
                + trigger.getRepeatCount() + " times, every "
                + trigger.getRepeatInterval() / 1000 + " seconds");
    }

    public static void main(String[] args) throws Exception {
        SimpleTriggerExample example = new SimpleTriggerExample();
        example.run();
    }

}
