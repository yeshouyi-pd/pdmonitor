package com.pd.server.main.service.shj;


import com.pd.server.main.domain.*;
import com.pd.server.main.mapper.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 历史 EquipmentFile 迁移
 * 历史 equipment_file_event 迁移
 */
@Service
public class EquipmentFileMigrationTyService {

    private static final Logger LOG = LoggerFactory.getLogger(EquipmentFileMigrationTyService.class);

    /**
     * 待迁移数据源
     */
    @Resource
    private EquipmentFileTyMapper  equipmentFileTyMapper;

    /**
     * 待迁移数据事件源
     */
    @Resource
    private EquipmentTyEventMapper equipmentTyEventMapper;

    /**
     * 聚类 33 35
     */
    @Resource
    private EquipmentFileTClusterMapper equipmentFileTClusterMapper;

    /**
     * 图片pic 10 11
     */
    @Resource
    private EquipmentFileTPicMapper equipmentFileTPicMapper;

    /**
     * mp4 40
     */
    @Resource
    private EquipmentFileTVideoMapper equipmentFileTVideoMapper;

    /**
     * 文件 31 32 34 36
     */
    @Resource
    private EquipmentFileTTxtMapper   equipmentFileTTxtMapper;

    /**
     * 音档 20 21
     */
    @Resource
    private EquipmentFileTWavMapper equipmentFileTWavMapper;

    /**
     * 线程池 - 建议使用10个线程
     */
    private final ExecutorService executorTyService = Executors.newFixedThreadPool(10);
    
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
                EquipmentFileTyExample example = new EquipmentFileTyExample();
                EquipmentFileTyExample.Criteria criteria = example.createCriteria();
                criteria.andSyncFlagEqualTo(0);
                example.setOrderByClause(" cjsj ASC limit 5000");

                // 使用selectByExample方法查询前5000条
                List<EquipmentFileTy> result = equipmentFileTyMapper.selectByExample(example);

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
     * 根据equipmentFileId关联EquipmentFileTCluster表更新kssj、jssj、jtnr字段
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
                EquipmentTyEventExample example = new EquipmentTyEventExample();
                EquipmentTyEventExample.Criteria criteria = example.createCriteria();
                criteria.andSyncFlagEqualTo(0);
                example.setOrderByClause("limit 5000");

                // 使用selectByExample方法查询前5000条
                List<EquipmentTyEvent> result = equipmentTyEventMapper.selectByExample(example);

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
    private void migrateEventBatchWithMultiThread(List<EquipmentTyEvent> equipmentTyEvents) {
        int batchSize = equipmentTyEvents.size();
        CountDownLatch latch = new CountDownLatch(batchSize);
        
        for (EquipmentTyEvent equipmentTyEvent : equipmentTyEvents) {
            executorTyService.submit(() -> {
                try {
                    migrateSingleEquipmentFileEvent(equipmentTyEvent);
                } catch (Exception e) {
                    LOG.error("迁移单条EquipmentFileEvent数据失败，ID: {}, 错误: {}",
                            equipmentTyEvent.getId(), e.getMessage(), e);
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
     * 根据equipmentFileId关联EquipmentFileTCluster表更新kssj、jssj、jtnr字段
     */
    private void migrateSingleEquipmentFileEvent(EquipmentTyEvent equipmentTyEvent) {
        try {
            String equipmentFileId = equipmentTyEvent.getBz();
            boolean migrationSuccess = false;
            
            if (StringUtils.isNotBlank(equipmentFileId)) {
                // 根据equipmentFileId查询EquipmentFileTCluster表
                EquipmentFileTClusterExample clusterExample = new EquipmentFileTClusterExample();
                clusterExample.createCriteria().andBidEqualTo(equipmentFileId);
                List<EquipmentFileTCluster> clusters = equipmentFileTClusterMapper.selectByExample(clusterExample);
                
                if (!clusters.isEmpty()) {
                    // 更新第一个匹配的记录
                    EquipmentFileTCluster cluster = clusters.get(0);
                    cluster.setKssj(equipmentTyEvent.getKssj());
                    cluster.setJssj(equipmentTyEvent.getJssj());
                    cluster.setSm6(equipmentTyEvent.getSm1()); //将拖曳的SM1 保存到equipmentFileTCluster的SM6中
                    
                    equipmentFileTClusterMapper.updateByPrimaryKeySelective(cluster);
                    migrationSuccess = true;
                    LOG.debug("成功更新EquipmentFileTCluster，ID: {}, equipmentFileId: {}",
                            equipmentTyEvent.getId(), equipmentFileId);
                } else {
                    LOG.warn("未找到对应的EquipmentFileTCluster记录，ID: {}, equipmentFileId: {}",
                            equipmentTyEvent.getId(), equipmentFileId);
                    migrationSuccess = false;
                }
            } else {
                LOG.warn("EquipmentFileEvent的equipmentFileId为空，跳过数据，ID: {}",
                        equipmentTyEvent.getId());
                migrationSuccess = false;
            }
            
            // 更新sync_flag状态
            updateEventSyncFlag(equipmentTyEvent.getId(), migrationSuccess);
            

            
        } catch (Exception e) {
            LOG.error("迁移EquipmentFileEvent数据失败，ID: {}", equipmentTyEvent.getId(), e);
            throw e;
        }
    }

    /**
     * 更新EquipmentFileEvent的sync_flag状态
     * 成功迁移：sync_flag=1，跳过数据：sync_flag=2
     */
    private void updateEventSyncFlag(String id, boolean migrationSuccess) {
        try {
            EquipmentTyEvent  updateRecord = new EquipmentTyEvent();
            updateRecord.setId(id);
            if (migrationSuccess) {
                updateRecord.setSyncFlag(1); // 成功迁移
            } else {
                updateRecord.setSyncFlag(2); // 跳过数据
            }
            equipmentTyEventMapper.updateByPrimaryKeySelective(updateRecord);
        } catch (Exception e) {
            LOG.error("更新EquipmentFileEvent sync_flag失败，ID: {}", id, e);
            throw e;
        }
    }

    /**
     * 使用多线程迁移一批数据
     * CountDownLatch建议使用10个线程进行并发处理
     */
    private void migrateBatchWithMultiThread(List<EquipmentFileTy> equipmentFileTys) {
        int batchSize = equipmentFileTys.size();
        CountDownLatch latch = new CountDownLatch(batchSize);
        
        for (EquipmentFileTy equipmentFileTy : equipmentFileTys) {
            executorTyService.submit(() -> {
                try {
                    migrateSingleEquipmentFile(equipmentFileTy);
                } catch (Exception e) {
                    LOG.error("迁移单条EquipmentFile数据失败，ID: {}, 错误: {}",
                            equipmentFileTy.getId(), e.getMessage(), e);
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

    private void migrateSingleEquipmentFile(EquipmentFileTy equipmentFileTy) {
        try {
            String wjlx = equipmentFileTy.getWjlx();
            String txtlx = equipmentFileTy.getTxtlx();
            boolean migrationSuccess = false;
            
            // 分发规则：
            // wjlx = 3 时 txtlx = 3 或者 txtlx = 5 写入EquipmentFileTCluster表  聚类
            // wjlx = 1 时 txtlx 为空 或者 txtlx = 0 或者 txtlx = 1 写入EquipmentFileTPic表
            // wjlx = 3 时 txtlx = 1 或者 txtlx = 2 或者 txtlx = 4 或者 txtlx = 6 写入EquipmentFileTTxt表
            // wjlx = 4 时 txtlx 为空 或者 txtlx = 0 写入EquipmentFileTVideo表
            // wjlx = 2 时 txtlx 为空 或者 txtlx = 0 或者 txtlx = 1 写入EquipmentFileTWav表
            
            if ("3".equals(wjlx) && ("3".equals(txtlx) || "5".equals(txtlx))) {
                // 聚类
                saveToClusterTable(equipmentFileTy);
                migrationSuccess = true;
            } else if ("1".equals(wjlx) && (StringUtils.isBlank(txtlx) || "0".equals(txtlx) || "1".equals(txtlx))) {
                // 图片
                if(StringUtils.isBlank(txtlx)){
                    equipmentFileTy.setTxtlx("0");
                }
                saveToPicTable(equipmentFileTy);
                migrationSuccess = true;
            } else if ("3".equals(wjlx) && ("1".equals(txtlx) || "2".equals(txtlx) || "4".equals(txtlx) || "6".equals(txtlx))) {
                // 文件
                saveToTxtTable(equipmentFileTy);
                migrationSuccess = true;
            } else if ("4".equals(wjlx) && (StringUtils.isBlank(txtlx) || "0".equals(txtlx))) {
                // 视频
                if(StringUtils.isBlank(txtlx)){
                    equipmentFileTy.setTxtlx("0");
                }
                saveToVideoTable(equipmentFileTy);
                migrationSuccess = true;
            } else if ("2".equals(wjlx) && (StringUtils.isBlank(txtlx) || "0".equals(txtlx) || "1".equals(txtlx))) {
                // 音频
                if(StringUtils.isBlank(txtlx)){
                    equipmentFileTy.setTxtlx("0");
                }
                saveToWavTable(equipmentFileTy);
                migrationSuccess = true;
            } else {
                // 未匹配到分发规则，跳过数据
                LOG.warn("未匹配到分发规则，跳过数据，ID: {}, wjlx: {}, txtlx: {}",
                        equipmentFileTy.getId(), wjlx, txtlx);
                migrationSuccess = false;
            }
            
            // 更新sync_flag状态
            updateSyncFlag(equipmentFileTy.getId(), migrationSuccess);
            
            if (migrationSuccess) {
                LOG.debug("成功迁移，ID: {}", equipmentFileTy.getId());
            } else {
                LOG.debug("跳过数据，ID: {}", equipmentFileTy.getId());
            }


        } catch (Exception e) {
            LOG.error("迁移EquipmentFile数据失败，ID: {}", equipmentFileTy.getId(), e);
            // 使用事务，如果迁移失败会回滚，确保数据一致性
            throw e;
        }
    }

    /**
     * 保存到聚类表
     */
    private void saveToClusterTable(EquipmentFileTy equipmentFileTy) {
        try {
            EquipmentFileTCluster cluster = new EquipmentFileTCluster();
            copyEquipmentFileToTarget(equipmentFileTy, cluster);
            cluster.setBid(equipmentFileTy.getId());
            equipmentFileTClusterMapper.insertSelective(cluster);
            LOG.debug("成功保存到聚类表，ID: {}", equipmentFileTy.getId());
        } catch (Exception e) {
            LOG.error("保存到聚类表失败，ID: {}", equipmentFileTy.getId(), e);
            throw e;
        }
    }

    /**
     * 保存到图片表
     */
    private void saveToPicTable(EquipmentFileTy equipmentFileTy) {
        try {
            EquipmentFileTPic pic = new EquipmentFileTPic();
            copyEquipmentFileToTarget(equipmentFileTy, pic);
            pic.setBid(equipmentFileTy.getId());
            
            equipmentFileTPicMapper.insertSelective(pic);
            LOG.debug("成功保存到图片表，ID: {}", equipmentFileTy.getId());
        } catch (Exception e) {
            LOG.error("保存到图片表失败，ID: {}", equipmentFileTy.getId(), e);
            throw e;
        }
    }

    /**
     * 保存到文件表
     */
    private void saveToTxtTable(EquipmentFileTy equipmentFileTy) {
        try {
            EquipmentFileTTxt txt = new EquipmentFileTTxt();
            copyEquipmentFileToTarget(equipmentFileTy, txt);
            txt.setBid(equipmentFileTy.getId());
            
            equipmentFileTTxtMapper.insertSelective(txt);
            LOG.debug("成功保存到文件表，ID: {}", equipmentFileTy.getId());
        } catch (Exception e) {
            LOG.error("保存到文件表失败，ID: {}", equipmentFileTy.getId(), e);
            throw e;
        }
    }

    /**
     * 保存到视频表
     */
    private void saveToVideoTable(EquipmentFileTy equipmentFileTy) {
        try {
            EquipmentFileTVideo video = new EquipmentFileTVideo();
            copyEquipmentFileToTarget(equipmentFileTy, video);
            video.setBid(equipmentFileTy.getId());
            
            equipmentFileTVideoMapper.insertSelective(video);
            LOG.debug("成功保存到视频表，ID: {}", equipmentFileTy.getId());
        } catch (Exception e) {
            LOG.error("保存到视频表失败，ID: {}", equipmentFileTy.getId(), e);
            throw e;
        }
    }

    /**
     * 保存到音频表
     */
    private void saveToWavTable(EquipmentFileTy equipmentFileTy) {
        try {
            EquipmentFileTWav wav = new EquipmentFileTWav();
            copyEquipmentFileToTarget(equipmentFileTy, wav);
            wav.setBid(equipmentFileTy.getId());
            
            equipmentFileTWavMapper.insertSelective(wav);
            LOG.debug("成功保存到音频表，ID: {}", equipmentFileTy.getId());
        } catch (Exception e) {
            LOG.error("保存到音频表失败，ID: {}", equipmentFileTy.getId(), e);
            throw e;
        }
    }

    /**
     * 复制EquipmentFile的公共字段到目标对象
     */
    private void copyEquipmentFileToTarget(EquipmentFileTy source, Object target) {
        try {
            if (target instanceof EquipmentFileTCluster) {
                EquipmentFileTCluster cluster = (EquipmentFileTCluster) target;
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
            } else if (target instanceof EquipmentFileTPic) {
                EquipmentFileTPic pic = (EquipmentFileTPic) target;
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
            } else if (target instanceof EquipmentFileTTxt) {
                EquipmentFileTTxt txt = (EquipmentFileTTxt) target;
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
            } else if (target instanceof EquipmentFileTVideo) {
                EquipmentFileTVideo video = (EquipmentFileTVideo) target;
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
            } else if (target instanceof EquipmentFileTWav) {
                EquipmentFileTWav wav = (EquipmentFileTWav) target;
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
            EquipmentFileTy updateRecord = new EquipmentFileTy();
            updateRecord.setId(id);
            if (migrationSuccess) {
                updateRecord.setSyncFlag(1); // 成功迁移
            } else {
                updateRecord.setSyncFlag(2); // 跳过数据
            }
            equipmentFileTyMapper.updateByPrimaryKeySelective(updateRecord);
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
        
        if (executorTyService != null && !executorTyService.isShutdown()) {
            LOG.info("正在关闭线程池...");
            executorTyService.shutdown();
            try {
                // 等待正在执行的任务完成，最多等待60秒
                if (!executorTyService.awaitTermination(60, TimeUnit.SECONDS)) {
                    LOG.warn("线程池未能在60秒内正常关闭，强制关闭");
                    executorTyService.shutdownNow();
                } else {
                    LOG.info("线程池已正常关闭");
                }
            } catch (InterruptedException e) {
                LOG.warn("等待线程池关闭时被中断，强制关闭");
                executorTyService.shutdownNow();
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
            EquipmentFileTyExample migratedExample = new EquipmentFileTyExample();
            migratedExample.createCriteria().andSyncFlagEqualTo(1);
            long migratedCount = equipmentFileTyMapper.countByExample(migratedExample);
            
            // 查询跳过的数据
            EquipmentFileTyExample skippedExample = new EquipmentFileTyExample();
            skippedExample.createCriteria().andSyncFlagEqualTo(2);
            long skippedCount = equipmentFileTyMapper.countByExample(skippedExample);
            
            // 查询待迁移的数据
            EquipmentFileTyExample pendingExample = new EquipmentFileTyExample();
            pendingExample.createCriteria().andSyncFlagEqualTo(0);
            long pendingCount = equipmentFileTyMapper.countByExample(pendingExample);
            
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
