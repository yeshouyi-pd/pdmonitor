package com.pd.server.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import com.pd.server.main.dto.LoginUserDto;
import com.pd.server.main.service.SysLogService;
import com.pd.server.util.UuidUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Aspect
@Component
public class LogAspect {

    private final static Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    @Resource
    private SysLogService sysLogService;

    /** 定义一个切点 */
    @Pointcut("execution(public * com.pd..*.controller..*Controller.*(..))")
    public void controllerPointcut() {}

    @Before("controllerPointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 日志编号
        MDC.put("UUID", UuidUtil.getShortUuid());

        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();

        // 打印业务操作
        String nameCn = "";
        if (name.contains("list") || name.contains("query")|| name.contains("find")|| name.contains("select")) {
            nameCn = "查询";
        } else if (name.contains("save") || name.contains("add") || name.contains("update")) {
            nameCn = "保存/修改";
        } else if (name.contains("delete")) {
            nameCn = "删除";
        } else if(name.contains("statistics")){
            nameCn = "统计";
        } else if(name.contains("export")){
            nameCn = "导出";
        } else if(name.contains("import")){
            nameCn = "导入";
        } else if(name.contains("login")){
            nameCn = "登录";
        } else {
            nameCn = "操作";
        }

        // 使用反射，获取业务名称
        Class clazz = signature.getDeclaringType();
        Field field;
        String businessName = "";
        String czr="";
        String xmbh="";
        //直接java反射得到方法
        try {
            Method method= clazz.getMethod("getRequestHeader");
            Object obj = method.invoke(clazz.newInstance());
            if(null != obj ){
                LoginUserDto loginUserDto =  (LoginUserDto)obj;
                if(null!= loginUserDto){
                    if(!StringUtils.isEmpty(loginUserDto.getLoginName())){
                        czr = loginUserDto.getLoginName();
                        Map<String, List<String>> xmbhsbsns = loginUserDto.getXmbhsbsns();
                        Set<String> xmbhs = xmbhsbsns.keySet();
                        for(String tem : xmbhs){
                            xmbh = tem;
                            break;
                        }
                    }
                }
            }
        }catch (Exception e){}

        try {
            field = clazz.getField("BUSINESS_NAME");
            if (!StringUtils.isEmpty(field)) {
                businessName = (String) field.get(clazz);
            }
        } catch (NoSuchFieldException e) {
            //   LOG.error("未获取到业务名称");
        } catch (SecurityException e) {
            //   LOG.error("获取业务名称失败", e);
        }

        // 打印请求信息
        //   LOG.info("------------- 【{}】{}开始 -------------", businessName, nameCn);
        //  LOG.info("请求地址: {} {}", request.getRequestURL().toString(), request.getMethod());
        String thisurlMethod = request.getRequestURL().toString().substring(request.getRequestURL().toString().lastIndexOf("/")+1);
        //   LOG.info("类名方法: {}.{}", signature.getDeclaringTypeName(), name);
        //   LOG.info("远程地址: {}", request.getRemoteAddr());

        // 打印请求参数
        Object[] args = joinPoint.getArgs();
        Object[] arguments  = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest
                    || args[i] instanceof ServletResponse
                    || args[i] instanceof MultipartFile) {
                continue;
            }
            arguments[i] = args[i];
        }
        // 排除字段，敏感字段或太长的字段不显示
        String[] excludeProperties = {"shard","base64"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludefilter = filters.addFilter();
        excludefilter.addExcludes(excludeProperties);

        /**
         * 操作人为空 可过滤系统请求
         */
        if(!StringUtils.isEmpty(czr) && !StringUtils.isEmpty(businessName)){
            sysLogService.addLog(czr ,request.getRemoteAddr(),businessName+"["+thisurlMethod+"]",nameCn,
                    "1" ,"",JSONObject.toJSONString(arguments, excludefilter),"",
                    "","1",xmbh);
        }
        LOG.info("请求参数: {}", JSONObject.toJSONString(arguments, excludefilter)); // 为空的会不打印，但是像图片等长字段也会打印
    }

    @Around("controllerPointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        // 排除字段，敏感字段或太长的字段不显示
        String[] excludeProperties = {"password", "shard","base64"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludefilter = filters.addFilter();
        excludefilter.addExcludes(excludeProperties);
        LOG.info("返回结果: {}", JSONObject.toJSONString(result, excludefilter));
        LOG.info("------------- 结束 耗时：{} ms -------------", System.currentTimeMillis() - startTime);
        return result;
    }

}