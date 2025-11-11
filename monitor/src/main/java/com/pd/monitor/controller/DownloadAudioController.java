package com.pd.monitor.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ZipUtil;
import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.*;
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
import java.util.*;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/download/audio")
public class DownloadAudioController {
    private static final Logger LOG = LoggerFactory.getLogger(DownloadAudioController.class);
    public static final String BUSINESS_NAME = "下载江豚音频文件";

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
    @Resource
    private EquipmentFilePClusterService equipmentFilePClusterService;
    @Resource
    private WaterProEquipService waterProEquipService;
    @Resource
    private EquipmentFileTClusterService equipmentFileTClusterService;
    @Resource
    private EquipmentFileTVideoService equipmentFileTVideoService;

    /**
     * A4聚类文件下载txt
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping("/downloadEquipmentFileEvent")
    public void downloadEquipmentFileEvent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        EquipmentFilePClusterExample clusterExample = new EquipmentFilePClusterExample();
        EquipmentFilePClusterExample.Criteria ca = clusterExample.createCriteria();
        if(!StringUtils.isEmpty(request.getParameter("stime"))){
            ca.andRqGreaterThanOrEqualTo(request.getParameter("stime"));
        }
        if(!StringUtils.isEmpty(request.getParameter("etime"))){
            ca.andRqLessThanOrEqualTo(request.getParameter("etime"));
        }
        if(!StringUtils.isEmpty(request.getParameter("sbbh"))){
            ca.andSbbhEqualTo(request.getParameter("sbbh"));
        }
        clusterExample.setOrderByClause(" kssj desc ");
        if(!StringUtils.isEmpty(request.getParameter("xmbh"))){
            WaterProEquipExample waterProEquipExample = new WaterProEquipExample();
            waterProEquipExample.createCriteria().andXmbhEqualTo(request.getParameter("xmbh"));
            List<WaterProEquip> waterProEquips = waterProEquipService.selectByExample(waterProEquipExample);
            //根据xmbh 查询关联的设备编号
            if(waterProEquips != null && !waterProEquips.isEmpty()){
                List<String> sbsnList = waterProEquips.stream()
                        .map(WaterProEquip::getSbsn)
                        .filter(sbsn -> !org.apache.commons.lang.StringUtils.isEmpty(sbsn))
                        .collect(Collectors.toList());
                if(!sbsnList.isEmpty()){
                    ca.andSbbhIn(sbsnList);
                }
            }
        }
        List<EquipmentFilePCluster> lists = equipmentFilePClusterService.selectByExampleOnlyId(clusterExample);
        if(!lists.isEmpty()){
            /**
             * 根据ID查询
             */
            List<Long> ids = lists.stream()
                    .map(EquipmentFilePCluster::getId)
                    .collect(Collectors.toList());
            EquipmentFilePClusterExample equipmentFileExampleId = new EquipmentFilePClusterExample();
            equipmentFileExampleId.createCriteria().andIdIn(ids);
            lists = equipmentFilePClusterService.selectByExample(equipmentFileExampleId);
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
        String filePath = attrService.findByAttrKey("filePath");
        for(EquipmentFilePCluster event : lists){
            FileUtil.copyFile(new File(event.getTplj().replaceAll("http://[^/]+", Matcher.quoteReplacement(filePath)).replace("/", "\\")), FileUtil.file("C:/A4File"));
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

    /**
     * 根据文件名称下载视频文件压缩包(A1，A4)
     */
    @GetMapping("/downZipByWjmc")
    public void downZipByWjmc(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("UTF-8");
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
                fileToZip(entity.getTplj().replaceAll("http://[^/]+", Matcher.quoteReplacement(filePath)).replace("/", "\\"), zipOut);
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

    /**
     * 根据聚类id下载视频文件
     */
    @GetMapping("/downZipByA4Id")
    public void downZipByA4Id(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("UTF-8");
        String filePath = attrService.findByAttrKey("filePath");
        Long id = Long.getLong(request.getParameter("id"));
        EquipmentFilePCluster pCluster = equipmentFilePClusterService.selectByPrimaryKey(id);
        EquipmentFilePVideoExample example = new EquipmentFilePVideoExample();
        EquipmentFilePVideoExample.Criteria ca = example.createCriteria();
        ca.andCjsjEqualTo(pCluster.getCjsj());
        List<EquipmentFilePVideo> lists = equipmentFilePVideoService.selectByExample(example);
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
            for (EquipmentFilePVideo pVideo : lists) {
                // 该方法在下面定义
                fileToZip(pVideo.getTplj().replaceAll("http://[^/]+", Matcher.quoteReplacement(filePath)).replace("/", "\\"), zipOut);
            }
            // 压缩完成后,关闭压缩流
            zipOut.close();

            //拼接下载默认名称并转为ISO-8859-1格式
            String fileName = new String((pCluster.getWjmc()+".zip").getBytes(),"ISO-8859-1");
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

    /**
     * 下载拖曳的聚类视频
     */
    @GetMapping("/downZipById")
    public void downZipById(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("UTF-8");
        String filePath = attrService.findByAttrKey("filePath");
        EquipmentFileTCluster tCluster = equipmentFileTClusterService.selectByPrimaryKey(Long.parseLong(request.getParameter("id")));
        EquipmentFileTVideoExample example = new EquipmentFileTVideoExample();
        example.createCriteria().andCjsjEqualTo(tCluster.getCjsj());
        List<EquipmentFileTVideo> lists = equipmentFileTVideoService.selectByExample(example);
        if (lists.size() != 0) {
            // 创建临时路径,存放压缩文件
            String picStorePath = (String) redisTemplate.opsForValue().get(RedisCode.STATICPATH);//静态路径地址
            String zipFilePath = picStorePath+"\\我的zip.zip";
            // 压缩输出流,包装流,将临时文件输出流包装成压缩流,将所有文件输出到这里,打成zip包
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFilePath));
            // 循环调用压缩文件方法,将一个一个需要下载的文件打入压缩文件包
            for (EquipmentFileTVideo entity : lists) {
                // 该方法在下面定义
                fileToZip(entity.getTplj().replaceAll("http://[^/]+", Matcher.quoteReplacement(filePath)).replace("/", "\\"), zipOut);
            }
            // 压缩完成后,关闭压缩流
            zipOut.close();

            //拼接下载默认名称并转为ISO-8859-1格式
            String fileName = new String((tCluster.getWjmc()+".zip").getBytes(),"ISO-8859-1");
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

    /**
     * 批量下载A1,A4音频文件压缩包
     */
    @GetMapping("/downVideoZip")
    public void downVideoZip(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setCharacterEncoding("UTF-8");
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
                fileVideoToZip(entity.getTplj().replaceAll("http://[^/]+", Matcher.quoteReplacement(filePath)).replace("/", "\\"), zipOut, fileNameList);
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

    /**
     * 下载单个聚类文件txt（A2）
     * @param request
     * @param response
     */
    @GetMapping("/downAudioFileById")
    public void downAudioFileById(HttpServletRequest request, HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        String filePath = attrService.findByAttrKey("filePath");
        BufferedInputStream in = null;
        try{
            EquipmentFileTCluster tCluster = equipmentFileTClusterService.selectByPrimaryKey(Long.parseLong(request.getParameter("id")));
            String fileName = tCluster.getTplj().substring(tCluster.getTplj().lastIndexOf("/"));
            in = new BufferedInputStream(new FileInputStream(tCluster.getTplj().replaceAll("http://[^/]+", Matcher.quoteReplacement(filePath)).replace("/", "\\")));
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

    /**
     * 直接根据文件路径下载文件
     */
    @GetMapping("/downAudioFile")
    public void downAudioFile(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        String filePath = attrService.findByAttrKey("filePath");
        BufferedInputStream in = null;
        try{
            String fileName = request.getParameter("fileName");
            String path = request.getParameter("fileUrl")+fileName;
            // HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            // in = new BufferedInputStream(conn.getInputStream());
            // 这和上面两句一样的效果
            in = new BufferedInputStream(new FileInputStream(path.replaceAll("http://[^/]+", Matcher.quoteReplacement(filePath)).replace("/", "\\")));
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

    /**
     * 下载分析视频
     * @param request
     * @param response
     */
    @GetMapping("/downVideo")
    public void downVideo(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        String filePath = attrService.findByAttrKey("filePath");
        BufferedInputStream in = null;
        try{
            String id = request.getParameter("id");
            VideoEvent videoEvent = videoEventService.selectById(id);
            String fileName = videoEvent.getWjlj().substring(videoEvent.getWjlj().lastIndexOf('/'),videoEvent.getWjlj().length());//http://111.38.20.175:7003/tempData/tl003/2024_04_22_10_49_51_2024_04_22_10_59_04_0_A4_TD20.mp4
            in = new BufferedInputStream(new FileInputStream(videoEvent.getWjlj().replaceAll("http://[^/]+", Matcher.quoteReplacement(filePath)).replace("/", "\\")));
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
