package com.pd.wxbase.wx.wxutlis.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TokenThread implements Runnable{

    private static final Logger LOG = LoggerFactory.getLogger(TokenThread.class);

    public  static  AccessToken accessToken =  null ;

    @Override
    public void run() {
        while  ( true ){
            try {
                accessToken =  WeixinUtil.getAccessToken();
                if ( null != accessToken){
                    AccountsServlet.accessToken = accessToken.getToken();
                    AccountsServlet.ticket = WeixinUtil.getTicket(accessToken.getToken());
                    AccountsServlet.jspTicet = AccountsServlet.ticket.getTicket();
                    LOG.info("获取到access_token="+AccountsServlet.accessToken);
                    Thread.sleep( 7000  *  1000 );  //获取到access_token 休眠7000秒
                } else {
                    LOG.info("获取到access_token失败");
                    break;
                }
            } catch (Exception e){
                LOG.error( "获取accessToken发生异常：" +e.getMessage());
                e.printStackTrace();
                break;
            }
        }
    }

}
