package com.fsmflying.sys.dm.helper;

import com.fsmflying.sys.dm.helper.User;

/**
 * 此类用于存放登录操作的结果
 * @author FangMing
 *
 */
public class LoginResult {
	private User user;
	private int result = -1;
	private String redirectUrl;
	private String message;

	/**
	 * 获取登录用户信息，只有登录成功者后才有值
	 * @return
	 */
	public User getUser() {
		return user;
	}

	/**
	 * 登录成功后，构造一个User对象,用于存放与用户相关的信息
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * 获取登录操作结果标志：<br/>
	 * 0:登录失败，登录失败原因<br/>
	 * 1:登录成功;<br/>
	 * -2:已经登录成功;<br/>
	 * @param result
	 */
	public int getResult() {
		return result;
	}
	/**
	 * 设置登录结果标志：<br/>
	 * 0:登录失败，登录失败原因<br/>
	 * 1:登录成功;<br/>
	 * -2:已经登录成功;<br/>
	 * @param result
	 */
	public void setResult(int result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void success() {
		this.result = 1;
	}

	/**
	 * 获取重定向url
	 * @return
	 */
	public String getRedirectUrl() {
		return redirectUrl;
	}

	/**
	 * 设置重定向url
	 * @param redirectUrl
	 */
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
}
