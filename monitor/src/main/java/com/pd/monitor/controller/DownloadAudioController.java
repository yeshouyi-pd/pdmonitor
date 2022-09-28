package com.pd.monitor.controller;

import com.pd.server.config.RedisCode;
import com.pd.server.main.domain.EquipmentFileTy;
import com.pd.server.main.domain.EquipmentFileTyExample;
import com.pd.server.main.domain.EquipmentTyEvent;
import com.pd.server.main.domain.EquipmentTyEventExample;
import com.pd.server.main.service.EquipmentFileTyService;
import com.pd.server.main.service.EquipmentTyEventService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    private EquipmentTyEventService equipmentTyEventService;
    @Resource
    public RedisTemplate redisTemplate;

    @GetMapping("/downZipById")
    public void downZipById(HttpServletRequest request, HttpServletResponse response) throws Exception{
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
                fileToZip(entity.getTplj().replace("http://146.56.226.176:8088/","C:\\FileInfoApi\\"), zipOut);
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

    @GetMapping("/downAudioFileById")
    public void downAudioFileById(HttpServletRequest request, HttpServletResponse response){
        BufferedInputStream in = null;
        try{
            String id = request.getParameter("id");
            EquipmentTyEvent event = equipmentTyEventService.selectByPrimaryKey(id);
            EquipmentFileTy fileEntity = equipmentFileTyService.selectByPrimaryKey(event.getBz());
            String fileName = fileEntity.getTplj().substring(fileEntity.getTplj().lastIndexOf("/"));
            String fileUrl = fileEntity.getTplj().substring(0,fileEntity.getTplj().lastIndexOf("/")+1);
            String remoteFileUrl = java.net.URLEncoder.encode(fileName, "UTF-8").replace("+","%20");
            if (null == remoteFileUrl || remoteFileUrl.length() == 0) {
                throw new RuntimeException("remoteFileUrl is invalid!");
            }
            URL url = new URL(fileUrl+remoteFileUrl);
            // URLConnection conn = url.openConnection();
            // in = new BufferedInputStream(conn.getInputStream());
            // 这和上面两句一样的效果
            in = new BufferedInputStream(url.openStream());
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
        BufferedInputStream in = null;
        try{
            String fileUrl = request.getParameter("fileUrl");
            String fileName = request.getParameter("fileName");
            String remoteFileUrl = java.net.URLEncoder.encode(fileName, "UTF-8").replace("+","%20");
            if (null == remoteFileUrl || remoteFileUrl.length() == 0) {
                throw new RuntimeException("remoteFileUrl is invalid!");
            }
            URL url = new URL(fileUrl+remoteFileUrl);
            // URLConnection conn = url.openConnection();
            // in = new BufferedInputStream(conn.getInputStream());
            // 这和上面两句一样的效果
            in = new BufferedInputStream(url.openStream());
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

}
