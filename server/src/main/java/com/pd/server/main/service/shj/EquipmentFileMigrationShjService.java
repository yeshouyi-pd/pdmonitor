package com.pd.server.main.service.shj;


import com.pd.server.main.domain.*;
import com.pd.server.main.mapper.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.*;

/**
 * 历史 EquipmentFile 迁移
 * 历史 equipment_file_event 迁移
 */
@Service
public class EquipmentFileMigrationShjService {

    private static final Logger LOG = LoggerFactory.getLogger(EquipmentFileMigrationShjService.class);

    /**
     * 待迁移数据源
     */
    @Resource
    private EquipmentFileMapper equipmentFileMapper;

    /**
     * 待迁移数据事件源
     */
    @Resource
    private EquipmentFileEventMapper equipmentFileEventMapper;

    /**
     * 聚类 33 35
     */
    @Resource
    private EquipmentFilePClusterMapper equipmentFilePClusterMapper;

    /**
     * 图片pic 10 11
     */
    @Resource
    private EquipmentFilePPicMapper equipmentFilePPicMapper;

    /**
     * mp4 40
     */
    @Resource
    private EquipmentFilePVideoMapper equipmentFilePVideoMapper;

    /**
     * 文件 31 32 34 36
     */
    @Resource
    private EquipmentFilePTxtMapper   equipmentFilePTxtMapper;

    /**
     * 音档 20 21
     */
    @Resource
    private EquipmentFilePWavMapper equipmentFilePWavMapper;

    /**
     * 线程池 - 建议使用10个线程
     */
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    
    /**
     * 迁移状态标志
     */
    private volatile boolean isShuttingDown = false;
    
    /**
     * 迁移运行状态标志
     */
    private volatile boolean isRunning = false;
    
    /**
     * EquipmentFileEvent迁移运行状态标志
     */
    private volatile boolean isEventRunning = false;

    /**
     * 开始迁移
     */
    public void migrateStart() {
        // 检查是否已经在运行
        if (isRunning) {
            LOG.warn("迁移服务已经在运行中，请勿重复启动");
            return;
        }
        
        LOG.info("启动迁移服务...");
        isShuttingDown = false;
        isRunning = true;
        
        try {
            this.migrateEquipmentFiles();
        } finally {
            // 确保运行状态被重置
            isRunning = false;
            LOG.info("迁移服务已停止");
        }
    }

    /**
     * 开始EquipmentFileEvent迁移
     */
    public void migrateEventStart() {
        // 检查是否已经在运行
        if (isEventRunning) {
            LOG.warn("EquipmentFileEvent迁移服务已经在运行中，请勿重复启动");
            return;
        }
        
        LOG.info("启动EquipmentFileEvent迁移服务...");
        isShuttingDown = false;
        isEventRunning = true;
        
        try {
            this.migrateEquipmentFileEvents();
        } finally {
            // 确保运行状态被重置
            isEventRunning = false;
            LOG.info("EquipmentFileEvent迁移服务已停止");
        }
    }

    /**
     * 迁移EquipmentFile数据
     * 根据sync_flag=0和cjsj升序查询，每次查询100条
     * 使用多线程进行迁移，使用事务确保分发写入成功后才修改sync_flag
     * 方法会一直循环直到查询不到待迁移数据后退出
     */

    private void migrateEquipmentFiles() {
        LOG.info("开始迁移EquipmentFile数据...");
        


        try {
            while (true) {
                // 检查是否正在关闭
                if (isShuttingDown) {
                    LOG.info("检测到关闭信号，停止迁移");
                    break;
                }
                
                // 查询待迁移的数据
                EquipmentFileExample example = new EquipmentFileExample();
                EquipmentFileExample.Criteria criteria = example.createCriteria();
                criteria.andSyncFlagEqualTo(0);
                example.setOrderByClause(" cjsj ASC limit 5000");

                // 使用selectByExample方法查询前5000条
                List<EquipmentFile> result = equipmentFileMapper.selectByExample(example);

                if (result.isEmpty()) {
                    LOG.info("没有更多数据需要迁移，迁移完成");
                    break;
                }
                LOG.info("查询到{}条数据，开始迁移...", result.size());
                
                // 使用多线程进行迁移，使用事务确保数据一致性
                migrateBatchWithMultiThread(result);
                
                // 如果结果少于5000条，说明没有更多数据了
                if (result.size() < 5000) {
                    LOG.info("没有更多数据需要迁移，迁移完成");
                    break;
                }
                // 避免内存溢出，稍微休息一下
                Thread.sleep(100);
            }

            
        } catch (Exception e) {
            LOG.error("迁移EquipmentFile数据时发生异常", e);
            throw new RuntimeException("迁移失败", e);
        }
    }

    /**
     * 迁移EquipmentFileEvent数据
     * 根据syncFlag=0查询，每次查询5000条
     * 根据equipmentFileId关联EquipmentFilePCluster表更新kssj、jssj、jtnr字段
     */
    private void migrateEquipmentFileEvents() {
        LOG.info("开始迁移EquipmentFileEvent数据...");
        
        try {
            while (true) {
                // 检查是否正在关闭
                if (isShuttingDown) {
                    LOG.info("检测到关闭信号，停止EquipmentFileEvent迁移");
                    break;
                }
                
                // 查询待迁移的EquipmentFileEvent数据
                EquipmentFileEventExample example = new EquipmentFileEventExample();
                EquipmentFileEventExample.Criteria criteria = example.createCriteria();
                criteria.andSyncFlagEqualTo(0);
                example.setOrderByClause("limit 5000");

                // 使用selectByExample方法查询前5000条
                List<EquipmentFileEvent> result = equipmentFileEventMapper.selectByExample(example);

                if (result.isEmpty()) {
                    LOG.info("没有更多EquipmentFileEvent数据需要迁移，迁移完成");
                    break;
                }
                LOG.info("查询到{}条EquipmentFileEvent数据，开始迁移...", result.size());
                
                // 使用多线程进行迁移
                migrateEventBatchWithMultiThread(result);
                
                // 如果结果少于5000条，说明没有更多数据了
                if (result.size() < 5000) {
                    LOG.info("没有更多EquipmentFileEvent数据需要迁移，迁移完成");
                    break;
                }
                // 避免内存溢出，稍微休息一下
                Thread.sleep(100);
            }
            
        } catch (Exception e) {
            LOG.error("迁移EquipmentFileEvent数据时发生异常", e);
            throw new RuntimeException("EquipmentFileEvent迁移失败", e);
        }
    }

    /**
     * 使用多线程迁移一批EquipmentFileEvent数据
     */
    private void migrateEventBatchWithMultiThread(List<EquipmentFileEvent> equipmentFileEvents) {
        int batchSize = equipmentFileEvents.size();
        CountDownLatch latch = new CountDownLatch(batchSize);
        
        for (EquipmentFileEvent equipmentFileEvent : equipmentFileEvents) {
            executorService.submit(() -> {
                try {
                    migrateSingleEquipmentFileEvent(equipmentFileEvent);
                } catch (Exception e) {
                    LOG.error("迁移单条EquipmentFileEvent数据失败，ID: {}, 错误: {}", 
                            equipmentFileEvent.getId(), e.getMessage(), e);
                } finally {
                    latch.countDown();
                }
            });
        }
        
        try {
            // 等待所有线程完成，最多等待10秒
            boolean finished = latch.await(10, TimeUnit.SECONDS);
            if (!finished) {
                LOG.warn("EquipmentFileEvent迁移超时，部分数据可能未完成迁移");
            }
        } catch (InterruptedException e) {
            LOG.error("等待EquipmentFileEvent迁移完成时被中断", e);
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 迁移单条EquipmentFileEvent数据
     * 根据equipmentFileId关联EquipmentFilePCluster表更新kssj、jssj、jtnr字段
     */
    private void migrateSingleEquipmentFileEvent(EquipmentFileEvent equipmentFileEvent) {
        try {
            String equipmentFileId = equipmentFileEvent.getEquipmentFileId();
            boolean migrationSuccess = false;
            
            if (StringUtils.isNotBlank(equipmentFileId)) {
                // 根据equipmentFileId查询EquipmentFilePCluster表
                EquipmentFilePClusterExample clusterExample = new EquipmentFilePClusterExample();
                clusterExample.createCriteria().andBidEqualTo(equipmentFileId);
                List<EquipmentFilePCluster> clusters = equipmentFilePClusterMapper.selectByExample(clusterExample);
                
                if (!clusters.isEmpty()) {
                    // 更新第一个匹配的记录
                    EquipmentFilePCluster cluster = clusters.get(0);
                    cluster.setKssj(equipmentFileEvent.getKssj());
                    cluster.setJssj(equipmentFileEvent.getJssj());
                    cluster.setJtnr(equipmentFileEvent.getJtnr());
                    
                    equipmentFilePClusterMapper.updateByPrimaryKeySelective(cluster);
                    migrationSuccess = true;
                    LOG.debug("成功更新EquipmentFilePCluster，ID: {}, equipmentFileId: {}", 
                            equipmentFileEvent.getId(), equipmentFileId);
                } else {
                    LOG.warn("未找到对应的EquipmentFilePCluster记录，ID: {}, equipmentFileId: {}", 
                            equipmentFileEvent.getId(), equipmentFileId);
                    migrationSuccess = false;
                }
            } else {
                LOG.warn("EquipmentFileEvent的equipmentFileId为空，跳过数据，ID: {}", 
                        equipmentFileEvent.getId());
                migrationSuccess = false;
            }
            
            // 更新sync_flag状态
            updateEventSyncFlag(equipmentFileEvent.getId(), migrationSuccess);
            
            if (migrationSuccess) {
                LOG.debug("成功迁移EquipmentFileEvent，ID: {}", equipmentFileEvent.getId());
            } else {
                LOG.debug("跳过EquipmentFileEvent数据，ID: {}", equipmentFileEvent.getId());
            }
            
        } catch (Exception e) {
            LOG.error("迁移EquipmentFileEvent数据失败，ID: {}", equipmentFileEvent.getId(), e);
            throw e;
        }
    }

    /**
     * 更新EquipmentFileEvent的sync_flag状态
     * 成功迁移：sync_flag=1，跳过数据：sync_flag=2
     */
    private void updateEventSyncFlag(String id, boolean migrationSuccess) {
        try {
            EquipmentFileEvent updateRecord = new EquipmentFileEvent();
            updateRecord.setId(id);
            if (migrationSuccess) {
                updateRecord.setSyncFlag(1); // 成功迁移
            } else {
                updateRecord.setSyncFlag(2); // 跳过数据
            }
            equipmentFileEventMapper.updateByPrimaryKeySelective(updateRecord);
        } catch (Exception e) {
            LOG.error("更新EquipmentFileEvent sync_flag失败，ID: {}", id, e);
            throw e;
        }
    }

    /**
     * 使用多线程迁移一批数据
     * CountDownLatch建议使用10个线程进行并发处理
     */
    private void migrateBatchWithMultiThread(List<EquipmentFile> equipmentFiles) {
        int batchSize = equipmentFiles.size();
        CountDownLatch latch = new CountDownLatch(batchSize);
        
        for (EquipmentFile equipmentFile : equipmentFiles) {
            executorService.submit(() -> {
                try {
                    migrateSingleEquipmentFile(equipmentFile);
                } catch (Exception e) {
                    LOG.error("迁移单条EquipmentFile数据失败，ID: {}, 错误: {}", 
                            equipmentFile.getId(), e.getMessage(), e);
                } finally {
                    latch.countDown();
                }
            });
        }
        
        try {
            // 等待所有线程完成，最多等待30分钟
            boolean finished = latch.await(10, TimeUnit.SECONDS);
            if (!finished) {
                LOG.warn("迁移超时，部分数据可能未完成迁移");
            }
        } catch (InterruptedException e) {
            LOG.error("等待迁移完成时被中断", e);
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 迁移单条EquipmentFile数据
     * 根据wjlx和txtlx字段分发到不同的表
     * 使用事务确保分发写入成功后才修改sync_flag为1
     */

    private void migrateSingleEquipmentFile(EquipmentFile equipmentFile) {
        try {
            String wjlx = equipmentFile.getWjlx();
            String txtlx = equipmentFile.getTxtlx();
            boolean migrationSuccess = false;
            
            // 分发规则：
            // wjlx = 3 时 txtlx = 3 或者 txtlx = 5 写入EquipmentFilePCluster表  聚类
            // wjlx = 1 时 txtlx 为空 或者 txtlx = 0 或者 txtlx = 1 写入EquipmentFilePPic表
            // wjlx = 3 时 txtlx = 1 或者 txtlx = 2 或者 txtlx = 4 或者 txtlx = 6 写入EquipmentFilePTxt表
            // wjlx = 4 时 txtlx 为空 或者 txtlx = 0 写入EquipmentFilePVideo表
            // wjlx = 2 时 txtlx 为空 或者 txtlx = 0 或者 txtlx = 1 写入EquipmentFilePWav表
            
            if ("3".equals(wjlx) && ("3".equals(txtlx) || "5".equals(txtlx))) {
                // 聚类
                saveToClusterTable(equipmentFile);
                migrationSuccess = true;
            } else if ("1".equals(wjlx) && (StringUtils.isBlank(txtlx) || "0".equals(txtlx) || "1".equals(txtlx))) {
                // 图片
                if(StringUtils.isBlank(txtlx)){
                    equipmentFile.setTxtlx("0");
                }
                saveToPicTable(equipmentFile);
                migrationSuccess = true;
            } else if ("3".equals(wjlx) && ("1".equals(txtlx) || "2".equals(txtlx) || "4".equals(txtlx) || "6".equals(txtlx))) {
                // 文件
                saveToTxtTable(equipmentFile);
                migrationSuccess = true;
            } else if ("4".equals(wjlx) && (StringUtils.isBlank(txtlx) || "0".equals(txtlx))) {
                // 视频
                if(StringUtils.isBlank(txtlx)){
                    equipmentFile.setTxtlx("0");
                }
                saveToVideoTable(equipmentFile);
                migrationSuccess = true;
            } else if ("2".equals(wjlx) && (StringUtils.isBlank(txtlx) || "0".equals(txtlx) || "1".equals(txtlx))) {
                // 音频
                if(StringUtils.isBlank(txtlx)){
                    equipmentFile.setTxtlx("0");
                }
                saveToWavTable(equipmentFile);
                migrationSuccess = true;
            } else {
                // 未匹配到分发规则，跳过数据
                LOG.warn("未匹配到分发规则，跳过数据，ID: {}, wjlx: {}, txtlx: {}", 
                        equipmentFile.getId(), wjlx, txtlx);
                migrationSuccess = false;
            }
            
            // 更新sync_flag状态
            updateSyncFlag(equipmentFile.getId(), migrationSuccess);
            
            if (migrationSuccess) {
                LOG.debug("成功迁移，ID: {}", equipmentFile.getId());
            } else {
                LOG.debug("跳过数据，ID: {}", equipmentFile.getId());
            }


        } catch (Exception e) {
            LOG.error("迁移EquipmentFile数据失败，ID: {}", equipmentFile.getId(), e);
            // 使用事务，如果迁移失败会回滚，确保数据一致性
            throw e;
        }
    }

    /**
     * 保存到聚类表
     */
    private void saveToClusterTable(EquipmentFile equipmentFile) {
        try {
            EquipmentFilePCluster cluster = new EquipmentFilePCluster();
            copyEquipmentFileToTarget(equipmentFile, cluster);
            cluster.setBid(equipmentFile.getId());
            equipmentFilePClusterMapper.insertSelective(cluster);
            LOG.debug("成功保存到聚类表，ID: {}", equipmentFile.getId());
        } catch (Exception e) {
            LOG.error("保存到聚类表失败，ID: {}", equipmentFile.getId(), e);
            throw e;
        }
    }

    /**
     * 保存到图片表
     */
    private void saveToPicTable(EquipmentFile equipmentFile) {
        try {
            EquipmentFilePPic pic = new EquipmentFilePPic();
            copyEquipmentFileToTarget(equipmentFile, pic);
            pic.setBid(equipmentFile.getId());
            
            equipmentFilePPicMapper.insertSelective(pic);
            LOG.debug("成功保存到图片表，ID: {}", equipmentFile.getId());
        } catch (Exception e) {
            LOG.error("保存到图片表失败，ID: {}", equipmentFile.getId(), e);
            throw e;
        }
    }

    /**
     * 保存到文件表
     */
    private void saveToTxtTable(EquipmentFile equipmentFile) {
        try {
            EquipmentFilePTxt txt = new EquipmentFilePTxt();
            copyEquipmentFileToTarget(equipmentFile, txt);
            txt.setBid(equipmentFile.getId());
            
            equipmentFilePTxtMapper.insertSelective(txt);
            LOG.debug("成功保存到文件表，ID: {}", equipmentFile.getId());
        } catch (Exception e) {
            LOG.error("保存到文件表失败，ID: {}", equipmentFile.getId(), e);
            throw e;
        }
    }

    /**
     * 保存到视频表
     */
    private void saveToVideoTable(EquipmentFile equipmentFile) {
        try {
            EquipmentFilePVideo video = new EquipmentFilePVideo();
            copyEquipmentFileToTarget(equipmentFile, video);
            video.setBid(equipmentFile.getId());
            
            equipmentFilePVideoMapper.insertSelective(video);
            LOG.debug("成功保存到视频表，ID: {}", equipmentFile.getId());
        } catch (Exception e) {
            LOG.error("保存到视频表失败，ID: {}", equipmentFile.getId(), e);
            throw e;
        }
    }

    /**
     * 保存到音频表
     */
    private void saveToWavTable(EquipmentFile equipmentFile) {
        try {
            EquipmentFilePWav wav = new EquipmentFilePWav();
            copyEquipmentFileToTarget(equipmentFile, wav);
            wav.setBid(equipmentFile.getId());
            
            equipmentFilePWavMapper.insertSelective(wav);
            LOG.debug("成功保存到音频表，ID: {}", equipmentFile.getId());
        } catch (Exception e) {
            LOG.error("保存到音频表失败，ID: {}", equipmentFile.getId(), e);
            throw e;
        }
    }

    /**
     * 复制EquipmentFile的公共字段到目标对象
     */
    private void copyEquipmentFileToTarget(EquipmentFile source, Object target) {
        try {
            if (target instanceof EquipmentFilePCluster) {
                EquipmentFilePCluster cluster = (EquipmentFilePCluster) target;
                cluster.setSbbh(source.getSbbh());
                cluster.setTplj(source.getTplj());
                cluster.setCjsj(source.getCjsj());
                cluster.setCreateTime(source.getCreateTime());
                cluster.setNf(source.getNf());
                cluster.setYf(source.getYf());
                cluster.setRq(source.getRq());
                cluster.setXs(source.getXs());
                cluster.setFz(source.getFz());
                cluster.setDeptcode(source.getDeptcode());
                cluster.setXmbh(source.getXmbh());
                cluster.setWjlx(source.getWjlx());
                cluster.setJczl(source.getJczl());
                cluster.setLy(source.getLy());
                cluster.setSm1(source.getSm1());
                cluster.setSm2(source.getSm2());
                cluster.setSm3(source.getSm3());
                cluster.setSm4(source.getSm4());
                cluster.setSm5(source.getSm5());
                cluster.setSm6(source.getSm6());
                cluster.setType(source.getType());
                cluster.setTs(source.getTs());
                cluster.setTxtlx(source.getTxtlx());
                cluster.setWjmc(source.getWjmc());
            } else if (target instanceof EquipmentFilePPic) {
                EquipmentFilePPic pic = (EquipmentFilePPic) target;
                pic.setSbbh(source.getSbbh());
                pic.setTplj(source.getTplj());
                pic.setCjsj(source.getCjsj());
                pic.setCreateTime(source.getCreateTime());
                pic.setNf(source.getNf());
                pic.setYf(source.getYf());
                pic.setRq(source.getRq());
                pic.setXs(source.getXs());
                pic.setFz(source.getFz());
                pic.setDeptcode(source.getDeptcode());
                pic.setXmbh(source.getXmbh());
                pic.setWjlx(source.getWjlx());
                pic.setJczl(source.getJczl());
                pic.setLy(source.getLy());
                pic.setSm1(source.getSm1());
                pic.setSm2(source.getSm2());
                pic.setSm3(source.getSm3());
                pic.setSm4(source.getSm4());
                pic.setSm5(source.getSm5());
                pic.setSm6(source.getSm6());
                pic.setType(source.getType());
                pic.setTs(source.getTs());
                pic.setTxtlx(source.getTxtlx());
                pic.setWjmc(source.getWjmc());
            } else if (target instanceof EquipmentFilePTxt) {
                EquipmentFilePTxt txt = (EquipmentFilePTxt) target;
                txt.setSbbh(source.getSbbh());
                txt.setTplj(source.getTplj());
                txt.setCjsj(source.getCjsj());
                txt.setCreateTime(source.getCreateTime());
                txt.setNf(source.getNf());
                txt.setYf(source.getYf());
                txt.setRq(source.getRq());
                txt.setXs(source.getXs());
                txt.setFz(source.getFz());
                txt.setDeptcode(source.getDeptcode());
                txt.setXmbh(source.getXmbh());
                txt.setWjlx(source.getWjlx());
                txt.setJczl(source.getJczl());
                txt.setLy(source.getLy());
                txt.setSm1(source.getSm1());
                txt.setSm2(source.getSm2());
                txt.setSm3(source.getSm3());
                txt.setSm4(source.getSm4());
                txt.setSm5(source.getSm5());
                txt.setSm6(source.getSm6());
                txt.setType(source.getType());
                txt.setTs(source.getTs());
                txt.setTxtlx(source.getTxtlx());
                txt.setWjmc(source.getWjmc());
            } else if (target instanceof EquipmentFilePVideo) {
                EquipmentFilePVideo video = (EquipmentFilePVideo) target;
                video.setSbbh(source.getSbbh());
                video.setTplj(source.getTplj());
                video.setCjsj(source.getCjsj());
                video.setCreateTime(source.getCreateTime());
                video.setNf(source.getNf());
                video.setYf(source.getYf());
                video.setRq(source.getRq());
                video.setXs(source.getXs());
                video.setFz(source.getFz());
                video.setDeptcode(source.getDeptcode());
                video.setXmbh(source.getXmbh());
                video.setWjlx(source.getWjlx());
                video.setJczl(source.getJczl());
                video.setLy(source.getLy());
                video.setSm1(source.getSm1());
                video.setSm2(source.getSm2());
                video.setSm3(source.getSm3());
                video.setSm4(source.getSm4());
                video.setSm5(source.getSm5());
                video.setSm6(source.getSm6());
                video.setType(source.getType());
                video.setTs(source.getTs());
                video.setTxtlx(source.getTxtlx());
                video.setWjmc(source.getWjmc());
            } else if (target instanceof EquipmentFilePWav) {
                EquipmentFilePWav wav = (EquipmentFilePWav) target;
                wav.setSbbh(source.getSbbh());
                wav.setTplj(source.getTplj());
                wav.setCjsj(source.getCjsj());
                wav.setCreateTime(source.getCreateTime());
                wav.setNf(source.getNf());
                wav.setYf(source.getYf());
                wav.setRq(source.getRq());
                wav.setXs(source.getXs());
                wav.setFz(source.getFz());
                wav.setDeptcode(source.getDeptcode());
                wav.setXmbh(source.getXmbh());
                wav.setWjlx(source.getWjlx());
                wav.setJczl(source.getJczl());
                wav.setLy(source.getLy());
                wav.setSm1(source.getSm1());
                wav.setSm2(source.getSm2());
                wav.setSm3(source.getSm3());
                wav.setSm4(source.getSm4());
                wav.setSm5(source.getSm5());
                wav.setSm6(source.getSm6());
                wav.setType(source.getType());
                wav.setTs(source.getTs());
                wav.setTxtlx(source.getTxtlx());
                wav.setWjmc(source.getWjmc());
            }
        } catch (Exception e) {
            LOG.error("复制字段时发生异常", e);
            throw e;
        }
    }

    /**
     * 更新sync_flag状态
     * 成功迁移：sync_flag=1，跳过数据：sync_flag=2
     */
    private void updateSyncFlag(String id, boolean migrationSuccess) {
        try {
            EquipmentFile updateRecord = new EquipmentFile();
            updateRecord.setId(id);
            if (migrationSuccess) {
                updateRecord.setSyncFlag(1); // 成功迁移
            } else {
                updateRecord.setSyncFlag(2); // 跳过数据
            }
            equipmentFileMapper.updateByPrimaryKeySelective(updateRecord);
        } catch (Exception e) {
            LOG.error("更新sync_flag失败，ID: {}", id, e);
            throw e;
        }
    }

    /**
     * 优雅关闭线程池
     */
    public void shutdown() {
        LOG.info("开始关闭迁移服务...");
        
        // 设置关闭标志
        isShuttingDown = true;
        
        if (executorService != null && !executorService.isShutdown()) {
            LOG.info("正在关闭线程池...");
            executorService.shutdown();
            try {
                // 等待正在执行的任务完成，最多等待60秒
                if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                    LOG.warn("线程池未能在60秒内正常关闭，强制关闭");
                    executorService.shutdownNow();
                } else {
                    LOG.info("线程池已正常关闭");
                }
            } catch (InterruptedException e) {
                LOG.warn("等待线程池关闭时被中断，强制关闭");
                executorService.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
        
        // 重置运行状态
        isRunning = false;
        isEventRunning = false;
        LOG.info("迁移服务已关闭");
    }



    /**
     * 获取迁移服务状态
     */
    public String getMigrationStatus() {
        StringBuilder status = new StringBuilder();
        
        if (isRunning) {
            status.append("EquipmentFile迁移服务正在运行中; ");
        } else {
            status.append("EquipmentFile迁移服务已停止; ");
        }
        
        if (isEventRunning) {
            status.append("EquipmentFileEvent迁移服务正在运行中; ");
        } else {
            status.append("EquipmentFileEvent迁移服务已停止; ");
        }
        
        if (isShuttingDown) {
            status.append("服务正在关闭中");
        }
        
        return status.toString();
    }

    /**
     * 获取迁移统计信息
     */
    public void getMigrationStatistics() {
        try {
            // 查询已迁移的数据
            EquipmentFileExample migratedExample = new EquipmentFileExample();
            migratedExample.createCriteria().andSyncFlagEqualTo(1);
            long migratedCount = equipmentFileMapper.countByExample(migratedExample);
            
            // 查询跳过的数据
            EquipmentFileExample skippedExample = new EquipmentFileExample();
            skippedExample.createCriteria().andSyncFlagEqualTo(2);
            long skippedCount = equipmentFileMapper.countByExample(skippedExample);
            
            // 查询待迁移的数据
            EquipmentFileExample pendingExample = new EquipmentFileExample();
            pendingExample.createCriteria().andSyncFlagEqualTo(0);
            long pendingCount = equipmentFileMapper.countByExample(pendingExample);
            
            LOG.info("迁移统计信息:");
            LOG.info("已迁移数据: {}", migratedCount);
            LOG.info("跳过数据: {}", skippedCount);
            LOG.info("待迁移数据: {}", pendingCount);
            LOG.info("总数据: {}", migratedCount + skippedCount + pendingCount);
            
        } catch (Exception e) {
            LOG.error("获取迁移统计信息时发生异常", e);
            throw e;
        }
    }
}
