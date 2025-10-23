package com.pd.server.main.controller;

import com.pd.server.main.service.shj.EquipmentFileMigrationShjService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * EquipmentFile迁移控制器
 * 提供迁移功能的REST API接口
 */
@RestController
@RequestMapping("/api/migration")
public class EquipmentFileMigrationController {

    private static final Logger LOG = LoggerFactory.getLogger(EquipmentFileMigrationController.class);

    @Resource
    private EquipmentFileMigrationShjService migrationService;

    /**
     * 开始迁移EquipmentFile数据
     * GET /api/migration/equipment-files
     */
    @GetMapping("/equipment-files")
    public String startMigration() {
        try {
            LOG.info("收到迁移请求");
            migrationService.migrateEquipmentFiles();
            return "迁移完成";
        } catch (Exception e) {
            LOG.error("迁移失败", e);
            return "迁移失败: " + e.getMessage();
        }
    }

    /**
     * 测试迁移功能
     * GET /api/migration/test
     */
    @GetMapping("/test")
    public String testMigration() {
        try {
            LOG.info("收到测试迁移请求");
            migrationService.testMigration();
            return "测试迁移完成";
        } catch (Exception e) {
            LOG.error("测试迁移失败", e);
            return "测试迁移失败: " + e.getMessage();
        }
    }

    /**
     * 获取迁移统计信息
     * GET /api/migration/statistics
     */
    @GetMapping("/statistics")
    public String getStatistics() {
        try {
            LOG.info("收到统计信息请求");
            migrationService.getMigrationStatistics();
            return "统计信息已输出到日志";
        } catch (Exception e) {
            LOG.error("获取统计信息失败", e);
            return "获取统计信息失败: " + e.getMessage();
        }
    }

    /**
     * 关闭迁移服务
     * POST /api/migration/shutdown
     */
    @PostMapping("/shutdown")
    public String shutdown() {
        try {
            LOG.info("收到关闭服务请求");
            migrationService.shutdown();
            return "服务已关闭";
        } catch (Exception e) {
            LOG.error("关闭服务失败", e);
            return "关闭服务失败: " + e.getMessage();
        }
    }
}
