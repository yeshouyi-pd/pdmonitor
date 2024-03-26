package com.pd.monitor.netsdk.utils;

public class ResultUtils {
    /**
     * 成功的Status Code.
     */
    private static final int resCode_OK = 200;
    /**
     * 失败的Status Code.
     */
    private static final int resCode_FAIL = 201;
    /**
     * 需要重新登录State Code.
     */
    private static final  int resCode_FAIL_RELOGIN = 202;


    /**
     * 获取成功结果
     */
    public static <T> Result<T> getSuccessResult(T obj) {
        return new Result<T>("ok", resCode_OK,obj);
    }

    /**
     * 获取成功信息
     */
    public static Result getSuccessResult(String msg) {
        return new Result(msg, resCode_OK,null);
    }


    /**
     * 获取成功信息.
     */
    public static <T> Result<T> getSuccessResult(String msg, T obj) {
        return new Result(msg, resCode_OK, obj);
    }


    /**
     * 获取默认ajax成功信息.
     */
    public static Result getSuccessResult() {
        return getSuccessResult("操作成功");
    }

    /**
     * 获取失败结果
     */
    public static Result getFailResult(String msg) {
        return getFailResult(resCode_FAIL, msg);
    }

    /**
     * 重新登录消息通知
     */
    public static Result getReLogin(String msg) {
        return getFailResult(resCode_FAIL_RELOGIN, msg);
    }

    /**
     * 获取失败结果
     */
    public static Result getFailResult(int errCode, String msg) {
        return new Result(msg, errCode, null);
    }


}
