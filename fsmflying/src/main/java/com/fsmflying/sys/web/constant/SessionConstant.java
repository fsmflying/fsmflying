package com.fsmflying.sys.web.constant;

import com.fsmflying.sys.service.AccountService;

/**
 * 
 * @描述: 会话键常量类.
 * @作者: WuShuicheng.
 * @创建: 2014-8-19,上午9:26:46
 * @版本: V1.0
 *
 */
public class SessionConstant {

	public static final String USERID_KEY_IN_SESSION = AccountService.USERID_KEY_IN_SESSION;
	
	/**
	 * 商户主帐号ID的session键名.
	 */
	public static final String MAIN_USERID_KEY_IN_SESSION = AccountService.MAIN_USERID_KEY_IN_SESSION;
	/**
	 * 登录用户的session键名.
	 */
	public static final String USER_KEY_IN_SESSION = AccountService.USER_KEY_IN_SESSION;
	// public static final int WEB_PWD_INPUT_ERROR_LIMIT=5;

	/**
	 * 登录用户拥有的权限集合的session键名.
	 */
	public static final String ACTIONS_IN_SESSION = "fmactions";

	/**
	 * 用户密码连续输错次数限制(默认5).
	 */
	public static final int WEB_PWD_INPUT_ERROR_LIMIT = AccountService.WEB_PWD_INPUT_ERROR_LIMIT;

}
