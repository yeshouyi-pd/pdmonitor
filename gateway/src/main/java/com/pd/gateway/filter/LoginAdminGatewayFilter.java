package com.pd.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class LoginAdminGatewayFilter implements GatewayFilter, Ordered {

    private static final Logger LOG = LoggerFactory.getLogger(LoginAdminGatewayFilter.class);

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        // 请求地址中不包含/admin/的，不是控台请求，不需要拦截1
        if (!path.contains("/admin/")) {
            return chain.filter(exchange);
        }
        if (path.contains("/system/admin/user/login")
                || path.contains("/system/admin/user/logout")
                || path.contains("/system/admin/userVideo/login")
                || path.contains("/system/admin/userVideo/logout")
                || path.contains("/system/admin/kaptcha")
                || path.contains("/system/admin/resource")
                || path.contains("/monitor/admin/cameraInfo/getDataCamera")
                || path.contains("/monitor/admin/equipmentFile/statisticsAlarmNumsByTimeSum")
                || path.contains("/monitor/admin/equipmentFile/listsbbh")
                || path.contains("/monitor/admin/equipmentFile/statisticsAlarmNumsByHourDP")
                || path.contains("/monitor/admin/waterData/findAll")
                || path.contains("/monitor/admin/waterEquipment/findAll")
                || path.contains("/monitor/admin/equipmentFileAlarmEvent/echartsAlarmDataByDp")
                || path.contains("/system/admin/user/loginDw")
                || path.contains("/monitor/admin/waterEquipment/getTyEquipment")
                || path.contains("/monitor/admin/equipmentFileTyToday/selectTodayGps")
                || path.contains("/monitor/admin/equipmentTyEvent/getTodayEvent")
                || path.contains("/monitor/admin/equipmentFileTyToday/getDataStatistics")
                || path.contains("/monitor/admin/turbidity/getAllDataByTime")
                || path.contains("/monitor/admin/meteorologicalData/getAllDataByTime")
                || path.contains("/monitor/admin/currentMeter/getAllDataByTime")
                || path.contains("/monitor/admin/equipmentFileToday/statisticsAlarmNumsByTimeSum")
                || path.contains("/system/admin/attr/findByAttrKey/restartinterval")
        ){
            LOG.info("不需要控台登录验证：{}", path);
            return chain.filter(exchange);
        }
        //获取header的token参数
        String token = exchange.getRequest().getHeaders().getFirst("token");
        LOG.info("控台登录验证开始，token：{}", token);
        if (token == null || token.isEmpty()) {
            LOG.info( "token为空，请求被拦截" );
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        Object object = redisTemplate.opsForValue().get(token);
        if (object == null) {
            LOG.warn( "token无效，请求被拦截" );
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        } else {
            LOG.info("已登录：{}", object);

            // 增加权限校验，gateway里没有LoginUserDto，所以全部用JSON操作
            LOG.info("接口权限校验，请求地址：{}", path);
            boolean exist = false;
            JSONObject loginUserDto = JSON.parseObject(String.valueOf(object));
            redisTemplate.opsForValue().set(token, JSON.toJSONString(loginUserDto), 3600, TimeUnit.SECONDS);
            JSONArray requests = loginUserDto.getJSONArray("requests");
            // 遍历所有【权限请求】，判断当前请求的地址是否在【权限请求】里
            for (int i = 0, l = requests.size(); i < l; i++) {
                String request = (String) requests.get(i);
                if (path.contains(request)) {
                    exist = true;
                    break;
                }
            }
            if (exist) {
                LOG.info("权限校验通过");
            } else {
                LOG.warn("权限校验未通过");
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }


            return chain.filter(exchange);
        }
    }

    @Override
    public int getOrder()
    {
        return 1;
    }
}
