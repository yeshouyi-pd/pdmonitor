package com.pd.system.controller.conf;

import com.alibaba.fastjson.JSONObject;
import com.pd.server.config.CodeType;
import com.pd.server.config.RedisCode;
import com.pd.server.main.dto.KvIntDto;
import com.pd.server.main.domain.*;
import com.pd.server.main.mapper.AttrMapper;
import com.pd.server.main.mapper.CodesetMapper;
import com.pd.server.main.mapper.DeptMapper;
import com.pd.server.main.mapper.UserMapper;
import com.pd.server.main.mapper.my.MyDeptMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * rddis 缓存工具
 */
@Component
@Order(Integer.MAX_VALUE)
public class RedisConfig  implements CommandLineRunner {


    private static final Logger LOG = LoggerFactory.getLogger(RedisConfig.class);

    public  static CodesetMapper  CodesetstaticMapper;

    public  static DeptMapper  deptstaticMapper;

    public  static RedisTemplate  redisTstaticemplate;


    public  static  UserMapper UserstaticMapper;

    public  static MyDeptMapper  myDeptstaticMapper;

    public  static AttrMapper  attrstaticMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private CodesetMapper codesetMapper;

    @Resource
    private DeptMapper deptMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private MyDeptMapper myDeptMapper;

    @Resource
    private AttrMapper attrMapper;

    @PostConstruct
    protected void init() {
        CodesetstaticMapper = codesetMapper;
        redisTstaticemplate = redisTemplate;
        deptstaticMapper    = deptMapper;
        UserstaticMapper = userMapper;
        myDeptstaticMapper =myDeptMapper;
        attrstaticMapper =attrMapper;
    }


    @Override
    public void run(String... args) throws Exception {
        LOG.info(">>>>>>>>>>>>>>开始加载数据库参数<<<<<<<<<<<<<");
        reload();
        LOG.info(">>>>>>>>>>>>>>>服务启动，基础数据加载完成 <<<<<<<<<<<<<");

    }


    /*
     * 重载参数，刷新缓存调用
     */
    public static synchronized void reload() {
        init_code();//加载部门缓存
        init_dept();//加载部门
        init_user();//加载用户缓存
        init_Yynum();//所有部门预约量
        init_attr();//加载系统参数
        LegalholidayUtils.loop();//获取当年以及明年的所有法定节假日

    }

    /**
     * 加载系统参数  系统静态路径
     */
    public  synchronized static boolean init_attr() {
        try {

            List<Attr>  list = attrstaticMapper.selectByExample(new AttrExample());
            if(!CollectionUtils.isEmpty(list)){
                Map<String, String>  map = new LinkedHashMap<String,String>();
                for(Attr attr  :list){
                    map.put(attr.getAttrcode(),attr.getAttrkey());
                }
                redisTstaticemplate.opsForValue().set(RedisCode.ATTRECODEKEY, map);//将参数信息写入redis缓存

                String   macos = System.getProperties().getProperty("os.name");// 获取系统操作类型
                String picStorePath ;
                if (macos.toLowerCase().contains("windows")) {
                    picStorePath= map.get("pathwindows");
                } else {
                    picStorePath= map.get("pathliunx");
                }
                redisTstaticemplate.opsForValue().set(RedisCode.STATICPATH, picStorePath);//静态路径地址

            }
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }


    /**
     * 加载当前所有部门能办理的业务预约量
     */

    public  synchronized static boolean init_Yynum() {
        try {

            List<KvIntDto>  list = myDeptstaticMapper.getDeptYyMum();
            if(!CollectionUtils.isEmpty(list)){
                Map<String, Integer>  map = new LinkedHashMap<String,Integer>();
                for(KvIntDto kv  :list){
                    map.put(kv.getKey(),kv.getValue());
                }
                redisTstaticemplate.opsForValue().set(RedisCode.DEPTYYNUM, map);//将参数信息写入redis缓存
            }
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    /**
     *加载用户
     * @return
     */

    public synchronized static boolean init_user() {
        try {
            UserExample example  = new  UserExample();
            UserExample.Criteria ca = example.createCriteria();
            List<User> list = UserstaticMapper.selectByExample(example);
            if(!CollectionUtils.isEmpty(list)){
                Map<String, String>  map = new LinkedHashMap<String,String>();
                for(User vo  :list){
                    map.put(vo.getLoginName(),vo.getName());
                }
                redisTstaticemplate.opsForValue().set(RedisCode.USERCODENAME, map);//将参数信息写入redis缓存
            }
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
    /**
     * 加载部门
     * @return
     */

    public synchronized static boolean init_dept() {
        try {
            DeptExample example  = new  DeptExample();
            DeptExample.Criteria ca = example.createCriteria();
            List<Dept> list = deptstaticMapper.selectByExample(example);
            if(!CollectionUtils.isEmpty(list)){
                Map<String, String>  map = new LinkedHashMap<String,String>();
                for(Dept vo  :list){
                    map.put(vo.getDeptcode(),vo.getDeptname());
                }
                redisTstaticemplate.opsForValue().set(RedisCode.DEPTCODENAME, map);//将参数信息写入redis缓存
            }
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
        // 刷新后的数据

    /**
     * 加载代码缓存
     * @return
     */
    public  synchronized static boolean init_code() {
        // 刷新后的数据
        Map<String, Map<String, String>> nmap = new LinkedHashMap<String, Map<String, String>>();
        // 刷新后的数据
        Map<String, List<Codeset>> nlist = new LinkedHashMap<String, List<Codeset>>();
        try {
            CodesetExample exampleall =  new  CodesetExample();
            CodesetExample.Criteria caall = exampleall.createCriteria();
            caall.andTypeEqualTo(CodeType.YWZL_CODE);//业务种类 所有分类
            List<Codeset> alltype = CodesetstaticMapper.selectByExample(exampleall);
            if(!CollectionUtils.isEmpty(alltype)){
                Map<String, String>  mapall  = new LinkedHashMap<String,String>();
                for(Codeset vo :alltype){
                    if(StringUtils.isNotBlank(vo.getCode())){
                        mapall.put(vo.getCode(),vo.getName());//更具类型存放
                        Map<String, String>  map  = new LinkedHashMap<String,String>();
                        CodesetExample example =  new  CodesetExample();
                        CodesetExample.Criteria ca = example.createCriteria();
                        ca.andTypeEqualTo(vo.getCode());//业务种类 所有分类
                        example.setOrderByClause(" code asc");
                        List<Codeset> list = CodesetstaticMapper.selectByExample(example);
                        if(!CollectionUtils.isEmpty(list)){
                            for(Codeset thisvo:list){
                                if(StringUtils.isNotBlank(thisvo.getCode())
                                        &&StringUtils.isNotBlank(thisvo.getName())){
                                      map.put(thisvo.getCode(),thisvo.getName());//更具类型存放
                                }
                            }
                            nmap.put(vo.getCode(),map); //根据部门种类将所有代码存放起来
                            nlist.put(vo.getCode(),list);
                        }

                    }
                }
                nmap.put(CodeType.YWZL_CODE,mapall); //总类
                nlist.put(CodeType.YWZL_CODE,alltype); //总类
                String s = JSONObject.toJSONString(nlist);
                JSONObject jsonObj = JSONObject.parseObject(s);
                redisTstaticemplate.opsForValue().set(RedisCode.CODESET, nmap);//将参数信息写入redis缓存
                redisTstaticemplate.opsForValue().set(RedisCode.CODESETVO, jsonObj);//将参数信息写入redis缓存
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
