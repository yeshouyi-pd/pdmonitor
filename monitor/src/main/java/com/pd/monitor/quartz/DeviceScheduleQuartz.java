package com.pd.monitor.quartz;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.MqttClientSpace;
import com.pd.server.main.domain.*;
import com.pd.server.main.service.DeviceSchedulesService;
import com.pd.server.main.service.ScheduleExecutionsService;
import com.pd.server.main.service.VoicePowerDeviceService;
import com.pd.server.main.service.WaterEquipmentService;
import com.pd.server.util.DateTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 设备计划任务调度器
 * 每分钟检查一次，执行符合条件的计划任务
 */
@Component
@EnableScheduling
public class DeviceScheduleQuartz {

    private static final Logger LOG = LoggerFactory.getLogger(DeviceScheduleQuartz.class);

    @Resource
    private DeviceSchedulesService deviceSchedulesService;

    @Resource
    private ScheduleExecutionsService scheduleExecutionsService;

    @Resource
    private WaterEquipmentService waterEquipmentService;

    @Resource
    private VoicePowerDeviceService voicePowerDeviceService;
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 每分钟执行一次，检查并执行计划任务
     */
    @Scheduled(cron = "0 * * * * ?")
    public void executeSchedules() {
        try {
            // 查询所有启用的计划
            List<DeviceSchedules> activeSchedules = deviceSchedulesService.getActiveSchedules();
            
            if (activeSchedules == null || activeSchedules.isEmpty()) {
                return;
            }

            Date now = new Date();
            LOG.debug("开始检查计划任务，当前时间：{}，启用计划数：{}", DateTools.getFormatDate(now, DateTools.yyyy_MM_dd_HH_mm_ss), activeSchedules.size());

            for (DeviceSchedules schedule : activeSchedules) {
                try {
                    // 1 开始执行（任务下达） 2 正在执行（任务成功） 0 未执行
                    if (schedule.getIsRunning() == 1 || schedule.getIsRunning() == 2) {
                        continue;
                    }

                    // 检查是否应该执行
                    if (shouldExecute(schedule, now)) {
                        executeSchedule(schedule, now);
                    }
                } catch (Exception e) {
                    LOG.error("处理计划任务异常，计划ID：{}，错误：{}", schedule.getId(), e.getMessage(), e);
                }
            }
        } catch (Exception e) {
            LOG.error("执行计划任务调度异常：{}", e.getMessage(), e);
        }
    }

    /**
     * 判断计划是否应该执行
     * @param schedule 计划对象
     * @param now 当前时间
     * @return 是否应该执行
     */
    private boolean shouldExecute(DeviceSchedules schedule, Date now) {
        if (StringUtils.isEmpty(schedule.getStartTime())) {
            return false;
        }

        String repeatType = schedule.getRepeatType();
        if (StringUtils.isEmpty(repeatType)) {
            return false;
        }

        try {
            if ("once".equals(repeatType)) {
                // once类型：start_time格式为 yyyy-MM-dd HH:mm:ss，需要大于等于当前时间
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date startTime = sdf.parse(schedule.getStartTime());
                // 允许在当前分钟执行（时间差在1分钟内）
                long diff = now.getTime() - startTime.getTime();
                return diff >= 0 && diff <= 60000; // 60秒内
            } else if ("daily".equals(repeatType)) {
                // daily类型：start_time格式为 HH:mm，补全当前年月日，秒补0，然后比较时间
                String[] timeParts = schedule.getStartTime().split(":");
                if (timeParts.length != 2) {
                    return false;
                }
                
                // 补全当前年月日，秒补0
                Calendar cal = Calendar.getInstance();
                cal.setTime(now);
                cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeParts[0]));
                cal.set(Calendar.MINUTE, Integer.parseInt(timeParts[1]));
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                Date scheduleTime = cal.getTime();
                
                // 允许1分钟误差：当前时间在计划时间到计划时间+1分钟之间
                long diff = now.getTime() - scheduleTime.getTime();
                return diff >= 0 && diff <= 60000; // 0到60秒内
            } else if ("weekly".equals(repeatType)) {
                // weekly类型：start_time格式为 HH:mm，先检查星期是否匹配，再比较时间
                Calendar cal = Calendar.getInstance();
                cal.setTime(now);
                int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1=Sunday, 2=Monday, ..., 7=Saturday
                
                // 转换为我们的星期格式（1=Monday, 7=Sunday）
                int weekDay = (dayOfWeek == Calendar.SUNDAY) ? 7 : dayOfWeek - 1;
                
                // 检查星期是否匹配
                boolean weekMatch = false;
                switch (weekDay) {
                    case 1: weekMatch = schedule.getMonday() != null && schedule.getMonday() == 1; break;
                    case 2: weekMatch = schedule.getTuesday() != null && schedule.getTuesday() == 1; break;
                    case 3: weekMatch = schedule.getWednesday() != null && schedule.getWednesday() == 1; break;
                    case 4: weekMatch = schedule.getThursday() != null && schedule.getThursday() == 1; break;
                    case 5: weekMatch = schedule.getFriday() != null && schedule.getFriday() == 1; break;
                    case 6: weekMatch = schedule.getSaturday() != null && schedule.getSaturday() == 1; break;
                    case 7: weekMatch = schedule.getSunday() != null && schedule.getSunday() == 1; break;
                }
                
                if (!weekMatch) {
                    return false;
                }
                
                // 星期匹配后，检查时间：补全当前年月日，秒补0，然后比较时间
                String[] timeParts = schedule.getStartTime().split(":");
                if (timeParts.length != 2) {
                    return false;
                }
                
                // 补全当前年月日，秒补0
                cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeParts[0]));
                cal.set(Calendar.MINUTE, Integer.parseInt(timeParts[1]));
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                Date scheduleTime = cal.getTime();
                
                // 允许1分钟误差：当前时间在计划时间到计划时间+1分钟之间
                long diff = now.getTime() - scheduleTime.getTime();
                return diff >= 0 && diff <= 60000; // 0到60秒内
            }
        } catch (Exception e) {
            LOG.error("判断计划是否应该执行异常，计划ID：{}，错误：{}", schedule.getId(), e.getMessage(), e);
        }

        return false;
    }

    /**
     * 执行计划任务（只负责启动任务）
     * @param schedule 计划对象
     * @param now 当前时间
     */
    private void executeSchedule(DeviceSchedules schedule, Date now) {
        LOG.info("启动计划任务，计划ID：{}，计划名称：{}，设备ID：{}", 
                schedule.getId(), schedule.getScheduleName(), schedule.getDeviceId());

        try {
            WaterEquipmentExample equipmentExample = new WaterEquipmentExample();
            equipmentExample.createCriteria().andSbsnEqualTo(schedule.getDeviceId());
            List<WaterEquipment> list = waterEquipmentService.list(equipmentExample);
            if(CollectionUtils.isEmpty(list)){
                return;
            }
            WaterEquipment waterEquipment = list.get(0);
            String[] topicArr =waterEquipment.getJdfw().split(";");//WHPD[meg],WHPD[updata];RPCD[meg],RPCD[updata]
            Date  startSendTime = new Date();
            //开始调用发声
            String oneitem = topicArr[0];
            String[] oneArr = oneitem.split(",");
            if (oneArr.length < 2) {
                LOG.error("主题配置格式错误: {}", oneitem);
                return;
            }
            MqttClientSpace client = MqttClientSpace.getInstance();
            byte[] messageStart = hexStringToByteArray(waterEquipment.getSm1());
            String topicName = oneArr[0].substring(0, oneArr[0].indexOf("["));
            VoicePowerDevice voicePowerDevice = voicePowerDeviceService.saveData(topicName);
            redisTemplate.opsForValue().set(topicName+"QLWJ", JSONObject.toJSONString(voicePowerDevice));

            client.publishMessage(oneArr[0], messageStart, 2);
            //提供判断是否正在播放
            redisTemplate.opsForValue().set(topicName+"SFDS", "SFDS", schedule.getDurationMinutes(), TimeUnit.MINUTES);


            // 设置任务为执行中
            schedule.setIsRunning(1);
            deviceSchedulesService.updateScheduleStatus(schedule);

            // 创建执行记录，状态为 RUNNING（执行中）
            ScheduleExecutions execution = new ScheduleExecutions();
            execution.setScheduleId(schedule.getId());
            execution.setDeviceId(schedule.getDeviceId());
            execution.setExecutionStatus("RUNNING"); // 执行中状态
            execution.setCreatedAt(now);
            // 保存执行记录
            Integer executionId = scheduleExecutionsService.insertExecution(execution);
            //停止发声
            sendStop(oneArr[0],schedule.getDurationMinutes() ,executionId ,schedule.getId(),schedule.getDeviceId(),startSendTime);


            simulateExecutionCallback(schedule.getId(), executionId, schedule.getDeviceId(),startSendTime);
            LOG.info("计划任务已启动，计划ID：{}，执行记录ID：{}，等待回调确认执行结果",
                    schedule.getId(), executionId);
        } catch (Exception e) {
            LOG.error("检查MQTT状态失败", e);
            LOG.error("启动计划任务异常，计划ID：{}，错误：{}", schedule.getId(), e.getMessage(), e);
            // 启动失败，重置执行状态
            schedule.setIsRunning(0);
            deviceSchedulesService.updateScheduleStatus(schedule);
        }



    }

    /**
     * 模拟执行回调（暂时使用，后续由实际业务回调替换）
     * @param scheduleId 计划ID
     * @param executionId 执行记录ID
     * @param deviceId 设备ID
     */
    private void simulateExecutionCallback(Integer scheduleId, Integer executionId, String deviceId,Date startSendTime) {
        // 最多执行2分组
        new Thread(() -> {
            try {
                Boolean success =false ;
                for (int i=0;i<8;i++){
                     Thread.sleep(15000);
                     success   = voicePowerDeviceService.findBySbsnAndSentTime(deviceId,startSendTime);
                     if(success){
                         break;
                     }

                }
                if (success) {
                    handleExecutionSuccess(scheduleId, executionId);
                } else {
                    handleExecutionFailure(scheduleId, executionId, "未找到成功计划");
                }
            } catch (InterruptedException e) {
                LOG.error("模拟回调线程被中断，计划ID：{}", scheduleId);
                handleExecutionFailure(scheduleId, executionId, "执行被中断");
            }
        }).start();
    }

    /**
     * 处理执行成功回调
     * @param scheduleId 计划ID
     * @param executionId 执行记录ID
     */
    public void handleExecutionSuccess(Integer scheduleId, Integer executionId) {
        try {
            LOG.info("收到执行成功回调，计划ID：{}，执行记录ID：{}", scheduleId, executionId);
            
            // 更新执行记录状态
            ScheduleExecutions execution = scheduleExecutionsService.getById(executionId);
            if (execution != null) {
                execution.setExecutionStatus("SUCCESS");
                execution.setPlannedTime(new Date());
                scheduleExecutionsService.updateExecution(execution);
            }

            // 更新计划状态
            DeviceSchedules schedule = deviceSchedulesService.getById(scheduleId);
            if (schedule != null) {
                schedule.setIsRunning(2); //执行成功 执行中
                schedule.setLastRunTime(new Date());

                // 计算下次执行时间
                Date nextRunTime = calculateNextRunTime(schedule, new Date());
                schedule.setNextRunTime(nextRunTime);


                deviceSchedulesService.updateScheduleStatus(schedule);

                LOG.info("计划任务执行成功，计划ID：{}，下次执行时间：{}", 
                        scheduleId, 
                        nextRunTime != null ? DateTools.getFormatDate(nextRunTime, DateTools.yyyy_MM_dd_HH_mm_ss) : "无");
            }
        } catch (Exception e) {
            LOG.error("处理执行成功回调异常，计划ID：{}，执行记录ID：{}，错误：{}", 
                    scheduleId, executionId, e.getMessage(), e);
        }
    }

    /**
     * 处理执行失败回调
     * @param scheduleId 计划ID
     * @param executionId 执行记录ID
     * @param errorMessage 错误信息
     */
    public void handleExecutionFailure(Integer scheduleId, Integer executionId, String errorMessage) {
        try {
            LOG.error("收到执行失败回调，计划ID：{}，执行记录ID：{}，错误：{}", 
                    scheduleId, executionId, errorMessage);
            
            // 更新执行记录状态
            ScheduleExecutions execution = scheduleExecutionsService.getById(executionId);
            if (execution != null) {
                execution.setExecutionStatus("FAILED");
                execution.setErrorMessage(errorMessage);
                scheduleExecutionsService.updateExecution(execution);
            }

            // 更新计划状态
            DeviceSchedules schedule = deviceSchedulesService.getById(scheduleId);
            if (schedule != null) {
                schedule.setIsRunning(0);
                deviceSchedulesService.updateScheduleStatus(schedule);
            }
        } catch (Exception e) {
            LOG.error("处理执行失败回调异常，计划ID：{}，执行记录ID：{}，错误：{}", 
                    scheduleId, executionId, e.getMessage(), e);
        }
    }

    /**
     * 计算下次执行时间
     * @param schedule 计划对象
     * @param now 当前时间
     * @return 下次执行时间
     */
    private Date calculateNextRunTime(DeviceSchedules schedule, Date now) {
        if ("once".equals(schedule.getRepeatType())) {
            // once类型不计算下次执行时间
            return null;
        }

        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(now);

            if ("daily".equals(schedule.getRepeatType())) {
                // daily类型：明天同一时间
                cal.add(Calendar.DAY_OF_YEAR, 1);
                String[] timeParts = schedule.getStartTime().split(":");
                if (timeParts.length == 2) {
                    cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeParts[0]));
                    cal.set(Calendar.MINUTE, Integer.parseInt(timeParts[1]));
                    cal.set(Calendar.SECOND, 0);
                    cal.set(Calendar.MILLISECOND, 0);
                }
                return cal.getTime();
            } else if ("weekly".equals(schedule.getRepeatType())) {
                // weekly类型：找到下一个匹配的星期
                String[] timeParts = schedule.getStartTime().split(":");
                if (timeParts.length != 2) {
                    return null;
                }
                int scheduleHour = Integer.parseInt(timeParts[0]);
                int scheduleMinute = Integer.parseInt(timeParts[1]);

                // 从明天开始查找下一个匹配的星期
                for (int i = 1; i <= 7; i++) {
                    cal.add(Calendar.DAY_OF_YEAR, 1);
                    int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
                    int weekDay = (dayOfWeek == Calendar.SUNDAY) ? 7 : dayOfWeek - 1;

                    boolean weekMatch = false;
                    switch (weekDay) {
                        case 1: weekMatch = schedule.getMonday() != null && schedule.getMonday() == 1; break;
                        case 2: weekMatch = schedule.getTuesday() != null && schedule.getTuesday() == 1; break;
                        case 3: weekMatch = schedule.getWednesday() != null && schedule.getWednesday() == 1; break;
                        case 4: weekMatch = schedule.getThursday() != null && schedule.getThursday() == 1; break;
                        case 5: weekMatch = schedule.getFriday() != null && schedule.getFriday() == 1; break;
                        case 6: weekMatch = schedule.getSaturday() != null && schedule.getSaturday() == 1; break;
                        case 7: weekMatch = schedule.getSunday() != null && schedule.getSunday() == 1; break;
                    }

                    if (weekMatch) {
                        cal.set(Calendar.HOUR_OF_DAY, scheduleHour);
                        cal.set(Calendar.MINUTE, scheduleMinute);
                        cal.set(Calendar.SECOND, 0);
                        cal.set(Calendar.MILLISECOND, 0);
                        return cal.getTime();
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("计算下次执行时间异常，计划ID：{}，错误：{}", schedule.getId(), e.getMessage(), e);
        }

        return null;
    }

    public static byte[] hexStringToByteArray(String hex) {
        hex = hex.replaceAll("\\s+", ""); // 去掉空格
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i+1), 16));
        }
        return data;
    }

    public void sendStop(String topic,Integer durationMinutes ,Integer dID ,Integer sid,String deviceId,Date startSendTime) {


        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                MqttClientSpace client = MqttClientSpace.getInstance();
                byte[] messageStop = new byte[] { 0x43, 0x52, 0x44, 0x02, 0x00, 0x07, 0x10, 0x02, 0x4C };
                client.publishMessage(topic, messageStop, 2);
                String topicName = topic.substring(0,topic.indexOf("["));
                voicePowerDeviceService.updateStopTime(topicName);
            }
        };
        int delay = durationMinutes*60*1000; // 延迟时间，单位为毫秒
        timer.schedule(task, delay);

        new Thread(() -> {
            try {
                Thread.sleep(delay);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startSendTime);
                calendar.add(Calendar.MINUTE, durationMinutes); // 向后推n分钟
                Date after30Minutes = calendar.getTime();
                Boolean success =false ;
                for (int i=0;i<8;i++){
                    Thread.sleep(15000);
                    success   = voicePowerDeviceService.findBySbsnAndSentTimeend(deviceId,after30Minutes);
                    if(success){
                        break;
                    }

                }
                if(!success){
                    return;
                }
                // 更新计划状态
                DeviceSchedules schedule = deviceSchedulesService.getById(sid);
                if (schedule != null) {
                    schedule.setIsRunning(0); //停止执行
                    deviceSchedulesService.updateScheduleStatus(schedule);
                }
                // 更新执行记录状态
                ScheduleExecutions execution = scheduleExecutionsService.getById(dID);
                if (execution != null) {
                    execution.setActualTime(new Date());
                    scheduleExecutionsService.updateExecution(execution);
                }
            } catch (InterruptedException e) {
                // 忽略异常
            }
        }).start();
    }


}

