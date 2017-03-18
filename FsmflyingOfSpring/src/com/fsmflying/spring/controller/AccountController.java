package com.fsmflying.spring.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.annotate.JsonView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fsmflying.helpers.Helper;
import com.fsmflying.spring.auth.AuthHelper;
import com.fsmflying.spring.auth.AuthInterceptor;
import com.fsmflying.sys.dm.SysFuncPoint;
import com.fsmflying.sys.dm.SysUser;
import com.fsmflying.sys.dm.helper.LoginResult;
import com.fsmflying.sys.dm.helper.User;
import com.fsmflying.sys.service.ConfigService;
import com.fsmflying.sys.service.SystemManageService;

import com.fsmflying.http.JsonHttpResult;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Resource
	SystemManageService systemManageService;

	@Resource
	ConfigService configService;

	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	@JsonView
	public LoginResult login(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			String username, String password) {
		System.out.println("username=" + username);
		System.out.println("password=" + password);

		LoginResult loginResult = new LoginResult();
		int userId = AuthHelper.getUserId(request);
		if (userId != -1) {
			loginResult.setResult(-2);
			loginResult.setMessage("用户已经登录成功！");
			loginResult.setUser(AuthHelper.getUser(request));
			loginResult.setRedirectUrl(request.getRequestURI().toString());
			return loginResult;
		}
		SysUser sysUser = systemManageService.getModelOfSysUser(username, true);
		if (sysUser != null) {

			if (sysUser.getStatus() == 1) {
				loginResult.setResult(0);
				loginResult.setMessage("用户被永久禁用！");
				return loginResult;
			} else if (sysUser.getStatus() == 2) {
				Date currentTime = Calendar.getInstance().getTime();

				if (sysUser.getDisabledTime() != null) {
					if (currentTime.getTime() < sysUser.getDisabledTime().getTime()
							+ sysUser.getDisabledMinutes() * 60 * 1000) {
						loginResult.setResult(0);
						loginResult.setMessage("用户处于被禁用的时间段内！");

						return loginResult;
					}
				} else {

					loginResult.setResult(0);
					loginResult.setMessage("用户禁用时间段有错，请联系管理员！");

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
					loginResult.setMessage("用户处于每天被禁用的时间段内！");
					return loginResult;
				}
			} else if (sysUser.getStatus() == 0 && !sysUser.getUserPwd().equals(Helper.getMD5(password))) {
				loginResult.setResult(0);
				loginResult.setMessage("密码错误！");
				return loginResult;
			}

			User user = new User(sysUser);
			userId = user.getUserId();

			session.setAttribute(AuthInterceptor.SESSION_USERID, userId);
			session.setAttribute(AuthInterceptor.SESSION_USER, user);
			if (configService != null) {
				int sessionExpirePolicy = configService.getSessionExpirePolicy();
				if (sessionExpirePolicy == 0)
					session.setMaxInactiveInterval(configService.getSessionExpireIntervalMinutes());
				else if (sessionExpirePolicy == 1)
					response.setDateHeader("Expires",
							System.currentTimeMillis() + configService.getSessionExpireMinutes() * 60 * 1000);
				else
					session.setMaxInactiveInterval(300);
			}
			else
				session.setMaxInactiveInterval(300);

			List<SysFuncPoint> funcPoints = systemManageService.getUserFuncPoints(userId);
			List<String> list = new ArrayList<String>();
			for (SysFuncPoint e : funcPoints)
				list.add(e.getKeyCode().toLowerCase().trim());

			// CacheHelper.put("[User][" + userId + "][funcpoint]", list);

			sysUser.setLastLoginTime(Calendar.getInstance().getTime());

			systemManageService.save(sysUser);

			// List<String> funcPoints
			// session.setAttribute(AuthInterceptor.SESSION_AUTHS, new
			// HashSet<String>(
			// Arrays.asList("sysadmin.company.delete", "sysadmin.company.add",
			// "sysadmin.company.update")));

			loginResult.setResult(1);
			loginResult.setMessage("用户登录成功！");
			loginResult.setUser(user);
			if (request.getParameter("redirectUrl") == null) {
				loginResult.setRedirectUrl(request.getServletContext().getContextPath() + "/ui/user/desktop");
			} else {
				loginResult.setRedirectUrl(request.getParameter("redirectUrl"));
			}
			return loginResult;
		} else {

			loginResult.setResult(0);
			loginResult.setMessage("用户名输入错误，请确认！");
			return loginResult;
		}
	}

	@RequestMapping("/logout")
	@JsonView
	public JsonHttpResult logout(HttpSession session) {

		JsonHttpResult jsonResult = new JsonHttpResult();
		Object objectUserId = session.getAttribute(AuthInterceptor.SESSION_USERID);
		if (objectUserId != null) {
			int userId = (Integer) objectUserId;
			// CacheHelper.remove("[User][" + userId + "][funcpoint]");
			System.out.println("用户[" + userId + "]登出系统!");
			session.removeAttribute(AuthInterceptor.SESSION_USERID);
			session.removeAttribute(AuthInterceptor.SESSION_USER);
			jsonResult.setResult(1);
			jsonResult.setMessage("成功登出系统!");

		}

		return jsonResult;
	}

	@RequestMapping("/getStatus")
	@JsonView
	public JsonHttpResult getStatus(HttpServletRequest request, HttpSession session) {

		JsonHttpResult jsonResult = new JsonHttpResult();
		int userId = AuthHelper.getUserId(request);
		if (userId != -1) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("user", AuthHelper.getUser(request));
			jsonResult.setResult(1);
			jsonResult.setData(data);
			jsonResult.setMessage("已经登录系统!");
		} else {
			jsonResult.setResult(0);
			jsonResult.setMessage("已经登录系统!");
		}

		return jsonResult;
	}

}
