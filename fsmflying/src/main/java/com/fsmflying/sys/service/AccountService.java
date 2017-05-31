package com.fsmflying.sys.service;

import java.util.List;

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
	
	public LoginResult login(String username,String password);
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
