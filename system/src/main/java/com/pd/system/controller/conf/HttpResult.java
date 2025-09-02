package com.pd.system.controller.conf;



/**
 * HTTP结果封装
 */

public class HttpResult {

	private boolean success = true;
	private String msg = "请求成功";
	private Object data;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static HttpResult error(String msg) {
		HttpResult r = new HttpResult();
		r.setSuccess(false);
		r.setMsg(msg);
		return r;
	}

	public static HttpResult ok(Object data) {
		HttpResult r = new HttpResult();
		r.setData(data);
		return r;
	}

	public static HttpResult ok() {
		return new HttpResult();
	}

	public static HttpResult okMsg(String msg) {
		HttpResult r = new HttpResult();
		r.setMsg(msg);
		return r;
	}

}
