package com.pd.server.main.service.shj;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.AzimuthAngleDto;
import com.pd.server.main.dto.AzimuthAngleRqDto;
import com.pd.server.main.dto.CameraMiddleDto;
import com.pd.server.main.mapper.*;
import com.pd.server.main.service.*;
import com.pd.server.util.*;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 接口信息分发
 */
@Service
public class EquipmentFileSplitShjService {

    private static final Logger LOG = LoggerFactory.getLogger(EquipmentFileSplitShjService.class);

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

    @Resource
    private EquipmentFileTodayMapper  equipmentFileTodayMapper;

    @Resource
    private AzimuthAngleService azimuthAngleService;

    @Resource
    private AzimuthAngleRqService azimuthAngleRqService;

    @Resource
    private CodesetMapper codesetMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private CameraMiddleService cameraMiddleService;
    
    @Resource
    private CameraInfoService cameraInfoService;

    public static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

    /**
     * 异步分发保存数据
     * 根据EquipmentFile对象的wjlx和txtlx字段判断写入到哪个表
     * 
     * 分发规则：
     * wjlx = 3 时 txtlx = 3 或者 txtlx = 5 写入EquipmentFilePCluster表  聚类
     * wjlx = 1 时 txtlx 为空 或者 txtlx = 0 或者 txtlx = 1 写入EquipmentFilePPic表
     * wjlx = 3 时 txtlx = 1 或者 txtlx = 2 或者 txtlx = 4 或者 txtlx = 6 写入EquipmentFilePTxt表
     * wjlx = 4 时 txtlx 为空 或者 txtlx = 0 写入EquipmentFilePVideo表
     * wjlx = 2 时 txtlx 为空 或者 txtlx = 0 或者 txtlx = 1 写入EquipmentFilePWav表
     * 
     * @param equipmentFile 要分发的EquipmentFile对象
     */
    public void distributeAndSave(EquipmentFile equipmentFile) {
        try {
            if (equipmentFile == null) {
                LOG.warn("EquipmentFile对象为空，跳过分发");
                return;
            }

            String wjlx = equipmentFile.getWjlx();
            String txtlx = equipmentFile.getTxtlx();

            //推送文件
            if("JXYSA4001".equals(equipmentFile.getSbbh())){
                PushFile.pushFile1(equipmentFile.getTplj());
            }

            //LOG.debug("开始分发数据，EquipmentFile ID: {}, wjlx: {}, txtlx: {}", 
                 //   equipmentFile.getId(), wjlx, txtlx);

            // 判断分发规则
            if ("3".equals(wjlx) && ("3".equals(txtlx) || "5".equals(txtlx))) {
                // 写入EquipmentFilePCluster表
                saveToCluster(equipmentFile);
            } else if ("1".equals(wjlx) && (StringUtils.isBlank(txtlx) || "0".equals(txtlx) || "1".equals(txtlx))) {
                // 写入EquipmentFilePPic表
                if(StringUtils.isBlank(txtlx)){
                    equipmentFile.setTxtlx("0");
                }
                saveToPic(equipmentFile);
            } else if ("3".equals(wjlx) && ("1".equals(txtlx) || "2".equals(txtlx) || "4".equals(txtlx) || "6".equals(txtlx))) {
                // 写入EquipmentFilePTxt表
                saveToTxt(equipmentFile);
            } else if ("4".equals(wjlx) && (StringUtils.isBlank(txtlx) || "0".equals(txtlx))) {
                // 写入EquipmentFilePVideo表
                if(StringUtils.isBlank(txtlx)){
                    equipmentFile.setTxtlx("0");
                }
                saveToVideo(equipmentFile);
            } else if ("2".equals(wjlx) && (StringUtils.isBlank(txtlx) || "0".equals(txtlx) || "1".equals(txtlx))) {
                // 写入EquipmentFilePWav表
                if(StringUtils.isBlank(txtlx)){
                    equipmentFile.setTxtlx("0");
                }
                saveToWav(equipmentFile);
            } else {
                LOG.warn("未匹配到分发规则，EquipmentFile ID: {}, wjlx: {}, txtlx: {}", 
                        equipmentFile.getId(), wjlx, txtlx);
            }

        } catch (Exception e) {
            LOG.error("分发数据时发生异常，EquipmentFile ID: {}, 错误信息: {}", 
                    equipmentFile != null ? equipmentFile.getId() : "null", e.getMessage(), e);
        }
    }

    /**
     * 保存到聚类表
     */
    private void saveToCluster(EquipmentFile equipmentFile) {
        try {
            EquipmentFilePCluster cluster = new EquipmentFilePCluster();
            if(copyCommonFields(equipmentFile, cluster)){
                equipmentFilePClusterMapper.insertSelective(cluster);
                insertEquipFile(equipmentFile);
                //统计方位角次数
                staticAzimuthAngle(cluster.getSbbh(),cluster.getDeptcode(),cluster.getRq(),cluster.getJtnr());
                //南京设备对接无人机
                pushMqMsg(cluster.getSbbh(),cluster.getJtnr());
                //白海豚写剪切视频的事件，李响读了去剪切视频
                if(cluster.getSbbh().contains("RPCD")||cluster.getSbbh().contains("tl")){
                    saveNewEvent(equipmentFile);
                }
            }
            //LOG.debug("成功保存到聚类表，EquipmentFile ID: {}, 新ID: {}", 
                  //  equipmentFile.getId(), cluster.getId());
        } catch (Exception e) {
            LOG.error("保存到聚类表失败，EquipmentFile ID: {}, 错误信息: {}", 
                    equipmentFile.getId(), e.getMessage(), e);
        }
    }

    /**
     * 南京设备对接无人机
     */
    public void pushMqMsg(String sbbh,String jtnr){
        try {
            CodesetExample codesetExample = new CodesetExample();
            CodesetExample.Criteria codesetCa = codesetExample.createCriteria();
            codesetCa.andTypeEqualTo("15");
            List<Codeset> list = codesetMapper.selectByExample(codesetExample);
            Map<String,String> codesetMap = list==null?new HashMap<>():list.stream().collect(Collectors.toMap(p -> p.getCode(), p -> p.getName()));
            Set<String> nja4sbsn = codesetMap.keySet();
            if(nja4sbsn!= null && nja4sbsn.contains(sbbh)){
                Connection connection = MQUtil.getConnection();
                Channel channel = connection.createChannel();
                channel.queueDeclare(MQUtil.QUEUE_NAME,true,false,false,null);
                String message = sbbh+"&"+codesetMap.get(sbbh)+"@"+jtnr;
                channel.basicPublish("",MQUtil.QUEUE_NAME,null,message.getBytes("UTF-8"));
                channel.close();
                connection.close();
                LOG.error("无人机数据已发送："+message);
            }
        }catch (Exception e){
            LOG.error("无人机数据错误："+e.getMessage());
        }
    }

    /**
     * 方位角统计
     */
    public void staticAzimuthAngle(String sbbh,String deptcode,String rq,String jtnr){
        try {
            JSONObject jsonObject = FileShjServiceTool.getStatic(jtnr);
            Map<String, Map<String,Integer>> rqMap = (Map<String, Map<String, Integer>>) jsonObject.get("rqMap");
            Map<String,Integer> rqTsMap = (Map<String, Integer>) jsonObject.get("rqTsMap");
            for(String xsStr : rqMap.keySet()){
                Map<String,Integer> jdNum = rqMap.get(xsStr);
                String[] rqArr = xsStr.split("_");
                AzimuthAngleDto dto = new AzimuthAngleDto();
                dto.setSbbh(sbbh);
                dto.setRq(rq);
                dto.setXs(rqArr[0]+"-"+rqArr[1]+"-"+rqArr[2]+" "+rqArr[3]);
                dto.setFz(rqArr[0]+"-"+rqArr[1]+"-"+rqArr[2]+" "+rqArr[3]+":"+rqArr[4]);
                dto.setTs(rqTsMap.get(xsStr));
                dto.setDeptcode(deptcode);
                dto.setNorthNortheast(jdNum.get("north_northeast"));
                dto.setNortheastEast(jdNum.get("northeast_east"));
                dto.setEastEastsouth(jdNum.get("east_eastsouth"));
                dto.setEastsouthSouth(jdNum.get("eastsouth_south"));
                dto.setSouthSouthwest(jdNum.get("south_southwest"));
                dto.setSouthwestWest(jdNum.get("southwest_west"));
                dto.setWestWestnorth(jdNum.get("west_westnorth"));
                dto.setWestnorthNorth(jdNum.get("westnorth_north"));
                azimuthAngleService.save(dto);
                AzimuthAngleRqDto rqDto = new AzimuthAngleRqDto();
                rqDto.setSbbh(sbbh);
                rqDto.setRq(rq);
                azimuthAngleRqService.save(rqDto);
            }
        }catch (Exception e){
            LOG.error("方位角统计错误："+e.getMessage());
        }
    }

    /**
     * 保存到图片表
     */
    private void saveToPic(EquipmentFile equipmentFile) {
        try {
            EquipmentFilePPic pic = new EquipmentFilePPic();
            copyCommonFields(equipmentFile, pic);
            equipmentFilePPicMapper.insertSelective(pic);
            insertEquipFile(equipmentFile);
            //白海豚写剪切视频的事件，李响读了去剪切视频
            if(equipmentFile.getSbbh().contains("RPCD")||equipmentFile.getSbbh().contains("tl")){
                saveNewEvent(equipmentFile);
            }
            //LOG.debug("成功保存到图片表，EquipmentFile ID: {}, 新ID: {}", 
                   // equipmentFile.getId(), pic.getId());
        } catch (Exception e) {
            LOG.error("保存到图片表失败，EquipmentFile ID: {}, 错误信息: {}", 
                    equipmentFile.getId(), e.getMessage(), e);
        }
    }

    public void insertEquipFile(EquipmentFile saveEntity){
        try {
            equipmentFileTodayMapper.insertEquipFile(saveEntity);
        }catch (DuplicateKeyException e){
            saveEntity.setId(UuidUtil.getShortUuid());
            insertEquipFile(saveEntity);
        }
    }

    /**
     * 保存到文本表
     */
    private void saveToTxt(EquipmentFile equipmentFile) {
        try {
            EquipmentFilePTxt txt = new EquipmentFilePTxt();
            copyCommonFields(equipmentFile, txt);
            equipmentFilePTxtMapper.insertSelective(txt);
            insertEquipFile(equipmentFile);
            //LOG.debug("成功保存到文本表，EquipmentFile ID: {}, 新ID: {}", 
                  //  equipmentFile.getId(), txt.getId());
        } catch (Exception e) {
            LOG.error("保存到文本表失败，EquipmentFile ID: {}, 错误信息: {}", 
                    equipmentFile.getId(), e.getMessage(), e);
        }
    }

    /**
     * 保存到视频表
     */
    private void saveToVideo(EquipmentFile equipmentFile) {
        try {
            EquipmentFilePVideo video = new EquipmentFilePVideo();
            copyCommonFields(equipmentFile, video);
            equipmentFilePVideoMapper.insertSelective(video);
            insertEquipFile(equipmentFile);
            //LOG.debug("成功保存到视频表，EquipmentFile ID: {}, 新ID: {}", 
                 //   equipmentFile.getId(), video.getId());
        } catch (Exception e) {
            LOG.error("保存到视频表失败，EquipmentFile ID: {}, 错误信息: {}", 
                    equipmentFile.getId(), e.getMessage(), e);
        }
    }

    /**
     * 保存到音频表
     */
    private void saveToWav(EquipmentFile equipmentFile) {
        try {
            EquipmentFilePWav wav = new EquipmentFilePWav();
            copyCommonFields(equipmentFile, wav);
            equipmentFilePWavMapper.insertSelective(wav);
            insertEquipFile(equipmentFile);
            //LOG.debug("成功保存到音频表，EquipmentFile ID: {}, 新ID: {}", 
                  //  equipmentFile.getId(), wav.getId());
        } catch (Exception e) {
            LOG.error("保存到音频表失败，EquipmentFile ID: {}, 错误信息: {}", 
                    equipmentFile.getId(), e.getMessage(), e);
        }
    }

    /**
     * 判断是否是雾报数据
     * 使用分布式锁防止并发时多条数据返回同一条数据
     */
    private EquipmentFile isMistData(EquipmentFile equipmentFile){
        Map<String,String> attrMap = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.ATTRECODEKEY);
        if(attrMap.get("predationsbsn").contains(equipmentFile.getSbbh())){
            // 使用分布式锁防止并发问题
            String lockKey = "mist_data_lock:" + equipmentFile.getSbbh();
            // 使用唯一标识作为锁值，确保只能由持有者释放
            String lockValue = UuidUtil.getUuid();
            Boolean lockAcquired = false;
            try {
                // 循环等待获取分布式锁，确保同一设备的数据串行执行
                // 最大等待时间5秒，避免无限等待
                long maxWaitTime = 5000; // 5秒
                long startTime = System.currentTimeMillis();
                int retryCount = 0;
                
                while (!lockAcquired && (System.currentTimeMillis() - startTime) < maxWaitTime) {
                    // 尝试获取分布式锁，超时时间10秒
                    lockAcquired = redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, 10, TimeUnit.SECONDS);
                    
                    if (!lockAcquired) {
                        retryCount++;
                        try {
                            // 等待100ms后重试
                            Thread.sleep(100);
                            // 每次重试时生成新的锁值（虽然理论上不需要，但为了安全）
                            if (retryCount % 10 == 0) {
                                lockValue = UuidUtil.getUuid();
                            }
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            LOG.warn("获取雾报数据锁时被中断，设备编号: {}", equipmentFile.getSbbh());
                            break;
                        }
                    }
                }
                
                if (!lockAcquired) {
                    // 如果超过最大等待时间仍然获取不到锁，记录警告并返回当前数据
                    LOG.warn("无法获取雾报数据锁（已等待{}ms，重试{}次），直接返回当前数据，设备编号: {}", 
                            System.currentTimeMillis() - startTime, retryCount, equipmentFile.getSbbh());
                    return equipmentFile;
                }
                
                LOG.error("获取雾报数据锁成功，设备编号: {}, 锁值: {}, 等待时间: {}ms, 重试次数: {}",
                        equipmentFile.getSbbh(), lockValue, System.currentTimeMillis() - startTime, retryCount);
                //判断是否是雾报(前后三分钟都没有报警的数据是雾报数据，雾报数据不保存)
                EquipmentFile beforeEntity = new EquipmentFile();
                String wbKey = equipmentFile.getSbbh()+"WB";
                // 只读取一次Redis，避免重复读取
                Object wbValue = redisTemplate.opsForValue().get(wbKey);
                if(!org.springframework.util.StringUtils.isEmpty(wbValue)){
                    String entityJson = (String) wbValue;
                    beforeEntity = JSONObject.parseObject(entityJson,EquipmentFile.class);
                    if(!org.springframework.util.StringUtils.isEmpty(beforeEntity.getCjsj())&&isOverThreeMinute(DateUtil.getFormatDate(beforeEntity.getCjsj(),"yyyy-MM-dd HH:mm:ss"),DateUtil.getFormatDate(equipmentFile.getCjsj(),"yyyy-MM-dd HH:mm:ss"))){
                        // 更新Redis缓存为当前数据
                        redisTemplate.opsForValue().set(wbKey, JSONObject.toJSONString(equipmentFile));
                        return beforeEntity;
                    }else {
                        EquipmentFilePPicExample example = new EquipmentFilePPicExample();
                        EquipmentFilePPicExample.Criteria ca = example.createCriteria();
                        ca.andSbbhEqualTo(equipmentFile.getSbbh());
                        ca.andCjsjLessThanOrEqualTo(DateUtil.getMinutesLater(beforeEntity.getCjsj(),-3));
                        List<EquipmentFilePPic> lastFile = equipmentFilePPicMapper.selectByExample(example);
                        if(!lastFile.isEmpty()){
                            return beforeEntity;
                        }
                        // 更新Redis缓存为当前数据
                        redisTemplate.opsForValue().set(wbKey, JSONObject.toJSONString(equipmentFile));
                        return null;
                    }
                }else{
                    // 首次设置缓存
                    redisTemplate.opsForValue().set(wbKey, JSONObject.toJSONString(equipmentFile));
                    return null;
                }
            } finally {
                // 安全释放分布式锁：只有持有者才能释放
                if (lockAcquired) {
                    releaseLock(lockKey, lockValue);
                }
            }
        }else {
            return equipmentFile;
        }
    }

    /**
     * 安全释放分布式锁
     * 先检查锁的值是否匹配，只有匹配时才删除，避免误释放其他线程的锁
     * 
     * @param lockKey 锁的键
     * @param lockValue 锁的值（唯一标识）
     */
    private void releaseLock(String lockKey, String lockValue) {
        try {
            // 先获取当前锁的值
            Object currentValue = redisTemplate.opsForValue().get(lockKey);
            
            // 只有当锁的值匹配时，才删除锁
            // 这样可以避免误释放其他线程的锁（例如：线程A的锁过期后，线程B获取了锁，线程A不应该删除线程B的锁）
            if (currentValue != null && lockValue.equals(currentValue.toString())) {
                redisTemplate.delete(lockKey);
                LOG.debug("成功释放分布式锁，锁键: {}, 锁值: {}", lockKey, lockValue);
            } else {
                LOG.warn("释放分布式锁失败，锁值不匹配或锁已过期，锁键: {}, 期望锁值: {}, 实际锁值: {}", 
                        lockKey, lockValue, currentValue);
            }
        } catch (Exception e) {
            LOG.error("释放分布式锁时发生异常，锁键: {}, 锁值: {}, 错误信息: {}", 
                    lockKey, lockValue, e.getMessage(), e);
        }
    }

    /**
     * 雾报间隔时间
     */
    public Boolean isOverThreeMinute(String curDateStr, String nextDateStr){
        Map<String,String> attrMap = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.ATTRECODEKEY);
        Date begin = DateUtil.toDate(curDateStr,"yyyy-MM-dd HH:mm");
        Date end = DateUtil.toDate(nextDateStr,"yyyy-MM-dd HH:mm");
        long minute=(end.getTime()-begin.getTime())/(1000*60);//除以1000是为了转换成秒
        if(minute<=Integer.parseInt(attrMap.get("predationInterval"))){
            return true;
        }
        return false;
    }

    /**
     * 复制公共字段
     * 所有目标表都有相同的字段结构，除了id（自增）和sync_flag（不需要）
     */
    private boolean copyCommonFields(EquipmentFile source, Object target) {
        if (target instanceof EquipmentFilePCluster) {

            EquipmentFilePCluster cluster = (EquipmentFilePCluster) target;
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
            //cluster.setSm1(source.getSm1());
            cluster.setSm2(source.getSm2());
            cluster.setSm3(source.getSm3());
            cluster.setSm4(source.getSm4());
            cluster.setSm5(source.getSm5());
            cluster.setSm6(source.getSm6());
            cluster.setType(source.getType());
            cluster.setTs(source.getTs());
            cluster.setTxtlx(source.getTxtlx());
            cluster.setWjmc(source.getWjmc());
            /**
             * 添加聚类事件开始和结束信息
             */
            if(!"0".equals(cluster.getTs())&&("1020".equals(cluster.getType())||"1022".equals(cluster.getType())||"1024".equals(cluster.getType())||"1026".equals(cluster.getType()))){
                String temp = cluster.getTplj().substring(cluster.getTplj().lastIndexOf("/")+1,cluster.getTplj().lastIndexOf("_A4.txt"));
                String[] arr = temp.split("_");
                String kssj = arr[0]+"-"+arr[1]+"-"+arr[2]+" "+arr[3]+":"+arr[4]+":"+arr[5];
                String jssj = "1020".equals(cluster.getType())||"1026".equals(cluster.getType())?arr[7]+"-"+arr[8]+"-"+arr[9]+" "+arr[10]+":"+arr[11]+":"+arr[12]:arr[6]+"-"+arr[7]+"-"+arr[8]+" "+arr[9]+":"+arr[10]+":"+arr[11];
                Map<String,String> map = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.ATTRECODEKEY);
                if(map.get("eventsbsn").contains(cluster.getSbbh())){
                    cluster.setKssj(kssj);
                    cluster.setJssj(jssj);
                    cluster.setJtnr(source.getSm1());
                    return true;
                }else {
                    if(!kssj.equals(jssj)){
                        cluster.setKssj(kssj);
                        cluster.setJssj(jssj);
                        cluster.setJtnr(source.getSm1());
                        return true;
                    }
                }
            }
            return false;

        } else if (target instanceof EquipmentFilePPic) {
            EquipmentFilePPic pic = (EquipmentFilePPic) target;
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
            return true;
        } else if (target instanceof EquipmentFilePTxt) {
            EquipmentFilePTxt txt = (EquipmentFilePTxt) target;
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
            return true;
        } else if (target instanceof EquipmentFilePVideo) {
            EquipmentFilePVideo video = (EquipmentFilePVideo) target;
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
            return true;
        } else if (target instanceof EquipmentFilePWav) {
            EquipmentFilePWav wav = (EquipmentFilePWav) target;
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
            return true;
        }
        return true;
    }

    /**
     * 剪切视频（根据图片和聚类去剪切视频）
     * 判断视频是否在zskssj(早上7点)和wsjssj(晚上7点之间)
     * 判断图片类型数据的时间有没有在聚类类型数据之间。如果图片类型的数据和聚类类型的数据有重叠，忽略图片类型的数据
     * 如果是图片类型，就根据开始时间往前推spjqjgsjq分钟，往后推spjqjgsj分钟，作为视频的剪切时间
     * 如果是聚类类型，就根据聚类的开始时间和结束时间作为视频的剪切时间
     */
    public void saveNewEvent(EquipmentFile record){
        try {
            LOG.error("剪切视频数据："+JSONObject.toJSONString(record));
            String[] arr = record.getWjmc().split("_");
            Map<String,String> attrMap = (Map<String, String>) redisTemplate.opsForValue().get(RedisCode.ATTRECODEKEY);
            if(StringUtils.isEmpty(attrMap.get("zskssj")) ||  StringUtils.isEmpty(attrMap.get("wsjssj"))){
                return;
            }
            if(Integer.parseInt(arr[3])<Integer.parseInt(attrMap.get("zskssj")) || Integer.parseInt(arr[3])>Integer.parseInt(attrMap.get("wsjssj"))){
                return;
            }
            String temp = arr[0]+"-"+arr[1]+"-"+arr[2]+" "+arr[3]+":"+arr[4]+":"+arr[5];
            String temJssj = (String) redisTemplate.opsForValue().get("JQ"+record.getSbbh());
            if(StringUtils.isEmpty(temJssj) || !StringUtils.isEmpty(temJssj)&& DateUtil.toDate(temp,"yyyy-MM-dd HH:mm:ss").getTime()>DateUtil.toDate(temJssj,"yyyy-MM-dd HH:mm:ss").getTime()){
                List<CameraInfo> cameraInfoList = cameraInfoService.findBySbbh(record.getSbbh());
                String jssj = "";
                for(CameraInfo cameraInfo: cameraInfoList){
                    CameraMiddleDto cameraMiddle = new CameraMiddleDto();
                    cameraMiddle.setSbbh(record.getSbbh());//设备编号
                    cameraMiddle.setIp(cameraInfo.getIp());//摄像头ip
                    cameraMiddle.setPort(cameraInfo.getPort()+"");//nvr剪切端口
                    cameraMiddle.setUsername(cameraInfo.getUsername());//nvr用户名
                    cameraMiddle.setCamerapws(cameraInfo.getCamerapws());//nvr密码
                    cameraMiddle.setTdh(cameraInfo.getSbdk()+"");//通道号
                    cameraMiddle.setDvrip(cameraInfo.getDvrip());//nvrip
                    if("1002,1003,1005,1006".contains(record.getType())){
                        //cameraMiddle.setJgsj(attrServiceStatic.findByAttrKey("spjqjgsjq"));//视频剪切间隔时间往前推的时间
                        //cameraMiddle.setJgsj(attrServiceStatic.findByAttrKey("spjqjgsj"));//视频剪切间隔时间往后推的时间
                        String kssj = DateUtil.getFormatDate(DateUtil.getSecondLater(DateUtil.toDate(temp,"yyyy-MM-dd HH:mm:ss"),Integer.parseInt(attrMap.get("spjqjgsjq"))),"yyyy-MM-dd HH:mm:ss");
                        jssj = DateUtil.getFormatDate(DateUtil.getSecondLater(DateUtil.toDate(temp,"yyyy-MM-dd HH:mm:ss"),Integer.parseInt(attrMap.get("spjqjgsj"))),"yyyy-MM-dd HH:mm:ss");
                        cameraMiddle.setJqsj(kssj+","+jssj);
                        cameraMiddle.setJgsj("0");
                        //cameraMiddle.setJqsj(arr[0]+"-"+arr[1]+"-"+arr[2]+" "+arr[3]+":"+arr[4]+":"+arr[5]);//剪切时间
                    }else if("1020,1022,1024,1026".contains(record.getType())){
                        arr = record.getTplj().substring(record.getTplj().lastIndexOf("/")+1,record.getTplj().lastIndexOf("_A4.txt")).split("_");
                        jssj = "1020".equals(record.getType())||"1026".equals(record.getType())?arr[7]+"-"+arr[8]+"-"+arr[9]+" "+arr[10]+":"+arr[11]+":"+arr[12]:arr[6]+"-"+arr[7]+"-"+arr[8]+" "+arr[9]+":"+arr[10]+":"+arr[11];
                        if(temp.equals(jssj)){
                            return;
                        }
                        cameraMiddle.setJqsj(temp+","+jssj);
                        cameraMiddle.setJgsj("0");
                    }
                    cameraMiddle.setSfjq("0");
                    cameraMiddle.setBz(cameraInfo.getSm4());//默认预置位
                    if("1002,1003,1005,1006".contains(record.getType())){
                        // 提交一个延迟执行的任务
                        scheduledExecutorService.schedule(() -> {
                            LOG.error("cameraMiddle数据："+cameraMiddle.toString());
                            cameraMiddleService.save(cameraMiddle);
                        }, Integer.parseInt(attrMap.get("spjqjgsj")), TimeUnit.SECONDS);
                    }else{
                        LOG.error("cameraMiddle数据："+cameraMiddle.toString());
                        cameraMiddleService.save(cameraMiddle);
                    }

                }
                redisTemplate.opsForValue().set("JQ"+record.getSbbh(), jssj);
            }else if("1020,1022,1024,1026".contains(record.getType())){
                arr = record.getTplj().substring(record.getTplj().lastIndexOf("/")+1,record.getTplj().lastIndexOf("_A4.txt")).split("_");
                String jssj = "1020".equals(record.getType())||"1026".equals(record.getType())?arr[7]+"-"+arr[8]+"-"+arr[9]+" "+arr[10]+":"+arr[11]+":"+arr[12]:arr[6]+"-"+arr[7]+"-"+arr[8]+" "+arr[9]+":"+arr[10]+":"+arr[11];
                if(StringUtils.isEmpty(temJssj) || !StringUtils.isEmpty(temJssj)&&(DateUtil.toDate(temJssj,"yyyy-MM-dd HH:mm:ss").getTime()-DateUtil.toDate(temp,"yyyy-MM-dd HH:mm:ss").getTime()>5000 && DateUtil.toDate(jssj,"yyyy-MM-dd HH:mm:ss").getTime()-DateUtil.toDate(temJssj,"yyyy-MM-dd HH:mm:ss").getTime()>5000)){
                    List<CameraInfo> cameraInfoList = cameraInfoService.findBySbbh(record.getSbbh());
                    for(CameraInfo cameraInfo: cameraInfoList){
                        CameraMiddleDto cameraMiddle = new CameraMiddleDto();
                        cameraMiddle.setSbbh(record.getSbbh());//设备编号
                        cameraMiddle.setIp(cameraInfo.getIp());//摄像头ip
                        cameraMiddle.setPort(cameraInfo.getPort()+"");//nvr剪切端口
                        cameraMiddle.setUsername(cameraInfo.getUsername());//nvr用户名
                        cameraMiddle.setCamerapws(cameraInfo.getCamerapws());//nvr密码
                        cameraMiddle.setTdh(cameraInfo.getSbdk()+"");//通道号
                        cameraMiddle.setDvrip(cameraInfo.getDvrip());//nvrip
                        cameraMiddle.setJqsj(StringUtils.isEmpty(temJssj)?temp:temJssj+","+jssj);
                        cameraMiddle.setJgsj("0");
                        cameraMiddle.setSfjq("0");
                        cameraMiddle.setBz(cameraInfo.getSm4());//默认预置位
                        LOG.error("cameraMiddle数据："+cameraMiddle.toString());
                        cameraMiddleService.save(cameraMiddle);
                    }
                    redisTemplate.opsForValue().set("JQ"+record.getSbbh(), jssj);
                }
            }
        }catch (Exception e){
            LOG.error("剪切视频数据出错");
        }
    }
}