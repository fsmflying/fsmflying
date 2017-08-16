package com.fsmflying.sys.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
//import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.fsmflying.common.web.constant.SessionConstant;
import com.fsmflying.helpers.Helper;
import com.fsmflying.http.JsonHttpResult;
import com.fsmflying.sys.dm.SysDataAuth;
import com.fsmflying.sys.dm.SysDataAuthItem;
import com.fsmflying.sys.dm.SysFuncPoint;
import com.fsmflying.sys.dm.SysMenu;
import com.fsmflying.sys.dm.SysRole;
import com.fsmflying.sys.dm.SysTab;
import com.fsmflying.sys.dm.SysUser;
import com.fsmflying.sys.dm.helper.LoginResult;
import com.fsmflying.sys.dm.helper.LogoutResult;
import com.fsmflying.sys.dm.helper.User;
import com.fsmflying.sys.service.AccountService;
import com.fsmflying.sys.service.IConfigService;
import com.fsmflying.sys.service.ISystemManageService;

public class AccountServiceImpl implements AccountService {

	Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Resource
	ISystemManageService systemManageService;

	@Resource
	IConfigService configService;

	@Override
	public LoginResult login(String username, String password) {
		LoginResult loginResult = new LoginResult();
		return loginResult;
	}

	public LoginResult login(HttpServletRequest request, HttpServletResponse response, String username,
			String password) {
		logger.debug("username=" + username);
		logger.debug("password=" + password);

		LoginResult loginResult = new LoginResult();
		HttpSession session = request.getSession();
		Object objectUserId = session.getAttribute(AccountService.USERID_KEY_IN_SESSION);

		int userId = -1;
		if (objectUserId != null) {
			userId = (Integer) objectUserId;
		}
		User user = null;
		if (userId != -1) {
			loginResult.setResult(-2);
			loginResult.setMessage("用户已经登录!");
			Object objectUser = session.getAttribute(AccountService.USER_KEY_IN_SESSION);
			if (objectUser != null) {
				user = (User) objectUser;
			}
			loginResult.setUser(user);
			loginResult.setRedirectUrl(request.getRequestURI().toString());
			return loginResult;
		}
		SysUser sysUser = systemManageService.getModelOfSysUser(username, true);
		if (sysUser != null) {
			if (!sysUser.getUserPwd().equals(Helper.getMD5(password))) {// 密码错误!
				int maxPwdErrorCount = configService.getPasswordErrorMaxCount();
				sysUser.setPwdErrorCount(sysUser.getPwdErrorCount() + 1);
				sysUser.setPwdErrorTime(Calendar.getInstance().getTime());
				if (sysUser.getPwdErrorCount() >= maxPwdErrorCount) {//
					sysUser.setDisabledTime(Calendar.getInstance().getTime());
					sysUser.setDisabledMinutes(configService.getPasswordErrorDisableMinutes());
				}
				systemManageService.save(sysUser);
				loginResult.setResult(0);
				if (sysUser.getPwdErrorCount() == (maxPwdErrorCount - 1))
					loginResult.setMessage("<br/>密码已连续输错【" + sysUser.getPwdErrorCount() + "】次，还有1次机会，如果不能成功，账号将被冻结！");
				else if (sysUser.getPwdErrorCount() == maxPwdErrorCount)
					loginResult.setMessage("<br/>密码已连续输错【" + sysUser.getPwdErrorCount() + "】次，帐号已被冻结！<br/>请于"
							+ configService.getPasswordErrorDisableMinutes() + "分钟后再尝试!");
				else
					loginResult.setMessage("用户名或密码有误!请确认!");

				return loginResult;
			}
			logger.info("用户[" + sysUser.getUsername() + "]尝试登录!");
			if (sysUser.getStatus() == 1) {
				loginResult.setResult(0);
				loginResult.setMessage("用户被永久禁用!请联系管理员!");
				return loginResult;
			} else if (sysUser.getStatus() == 2) {
				Date currentTime = Calendar.getInstance().getTime();

				if (sysUser.getDisabledTime() != null) {
					if (currentTime.getTime() < sysUser.getDisabledTime().getTime()
							+ sysUser.getDisabledMinutes() * 60 * 1000) {
						loginResult.setResult(0);
						loginResult.setMessage("用户账号被禁用!请联系管理员!");
						return loginResult;
					}
				} else {

					loginResult.setResult(0);
					loginResult.setMessage("登录失败");
					return loginResult;
				}

			} else if (sysUser.getStatus() == 3) {
				Calendar calendar = Calendar.getInstance();
				long currentOffset = calendar.get(Calendar.HOUR_OF_DAY) * 60 * 60 * 1000
						+ calendar.get(Calendar.MINUTE) * 60 * 1000 + calendar.get(Calendar.SECOND) * 1000;

				calendar.setTime(sysUser.getDisabledTime());
				long disabledStartOffset = calendar.get(Calendar.HOUR_OF_DAY) * 60 * 60 * 1000
						+ calendar.get(Calendar.MINUTE) * 60 * 1000 + calendar.get(Calendar.SECOND) * 1000;
				long disabledEndOffset = calendar.get(Calendar.HOUR_OF_DAY) * 60 * 60 * 1000
						+ calendar.get(Calendar.MINUTE) * 60 * 1000 + calendar.get(Calendar.SECOND) * 1000
						+ sysUser.getDisabledMinutes() * 60 * 1000;
				if (currentOffset < disabledStartOffset)
					currentOffset = currentOffset + (24 * 60 * 60 * 1000);
				if (currentOffset > disabledStartOffset && currentOffset < disabledEndOffset) {
					loginResult.setResult(0);
					loginResult.setMessage("用户账号处于每天被禁用的时段内!");
					return loginResult;
				}
			}

			user = new User(sysUser);
			userId = user.getUserId();

			session.setAttribute(AccountService.USERID_KEY_IN_SESSION, userId);
			session.setAttribute(AccountService.USER_KEY_IN_SESSION, user);
			if (configService != null) {
				int sessionExpirePolicy = configService.getSessionExpirePolicy();
				if (sessionExpirePolicy == 0)
					session.setMaxInactiveInterval(configService.getSessionExpireIntervalMinutes());
				else if (sessionExpirePolicy == 1)
					response.setDateHeader("Expires",
							System.currentTimeMillis() + configService.getSessionExpireMinutes() * 60 * 1000);
				else
					session.setMaxInactiveInterval(300);
			} else
				session.setMaxInactiveInterval(300);

			List<SysFuncPoint> funcPoints = systemManageService.getUserFuncPoints(userId);
			List<String> list = new ArrayList<String>();
			for (SysFuncPoint e : funcPoints)
				list.add(e.getKeyCode().toLowerCase().trim());
			sysUser.setLastLoginTime(Calendar.getInstance().getTime());
			sysUser.setPwdErrorCount(0);
			systemManageService.save(sysUser);

			loginResult.setResult(1);
			loginResult.setMessage("登录成功!");
			loginResult.setUser(user);
			logger.info("用户[" + user.toString() + "]登录系统!");
			if (request.getParameter("redirectUrl") == null) {
				loginResult.setRedirectUrl(request.getServletContext().getContextPath() + "/ui/user/desktop");
			} else {
				loginResult.setRedirectUrl(request.getParameter("redirectUrl"));
			}
			return loginResult;
		} else {

			loginResult.setResult(0);
			loginResult.setMessage("用户账号不存在!请确认!");
			return loginResult;
		}
	}

	public void logout(HttpSession session) {

		JsonHttpResult jsonResult = new JsonHttpResult();
		Object objectUserId = session.getAttribute(AccountService.USERID_KEY_IN_SESSION);
		if (objectUserId != null) {
			int userId = (Integer) objectUserId;
			User user = new User(systemManageService.getModelOfSysUser(userId));
			logger.info("用户[" + user.toString() + "]退出系统!");
			session.removeAttribute(AccountService.USERID_KEY_IN_SESSION);
			session.removeAttribute(AccountService.USER_KEY_IN_SESSION);
			jsonResult.setResult(1);
			jsonResult.setMessage("用户成功退出系统！");
		}
	}

	@Override
	public LogoutResult logout(String username) {
		LogoutResult logoutResult = new LogoutResult();

		return logoutResult;
	}

	@Override
	public SysUser getUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysUser getUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysRole> getUserRoles(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysTab> getUserTabs(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysMenu> getUserMenus(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysFuncPoint> getUserFuncPoints(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysDataAuth> getUserDataAuths(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysDataAuthItem> getUserDataAuthItems(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
