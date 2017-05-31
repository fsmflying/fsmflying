package com.fsmflying.sys.service;

import java.util.List;

import com.fsmflying.helpers.SqlParameter;
//import com.fsmflying.sys.dm.SysArea;
import com.fsmflying.sys.dm.SysCompany;
import com.fsmflying.sys.dm.SysConfig;
import com.fsmflying.sys.dm.SysCustomPage;
import com.fsmflying.sys.dm.SysDataAuth;
import com.fsmflying.sys.dm.SysDataAuthItem;
import com.fsmflying.sys.dm.SysDepartment;
import com.fsmflying.sys.dm.SysDictDir;
import com.fsmflying.sys.dm.SysDictItem;
import com.fsmflying.sys.dm.SysEmployee;
import com.fsmflying.sys.dm.SysFile;
import com.fsmflying.sys.dm.SysFuncPoint;
//import com.fsmflying.sys.dm.SysIOTemplate;
//import com.fsmflying.sys.dm.SysIOTemplateColumn;
//import com.fsmflying.sys.dm.SysIOTemplateField;
//import com.fsmflying.sys.dm.SysLog;
import com.fsmflying.sys.dm.SysMenu;
import com.fsmflying.sys.dm.SysRole;
//import com.fsmflying.sys.dm.SysSequence;
import com.fsmflying.sys.dm.SysTab;
import com.fsmflying.sys.dm.SysUser;
import com.fsmflying.sys.dm.helper.UserPermissions;
import com.fsmflying.util.TwoTuple;

public interface ISystemManageService {
	
	public <T> List<T> getListOf(Class<T> cls,List<SqlParameter<?>> parameters);
	public <T> T getModelOf(Class<T> clazz,List<SqlParameter<?>> parameters);
	public <T> boolean deleteModelOf(Class<T> clazz,List<SqlParameter<?>> parameters);
	
	public boolean add(SysUser model);
	public boolean save(SysUser model);
	public boolean deleteModelOfSysUser(int id);
	public SysUser getModelOfSysUser(int id);
	public SysUser getModelOfSysUser(String username,boolean refreshCache);
	public List<SysUser> getListOfSysUser();
	public List<SysUser> getListOfSysUser(List<SqlParameter<?>> parameters);
	
	public boolean add(SysRole model);
	public boolean save(SysRole model);
	public boolean deleteModelOfSysRole(int id);
	public SysRole getModelOfSysRole(int id);
	public List<SysRole> getListOfSysRole();
	
	public boolean add(SysCompany model);
	public boolean save(SysCompany model);
	public boolean deleteModelOfSysCompany(int id);
	public SysCompany getModelOfSysCompany(int id);
	public List<SysCompany> getListOfSysCompany();
	
	public boolean add(SysDepartment model);
	public boolean save(SysDepartment model);
	public boolean deleteModelOfSysDepartment(int id);
	public SysDepartment getModelOfSysDepartment(int id);
	public List<SysDepartment> getListOfSysDepartment();
	
	public boolean add(SysEmployee model);
	public boolean save(SysEmployee model);
	public boolean deleteModelOfSysEmployee(int id);
	public SysEmployee getModelOfSysEmployee(int id);
	public List<SysEmployee> getListOfSysEmployee();
	
	public boolean add(SysTab model);
	public boolean save(SysTab model);
	public boolean deleteModelOfSysTab(int id);
	public SysTab getModelOfSysTab(int id);
	public List<SysTab> getListOfSysTab();
	
	public boolean add(SysMenu model);
	public boolean save(SysMenu model);
	public boolean deleteModelOfSysMenu(int id);
	public SysMenu getModelOfSysMenu(int id);
	public List<SysMenu> getListOfSysMenu();
	
	public boolean add(SysFuncPoint model);
	public boolean save(SysFuncPoint model);
	public boolean deleteModelOfSysFuncPoint(int id);
	public SysFuncPoint getModelOfSysFuncPoint(int id);
	public List<SysFuncPoint> getListOfSysFuncPoint();
	
	public boolean add(SysConfig model);
	public boolean save(SysConfig model);
	public boolean deleteModelOfSysConfig(int id);
	public SysConfig getModelOfSysConfig(int id);
	public List<SysConfig> getListOfSysConfig();
	
	public boolean add(SysCustomPage model);
	public boolean save(SysCustomPage model);
	public boolean deleteModelOfSysCustomPage(int id);
	public SysCustomPage getModelOfSysCustomPage(int id);
	public List<SysCustomPage> getListOfSysCustomPage();
	
	public boolean add(SysDataAuth model);
	public boolean save(SysDataAuth model);
	public boolean deleteModelOfSysDataAuth(int id);
	public SysDataAuth getModelOfSysDataAuth(int id);
	public List<SysDataAuth> getListOfSysDataAuth();
	
	public boolean add(SysDataAuthItem model);
	public boolean save(SysDataAuthItem model);
	public boolean deleteModelOfSysDataAuthItem(int id);
	public SysDataAuthItem getModelOfSysDataAuthItem(int id);
	public List<SysDataAuthItem> getListOfSysDataAuthItem();
	
	public boolean add(SysDictDir model);
	public boolean save(SysDictDir model);
	public boolean deleteModelOfSysDictDir(int id);
	public SysDictDir getModelOfSysDictDir(int id);
	public List<SysDictDir> getListOfSysDictDir();
	
	public boolean add(SysDictItem model);
	public boolean save(SysDictItem model);
	public boolean deleteModelOfSysDictItem(int id);
	public SysDictItem getModelOfSysDictItem(int id);
	public List<SysDictItem> getListOfSysDictItem();
	public List<SysDictItem> getListOfSysDictItem(String dirKeyCode);
	
//	public boolean add(SysSequence model);
//	public boolean save(SysSequence model);
//	public boolean deleteModelOfSysSequence(String id);
//	public SysSequence getModelOfSysSequence(String id);
//	public List<SysSequence> getListOfSysSequence();
	
	public boolean add(SysFile model);
	public boolean save(SysFile model);
	public boolean deleteModelOfSysFile(int id);
	public SysFile getModelOfSysFile(int id);
	public List<SysFile> getListOfSysFile();
	
//	public boolean add(SysIOTemplate model);
//	public boolean save(SysIOTemplate model);
//	public boolean deleteModelOfSysIOTemplate(int id);
//	public SysIOTemplate getModelOfSysIOTemplate(int id);
//	public List<SysIOTemplate> getListOfSysIOTemplate();
//	
//	public boolean add(SysIOTemplateColumn model);
//	public boolean save(SysIOTemplateColumn model);
//	public boolean deleteModelOfSysIOTemplateColumn(int id);
//	public SysIOTemplateColumn getModelOfSysIOTemplateColumn(int id);
//	public List<SysIOTemplateColumn> getListOfSysIOTemplateColumn();
//	
//	public boolean add(SysIOTemplateField model);
//	public boolean save(SysIOTemplateField model);
//	public boolean deleteModelOfSysIOTemplateField(int id);
//	public SysIOTemplateField getModelOfSysIOTemplateField(int id);
//	public List<SysIOTemplateField> getListOfSysIOTemplateField();
	
//	public boolean add(SysLog model);
//	public boolean save(SysLog model);
//	public boolean deleteModelOfSysLog(int id);
//	public SysLog getModelOfSysLog(int id);
//	public List<SysLog> getListOfSysLog();
	
	
//	public boolean add(SysArea model);
//	public boolean save(SysArea model);
//	public boolean deleteModelOfSysArea(int id);
//	public SysArea getModelOfSysArea(int id);
//	public List<SysArea> getListOfSysArea();
	
	public List<SysRole> getUserRoles(int userId);
	public List<SysTab> getUserTabs(int userId);
	public List<SysMenu> getUserMenus(int userId);
	public List<SysFuncPoint> getUserFuncPoints(int userId);
	public List<SysDataAuth> getUserDataAuths(int userId);
	public List<SysDataAuthItem> getUserDataAuthItems(int userId);
	public UserPermissions getUserPermissions(int userId);

	public List<SysMenu> getTabMenus(int tabId);
	
	public List<TwoTuple<Integer,Integer>> getTabMenuTuples();
}
