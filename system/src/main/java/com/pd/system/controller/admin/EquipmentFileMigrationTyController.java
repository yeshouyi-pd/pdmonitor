package com.pd.system.controller.admin;

import com.pd.server.main.service.shj.EquipmentFileMigrationTyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * EquipmentFile迁移控制器 - Ty版本
 * 提供EquipmentFile和EquipmentFileEvent的迁移接口
 */
@RestController
@RequestMapping("/migration-ty")
public class EquipmentFileMigrationTyController {

    private static final Logger LOG = LoggerFactory.getLogger(EquipmentFileMigrationTyController.class);

    @Resource
    private EquipmentFileMigrationTyService migrationTyService;

    /**
     * 开始EquipmentFile迁移
     * GET /api/migration-ty/equipment-files
     */
    @GetMapping("/equipment-files")
    public String startMigration() {
        try {
            LOG.info("收到EquipmentFile迁移请求");
            migrationTyService.migrateStart();
            return "EquipmentFile迁移完成";
        } catch (Exception e) {
            LOG.error("EquipmentFile迁移失败", e);
            return "EquipmentFile迁移失败: " + e.getMessage();
        }
    }

    /**
     * 开始EquipmentFileEvent迁移
     * GET /api/migration-ty/equipment-file-events
     */
    @GetMapping("/equipment-file-events")
    public String startEventMigration() {
        try {
            LOG.info("收到EquipmentFileEvent迁移请求");
            migrationTyService.migrateEventStart();
            return "EquipmentFileEvent迁移完成";
        } catch (Exception e) {
            LOG.error("EquipmentFileEvent迁移失败", e);
            return "EquipmentFileEvent迁移失败: " + e.getMessage();
        }
    }

    /**
     * 获取迁移服务状态
     * GET /api/migration-ty/status
     */
    @GetMapping("/status")
    public String getStatus() {
        try {
            return migrationTyService.getMigrationStatus();
        } catch (Exception e) {
            LOG.error("获取服务状态失败", e);
            return "获取服务状态失败: " + e.getMessage();
        }
    }

    /**
     * 获取迁移统计信息
     * GET /api/migration-ty/statistics
     */
    @GetMapping("/statistics")
    public String getStatistics() {
        try {
            migrationTyService.getMigrationStatistics();
            return "统计信息已输出到日志";
        } catch (Exception e) {
            LOG.error("获取统计信息失败", e);
            return "获取统计信息失败: " + e.getMessage();
        }
    }

    /**
     * 关闭迁移服务
     * POST /api/migration-ty/shutdown
     */
    @PostMapping("/shutdown")
    public String shutdown() {
        try {
            LOG.info("收到关闭迁移服务请求");
            migrationTyService.shutdown();
            return "迁移服务已关闭";
        } catch (Exception e) {
            LOG.error("关闭迁移服务失败", e);
            return "关闭迁移服务失败: " + e.getMessage();
        }
    }
}
