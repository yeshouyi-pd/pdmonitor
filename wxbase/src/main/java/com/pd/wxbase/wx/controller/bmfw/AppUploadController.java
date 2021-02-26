package com.pd.wxbase.wx.controller.bmfw;

import com.pd.server.config.CodeType;
import com.pd.server.main.dto.ResponseDto;
import com.pd.server.main.dto.WebchatFileinfoDto;
import com.pd.server.main.service.WebchatFileinfoService;
import com.pd.wxbase.wx.conf.WxRedisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/appupload")
public class AppUploadController {

    private static final Logger LOG = LoggerFactory.getLogger(AppUploadController.class);
    public static final String BUSINESS_NAME = "文件上传(视频,图片)";

    @Resource
    private WebchatFileinfoService webchatFileinfoService;

    /**
     * 通用上传图片和视频
     * @param file
     * @param lsh
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload")
    public ResponseDto handleFileUpload(@RequestParam("file") MultipartFile file, String lsh) throws IOException {
        ResponseDto responseDto = new ResponseDto();
        System.out.println("文件类型：" + file.getContentType());
        System.out.println("文件名称：" + file.getOriginalFilename());
        System.out.println("文件大小:" + file.getSize());
        //将文件上传到服务器
        String fileDriectory = WxRedisConfig.createFileDriectory(WxRedisConfig.getPicStorePath());
        String fileName = file.getOriginalFilename();
        //String filehz = fileName.substring(fileName.lastIndexOf("."));
        String filehz = ".jpg";
        String filename = lsh + "_" + new Date().getTime() + filehz;
        file.transferTo(new File(fileDriectory + filename));
        WebchatFileinfoDto fileinfo = new WebchatFileinfoDto();
        fileinfo.setLsh(lsh);
        fileinfo.setZplj(WxRedisConfig.fileStr + filename);
        fileinfo.setCjsj(new Date());
        fileinfo.setZpzl("video");
        fileinfo.setZplx("2");
        webchatFileinfoService.save(fileinfo);
        return responseDto;
    }

    /**
     * @param file
     * @param lsh
     * @param zplx
     * @param pd 1:套牌车上报报案人资料 2:套牌车上报车辆资料 3:事故上报拍照
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/uploadpic")
    public ResponseDto handleFileUploadpic(@RequestParam("file") MultipartFile file, String lsh ,String zplx,String pd) throws IOException {
        ResponseDto responseDto = new ResponseDto();
        System.out.println("文件类型：" + file.getContentType());
        System.out.println("文件名称：" + file.getOriginalFilename());
        System.out.println("文件大小:" + file.getSize());
        //将文件上传到服务器
        String fileDriectory = WxRedisConfig.createFileDriectory(WxRedisConfig.getPicStorePath());
        String fileName = file.getOriginalFilename();
        //String filehz = fileName.substring(fileName.lastIndexOf("."));
        String filehz = ".jpg";
        String filename = lsh + "_" + new Date().getTime() + filehz;
        file.transferTo(new File(fileDriectory + filename));
        WebchatFileinfoDto fileinfo = new WebchatFileinfoDto();
        fileinfo.setLsh(lsh);
        fileinfo.setZplj(WxRedisConfig.fileStr + filename);
        fileinfo.setCjsj(new Date());
        if("1".equals(pd)){
            fileinfo.setZpzl(CodeType.CODE_XX_TBCSBBAR);
        }else if("2".equals(pd)){
            fileinfo.setZpzl(CodeType.CODE_XX_TBCSBCL);
        }else if("3".equals(pd)){
            fileinfo.setZpzl(CodeType.CODE_XX_SGSBPZ);
        }
        fileinfo.setZplx(zplx);
        webchatFileinfoService.save(fileinfo);
        return responseDto;
    }

}
