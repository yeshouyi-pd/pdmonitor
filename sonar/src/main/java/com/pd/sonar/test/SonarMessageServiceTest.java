package com.pd.sonar.test;

import com.pd.sonar.service.SonarMessageService;
import com.pd.sonar.model.SonarBiomassData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 声呐消息服务测试类
 * 用于验证依赖注入是否正常工作
 */
@Component
public class SonarMessageServiceTest implements CommandLineRunner {

    @Autowired
    private SonarMessageService sonarMessageService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== 开始测试SonarMessageService ===");
        
        try {
            // 创建测试数据
            SonarBiomassData testData = new SonarBiomassData();
            testData.setId(1L);
            testData.setDeviceCode("TEST001");
            testData.setAcTime("2024-01-01 12:00:00");
            
            Map<String, Double> paraValue = new HashMap<>();
            paraValue.put("0501", 100.0); // 生物密度
            paraValue.put("0502", 50.0);  // 生物重量累加值
            paraValue.put("0503", 25.0);  // 生物重量
            paraValue.put("0504", -60.0); // 平均SV
            testData.setMapParaValue(paraValue);
            
            // 测试处理生物量数据
            System.out.println("测试数据: " + testData);
            sonarMessageService.processBiomassData(testData);
            System.out.println("测试完成，无异常抛出");
            
        } catch (Exception e) {
            System.err.println("测试失败，出现异常: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("=== 测试结束 ===");
    }
} 