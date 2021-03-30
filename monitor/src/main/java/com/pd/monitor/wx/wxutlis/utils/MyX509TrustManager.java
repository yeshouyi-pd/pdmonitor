package com.pd.monitor.wx.wxutlis.utils;

import java.security.cert.X509Certificate;
import java.security.cert.CertificateException;
import javax.net.ssl.X509TrustManager;

/**
 * 信任管理器
 * @ClassName:     MyX509TrustManager.java
 * @Description:   TODO
 * @author:        ltj
 * @version:       V1.0  
 * @Date:          2017-9-25 下午3:22:41
 */
public class MyX509TrustManager implements X509TrustManager {

    // 检查客户端证书
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    // 检查服务器端证书
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    // 返回受信任的X509证书数组
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
