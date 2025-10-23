package com.pd.server.main.service.shj;

import com.pd.server.main.domain.*;
import com.pd.server.main.mapper.*;
import com.pd.server.main.service.AttrService;
import com.pd.server.util.UuidUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 接口信息分发 拖曳
 */
@Service
public class EquipmentFileSplitTyService {

    private static final Logger LOG = LoggerFactory.getLogger(EquipmentFileSplitTyService.class);

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

    @Resource
    private AttrService attrService;

    /**
     * 异步分发保存数据
     * 根据EquipmentFile对象的wjlx和txtlx字段判断写入到哪个表
     *
     * 分发规则：
     * wjlx = 3 时 txtlx = 3 或者 txtlx = 5 写入EquipmentFileTCluster表  聚类
     * wjlx = 1 时 txtlx 为空 或者 txtlx = 0 或者 txtlx = 1 写入EquipmentFileTPic表
     * wjlx = 3 时 txtlx = 1 或者 txtlx = 2 或者 txtlx = 4 或者 txtlx = 6 写入EquipmentFileTTxt表
     * wjlx = 4 时 txtlx 为空 或者 txtlx = 0 写入EquipmentFileTVideo表
     * wjlx = 2 时 txtlx 为空 或者 txtlx = 0 或者 txtlx = 1 写入EquipmentFileTWav表
     *
     * @param equipmentFileTy 要分发的EquipmentFile对象
     */
    public void distributeAndSave(EquipmentFileTy equipmentFileTy) {
        try {
            if (equipmentFileTy == null) {
                LOG.warn("EquipmentFile对象为空，跳过分发");
                return;
            }

            String wjlx = equipmentFileTy.getWjlx();
            String txtlx = equipmentFileTy.getTxtlx();

            //LOG.debug("开始分发数据，equipmentFileTy ID: {}, wjlx: {}, txtlx: {}",
                  //  equipmentFileTy.getId(), wjlx, txtlx);

            // 判断分发规则
            if ("3".equals(wjlx) && ("3".equals(txtlx) || "5".equals(txtlx))) {
                // 写入EquipmentFileTCluster表
                saveToCluster(equipmentFileTy);
            } else if ("1".equals(wjlx) && (StringUtils.isBlank(txtlx) || "0".equals(txtlx) || "1".equals(txtlx))) {
               if(StringUtils.isBlank(txtlx)){
                   equipmentFileTy.setTxtlx("0");
               }
                // 写入EquipmentFileTPic表
                saveToPic(equipmentFileTy);
            } else if ("3".equals(wjlx) && ("1".equals(txtlx) || "2".equals(txtlx) || "4".equals(txtlx) || "6".equals(txtlx))) {
                // 写入EquipmentFileTTxt表
                saveToTxt(equipmentFileTy);
            } else if ("4".equals(wjlx) && (StringUtils.isBlank(txtlx) || "0".equals(txtlx))) {
                // 写入EquipmentFileTVideo表
                if(StringUtils.isBlank(txtlx)){
                    equipmentFileTy.setTxtlx("0");
                }
                saveToVideo(equipmentFileTy);
            } else if ("2".equals(wjlx) && (StringUtils.isBlank(txtlx) || "0".equals(txtlx) || "1".equals(txtlx))) {
                // 写入EquipmentFileTWav表
                if(StringUtils.isBlank(txtlx)){
                    equipmentFileTy.setTxtlx("0");
                }
                saveToWav(equipmentFileTy);
            } else {
                LOG.warn("未匹配到分发规则，EquipmentFile ID: {}, wjlx: {}, txtlx: {}",
                        equipmentFileTy.getId(), wjlx, txtlx);
            }

        } catch (Exception e) {
            LOG.error("分发数据时发生异常，EquipmentFile ID: {}, 错误信息: {}",
                    equipmentFileTy != null ? equipmentFileTy.getId() : "null", e.getMessage(), e);
        }
    }

    /**
     * 保存到聚类表
     */
    private void saveToCluster(EquipmentFileTy equipmentFileTy) {
        try {
            EquipmentFileTCluster cluster = new EquipmentFileTCluster();
            copyCommonFields(equipmentFileTy, cluster);
            equipmentFileTClusterMapper.insertSelective(cluster);
            //LOG.debug("成功保存到聚类表，EquipmentFile ID: {}, 新ID: {}",
                  //  equipmentFileTy.getId(), cluster.getId());
        } catch (Exception e) {
            LOG.error("保存到聚类表失败，EquipmentFile ID: {}, 错误信息: {}",
                    equipmentFileTy.getId(), e.getMessage(), e);
        }
    }

    /**
     * 保存到图片表
     */
    private void saveToPic(EquipmentFileTy equipmentFileTy) {
        try {
            EquipmentFileTPic pic = new EquipmentFileTPic();
            copyCommonFields(equipmentFileTy, pic);
            equipmentFileTPicMapper.insertSelective(pic);
            //LOG.debug("成功保存到图片表，EquipmentFile ID: {}, 新ID: {}",
                  //  equipmentFileTy.getId(), pic.getId());
        } catch (Exception e) {
            LOG.error("保存到图片表失败，EquipmentFile ID: {}, 错误信息: {}",
                    equipmentFileTy.getId(), e.getMessage(), e);
        }
    }

    /**
     * 保存到文本表
     */
    private void saveToTxt(EquipmentFileTy equipmentFileTy) {
        try {
            EquipmentFileTTxt txt = new EquipmentFileTTxt();
            copyCommonFields(equipmentFileTy, txt);
            equipmentFileTTxtMapper.insertSelective(txt);
            //LOG.debug("成功保存到文本表，EquipmentFile ID: {}, 新ID: {}",
                //    equipmentFileTy.getId(), txt.getId());
        } catch (Exception e) {
            LOG.error("保存到文本表失败，EquipmentFile ID: {}, 错误信息: {}",
                    equipmentFileTy.getId(), e.getMessage(), e);
        }
    }

    /**
     * 保存到视频表
     */
    private void saveToVideo(EquipmentFileTy equipmentFileTy) {
        try {
            EquipmentFileTVideo video = new EquipmentFileTVideo();
            copyCommonFields(equipmentFileTy, video);
            equipmentFileTVideoMapper.insertSelective(video);
            //LOG.debug("成功保存到视频表，EquipmentFile ID: {}, 新ID: {}",
                  //  equipmentFileTy.getId(), video.getId());
        } catch (Exception e) {
            LOG.error("保存到视频表失败，EquipmentFile ID: {}, 错误信息: {}",
                    equipmentFileTy.getId(), e.getMessage(), e);
        }
    }

    /**
     * 保存到音频表
     */
    private void saveToWav(EquipmentFileTy equipmentFileTy) {
        try {
            EquipmentFileTWav wav = new EquipmentFileTWav();
            copyCommonFields(equipmentFileTy, wav);
            equipmentFileTWavMapper.insertSelective(wav);
            //LOG.debug("成功保存到音频表，EquipmentFile ID: {}, 新ID: {}",
                  //  equipmentFileTy.getId(), wav.getId());
        } catch (Exception e) {
            LOG.error("保存到音频表失败，EquipmentFile ID: {}, 错误信息: {}",
                    equipmentFileTy.getId(), e.getMessage(), e);
        }
    }

    /**
     * 复制公共字段
     * 所有目标表都有相同的字段结构，除了id（自增）和sync_flag（不需要）
     */
    private void copyCommonFields(EquipmentFileTy source, Object target) {
        if (target instanceof EquipmentFileTCluster) {

            EquipmentFileTCluster cluster = (EquipmentFileTCluster) target;
            cluster.setBid(source.getId());
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
            if("1012".equals(cluster.getType())){
                String wjmc = cluster.getTplj().substring(cluster.getTplj().lastIndexOf("/")+1,cluster.getTplj().lastIndexOf("_A2.txt"));
                String kssj = wjmc.substring(0,4)+"-"+wjmc.substring(5,7)+"-"+wjmc.substring(8,10)+" "+wjmc.substring(11,13)+":"+wjmc.substring(14,16)+":"+wjmc.substring(17,19);
                String jssj = wjmc.substring(20,24)+"-"+wjmc.substring(25,27)+"-"+wjmc.substring(28,30)+" "+wjmc.substring(31,33)+":"+wjmc.substring(34,36)+":"+wjmc.substring(37,39);
                cluster.setKssj(kssj);
                cluster.setJssj(jssj);
            }

        } else if (target instanceof EquipmentFileTPic) {
            EquipmentFileTPic pic = (EquipmentFileTPic) target;
            pic.setBid(source.getId());
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
            txt.setBid(source.getId());
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
            video.setBid(source.getId());
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
            wav.setBid(source.getId());
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
    }
}