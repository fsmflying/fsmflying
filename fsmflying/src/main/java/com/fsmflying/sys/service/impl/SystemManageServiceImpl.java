package com.fsmflying.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.fsmflying.helpers.SqlParameter;
import com.fsmflying.sys.dao.ISystemManageDao;
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
import com.fsmflying.sys.service.ISystemManageService;
import com.fsmflying.util.TwoTuple;

public class SystemManageServiceImpl implements ISystemManageService {

	private ISystemManageDao systemManageDao;
	
	@Resource
	public void setSystemManageDao(ISystemManageDao systemManageDao)
	{
		this.systemManageDao = systemManageDao;
	}
	
	@Override
	public <T> List<T> getListOf(Class<T> cls, List<SqlParameter<?>> parameters) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public <T> T getModelOf(Class<T> clazz,List<SqlParameter<?>> parameters){
		return null;
	}
	
	@Override
	public <T> boolean deleteModelOf(Class<T> clazz,List<SqlParameter<?>> parameters){
		return false;
	}
	

	@Override
	public boolean add(SysUser model) {
		return systemManageDao.add(model);
	}

	@Override
	public boolean save(SysUser model) {
		return systemManageDao.save(model);
	}

	@Override
	public boolean deleteModelOfSysUser(int id) {
		return systemManageDao.deleteModelOfSysUser(id);
	}

	@Override
	public SysUser getModelOfSysUser(int id) {
		return systemManageDao.getModelOfSysUser(id);
	}
	
	@Override
	public SysUser getModelOfSysUser(String username,boolean refreshCache) {
		return systemManageDao.getModelOfSysUser(username);
	}
	
	@Override
	public List<SysUser> getListOfSysUser() {
		return systemManageDao.getListOfSysUser();
	}
	
	public List<SysUser> getListOfSysUser(List<SqlParameter<?>> parameters)
	{
		
		return null;
	}

	@Override
	public boolean add(SysRole model) {
		return systemManageDao.add(model);
	}

	@Override
	public boolean save(SysRole model) {
		return systemManageDao.save(model);
	}

	@Override
	public boolean deleteModelOfSysRole(int id) {
		return systemManageDao.deleteModelOfSysUser(id);
	}

	@Override
	public SysRole getModelOfSysRole(int id) {
		return systemManageDao.getModelOfSysRole(id);
	}

	@Override
	public List<SysRole> getListOfSysRole() {
		return systemManageDao.getListOfSysRole();
	}

	@Override
	public boolean add(SysCompany model) {
		return systemManageDao.add(model);
	}

	@Override
	public boolean save(SysCompany model) {
		return systemManageDao.save(model);
	}

	@Override
	public boolean deleteModelOfSysCompany(int id) {
		return systemManageDao.deleteModelOfSysCompany(id);
	}

	@Override
	public SysCompany getModelOfSysCompany(int id) {
		return systemManageDao.getModelOfSysCompany(id);
	}

	@Override
	public List<SysCompany> getListOfSysCompany() {
		return systemManageDao.getListOfSysCompany();
	}

	@Override
	public boolean add(SysDepartment model) {
		return systemManageDao.add(model);
	}

	@Override
	public boolean save(SysDepartment model) {
		return systemManageDao.save(model);
	}

	@Override
	public boolean deleteModelOfSysDepartment(int id) {
		return systemManageDao.deleteModelOfSysDepartment(id);
	}

	@Override
	public SysDepartment getModelOfSysDepartment(int id) {
		return systemManageDao.getModelOfSysDepartment(id);
	}

	@Override
	public List<SysDepartment> getListOfSysDepartment() {
		return systemManageDao.getListOfSysDepartment();
	}

	@Override
	public boolean add(SysEmployee model) {
		return systemManageDao.add(model);
	}

	@Override
	public boolean save(SysEmployee model) {
		return systemManageDao.save(model);
	}

	@Override
	public boolean deleteModelOfSysEmployee(int id) {
		return systemManageDao.deleteModelOfSysEmployee(id);
	}

	@Override
	public SysEmployee getModelOfSysEmployee(int id) {
		return systemManageDao.getModelOfSysEmployee(id);
	}

	@Override
	public List<SysEmployee> getListOfSysEmployee() {
		return systemManageDao.getListOfSysEmployee();
	}

	@Override
	public boolean add(SysTab model) {
		return systemManageDao.add(model);
	}

	@Override
	public boolean save(SysTab model) {
		return systemManageDao.save(model);
	}

	@Override
	public boolean deleteModelOfSysTab(int id) {
		return systemManageDao.deleteModelOfSysTab(id);
	}

	@Override
	public SysTab getModelOfSysTab(int id) {
		return systemManageDao.getModelOfSysTab(id);
	}

	@Override
	public List<SysTab> getListOfSysTab() {
		return systemManageDao.getListOfSysTab();
	}

	@Override
	public boolean add(SysMenu model) {
		return systemManageDao.add(model);
	}

	@Override
	public boolean save(SysMenu model) {
		return systemManageDao.save(model);
	}

	@Override
	public boolean deleteModelOfSysMenu(int id) {
		return systemManageDao.deleteModelOfSysMenu(id);
	}

	@Override
	public SysMenu getModelOfSysMenu(int id) {
		return systemManageDao.getModelOfSysMenu(id);
	}

	@Override
	public List<SysMenu> getListOfSysMenu() {
		return systemManageDao.getListOfSysMenu();
	}

	@Override
	public boolean add(SysFuncPoint model) {
		return systemManageDao.add(model);
	}

	@Override
	public boolean save(SysFuncPoint model) {
		return systemManageDao.save(model);
	}

	@Override
	public boolean deleteModelOfSysFuncPoint(int id) {
		return systemManageDao.deleteModelOfSysFuncPoint(id);
	}

	@Override
	public SysFuncPoint getModelOfSysFuncPoint(int id) {
		return systemManageDao.getModelOfSysFuncPoint(id);
	}

	@Override
	public List<SysFuncPoint> getListOfSysFuncPoint() {
		return systemManageDao.getListOfSysFuncPoint();
	}

	@Override
	public boolean add(SysConfig model) {
		return systemManageDao.add(model);
	}

	@Override
	public boolean save(SysConfig model) {
		return systemManageDao.save(model);
	}

	@Override
	public boolean deleteModelOfSysConfig(int id) {
		return systemManageDao.deleteModelOfSysConfig(id);
	}

	@Override
	public SysConfig getModelOfSysConfig(int id) {
		return systemManageDao.getModelOfSysConfig(id);
	}

	@Override
	public List<SysConfig> getListOfSysConfig() {
		return systemManageDao.getListOfSysConfig();
	}

	@Override
	public boolean add(SysCustomPage model) {
		return systemManageDao.add(model);
	}

	@Override
	public boolean save(SysCustomPage model) {
		return systemManageDao.save(model);
	}

	@Override
	public boolean deleteModelOfSysCustomPage(int id) {
		return systemManageDao.deleteModelOfSysCustomPage(id);
	}

	@Override
	public SysCustomPage getModelOfSysCustomPage(int id) {
		return systemManageDao.getModelOfSysCustomPage(id);
	}

	@Override
	public List<SysCustomPage> getListOfSysCustomPage() {
		return systemManageDao.getListOfSysCustomPage();
	}

	@Override
	public boolean add(SysDataAuth model) {
		return systemManageDao.add(model);
	}

	@Override
	public boolean save(SysDataAuth model) {
		return systemManageDao.save(model);
	}

	@Override
	public boolean deleteModelOfSysDataAuth(int id) {
		return systemManageDao.deleteModelOfSysDataAuth(id);
	}

	@Override
	public SysDataAuth getModelOfSysDataAuth(int id) {
		return systemManageDao.getModelOfSysDataAuth(id);
	}

	@Override
	public List<SysDataAuth> getListOfSysDataAuth() {
		return systemManageDao.getListOfSysDataAuth();
	}

	@Override
	public boolean add(SysDataAuthItem model) {
		return systemManageDao.add(model);
	}

	@Override
	public boolean save(SysDataAuthItem model) {
		return systemManageDao.save(model);
	}

	@Override
	public boolean deleteModelOfSysDataAuthItem(int id) {
		return systemManageDao.deleteModelOfSysDataAuthItem(id);
	}

	@Override
	public SysDataAuthItem getModelOfSysDataAuthItem(int id) {
		return systemManageDao.getModelOfSysDataAuthItem(id);
	}

	@Override
	public List<SysDataAuthItem> getListOfSysDataAuthItem() {
		return systemManageDao.getListOfSysDataAuthItem();
	}

	@Override
	public boolean add(SysDictDir model) {
		return systemManageDao.add(model);
	}

	@Override
	public boolean save(SysDictDir model) {
		return systemManageDao.save(model);
	}

	@Override
	public boolean deleteModelOfSysDictDir(int id) {
		return systemManageDao.deleteModelOfSysDictDir(id);
	}

	@Override
	public SysDictDir getModelOfSysDictDir(int id) {
		return systemManageDao.getModelOfSysDictDir(id);
	}

	@Override
	public List<SysDictDir> getListOfSysDictDir() {
		return systemManageDao.getListOfSysDictDir();
	}

	@Override
	public boolean add(SysDictItem model) {
		return systemManageDao.add(model);
	}

	@Override
	public boolean save(SysDictItem model) {
		return systemManageDao.save(model);
	}

	@Override
	public boolean deleteModelOfSysDictItem(int id) {
		return systemManageDao.deleteModelOfSysDictItem(id);
	}

	@Override
	public SysDictItem getModelOfSysDictItem(int id) {
		return systemManageDao.getModelOfSysDictItem(id);
	}

	@Override
	public List<SysDictItem> getListOfSysDictItem() {
		return systemManageDao.getListOfSysDictItem();
	}

	@Override
	public List<SysDictItem> getListOfSysDictItem(String dirKeyCode) {
		return systemManageDao.getListOfSysDictItem(dirKeyCode);
	}
	
	

//	@Override
//	public boolean add(SysSequence model) {
//		return systemManageDao.add(model);
//	}
//
//	@Override
//	public boolean save(SysSequence model) {
//		return systemManageDao.save(model);
//	}
//
//	@Override
//	public boolean deleteModelOfSysSequence(String id) {
//		return systemManageDao.deleteModelOfSysSequence(id);
//	}
//
//	@Override
//	public SysSequence getModelOfSysSequence(String id) {
//		return systemManageDao.getModelOfSysSequence(id);
//	}
//
//	@Override
//	public List<SysSequence> getListOfSysSequence() {
//		return systemManageDao.getListOfSysSequence();
//	}

	@Override
	public boolean add(SysFile model) {
		return systemManageDao.add(model);
	}

	@Override
	public boolean save(SysFile model) {
		return systemManageDao.save(model);
	}

	@Override
	public boolean deleteModelOfSysFile(int id) {
		return systemManageDao.deleteModelOfSysFile(id);
	}

	@Override
	public SysFile getModelOfSysFile(int id) {
		return systemManageDao.getModelOfSysFile(id);
	}

	@Override
	public List<SysFile> getListOfSysFile() {
		return systemManageDao.getListOfSysFile();
	}

//	@Override
//	public boolean add(SysIOTemplate model) {
//		return systemManageDao.add(model);
//	}
//
//	@Override
//	public boolean save(SysIOTemplate model) {
//		return systemManageDao.save(model);
//	}
//
//	@Override
//	public boolean deleteModelOfSysIOTemplate(int id) {
//		return systemManageDao.deleteModelOfSysIOTemplate(id);
//	}
//
//	@Override
//	public SysIOTemplate getModelOfSysIOTemplate(int id) {
//		return systemManageDao.getModelOfSysIOTemplate(id);
//	}
//
//	@Override
//	public List<SysIOTemplate> getListOfSysIOTemplate() {
//		return systemManageDao.getListOfSysIOTemplate();
//	}
//
//	@Override
//	public boolean add(SysIOTemplateColumn model) {
//		return systemManageDao.add(model);
//	}
//
//	@Override
//	public boolean save(SysIOTemplateColumn model) {
//		return systemManageDao.save(model);
//	}
//
//	@Override
//	public boolean deleteModelOfSysIOTemplateColumn(int id) {
//		return systemManageDao.deleteModelOfSysIOTemplateColumn(id);
//	}
//
//	@Override
//	public SysIOTemplateColumn getModelOfSysIOTemplateColumn(int id) {
//		return systemManageDao.getModelOfSysIOTemplateColumn(id);
//	}
//
//	@Override
//	public List<SysIOTemplateColumn> getListOfSysIOTemplateColumn() {
//		return systemManageDao.getListOfSysIOTemplateColumn();
//	}
//
//	@Override
//	public boolean add(SysIOTemplateField model) {
//		return systemManageDao.add(model);
//	}
//
//	@Override
//	public boolean save(SysIOTemplateField model) {
//		return systemManageDao.save(model);
//	}
//
//	@Override
//	public boolean deleteModelOfSysIOTemplateField(int id) {
//		return systemManageDao.deleteModelOfSysIOTemplateField(id);
//	}
//
//	@Override
//	public SysIOTemplateField getModelOfSysIOTemplateField(int id) {
//		return systemManageDao.getModelOfSysIOTemplateField(id);
//	}
//
//	@Override
//	public List<SysIOTemplateField> getListOfSysIOTemplateField() {
//		return systemManageDao.getListOfSysIOTemplateField();
//	}
//
//	@Override
//	public boolean add(SysLog model) {
//		return systemManageDao.add(model);
//	}
//
//	@Override
//	public boolean save(SysLog model) {
//		return systemManageDao.save(model);
//	}
//
//	@Override
//	public boolean deleteModelOfSysLog(int id) {
//		return systemManageDao.deleteModelOfSysLog(id);
//	}
//
//	@Override
//	public SysLog getModelOfSysLog(int id) {
//		return systemManageDao.getModelOfSysLog(id);
//	}
//
//	@Override
//	public List<SysLog> getListOfSysLog() {
//		return getListOfSysLog();
//	}
//
//	@Override
//	public boolean add(SysArea model) {
//		return systemManageDao.add(model);
//	}
//
//	@Override
//	public boolean save(SysArea model) {
//		return systemManageDao.save(model);
//	}
//
//	@Override
//	public boolean deleteModelOfSysArea(int id) {
//		return systemManageDao.deleteModelOfSysArea(id);
//	}
//
//	@Override
//	public SysArea getModelOfSysArea(int id) {
//		return systemManageDao.getModelOfSysArea(id);
//	}
//
//	@Override
//	public List<SysArea> getListOfSysArea() {
//	
//		return systemManageDao.getListOfSysArea();
//	}

	@Override
	public List<SysRole> getUserRoles(int userId) {
//		@SuppressWarnings("unchecked")
//		List<SysRole> list = (List<SysRole>)cacheService.get("[SysRole][List][Of][User]["+userId+"]");
//		if(list==null){
//			list = systemManageDao.getUserRoles(userId);
//			cacheService.put("[User]["+userId+"][Role][List]", list);
//		}
//		return list;
		return systemManageDao.getUserRoles(userId);
	}

	@Override
	public List<SysTab> getUserTabs(int userId) {
//		@SuppressWarnings("unchecked")
//		List<SysTab> list = (List<SysTab>)cacheService.get("[SysTab][List][Of][User]["+userId+"]");
//		if(list==null){
//			list = systemManageDao.getUserTabs(userId);
//			cacheService.put("[User]["+userId+"][Role][List]", list);
//		}
//		return list;
		return systemManageDao.getUserTabs(userId);
	}

	@Override
	public List<SysMenu> getUserMenus(int userId) {
		return systemManageDao.getUserMenus(userId);
	}

	@Override
	public List<SysFuncPoint> getUserFuncPoints(int userId) {
		return systemManageDao.getUserFuncPoints(userId);
	}

	@Override
	public List<SysDataAuth> getUserDataAuths(int userId) {
		return systemManageDao.getUserDataAuths(userId);
	}

	@Override
	public List<SysDataAuthItem> getUserDataAuthItems(int userId) {
		return systemManageDao.getUserDataAuthItems(userId);
	}

	@Override
	public List<SysMenu> getTabMenus(int tabId) {
		return systemManageDao.getTabMenus(tabId);
	}

	@Override
	public UserPermissions getUserPermissions(int userId) {
		return systemManageDao.getUserPermissions(userId);
	}

	@Override
	public List<TwoTuple<Integer, Integer>> getTabMenuTuples() {
		return systemManageDao.getTabMenuTuples();
	}





	

	
}
