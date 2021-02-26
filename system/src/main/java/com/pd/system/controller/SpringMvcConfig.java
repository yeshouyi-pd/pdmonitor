package com.pd.system.controller;

import com.pd.server.main.domain.Attr;
import com.pd.server.main.domain.AttrExample;
import com.pd.server.main.mapper.AttrMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Springboot静态资源配置
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Resource
    private AttrMapper attrMapper;

    /**
     * 防止启动顺序问题
     */
    public  String getPicStorePath() {
        String picStorePath;
        List<Attr> list = attrMapper.selectByExample(new AttrExample());
        Map<String, String> map = new LinkedHashMap<String, String>();
        if(!CollectionUtils.isEmpty(list)) {
            for (Attr attr : list) {
                map.put(attr.getAttrcode(), attr.getAttrkey());
            }
        }
        String macos = System.getProperties().getProperty("os.name");// 获取系统操作类型
        if (macos.toLowerCase().contains("windows")) {
            picStorePath= map.get("pathwindows");
        } else {
            picStorePath= map.get("pathliunx");
        }
        return picStorePath;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/f/**").addResourceLocations("file:"+this.getPicStorePath());
    }
}