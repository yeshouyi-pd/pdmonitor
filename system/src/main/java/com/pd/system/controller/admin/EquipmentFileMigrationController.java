package com.pd.system.controller.admin;

import com.pd.server.main.service.shj.EquipmentFileMigrationShjService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * EquipmentFile迁移控制器
 * 提供迁移功能的REST API接口
 */
@RestController
@RequestMapping("/migration")
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
            migrationService.migrateStart();
            return "迁移完成";
        } catch (Exception e) {
            LOG.error("迁移失败", e);
            return "迁移失败: " + e.getMessage();
        }
    }



    /**
     * 开始EquipmentFileEvent迁移
     * GET /api/migration/equipment-file-events
     */
    @GetMapping("/equipment-file-events")
    public String startEventMigration() {
        try {
            LOG.info("收到EquipmentFileEvent迁移请求");
            migrationService.migrateEventStart();
            return "EquipmentFileEvent迁移完成";
        } catch (Exception e) {
            LOG.error("EquipmentFileEvent迁移失败", e);
            return "EquipmentFileEvent迁移失败: " + e.getMessage();
        }
    }

    /**
     * 获取迁移服务状态
     * GET /api/migration/status
     */
    @GetMapping("/status")
    public String getStatus() {
        try {
            return migrationService.getMigrationStatus();
        } catch (Exception e) {
            LOG.error("获取服务状态失败", e);
            return "获取服务状态失败: " + e.getMessage();
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