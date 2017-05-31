package com.fsmflying.sys.dm.helper;

public class LoginResult {
	private User user;
	private int result = -1;
	private String redirectUrl;
	private String message;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 获取用户登录结果标志位： <br/>
	 * 0:登录失败,用户名存在，但密码错误!  <br/>
	 * 1:登录成功!  <br/>
	 * 2:登录失败，用户名不存在!  <br/>
	 * 3:登录失败，账号被锁定! <br/>
	 * 4:用户被禁用 <br/>
	 * -1:未知(默认值)
	 * -2:已经登录成功，转向指定默认页(返回结果中的"redirectUrl"属性指定)
	 * @return 返回用户登录结果标志位
	 */
	public int getResult() {
		return result;
	}

	/**
	 * 设置用户登录结果标志位：<br/>
	 *  0:登录失败,用户名存在，但密码错误!  <br/>
	 *  1:登录成功!  <br/>
	 *  2:登录失败，用户名不存在!  <br/>
	 *  3:登录失败，账号被锁定! <br/>
	 *  4:用户被禁用 <br/>
	 * -1:未知(默认值) <br/>
	 * @param result 登录结果标志位
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

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
}
