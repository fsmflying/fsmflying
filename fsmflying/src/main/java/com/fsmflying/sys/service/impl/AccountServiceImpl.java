package com.fsmflying.sys.service.impl;

import java.util.List;
//import java.util.Set;

import javax.annotation.Resource;

import com.fsmflying.sys.dao.AccountDao;
import com.fsmflying.sys.dao.ISystemManageDao;
import com.fsmflying.sys.dm.SysDataAuth;
import com.fsmflying.sys.dm.SysDataAuthItem;
import com.fsmflying.sys.dm.SysFuncPoint;
import com.fsmflying.sys.dm.SysMenu;
import com.fsmflying.sys.dm.SysRole;
import com.fsmflying.sys.dm.SysTab;
import com.fsmflying.sys.dm.SysUser;
import com.fsmflying.sys.dm.helper.LoginResult;
import com.fsmflying.sys.dm.helper.LogoutResult;
import com.fsmflying.sys.service.AccountService;


public class AccountServiceImpl implements AccountService {

	@Resource
	ISystemManageDao systemManageDao;
	
	@Resource
	AccountDao accountDao;
	
	@Override
	public LoginResult login(String username, String password) {
		LoginResult loginResult = new LoginResult();
		
		return loginResult;
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
