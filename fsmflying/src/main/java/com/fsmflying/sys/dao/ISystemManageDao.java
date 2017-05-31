package com.fsmflying.sys.dao;

import java.util.List;

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
import com.fsmflying.sys.dm.SysMenu;
import com.fsmflying.sys.dm.SysRole;
import com.fsmflying.sys.dm.SysTab;
import com.fsmflying.sys.dm.SysUser;
import com.fsmflying.sys.dm.helper.UserPermissions;
import com.fsmflying.util.TwoTuple;

public interface ISystemManageDao {

	boolean add(SysUser model);

	boolean save(SysUser model);

	boolean deleteModelOfSysUser(int id);

	SysUser getModelOfSysUser(int id);

	SysUser getModelOfSysUser(String username);

	List<SysUser> getListOfSysUser();

	boolean add(SysRole model);

	boolean save(SysRole model);

	boolean deleteModelOfSysRole(int id);

	SysRole getModelOfSysRole(int id);

	List<SysRole> getListOfSysRole();

	boolean add(SysCompany model);

	boolean save(SysCompany model);

	boolean deleteModelOfSysCompany(int id);

	SysCompany getModelOfSysCompany(int id);

	List<SysCompany> getListOfSysCompany();

	boolean add(SysDepartment model);

	boolean save(SysDepartment model);

	boolean deleteModelOfSysDepartment(int id);

	SysDepartment getModelOfSysDepartment(int id);

	List<SysDepartment> getListOfSysDepartment();

	boolean add(SysEmployee model);

	boolean save(SysEmployee model);

	boolean deleteModelOfSysEmployee(int id);

	SysEmployee getModelOfSysEmployee(int id);

	List<SysEmployee> getListOfSysEmployee();

	boolean add(SysTab model);

	boolean save(SysTab model);

	boolean deleteModelOfSysTab(int id);

	SysTab getModelOfSysTab(int id);

	List<SysTab> getListOfSysTab();

	boolean add(SysMenu model);

	boolean save(SysMenu model);

	boolean deleteModelOfSysMenu(int id);

	SysMenu getModelOfSysMenu(int id);

	List<SysMenu> getListOfSysMenu();

	boolean add(SysFuncPoint model);

	boolean save(SysFuncPoint model);

	boolean deleteModelOfSysFuncPoint(int id);

	SysFuncPoint getModelOfSysFuncPoint(int id);

	List<SysFuncPoint> getListOfSysFuncPoint();

	boolean add(SysConfig model);

	boolean save(SysConfig model);

	boolean deleteModelOfSysConfig(int id);

	SysConfig getModelOfSysConfig(int id);

	List<SysConfig> getListOfSysConfig();

	boolean add(SysCustomPage model);

	boolean save(SysCustomPage model);

	boolean deleteModelOfSysCustomPage(int id);

	SysCustomPage getModelOfSysCustomPage(int id);

	List<SysCustomPage> getListOfSysCustomPage();

	boolean add(SysDataAuth model);

	boolean save(SysDataAuth model);

	boolean deleteModelOfSysDataAuth(int id);

	SysDataAuth getModelOfSysDataAuth(int id);

	List<SysDataAuth> getListOfSysDataAuth();

	boolean add(SysDataAuthItem model);

	boolean save(SysDataAuthItem model);

	boolean deleteModelOfSysDataAuthItem(int id);

	SysDataAuthItem getModelOfSysDataAuthItem(int id);

	List<SysDataAuthItem> getListOfSysDataAuthItem();

	boolean add(SysDictDir model);

	boolean save(SysDictDir model);

	boolean deleteModelOfSysDictDir(int id);

	SysDictDir getModelOfSysDictDir(int id);

	List<SysDictDir> getListOfSysDictDir();

	boolean add(SysDictItem model);

	boolean save(SysDictItem model);

	boolean deleteModelOfSysDictItem(int id);

	SysDictItem getModelOfSysDictItem(int id);

	List<SysDictItem> getListOfSysDictItem();

	java.util.List<SysDictItem> getListOfSysDictItem(String dirKeyCode);
/*
	boolean add(SysSequence model);

	boolean save(SysSequence model);

	boolean deleteModelOfSysSequence(String id);

	SysSequence getModelOfSysSequence(String id);

	List<SysSequence> getListOfSysSequence();
*/
	boolean add(SysFile model);

	boolean save(SysFile model);

	boolean deleteModelOfSysFile(int id);

	SysFile getModelOfSysFile(int id);

	List<SysFile> getListOfSysFile();
/*
	boolean add(SysIOTemplate model);

	boolean save(SysIOTemplate model);

	boolean deleteModelOfSysIOTemplate(int id);

	SysIOTemplate getModelOfSysIOTemplate(int id);

	List<SysIOTemplate> getListOfSysIOTemplate();

	boolean add(SysIOTemplateColumn model);

	boolean save(SysIOTemplateColumn model);

	boolean deleteModelOfSysIOTemplateColumn(int id);

	SysIOTemplateColumn getModelOfSysIOTemplateColumn(int id);

	List<SysIOTemplateColumn> getListOfSysIOTemplateColumn();

	boolean add(SysIOTemplateField model);

	boolean save(SysIOTemplateField model);

	boolean deleteModelOfSysIOTemplateField(int id);

	SysIOTemplateField getModelOfSysIOTemplateField(int id);

	List<SysIOTemplateField> getListOfSysIOTemplateField();
*/
	
/*
	boolean add(SysLog model);

	boolean save(SysLog model);

	boolean deleteModelOfSysLog(int id);

	SysLog getModelOfSysLog(int id);

	List<SysLog> getListOfSysLog();
*/
	
/*
	boolean add(SysArea model);

	boolean save(SysArea model);

	boolean deleteModelOfSysArea(int id);

	SysArea getModelOfSysArea(int id);

	List<SysArea> getListOfSysArea();
*/
	List<SysRole> getUserRoles(int userId);

	List<SysTab> getUserTabs(int userId);

	List<SysMenu> getUserMenus(int userId);

	List<SysFuncPoint> getUserFuncPoints(int userId);

	List<SysDataAuth> getUserDataAuths(int userId);

	List<SysDataAuthItem> getUserDataAuthItems(int userId);

	UserPermissions getUserPermissions(int userId);

	List<SysMenu> getTabMenus(int tabId);

	List<TwoTuple<Integer, Integer>> getTabMenuTuples();
	//	Map<SysTab,List<SysMenu>> getUserTabMenus(int userId);

}