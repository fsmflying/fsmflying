package com.fsmflying.spring.dao.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.fsmflying.sys.dao.SystemManageDao;
import com.fsmflying.sys.dm.SysArea;
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
import com.fsmflying.sys.dm.SysIOTemplate;
import com.fsmflying.sys.dm.SysIOTemplateColumn;
import com.fsmflying.sys.dm.SysIOTemplateField;
import com.fsmflying.sys.dm.SysLog;
import com.fsmflying.sys.dm.SysMenu;
import com.fsmflying.sys.dm.SysRole;
import com.fsmflying.sys.dm.SysSequence;
import com.fsmflying.sys.dm.SysTab;
import com.fsmflying.sys.dm.SysUser;
//import org.springframework


public class SystemManageDaoImpl implements SystemManageDao{

	JdbcTemplate mJdbcTemplate = new JdbcTemplate();
	
	@Resource
	@Override
	public void setDataSource(DataSource dataSource) {
		
		this.mJdbcTemplate.setDataSource(dataSource);
	}

	@Override
	public boolean add(SysUser model) {
		
		return false;
	}

	@Override
	public boolean save(SysUser model) {
		
		return false;
	}

	@Override
	public boolean deleteModelOfSysUser(int id) {
		
		return false;
	}

	@Override
	public SysUser getModelOfSysUser(int id) {
		
		return null;
	}

	@Override
	public List<SysUser> getListOfSysUser() {
		
		return null;
	}

	@Override
	public boolean add(SysRole model) {
		
		return false;
	}

	@Override
	public boolean save(SysRole model) {
		
		return false;
	}

	@Override
	public boolean deleteModelOfSysRole(int id) {
		
		return false;
	}

	@Override
	public SysRole getModelOfSysRole(int id) {
		
		return null;
	}

	@Override
	public List<SysRole> getListOfSysRole() {
		
		return null;
	}

	@Override
	public boolean add(SysCompany model) {
		
		return false;
	}

	@Override
	public boolean save(SysCompany model) {
		
		return false;
	}

	@Override
	public boolean deleteModelOfSysCompany(int id) {
		
		return false;
	}

	@Override
	public SysCompany getModelOfSysCompany(int id) {
		
		return null;
	}

	@Override
	public List<SysCompany> getListOfSysCompany() {
		
		return null;
	}

	@Override
	public boolean add(SysDepartment model) {
		
		return false;
	}

	@Override
	public boolean save(SysDepartment model) {
		
		return false;
	}

	@Override
	public boolean deleteModelOfSysDepartment(int id) {
		
		return false;
	}

	@Override
	public SysDepartment getModelOfSysDepartment(int id) {
		
		return null;
	}

	@Override
	public List<SysDepartment> getListOfSysDepartment() {
		
		return null;
	}

	@Override
	public boolean add(SysEmployee model) {
		
		return false;
	}

	@Override
	public boolean save(SysEmployee model) {
		
		return false;
	}

	@Override
	public boolean deleteModelOfSysEmployee(int id) {
		
		return false;
	}

	@Override
	public SysEmployee getModelOfSysEmployee(int id) {
		
		return null;
	}

	@Override
	public List<SysEmployee> getListOfSysEmployee() {
		
		return null;
	}

	@Override
	public boolean add(SysTab model) {
		
		return false;
	}

	@Override
	public boolean save(SysTab model) {
		
		return false;
	}

	@Override
	public boolean deleteModelOfSysTab(int id) {
		
		return false;
	}

	@Override
	public SysTab getModelOfSysTab(int id) {
		
		return null;
	}

	@Override
	public List<SysTab> getListOfSysTab() {
		
		return null;
	}

	@Override
	public boolean add(SysMenu model) {
		
		return false;
	}

	@Override
	public boolean save(SysMenu model) {
		
		return false;
	}

	@Override
	public boolean deleteModelOfSysMenu(int id) {
		
		return false;
	}

	@Override
	public SysMenu getModelOfSysMenu(int id) {
		
		return null;
	}

	@Override
	public List<SysMenu> getListOfSysMenu() {
		
		return null;
	}

	@Override
	public boolean add(SysFuncPoint model) {
		
		return false;
	}

	@Override
	public boolean save(SysFuncPoint model) {
		
		return false;
	}

	@Override
	public boolean deleteModelOfSysFuncPoint(int id) {
		
		return false;
	}

	@Override
	public SysFuncPoint getModelOfSysFuncPoint(int id) {
		
		return null;
	}

	@Override
	public List<SysFuncPoint> getListOfSysFuncPoint() {
		
		return null;
	}

	@Override
	public boolean add(SysConfig model) {
		
		return false;
	}

	@Override
	public boolean save(SysConfig model) {
		
		return false;
	}

	@Override
	public boolean deleteModelOfSysConfig(int id) {
		
		return false;
	}

	@Override
	public SysConfig getModelOfSysConfig(int id) {
		
		return null;
	}

	@Override
	public List<SysConfig> getListOfSysConfig() {
		
		return null;
	}

	@Override
	public boolean add(SysCustomPage model) {
		
		return false;
	}

	@Override
	public boolean save(SysCustomPage model) {
		
		return false;
	}

	@Override
	public boolean deleteModelOfSysCustomPage(int id) {
		
		return false;
	}

	@Override
	public SysCustomPage getModelOfSysCustomPage(int id) {
		
		return null;
	}

	@Override
	public List<SysCustomPage> getListOfSysCustomPage() {
		
		return null;
	}

	@Override
	public boolean add(SysDataAuth model) {
		
		return false;
	}

	@Override
	public boolean save(SysDataAuth model) {
		
		return false;
	}

	@Override
	public boolean deleteModelOfSysDataAuth(int id) {
		
		return false;
	}

	@Override
	public SysDataAuth getModelOfSysDataAuth(int id) {
		
		return null;
	}

	@Override
	public List<SysDataAuth> getListOfSysDataAuth() {
		
		return null;
	}

	@Override
	public boolean add(SysDataAuthItem model) {
		
		return false;
	}

	@Override
	public boolean save(SysDataAuthItem model) {
		
		return false;
	}

	@Override
	public boolean deleteModelOfSysDataAuthItem(int id) {
		
		return false;
	}

	@Override
	public SysDataAuthItem getModelOfSysDataAuthItem(int id) {
		
		return null;
	}

	@Override
	public List<SysDataAuthItem> getListOfSysDataAuthItem() {
		
		return null;
	}

	@Override
	public boolean add(SysDictDir model) {
		
		return false;
	}

	@Override
	public boolean save(SysDictDir model) {
		
		return false;
	}

	@Override
	public boolean deleteModelOfSysDictDir(int id) {
		
		return false;
	}

	@Override
	public SysDictDir getModelOfSysDictDir(int id) {
		
		return null;
	}

	@Override
	public List<SysDictDir> getListOfSysDictDir() {
		
		return null;
	}

	@Override
	public boolean add(SysDictItem model) {
		
		return false;
	}

	@Override
	public boolean save(SysDictItem model) {
		
		return false;
	}

	@Override
	public boolean deleteModelOfSysDictItem(int id) {
		
		return false;
	}

	@Override
	public SysDictItem getModelOfSysDictItem(int id) {
		
		return null;
	}

	@Override
	public List<SysDictItem> getListOfSysDictItem() {
		
		return null;
	}

	@Override
	public boolean add(SysSequence model) {
		
		return false;
	}

	@Override
	public boolean save(SysSequence model) {
		
		return false;
	}

	@Override
	public boolean deleteModelOfSysSequence(int id) {
		
		return false;
	}

	@Override
	public SysSequence getModelOfSysSequence(int id) {
		
		return null;
	}

	@Override
	public List<SysSequence> getListOfSysSequence() {
		
		return null;
	}

	@Override
	public boolean add(SysFile model) {
		
		return false;
	}

	@Override
	public boolean save(SysFile model) {
		
		return false;
	}

	@Override
	public boolean deleteModelOfSysFile(int id) {
		
		return false;
	}

	@Override
	public SysFile getModelOfSysFile(int id) {
		
		return null;
	}

	@Override
	public List<SysFile> getListOfSysFile() {
		
		return null;
	}

	@Override
	public boolean add(SysIOTemplate model) {
		
		return false;
	}

	@Override
	public boolean save(SysIOTemplate model) {
		
		return false;
	}

	@Override
	public boolean deleteModelOfSysIOTemplate(int id) {
		
		return false;
	}

	@Override
	public SysIOTemplate getModelOfSysIOTemplate(int id) {
		
		return null;
	}

	@Override
	public List<SysIOTemplate> getListOfSysIOTemplate() {
		
		return null;
	}

	@Override
	public boolean add(SysIOTemplateColumn model) {
		
		return false;
	}

	@Override
	public boolean save(SysIOTemplateColumn model) {
		
		return false;
	}

	@Override
	public boolean deleteModelOfSysIOTemplateColumn(int id) {
		
		return false;
	}

	@Override
	public SysIOTemplateColumn getModelOfSysIOTemplateColumn(int id) {
		
		return null;
	}

	@Override
	public List<SysIOTemplateColumn> getListOfSysIOTemplateColumn() {
		
		return null;
	}

	@Override
	public boolean add(SysIOTemplateField model) {
		
		return false;
	}

	@Override
	public boolean save(SysIOTemplateField model) {
		
		return false;
	}

	@Override
	public boolean deleteModelOfSysIOTemplateField(int id) {
		
		return false;
	}

	@Override
	public SysIOTemplateField getModelOfSysIOTemplateField(int id) {
		
		return null;
	}

	@Override
	public List<SysIOTemplateField> getListOfSysIOTemplateField() {
		
		return null;
	}

	@Override
	public boolean add(SysLog model) {
		
		return false;
	}

	@Override
	public boolean save(SysLog model) {
		
		return false;
	}

	@Override
	public boolean deleteModelOfSysLog(int id) {
		
		return false;
	}

	@Override
	public SysLog getModelOfSysLog(int id) {
		
		return null;
	}

	@Override
	public List<SysLog> getListOfSysLog() {
		
		return null;
	}

	@Override
	public boolean add(SysArea model) {
		
		return false;
	}

	@Override
	public boolean save(SysArea model) {
		
		return false;
	}

	@Override
	public boolean deleteModelOfSysArea(int id) {
		
		return false;
	}

	@Override
	public SysArea getModelOfSysArea(int id) {
		
		return null;
	}

	@Override
	public List<SysArea> getListOfSysArea() {
		
		return null;
	}

}
