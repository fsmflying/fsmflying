package com.fsmflying.spring.auth;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fsmflying.sys.dm.SysFuncPoint;
import com.fsmflying.sys.service.CacheService;
import com.fsmflying.sys.service.SystemManageService;

//import fsmflying.CacheHelper;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Resource
	CacheService cacheService;

	@Resource
	SystemManageService systemManageService;

	public static final String SESSION_USERID = "kAUTHUSERID";
	public static final String SESSION_AUTHS = "kAUTHS";
	public static final String SESSION_USER = "kAUTHUSER";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		boolean flag = false;
		
		if(!flag)
			return flag;
		
		if (handler instanceof HandlerMethod) {
			Auth auth = ((HandlerMethod) handler).getMethod().getAnnotation(Auth.class);
			if (auth != null) {// 
				if (request.getSession().getAttribute(SESSION_USERID) == null) {//
					response.setStatus(HttpStatus.FORBIDDEN.value());
					response.setContentType("text/html; charset=UTF-8");
					String redirectUrl = request.getRequestURI().toString();
					if (request.getQueryString() != null) {
						redirectUrl = redirectUrl + "?" + request.getQueryString();
					}

					response.sendRedirect(request.getServletContext().getContextPath() + "/ui/user/login?redirectUrl="
							+ URLEncoder.encode(redirectUrl, "UTF-8"));

					// PrintWriter out=response.getWriter();
					// out.write("{\"type\":\"nosignin\",\"msg\":\"未登录!\"}");
					// out.flush();
					// out.close();
					flag = false;
				} else {//
					if (!"".equals(auth.value())) {
						int userId = AuthHelper.getUserId(request);
						List<SysFuncPoint> funcPoints = systemManageService.getUserFuncPoints(userId);
						for (SysFuncPoint e : funcPoints) {
							if (e.getKeyCode().toLowerCase().equals(auth.value())) {
								flag = true;
							}
						}

						if (!flag) {//
							response.setStatus(HttpStatus.FORBIDDEN.value());
							response.setContentType("text/html; charset=UTF-8");
							PrintWriter out = response.getWriter();
							out.write("{\"type\":\"noauth\",\"msg\":\"没有登录" + auth.name() + "!\"}");
							out.flush();
							out.close();
							flag = false;
						}
						System.out.println("用户[" + userId + "]的权限[" + auth.value() + "]:" + flag);
					}
				}
			}
		}
		return flag;
	}
}
