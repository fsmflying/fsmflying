package com.fsmflying.sys.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fsmflying.sys.dm.SysDataAuth;
import com.fsmflying.sys.dm.SysDataAuthItem;
import com.fsmflying.sys.dm.SysFuncPoint;
import com.fsmflying.sys.dm.SysMenu;
import com.fsmflying.sys.dm.SysRole;
import com.fsmflying.sys.dm.SysTab;
import com.fsmflying.sys.dm.SysUser;
import com.fsmflying.sys.dm.helper.LoginResult;
import com.fsmflying.sys.dm.helper.LogoutResult;

public interface AccountService {

	public static final String USERID_KEY_IN_SESSION = "pkUserId";
	public static final String MAIN_USERID_KEY_IN_SESSION = "pkMainUserId";
	public static final String USER_KEY_IN_SESSION = "pkUser";
	public static final int WEB_PWD_INPUT_ERROR_LIMIT=5;
	/**
	 * 登录用户拥有的权限集合的session键名.
	 */
	public static final String ACTIONS_IN_SESSION = "fmactions";

	public LoginResult login(String username, String password);

	public LoginResult login(HttpServletRequest request, HttpServletResponse response, String username,
			String password);
	public void logout(HttpSession session);

	public LogoutResult logout(String username);

	public SysUser getUser(String username);

	public SysUser getUser(int id);

	public List<SysRole> getUserRoles(int userId);

	public List<SysTab> getUserTabs(int userId);

	public List<SysMenu> getUserMenus(int userId);

	public List<SysFuncPoint> getUserFuncPoints(int userId);

	public List<SysDataAuth> getUserDataAuths(int userId);

	public List<SysDataAuthItem> getUserDataAuthItems(int userId);

}
