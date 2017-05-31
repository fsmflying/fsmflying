package com.fsmflying.spring.auth;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.fsmflying.sys.dm.helper.User;
import com.fsmflying.sys.service.SystemManageService;

public class AuthHelper {

	@Resource
	static SystemManageService systemManageService;

	/**
	 * �õ��Ѿ���¼�û���Userʵ�������δ��¼�򷵻�null
	 * @param request HttpServletRequest����ʵ��
	 * @return��Userʵ��
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
	 * �õ��Ѿ���¼�û����û�Id�����δ��¼�򷵻�-1
	 * @param request HttpServletRequest����ʵ��
	 * @return int���͵�UserId
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
