package com.pd.monitor.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.http.HttpUtil;
import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.*;
import com.pd.server.main.dto.EquipmentFileEventDto;
import com.pd.server.main.service.*;
import com.pd.server.util.DateUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/download/audio")
public class DownloadAudioController {
    private static final Logger LOG = LoggerFactory.getLogger(DownloadAudioController.class);
    public static final String BUSINESS_NAME = "下载江豚音频文件";

    @Resource
    private EquipmentFileTyService equipmentFileTyService;
    @Resource
    private EquipmentFileService equipmentFileService;
    @Resource
    private EquipmentTyEventService equipmentTyEventService;//A2设备聚类
    @Resource
    private EquipmentFileEventService equipmentFileEventService;//A4设备聚类
    @Resource
    public RedisTemplate redisTemplate;
    @Resource
    public AttrService attrService;
    @Resource
    public VideoEventService videoEventService;

    @Resource
    private EquipmentFilePVideoService equipmentFilePVideoService;

    @Resource
    private EquipmentFilePWavService equipmentFilePWavService;

    /**
     * A4聚类文件下载
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping("/downloadEquipmentFileEvent")
    public void downloadEquipmentFileEvent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        EquipmentFileEventExample equipmentFileEventExample = new EquipmentFileEventExample();
        EquipmentFileEventExample.Criteria ca = equipmentFileEventExample.createCriteria();
        EquipmentFileEventDto record = new EquipmentFileEventDto();
        record.setXmbh(request.getParameter("xmbh"));
        if(!StringUtils.isEmpty(request.getParameter("sbbh"))){
            ca.andSbbhEqualTo(request.getParameter("sbbh"));
            record.setSbbh(request.getParameter("sbbh"));
        }
        if(!StringUtils.isEmpty(request.getParameter("stime"))){
            ca.andRqGreaterThanOrEqualTo(request.getParameter("stime"));
            record.setStime(request.getParameter("stime"));
        }
        if(!StringUtils.isEmpty(request.getParameter("etime"))){
            ca.andRqLessThanOrEqualTo(request.getParameter("etime"));
            record.setEtime(request.getParameter("etime"));
        }
        equipmentFileEventExample.setOrderByClause(" kssj desc ");
        List<EquipmentFileEvent> equipmentFileEventList = new ArrayList<>();
        if(!StringUtils.isEmpty(request.getParameter("xmbh"))){
            equipmentFileEventList = equipmentFileEventService.selectByExampleSpecial(record);
        }else{
            equipmentFileEventList = equipmentFileEventService.list(equipmentFileEventExample);
        }
        // 目标文件夹a
        File destDir = new File("C:/A4File");
        // 确保目标文件夹存在
        if (!destDir.exists()) {
            destDir.mkdirs();
        }else{
            deleteFiles(destDir);
            destDir.mkdir();
        }
        for(EquipmentFileEvent event : equipmentFileEventList){
            EquipmentFile equipmentFile = equipmentFileService.selectByPrimaryKey(event.getEquipmentFileId());
            if(equipmentFile!=null&&!StringUtils.isEmpty(equipmentFile.getTplj())){
                HttpUtil.downloadFile(equipmentFile.getTplj(), FileUtil.file("C:/A4File"));
            }
        }
        ZipUtil.zip("C:/A4File");
        //拼接下载默认名称并转为ISO-8859-1格式
        response.setHeader("Content-Disposition", "attchment;filename=A4File.zip");
        //该流不可以手动关闭,手动关闭下载会出问题,下载完成后会自动关闭
        ServletOutputStream outputStream = response.getOutputStream();
        FileInputStream inputStream = new FileInputStream("C:/A4File.zip");
        // 如果是SpringBoot框架,在这个路径
        // org.apache.tomcat.util.http.fileupload.IOUtils产品
        // 否则需要自主引入apache的 commons-io依赖
        // copy方法为文件复制,在这里直接实现了下载效果
        IOUtils.copy(inputStream, outputStream);
        // 关闭输入流
        inputStream.close();
    }

    public static void deleteFiles(File folder) {
        Queue<File> queue = new ArrayDeque<>();
        queue.add(folder);

        while (!queue.isEmpty()) {
            File current = queue.poll();
            File[] files = current.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        queue.add(file); // 将子文件夹添加到队列中
                    } else {
                        file.delete(); // 删除文件
                    }
                }
            }
        }
    }


    @GetMapping("/downZipByWjmc")
    public void downZipByWjmc(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("UTF-8");
        String fileUrl = attrService.findByAttrKey("fileUrl");//http://49.239.193.146:8082/
        String filePath = attrService.findByAttrKey("filePath");
        String wjmc = request.getParameter("wjmc");
        EquipmentFilePVideoExample example = new EquipmentFilePVideoExample();
        EquipmentFilePVideoExample.Criteria ca = example.createCriteria();
        ca.andWjmcEqualTo(wjmc);
        List<EquipmentFilePVideo> lists = equipmentFilePVideoService.listAll(example);
        if (lists.size() != 0) {
            // 创建临时路径,存放压缩文件
            String zipFilePath = attrService.findByAttrKey("downloadPath")+"\\我的zip.zip";
            // 压缩输出流,包装流,将临时文件输出流包装成压缩流,将所有文件输出到这里,打成zip包
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFilePath));
            // 循环调用压缩文件方法,将一个一个需要下载的文件打入压缩文件包
            for (EquipmentFilePVideo entity : lists) {
                // 该方法在下面定义
                fileToZip(entity.getTplj().replace(fileUrl,filePath), zipOut);
            }
            // 压缩完成后,关闭压缩流
            zipOut.close();

            //拼接下载默认名称并转为ISO-8859-1格式
            String fileName = new String((wjmc+".zip").getBytes(),"ISO-8859-1");
            response.setHeader("Content-Disposition", "attchment;filename="+fileName);
            //该流不可以手动关闭,手动关闭下载会出问题,下载完成后会自动关闭
            ServletOutputStream outputStream = response.getOutputStream();
            FileInputStream inputStream = new FileInputStream(zipFilePath);
            // 如果是SpringBoot框架,在这个路径
            // org.apache.tomcat.util.http.fileupload.IOUtils产品
            // 否则需要自主引入apache的 commons-io依赖
            // copy方法为文件复制,在这里直接实现了下载效果
            IOUtils.copy(inputStream, outputStream);
            // 关闭输入流
            inputStream.close();
            //下载完成之后，删掉这个zip包
            File fileTempZip = new File(zipFilePath);
            fileTempZip.delete();
        }else {
            String result = "未找到对应视频";
            response.getOutputStream().write(result.getBytes());
            response.getOutputStream().close();
        }
    }

    @GetMapping("/downZipByA4Id")
    public void downZipByA4Id(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("UTF-8");
        String fileUrl = attrService.findByAttrKey("fileUrl");//http://49.239.193.146:8082/
        String filePath = attrService.findByAttrKey("filePath");
        String id = request.getParameter("id");
        EquipmentFileEvent event = equipmentFileEventService.selectByPrimaryKey(id);
        EquipmentFile fileEntity = equipmentFileService.selectByPrimaryKey(event.getEquipmentFileId());
        EquipmentFileExample example = new EquipmentFileExample();
        EquipmentFileExample.Criteria ca = example.createCriteria();
        ca.andWjmcEqualTo(fileEntity.getWjmc());
        ca.andWjlxEqualTo("4");
        List<EquipmentFile> lists = equipmentFileService.listAll(example);
        // 此处模拟处理ids,拿到文件下载url
//        List<String> paths = new ArrayList<>();
//        paths.add("C:\\Users\\Administrator\\Desktop\\2022_09_28_09_24_06_2022_09_28_09_24_08_1_A2_TD34.mp4");
//        paths.add("C:\\Users\\Administrator\\Desktop\\2022_09_28_09_24_06_2022_09_28_09_24_08_1_A2_TD33.mp4");
        if (lists.size() != 0) {
            // 创建临时路径,存放压缩文件
            String zipFilePath = attrService.findByAttrKey("downloadPath")+"\\我的zip.zip";
            // 压缩输出流,包装流,将临时文件输出流包装成压缩流,将所有文件输出到这里,打成zip包
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFilePath));
            // 循环调用压缩文件方法,将一个一个需要下载的文件打入压缩文件包
            for (EquipmentFile entity : lists) {
                // 该方法在下面定义
                fileToZip(entity.getTplj().replace(fileUrl,filePath), zipOut);
            }
            // 压缩完成后,关闭压缩流
            zipOut.close();

            //拼接下载默认名称并转为ISO-8859-1格式
            String fileName = new String((fileEntity.getWjmc()+".zip").getBytes(),"ISO-8859-1");
            response.setHeader("Content-Disposition", "attchment;filename="+fileName);

            //该流不可以手动关闭,手动关闭下载会出问题,下载完成后会自动关闭
            ServletOutputStream outputStream = response.getOutputStream();
            FileInputStream inputStream = new FileInputStream(zipFilePath);
            // 如果是SpringBoot框架,在这个路径
            // org.apache.tomcat.util.http.fileupload.IOUtils产品
            // 否则需要自主引入apache的 commons-io依赖
            // copy方法为文件复制,在这里直接实现了下载效果
            IOUtils.copy(inputStream, outputStream);

            // 关闭输入流
            inputStream.close();

            //下载完成之后，删掉这个zip包
            File fileTempZip = new File(zipFilePath);
            fileTempZip.delete();
        }else {
            String result = "未找到对应视频";
            response.getOutputStream().write(result.getBytes());
            response.getOutputStream().close();
        }

    }

    @GetMapping("/downZipByA4Id53")
    public void downZipByA4Id53(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        EquipmentFileEvent event = equipmentFileEventService.selectByPrimaryKey(id);
        EquipmentFile fileEntity = equipmentFileService.selectByPrimaryKey(event.getEquipmentFileId());
        EquipmentFileExample example = new EquipmentFileExample();
        EquipmentFileExample.Criteria ca = example.createCriteria();
        ca.andWjmcEqualTo(fileEntity.getWjmc());
        ca.andWjlxEqualTo("4");
        List<EquipmentFile> lists = equipmentFileService.listAll(example);
        // 此处模拟处理ids,拿到文件下载url
//        List<String> paths = new ArrayList<>();
//        paths.add("C:\\Users\\Administrator\\Desktop\\2022_09_28_09_24_06_2022_09_28_09_24_08_1_A2_TD34.mp4");
//        paths.add("C:\\Users\\Administrator\\Desktop\\2022_09_28_09_24_06_2022_09_28_09_24_08_1_A2_TD33.mp4");
        if (lists.size() != 0) {
            // 创建临时路径,存放压缩文件
            String picStorePath = (String) redisTemplate.opsForValue().get(RedisCode.STATICPATH);//静态路径地址
            String zipFilePath = picStorePath+"\\我的zip.zip";
            // 压缩输出流,包装流,将临时文件输出流包装成压缩流,将所有文件输出到这里,打成zip包
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFilePath));
            // 循环调用压缩文件方法,将一个一个需要下载的文件打入压缩文件包
            for (EquipmentFile entity : lists) {
                // 该方法在下面定义
                fileToZip53(entity.getTplj(), zipOut);
            }
            // 压缩完成后,关闭压缩流
            zipOut.close();

            //拼接下载默认名称并转为ISO-8859-1格式
            String fileName = new String((fileEntity.getWjmc()+".zip").getBytes(),"ISO-8859-1");
            response.setHeader("Content-Disposition", "attchment;filename="+fileName);

            //该流不可以手动关闭,手动关闭下载会出问题,下载完成后会自动关闭
            ServletOutputStream outputStream = response.getOutputStream();
            FileInputStream inputStream = new FileInputStream(zipFilePath);
            // 如果是SpringBoot框架,在这个路径
            // org.apache.tomcat.util.http.fileupload.IOUtils产品
            // 否则需要自主引入apache的 commons-io依赖
            // copy方法为文件复制,在这里直接实现了下载效果
            IOUtils.copy(inputStream, outputStream);

            // 关闭输入流
            inputStream.close();

            //下载完成之后，删掉这个zip包
            File fileTempZip = new File(zipFilePath);
            fileTempZip.delete();
        }else {
            String result = "未找到对应视频";
            response.getOutputStream().write(result.getBytes());
            response.getOutputStream().close();
        }

    }

    @GetMapping("/downZipById")
    public void downZipById(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("UTF-8");
        String fileUrl = attrService.findByAttrKey("fileUrl");//http://49.239.193.146:8082/
        String filePath = attrService.findByAttrKey("filePath");
        String id = request.getParameter("id");
        EquipmentTyEvent event = equipmentTyEventService.selectByPrimaryKey(id);
        EquipmentFileTy fileEntity = equipmentFileTyService.selectByPrimaryKey(event.getBz());
        EquipmentFileTyExample example = new EquipmentFileTyExample();
        EquipmentFileTyExample.Criteria ca = example.createCriteria();
        ca.andWjmcEqualTo(fileEntity.getWjmc());
        ca.andWjlxEqualTo("4");
        List<EquipmentFileTy> lists = equipmentFileTyService.selectByExample(example);
        // 此处模拟处理ids,拿到文件下载url
//        List<String> paths = new ArrayList<>();
//        paths.add("C:\\Users\\Administrator\\Desktop\\2022_09_28_09_24_06_2022_09_28_09_24_08_1_A2_TD34.mp4");
//        paths.add("C:\\Users\\Administrator\\Desktop\\2022_09_28_09_24_06_2022_09_28_09_24_08_1_A2_TD33.mp4");
        if (lists.size() != 0) {
            // 创建临时路径,存放压缩文件
            String picStorePath = (String) redisTemplate.opsForValue().get(RedisCode.STATICPATH);//静态路径地址
            String zipFilePath = picStorePath+"\\我的zip.zip";
            // 压缩输出流,包装流,将临时文件输出流包装成压缩流,将所有文件输出到这里,打成zip包
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFilePath));
            // 循环调用压缩文件方法,将一个一个需要下载的文件打入压缩文件包
            for (EquipmentFileTy entity : lists) {
                // 该方法在下面定义
                fileToZip(entity.getTplj().replace(fileUrl,filePath), zipOut);
            }
            // 压缩完成后,关闭压缩流
            zipOut.close();

            //拼接下载默认名称并转为ISO-8859-1格式
            String fileName = new String((fileEntity.getWjmc()+".zip").getBytes(),"ISO-8859-1");
            response.setHeader("Content-Disposition", "attchment;filename="+fileName);

            //该流不可以手动关闭,手动关闭下载会出问题,下载完成后会自动关闭
            ServletOutputStream outputStream = response.getOutputStream();
            FileInputStream inputStream = new FileInputStream(zipFilePath);
            // 如果是SpringBoot框架,在这个路径
            // org.apache.tomcat.util.http.fileupload.IOUtils产品
            // 否则需要自主引入apache的 commons-io依赖
            // copy方法为文件复制,在这里直接实现了下载效果
            IOUtils.copy(inputStream, outputStream);

            // 关闭输入流
            inputStream.close();

            //下载完成之后，删掉这个zip包
            File fileTempZip = new File(zipFilePath);
            fileTempZip.delete();
        }else {
            String result = "未找到对应视频";
            response.getOutputStream().write(result.getBytes());
            response.getOutputStream().close();
        }

    }

    public static void fileToZip(String filePath,ZipOutputStream zipOut) throws IOException {
        // 需要压缩的文件
        File file = new File(filePath);
        // 获取文件名称,如果有特殊命名需求,可以将参数列表拓展,传fileName
        String fileName = file.getName();
        FileInputStream fileInput = new FileInputStream(filePath);
        // 缓冲
        byte[] bufferArea = new byte[1024 * 10];
        BufferedInputStream bufferStream = new BufferedInputStream(fileInput, 1024 * 10);
        // 将当前文件作为一个zip实体写入压缩流,fileName代表压缩文件中的文件名称
        zipOut.putNextEntry(new ZipEntry(fileName));
        int length = 0;
        // 最常规IO操作,不必紧张
        while ((length = bufferStream.read(bufferArea, 0, 1024 * 10)) != -1) {
            zipOut.write(bufferArea, 0, length);
        }
        //关闭流
        fileInput.close();
        // 需要注意的是缓冲流必须要关闭流,否则输出无效
        bufferStream.close();
        // 压缩流不必关闭,使用完后再关
    }

    @GetMapping("/downVideoZip")
    public void downVideoZip(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("UTF-8");
        String fileUrl = attrService.findByAttrKey("fileUrl");//http://49.239.193.146:8082/
        String filePath = attrService.findByAttrKey("filePath");
        String sbbh = request.getParameter("sbbh");
        String stime = request.getParameter("stime");
        String etime = request.getParameter("etime");
        EquipmentFilePWavExample example = new EquipmentFilePWavExample();
        EquipmentFilePWavExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(sbbh)){
            ca.andSbbhEqualTo(sbbh);
        }
        if(!StringUtils.isEmpty(stime)){
            ca.andRqGreaterThanOrEqualTo(stime);
        }else{
            ca.andRqGreaterThanOrEqualTo(DateUtil.getFormatDate(new Date(),"yyyy-MM-dd"));
        }
        if(!StringUtils.isEmpty(etime)){
            ca.andRqLessThanOrEqualTo(etime);
        }else{
            ca.andRqLessThanOrEqualTo(DateUtil.getFormatDate(new Date(),"yyyy-MM-dd"));
        }
        List<EquipmentFilePWav> lists = equipmentFilePWavService.listAll(example);
        if (lists.size() != 0) {
            // 创建临时路径,存放压缩文件
            String picStorePath = (String) redisTemplate.opsForValue().get(RedisCode.STATICPATH);//静态路径地址
            String zipFilePath = picStorePath+"\\videoZip.zip";
            // 压缩输出流,包装流,将临时文件输出流包装成压缩流,将所有文件输出到这里,打成zip包
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFilePath));
            // 循环调用压缩文件方法,将一个一个需要下载的文件打入压缩文件包
            List<String> fileNameList = new ArrayList<>();
            for (EquipmentFilePWav entity : lists) {
                // 该方法在下面定义
                fileVideoToZip(entity.getTplj().replace(fileUrl,filePath), zipOut, fileNameList);
            }
            // 压缩完成后,关闭压缩流
            zipOut.close();
            //拼接下载默认名称并转为ISO-8859-1格式
            String fileName = new String(("video"+new Date().getTime() +".zip").getBytes(),"ISO-8859-1");
            response.setHeader("Content-Disposition", "attchment;filename="+fileName);
            //该流不可以手动关闭,手动关闭下载会出问题,下载完成后会自动关闭
            ServletOutputStream outputStream = response.getOutputStream();
            FileInputStream inputStream = new FileInputStream(zipFilePath);
            // 如果是SpringBoot框架,在这个路径
            // org.apache.tomcat.util.http.fileupload.IOUtils产品
            // 否则需要自主引入apache的 commons-io依赖
            // copy方法为文件复制,在这里直接实现了下载效果
            IOUtils.copy(inputStream, outputStream);
            // 关闭输入流
            inputStream.close();
            //下载完成之后，删掉这个zip包
            File fileTempZip = new File(zipFilePath);
            fileTempZip.delete();
        }else {
            String result = "未找到对应数据";
            response.getOutputStream().write(result.getBytes());
            response.getOutputStream().close();
        }

    }

    public static void fileVideoToZip(String filePath,ZipOutputStream zipOut,List<String> fileNameList) throws IOException {
        // 需要压缩的文件
        File file = new File(filePath);
        // 获取文件名称,如果有特殊命名需求,可以将参数列表拓展,传fileName
        String fileName = file.getName();
        if(fileNameList.contains(fileName)){
            return;
        }
        fileNameList.add(fileName);
        FileInputStream fileInput = new FileInputStream(filePath);
        // 缓冲
        byte[] bufferArea = new byte[1024 * 10];
        BufferedInputStream bufferStream = new BufferedInputStream(fileInput, 1024 * 10);
        // 将当前文件作为一个zip实体写入压缩流,fileName代表压缩文件中的文件名称
        zipOut.putNextEntry(new ZipEntry(fileName));
        int length = 0;
        // 最常规IO操作,不必紧张
        while ((length = bufferStream.read(bufferArea, 0, 1024 * 10)) != -1) {
            zipOut.write(bufferArea, 0, length);
        }
        //关闭流
        fileInput.close();
        // 需要注意的是缓冲流必须要关闭流,否则输出无效
        bufferStream.close();
        // 压缩流不必关闭,使用完后再关
    }

    @GetMapping("/downVideoZip53")
    public void downVideoZip53(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("UTF-8");
        String sbbh = request.getParameter("sbbh");
        String stime = request.getParameter("stime");
        String etime = request.getParameter("etime");
        EquipmentFileExample example = new EquipmentFileExample();
        EquipmentFileExample.Criteria ca = example.createCriteria();
        if(!StringUtils.isEmpty(sbbh)){
            ca.andSbbhEqualTo(sbbh);
        }
        if(!StringUtils.isEmpty(stime)){
            ca.andRqGreaterThanOrEqualTo(stime);
        }else{
            ca.andRqGreaterThanOrEqualTo(DateUtil.getFormatDate(new Date(),"yyyy-MM-dd"));
        }
        if(!StringUtils.isEmpty(etime)){
            ca.andRqLessThanOrEqualTo(etime);
        }else{
            ca.andRqLessThanOrEqualTo(DateUtil.getFormatDate(new Date(),"yyyy-MM-dd"));
        }
        ca.andWjlxEqualTo("2");
        List<EquipmentFile> lists = equipmentFileService.listAll(example);
        if (lists.size() != 0) {
            // 创建临时路径,存放压缩文件
            String picStorePath = (String) redisTemplate.opsForValue().get(RedisCode.STATICPATH);//静态路径地址
            String zipFilePath = picStorePath+"\\videoZip.zip";
            //打包文件夹
            String tempFilePath = "C:/video"+new Date().getTime();
            FileUtil.mkdir(tempFilePath);
            for(EquipmentFile entity: lists){
                HttpUtil.downloadFile(entity.getTplj(), FileUtil.file(tempFilePath));
            }
            ZipUtil.zip(tempFilePath, zipFilePath);
            //拼接下载默认名称并转为ISO-8859-1格式
            String fileName = new String(("video"+new Date().getTime() +".zip").getBytes(),"ISO-8859-1");
            response.setHeader("Content-Disposition", "attchment;filename="+fileName);
            //该流不可以手动关闭,手动关闭下载会出问题,下载完成后会自动关闭
            ServletOutputStream outputStream = response.getOutputStream();
            FileInputStream inputStream = new FileInputStream(zipFilePath);
            // 如果是SpringBoot框架,在这个路径
            // org.apache.tomcat.util.http.fileupload.IOUtils产品
            // 否则需要自主引入apache的 commons-io依赖
            // copy方法为文件复制,在这里直接实现了下载效果
            IOUtils.copy(inputStream, outputStream);
            // 关闭输入流
            inputStream.close();
            //下载完成之后，删掉文件夹及目录
            FileUtil.del(zipFilePath);
            FileUtil.del(tempFilePath);
        }else {
            String result = "未找到对应数据";
            response.getOutputStream().write(result.getBytes());
            response.getOutputStream().close();
        }

    }

    public static void fileVideoToZip53(String filePath,ZipOutputStream zipOut,List<String> fileNameList) throws IOException {
        // 需要压缩的文件
        // File file = new File(filePath);
        URL url = new URL(filePath);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setConnectTimeout(30000);
        conn.setReadTimeout(30000);
        // 获取文件名称,如果有特殊命名需求,可以将参数列表拓展,传fileName
        String fileName = filePath.substring(filePath.lastIndexOf("/")+1);
        if(fileNameList.contains(fileName)){
            return;
        }
        fileNameList.add(fileName);
        // FileInputStream fileInput = new FileInputStream(filePath);
        // 缓冲
        byte[] bufferArea = new byte[1024 * 10];
        BufferedInputStream bufferStream = new BufferedInputStream(conn.getInputStream(), 1024 * 10);
        // 将当前文件作为一个zip实体写入压缩流,fileName代表压缩文件中的文件名称
        zipOut.putNextEntry(new ZipEntry(fileName));
        int length = 0;
        // 最常规IO操作,不必紧张
        while ((length = bufferStream.read(bufferArea, 0, 1024 * 10)) != -1) {
            zipOut.write(bufferArea, 0, length);
        }
        //关闭流
        // fileInput.close();
        // 需要注意的是缓冲流必须要关闭流,否则输出无效
        bufferStream.close();
        // 压缩流不必关闭,使用完后再关
    }

    @GetMapping("/downZipById53")
    public void downZipById53(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        EquipmentTyEvent event = equipmentTyEventService.selectByPrimaryKey(id);
        EquipmentFileTy fileEntity = equipmentFileTyService.selectByPrimaryKey(event.getBz());
        EquipmentFileTyExample example = new EquipmentFileTyExample();
        EquipmentFileTyExample.Criteria ca = example.createCriteria();
        ca.andWjmcEqualTo(fileEntity.getWjmc());
        ca.andWjlxEqualTo("4");
        List<EquipmentFileTy> lists = equipmentFileTyService.selectByExample(example);
        if (lists.size() != 0) {
            // 创建临时路径,存放压缩文件
            String picStorePath = (String) redisTemplate.opsForValue().get(RedisCode.STATICPATH);//静态路径地址
            String zipFilePath = picStorePath+"\\我的zip.zip";
            // 压缩输出流,包装流,将临时文件输出流包装成压缩流,将所有文件输出到这里,打成zip包
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFilePath));
            // 循环调用压缩文件方法,将一个一个需要下载的文件打入压缩文件包
            for (EquipmentFileTy entity : lists) {
                // 该方法在下面定义
                fileToZip53(entity.getTplj(), zipOut);
            }
            // 压缩完成后,关闭压缩流
            zipOut.close();

            //拼接下载默认名称并转为ISO-8859-1格式
            String fileName = new String((fileEntity.getWjmc()+".zip").getBytes(),"ISO-8859-1");
            response.setHeader("Content-Disposition", "attchment;filename="+fileName);

            //该流不可以手动关闭,手动关闭下载会出问题,下载完成后会自动关闭
            ServletOutputStream outputStream = response.getOutputStream();
            FileInputStream inputStream = new FileInputStream(zipFilePath);
            // 如果是SpringBoot框架,在这个路径
            // org.apache.tomcat.util.http.fileupload.IOUtils产品
            // 否则需要自主引入apache的 commons-io依赖
            // copy方法为文件复制,在这里直接实现了下载效果
            IOUtils.copy(inputStream, outputStream);

            // 关闭输入流
            inputStream.close();

            //下载完成之后，删掉这个zip包
            File fileTempZip = new File(zipFilePath);
            fileTempZip.delete();
        }else {
            String result = "未找到对应视频";
            response.getOutputStream().write(result.getBytes());
            response.getOutputStream().close();
        }

    }

    public static void fileToZip53(String filePath,ZipOutputStream zipOut) throws IOException {
        // 需要压缩的文件
        // File file = new File(filePath);
        URL url = new URL(filePath);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setConnectTimeout(30000);
        conn.setReadTimeout(30000);
        // 获取文件名称,如果有特殊命名需求,可以将参数列表拓展,传fileName
        String fileName = filePath.substring(filePath.lastIndexOf("/")+1);
        // FileInputStream fileInput = new FileInputStream(filePath);
        // 缓冲
        byte[] bufferArea = new byte[1024 * 10];
        BufferedInputStream bufferStream = new BufferedInputStream(conn.getInputStream(), 1024 * 10);
        // 将当前文件作为一个zip实体写入压缩流,fileName代表压缩文件中的文件名称
        zipOut.putNextEntry(new ZipEntry(fileName));
        int length = 0;
        // 最常规IO操作,不必紧张
        while ((length = bufferStream.read(bufferArea, 0, 1024 * 10)) != -1) {
            zipOut.write(bufferArea, 0, length);
        }
        //关闭流
        // fileInput.close();
        // 需要注意的是缓冲流必须要关闭流,否则输出无效
        bufferStream.close();
        // 压缩流不必关闭,使用完后再关
    }

    @GetMapping("/downAudioFileById")
    public void downAudioFileById(HttpServletRequest request, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        String fileUrl = attrService.findByAttrKey("fileUrl");//http://49.239.193.146:8082/
        String filePath = attrService.findByAttrKey("filePath");
        BufferedInputStream in = null;
        try{
            String id = request.getParameter("id");
            EquipmentTyEvent event = equipmentTyEventService.selectByPrimaryKey(id);
            EquipmentFileTy fileEntity = equipmentFileTyService.selectByPrimaryKey(event.getBz());
            String fileName = fileEntity.getTplj().substring(fileEntity.getTplj().lastIndexOf("/"));
            in = new BufferedInputStream(new FileInputStream(fileEntity.getTplj().replace(fileUrl,filePath)));
            // 这和上面两句一样的效果
            //in = new BufferedInputStream(url.openStream());
            response.reset();
            response.setContentType("application/octet-stream");
            fileName = new String(fileName.getBytes(), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment; filename="+fileName);
            // 将网络输入流转换为输出流
            int i;
            while ((i = in.read()) != -1) {
                response.getOutputStream().write(i);
            }
            in.close();
            response.getOutputStream().close();
        }catch (IOException e){
            try{
                String result = "未找到该文件";
                response.getOutputStream().write(result.getBytes());
                in.close();
                response.getOutputStream().close();
            }catch (IOException exception){
                System.out.println("关闭流失败");
            }
        }
    }

    @GetMapping("/downAudioFileById53")
    public void downAudioFileById53(HttpServletRequest request, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        BufferedInputStream in = null;
        try{
            String id = request.getParameter("id");
            EquipmentTyEvent event = equipmentTyEventService.selectByPrimaryKey(id);
            EquipmentFileTy fileEntity = equipmentFileTyService.selectByPrimaryKey(event.getBz());
            String fileName = fileEntity.getTplj().substring(fileEntity.getTplj().lastIndexOf("/"));
            URL url = new URL(fileEntity.getTplj());
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            in = new BufferedInputStream(conn.getInputStream());
            // 这和上面两句一样的效果
            //in = new BufferedInputStream(url.openStream());
            response.reset();
            response.setContentType("application/octet-stream");
            fileName = new String(fileName.getBytes(), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment; filename="+fileName);
            // 将网络输入流转换为输出流
            int i;
            while ((i = in.read()) != -1) {
                response.getOutputStream().write(i);
            }
            in.close();
            response.getOutputStream().close();
        }catch (IOException e){
            try{
                String result = "未找到该文件";
                response.getOutputStream().write(result.getBytes());
                in.close();
                response.getOutputStream().close();
            }catch (IOException exception){
                System.out.println("关闭流失败");
            }
        }
    }

    @GetMapping("/downAudioFile")
    public void downAudioFile(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        String fileUrl = attrService.findByAttrKey("fileUrl");//http://49.239.193.146:8082/
        String filePath = attrService.findByAttrKey("filePath");
        BufferedInputStream in = null;
        try{
            String fileName = request.getParameter("fileName");
            String path = request.getParameter("fileUrl")+fileName;
            // HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            // in = new BufferedInputStream(conn.getInputStream());
            // 这和上面两句一样的效果
            in = new BufferedInputStream(new FileInputStream(path.replace(fileUrl,filePath)));
            response.reset();
            response.setContentType("application/octet-stream");
            fileName = new String(fileName.getBytes(), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment; filename="+fileName);
            // 将网络输入流转换为输出流
            int i;
            while ((i = in.read()) != -1) {
                response.getOutputStream().write(i);
            }
            in.close();
            response.getOutputStream().close();
        }catch (IOException e){
            try{
                String result = "未找到该文件";
                response.getOutputStream().write(result.getBytes());
                in.close();
                response.getOutputStream().close();
            }catch (IOException exception){
                System.out.println("关闭流失败");
            }
        }
    }

    @GetMapping("/downAudioFile53")
    public void downAudioFile53(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        String fileUrl = attrService.findByAttrKey("fileUrl");//http://49.239.193.146:8082/
        String filePath = attrService.findByAttrKey("filePath");
        BufferedInputStream in = null;
        try{
            String fileName = request.getParameter("fileName");
            String path = request.getParameter("fileUrl")+fileName;
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            in = new BufferedInputStream(conn.getInputStream());
            // 这和上面两句一样的效果
            // in = new BufferedInputStream(new FileInputStream(path.replace(fileUrl,filePath).replace("http:\\","http:\\\\")));
            response.reset();
            response.setContentType("application/octet-stream");
            fileName = new String(fileName.getBytes(), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment; filename="+fileName);
            // 将网络输入流转换为输出流
            int i;
            while ((i = in.read()) != -1) {
                response.getOutputStream().write(i);
            }
            in.close();
            response.getOutputStream().close();
        }catch (IOException e){
            try{
                String result = "未找到该文件";
                response.getOutputStream().write(result.getBytes());
                in.close();
                response.getOutputStream().close();
            }catch (IOException exception){
                System.out.println("关闭流失败");
            }
        }
    }

    /**
     * 下载文件
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/downloadFile")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String wjlj = request.getParameter("wjlj");
        System.out.println(wjlj);
        String fileName = wjlj.substring(wjlj.lastIndexOf("/")+1);
        BufferedInputStream in = null;
        in = new BufferedInputStream(new FileInputStream(wjlj));
        response.reset();
        response.setContentType("application/octet-stream");
        fileName = new String(fileName.getBytes(), "ISO-8859-1");
        response.setHeader("Content-Disposition", "attachment; filename="+fileName);
        // 将网络输入流转换为输出流
        int i;
        while ((i = in.read()) != -1) {
            response.getOutputStream().write(i);
        }
        in.close();
        response.getOutputStream().close();
    }


    @GetMapping("/downVideo")
    public void downVideo(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        String filePath = attrService.findByAttrKey("filePath");
        BufferedInputStream in = null;
        try{
            String id = request.getParameter("id");
            VideoEvent videoEvent = videoEventService.selectById(id);
            String fileName = videoEvent.getWjlj().substring(videoEvent.getWjlj().lastIndexOf('/'),videoEvent.getWjlj().length());//http://111.38.20.175:7003/tempData/tl003/2024_04_22_10_49_51_2024_04_22_10_59_04_0_A4_TD20.mp4
            in = new BufferedInputStream(new FileInputStream(videoEvent.getWjlj().replace("http://49.239.193.146:59088/",filePath).replace("http://49.239.193.146:49082/",filePath).replace("http://111.38.20.175:7003/",filePath)));
            response.reset();
            response.setContentType("application/octet-stream");
            fileName = new String(fileName.getBytes(), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment; filename="+fileName);
            // 将网络输入流转换为输出流
            int i;
            while ((i = in.read()) != -1) {
                response.getOutputStream().write(i);
            }
            in.close();
            response.getOutputStream().close();
        }catch (IOException e){
            try{
                String result = "未找到该文件";
                response.getOutputStream().write(result.getBytes());
                in.close();
                response.getOutputStream().close();
            }catch (IOException exception){
                System.out.println("关闭流失败");
            }
        }
    }
}
