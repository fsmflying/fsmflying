package com.fsmflying.spring.auth;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.fsmflying.sys.dm.helper.User;
import com.fsmflying.sys.service.SystemManageService;

public class AuthHelper {

	@Resource
	static SystemManageService systemManageService;

	/**
	 * 得到已经登录用户的User实例，如果未登录则返回null
	 * @param request HttpServletRequest请求实例
	 * @return　User实例
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
	 * 得到已经登录用户的用户Id，如果未登录则返回-1
	 * @param request HttpServletRequest请求实例
	 * @return int类型的UserId
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
