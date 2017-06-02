package com.fsmflying.spring.auth;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.fsmflying.sys.dm.helper.User;
import com.fsmflying.sys.service.SystemManageService;

public class AuthHelper {

	@Resource
	static SystemManageService systemManageService;

	/**
	 * 
	 * @param request 用户的请求
	 * @return 会话中的用户信息
	 */
	public static User getUser(HttpServletRequest request) {
		if (request != null) {
			Object objectUser = request.getSession().getAttribute(AuthInterceptor.SESSION_USER);
			if (objectUser != null) {
				return (User) objectUser;
			}
		}
		return null;
	}

	/**
	 * 根据会话获取登录用户Id
	 * @param request 用户的请求
	 * @return 用户Id
	 */
	public static int getUserId(HttpServletRequest request) {
		if (request != null) {
			Object objectUserId = request.getSession().getAttribute(AuthInterceptor.SESSION_USERID);
			if (objectUserId != null) {
				return (Integer) objectUserId;
			}
		}
		return -1;
	}

}
