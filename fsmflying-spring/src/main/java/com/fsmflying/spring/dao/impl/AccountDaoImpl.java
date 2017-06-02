package com.fsmflying.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.fsmflying.sys.dao.AccountDao;
import com.fsmflying.sys.dm.SysDataAuth;
import com.fsmflying.sys.dm.SysDataAuthItem;
import com.fsmflying.sys.dm.SysFuncPoint;
import com.fsmflying.sys.dm.SysMenu;
import com.fsmflying.sys.dm.SysRole;
import com.fsmflying.sys.dm.SysTab;
import com.fsmflying.sys.dm.SysUser;

public class AccountDaoImpl implements AccountDao {

	JdbcTemplate jdbcTemplate;
	
	@Resource
	public void setDataSource(DataSource dataSource) {
		// TODO Auto-generated method stub
		this.jdbcTemplate = new JdbcTemplate(dataSource);
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
//		return this.jdbcTemplate.query("", new Object[]{},SysRoleMapper());
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
