package com.fsmflying.spring.dao.impl.hibernate;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.orm.hibernate4.HibernateTemplate;

import com.fsmflying.sys.dm.SysConfig;
import com.fsmflying.sys.dm.SysDataAuth;
import com.fsmflying.sys.dm.SysDataAuthItem;
import com.fsmflying.sys.dm.SysFuncPoint;
import com.fsmflying.sys.dm.SysMenu;
import com.fsmflying.sys.dm.SysRole;
import com.fsmflying.sys.dm.SysTab;
import com.fsmflying.sys.dm.SysUser;
import com.fsmflying.sys.dm.helper.UserRightSet;
import com.fsmflying.util.TwoTuple;

public class SystemManageDaoImpl extends com.fsmflying.spring.dao.impl.SystemManageDaoImpl {

	HibernateTemplate hibernateTemplate;
	
	
	@Override
	public void setDataSource(DataSource ds) {
		// TODO Auto-generated method stub
		super.setDataSource(ds);
		this.hibernateTemplate = new HibernateTemplate();
		
	}

	@Override
	public boolean add(SysUser model) {
		// TODO Auto-generated method stub
		
		
		return super.add(model);
		
	}

	@Override
	public boolean save(SysUser model) {
		// TODO Auto-generated method stub
		return super.save(model);
	}

	@Override
	public boolean deleteModelOfSysUser(int userId) {
		// TODO Auto-generated method stub
		return super.deleteModelOfSysUser(userId);
	}

	@Override
	public SysUser getModelOfSysUser(String username) {
		// TODO Auto-generated method stub
		return super.getModelOfSysUser(username);
	}

	@Override
	public List<SysUser> getListOfSysUser() {
		// TODO Auto-generated method stub
		return super.getListOfSysUser();
	}

	@Override
	public boolean add(SysConfig model) {
		// TODO Auto-generated method stub
		return super.add(model);
	}

	@Override
	public boolean save(SysConfig model) {
		// TODO Auto-generated method stub
		return super.save(model);
	}

	@Override
	public boolean deleteModelOfSysConfig(int configId) {
		// TODO Auto-generated method stub
		return super.deleteModelOfSysConfig(configId);
	}

	@Override
	public SysConfig getModelOfSysConfig(int configId) {
		// TODO Auto-generated method stub
		return super.getModelOfSysConfig(configId);
	}

	@Override
	public List<SysConfig> getListOfSysConfig() {
		// TODO Auto-generated method stub
		return super.getListOfSysConfig();
	}

	@Override
	public List<SysRole> getUserRoles(int userId) {
		// TODO Auto-generated method stub
		return super.getUserRoles(userId);
	}

	@Override
	public List<SysTab> getUserTabs(int userId) {
		// TODO Auto-generated method stub
		return super.getUserTabs(userId);
	}

	@Override
	public List<SysMenu> getUserMenus(int userId) {
		// TODO Auto-generated method stub
		return super.getUserMenus(userId);
	}

	@Override
	public List<SysFuncPoint> getUserFuncPoints(int userId) {
		// TODO Auto-generated method stub
		return super.getUserFuncPoints(userId);
	}

	@Override
	public List<SysDataAuth> getUserDataAuths(int userId) {
		// TODO Auto-generated method stub
		return super.getUserDataAuths(userId);
	}

	@Override
	public List<SysDataAuthItem> getUserDataAuthItems(int userId) {
		// TODO Auto-generated method stub
		return super.getUserDataAuthItems(userId);
	}

	@Override
	public List<SysMenu> getTabMenus(int tabId) {
		// TODO Auto-generated method stub
		return super.getTabMenus(tabId);
	}

	@Override
	public UserRightSet getUserRightSet(int userId) {
		// TODO Auto-generated method stub
		return super.getUserRightSet(userId);
	}

	@Override
	public List<TwoTuple<Integer, Integer>> getTabMenuTuples() {
		// TODO Auto-generated method stub
		return super.getTabMenuTuples();
	}

}
