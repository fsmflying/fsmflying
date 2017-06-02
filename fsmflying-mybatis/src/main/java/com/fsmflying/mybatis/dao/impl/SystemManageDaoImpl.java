package com.fsmflying.mybatis.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.fsmflying.mybatis.dao.IMybatisAccessable;
import com.fsmflying.sys.dao.ISystemManageDao;
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

import com.fsmflying.mybatis.mapper.SysUserMapper;
import com.fsmflying.mybatis.mapper.SysEmployeeMapper;
import com.fsmflying.mybatis.mapper.SysCompanyMapper;
import com.fsmflying.mybatis.mapper.SysDepartmentMapper;
import com.fsmflying.mybatis.mapper.SysDictDirMapper;
import com.fsmflying.mybatis.mapper.SysDictItemMapper;
import com.fsmflying.mybatis.mapper.SysConfigMapper;
import com.fsmflying.mybatis.mapper.SysCustomPageMapper;
import com.fsmflying.mybatis.mapper.SysRoleMapper;
import com.fsmflying.mybatis.mapper.SysTabMapper;
import com.fsmflying.mybatis.mapper.SysMenuMapper;
import com.fsmflying.mybatis.mapper.SysFuncPointMapper;
import com.fsmflying.mybatis.mapper.SysDataAuthMapper;
import com.fsmflying.mybatis.mapper.SysDataAuthItemMapper;
import com.fsmflying.mybatis.mapper.SysFileMapper;
//import com.fsmflying.mybatis.mapper.SysLogMapper;
import com.fsmflying.mybatis.mapper.SysMapper;
//import com.fsmflying.mybatis.mapper.SysSequenceMapper;

import com.fsmflying.sys.dm.helper.UserPermissions;
import com.fsmflying.util.IntegerStringTuple;
import com.fsmflying.util.TwoIntegerTuple;
import com.fsmflying.util.TwoTuple;

public class SystemManageDaoImpl implements ISystemManageDao, IMybatisAccessable {

	SqlSessionFactory sessionFactory;

	@Override
	public void setSessionFactory(SqlSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SqlSession getSession() {
		return this.sessionFactory.openSession();
	}

	public void closeSession(SqlSession session) {
		if (session != null)
			session.close();
	}

	@Override
	public boolean add(SysUser model) {
		SqlSession session = this.getSession();
		session.getMapper(SysUserMapper.class).add(model);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public boolean save(SysUser model) {
		SqlSession session = this.getSession();
		session.getMapper(SysUserMapper.class).save(model);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public boolean deleteModelOfSysUser(int userId) {
		SqlSession session = this.getSession();
		session.getMapper(SysUserMapper.class).delete( userId);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public SysUser getModelOfSysUser(int id) {
		SqlSession session = this.getSession();
		SysUser model = session.getMapper(SysUserMapper.class).get(id);
//		session.commit();
		closeSession(session);
		return model;
	}

	@Override
	public SysUser getModelOfSysUser(String username) {
		SqlSession session = this.getSession();
		SysUser model = session.getMapper(SysUserMapper.class).getByUsername(username);
//		session.commit();
		closeSession(session);
		return model;
	}

	@Override
	public List<SysUser> getListOfSysUser() {
		SqlSession session = this.getSession();
		List<SysUser> list = session.getMapper(SysUserMapper.class).getAllList();
//		session.commit();
		closeSession(session);
		return list;
	}
	

	@Override
	public boolean add(SysEmployee model) {
		SqlSession session = this.getSession();
		session.getMapper(SysEmployeeMapper.class).add(model);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public boolean save(SysEmployee model) {
		SqlSession session = this.getSession();
		session.getMapper(SysEmployeeMapper.class).save(model);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public boolean deleteModelOfSysEmployee(int emplId) {
		SqlSession session = this.getSession();
		session.getMapper(SysEmployeeMapper.class).delete( emplId);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public SysEmployee getModelOfSysEmployee(int id) {
		SqlSession session = this.getSession();
		SysEmployee model = session.getMapper(SysEmployeeMapper.class).get(id);
//		session.commit();
		closeSession(session);
		return model;
	}

	@Override
	public List<SysEmployee> getListOfSysEmployee() {
		SqlSession session = this.getSession();
		List<SysEmployee> list = session.getMapper(SysEmployeeMapper.class).getAllList();
//		session.commit();
		closeSession(session);
		return list;
	}
	

	@Override
	public boolean add(SysCompany model) {
		SqlSession session = this.getSession();
		session.getMapper(SysCompanyMapper.class).add(model);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public boolean save(SysCompany model) {
		SqlSession session = this.getSession();
		session.getMapper(SysCompanyMapper.class).save(model);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public boolean deleteModelOfSysCompany(int companyId) {
		SqlSession session = this.getSession();
		session.getMapper(SysCompanyMapper.class).delete( companyId);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public SysCompany getModelOfSysCompany(int id) {
		SqlSession session = this.getSession();
		SysCompany model = session.getMapper(SysCompanyMapper.class).get(id);
//		session.commit();
		closeSession(session);
		return model;
	}

	@Override
	public List<SysCompany> getListOfSysCompany() {
		SqlSession session = this.getSession();
		List<SysCompany> list = session.getMapper(SysCompanyMapper.class).getAllList();
//		session.commit();
		closeSession(session);
		return list;
	}
	

	@Override
	public boolean add(SysDepartment model) {
		SqlSession session = this.getSession();
		session.getMapper(SysDepartmentMapper.class).add(model);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public boolean save(SysDepartment model) {
		SqlSession session = this.getSession();
		session.getMapper(SysDepartmentMapper.class).save(model);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public boolean deleteModelOfSysDepartment(int deptId) {
		SqlSession session = this.getSession();
		session.getMapper(SysDepartmentMapper.class).delete( deptId);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public SysDepartment getModelOfSysDepartment(int id) {
		SqlSession session = this.getSession();
		SysDepartment model = session.getMapper(SysDepartmentMapper.class).get(id);
//		session.commit();
		closeSession(session);
		return model;
	}

	@Override
	public List<SysDepartment> getListOfSysDepartment() {
		SqlSession session = this.getSession();
		List<SysDepartment> list = session.getMapper(SysDepartmentMapper.class).getAllList();
//		session.commit();
		closeSession(session);
		return list;
	}
	

	@Override
	public boolean add(SysDictDir model) {
		SqlSession session = this.getSession();
		session.getMapper(SysDictDirMapper.class).add(model);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public boolean save(SysDictDir model) {
		SqlSession session = this.getSession();
		session.getMapper(SysDictDirMapper.class).save(model);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public boolean deleteModelOfSysDictDir(int dirId) {
		SqlSession session = this.getSession();
		session.getMapper(SysDictDirMapper.class).delete( dirId);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public SysDictDir getModelOfSysDictDir(int id) {
		SqlSession session = this.getSession();
		SysDictDir model = session.getMapper(SysDictDirMapper.class).get(id);
//		session.commit();
		closeSession(session);
		return model;
	}

	@Override
	public List<SysDictDir> getListOfSysDictDir() {
		SqlSession session = this.getSession();
		List<SysDictDir> list = session.getMapper(SysDictDirMapper.class).getAllList();
//		session.commit();
		closeSession(session);
		return list;
	}
	

	@Override
	public boolean add(SysDictItem model) {
		SqlSession session = this.getSession();
		session.getMapper(SysDictItemMapper.class).add(model);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public boolean save(SysDictItem model) {
		SqlSession session = this.getSession();
		session.getMapper(SysDictItemMapper.class).save(model);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public boolean deleteModelOfSysDictItem(int itemId) {
		SqlSession session = this.getSession();
		session.getMapper(SysDictItemMapper.class).delete( itemId);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public SysDictItem getModelOfSysDictItem(int id) {
		SqlSession session = this.getSession();
		SysDictItem model = session.getMapper(SysDictItemMapper.class).get(id);
//		session.commit();
		closeSession(session);
		return model;
	}

	@Override
	public List<SysDictItem> getListOfSysDictItem() {
		SqlSession session = this.getSession();
		List<SysDictItem> list = session.getMapper(SysDictItemMapper.class).getAllList();
//		session.commit();
		closeSession(session);
		return list;
	}
	

	@Override
	public boolean add(SysConfig model) {
		SqlSession session = this.getSession();
		session.getMapper(SysConfigMapper.class).add(model);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public boolean save(SysConfig model) {
		SqlSession session = this.getSession();
		session.getMapper(SysConfigMapper.class).save(model);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public boolean deleteModelOfSysConfig(int configId) {
		SqlSession session = this.getSession();
		session.getMapper(SysConfigMapper.class).delete( configId);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public SysConfig getModelOfSysConfig(int id) {
		SqlSession session = this.getSession();
		SysConfig model = session.getMapper(SysConfigMapper.class).get(id);
//		session.commit();
		closeSession(session);
		return model;
	}

	@Override
	public List<SysConfig> getListOfSysConfig() {
		SqlSession session = this.getSession();
		List<SysConfig> list = session.getMapper(SysConfigMapper.class).getAllList();
//		session.commit();
		closeSession(session);
		return list;
	}
	

	@Override
	public boolean add(SysCustomPage model) {
		SqlSession session = this.getSession();
		session.getMapper(SysCustomPageMapper.class).add(model);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public boolean save(SysCustomPage model) {
		SqlSession session = this.getSession();
		session.getMapper(SysCustomPageMapper.class).save(model);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public boolean deleteModelOfSysCustomPage(int customPageId) {
		SqlSession session = this.getSession();
		session.getMapper(SysCustomPageMapper.class).delete( customPageId);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public SysCustomPage getModelOfSysCustomPage(int id) {
		SqlSession session = this.getSession();
		SysCustomPage model = session.getMapper(SysCustomPageMapper.class).get(id);
//		session.commit();
		closeSession(session);
		return model;
	}

	@Override
	public List<SysCustomPage> getListOfSysCustomPage() {
		SqlSession session = this.getSession();
		List<SysCustomPage> list = session.getMapper(SysCustomPageMapper.class).getAllList();
//		session.commit();
		closeSession(session);
		return list;
	}
	

	@Override
	public boolean add(SysRole model) {
		SqlSession session = this.getSession();
		session.getMapper(SysRoleMapper.class).add(model);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public boolean save(SysRole model) {
		SqlSession session = this.getSession();
		session.getMapper(SysRoleMapper.class).save(model);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public boolean deleteModelOfSysRole(int roleId) {
		SqlSession session = this.getSession();
		session.getMapper(SysRoleMapper.class).delete( roleId);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public SysRole getModelOfSysRole(int id) {
		SqlSession session = this.getSession();
		SysRole model = session.getMapper(SysRoleMapper.class).get(id);
//		session.commit();
		closeSession(session);
		return model;
	}

	@Override
	public List<SysRole> getListOfSysRole() {
		SqlSession session = this.getSession();
		List<SysRole> list = session.getMapper(SysRoleMapper.class).getAllList();
//		session.commit();
		closeSession(session);
		return list;
	}
	

	@Override
	public boolean add(SysTab model) {
		SqlSession session = this.getSession();
		session.getMapper(SysTabMapper.class).add(model);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public boolean save(SysTab model) {
		SqlSession session = this.getSession();
		session.getMapper(SysTabMapper.class).save(model);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public boolean deleteModelOfSysTab(int tabId) {
		SqlSession session = this.getSession();
		session.getMapper(SysTabMapper.class).delete( tabId);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public SysTab getModelOfSysTab(int id) {
		SqlSession session = this.getSession();
		SysTab model = session.getMapper(SysTabMapper.class).get(id);
//		session.commit();
		closeSession(session);
		return model;
	}

	@Override
	public List<SysTab> getListOfSysTab() {
		SqlSession session = this.getSession();
		List<SysTab> list = session.getMapper(SysTabMapper.class).getAllList();
//		session.commit();
		closeSession(session);
		return list;
	}
	

	@Override
	public boolean add(SysMenu model) {
		SqlSession session = this.getSession();
		session.getMapper(SysMenuMapper.class).add(model);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public boolean save(SysMenu model) {
		SqlSession session = this.getSession();
		session.getMapper(SysMenuMapper.class).save(model);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public boolean deleteModelOfSysMenu(int menuId) {
		SqlSession session = this.getSession();
		session.getMapper(SysMenuMapper.class).delete( menuId);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public SysMenu getModelOfSysMenu(int id) {
		SqlSession session = this.getSession();
		SysMenu model = session.getMapper(SysMenuMapper.class).get(id);
//		session.commit();
		closeSession(session);
		return model;
	}

	@Override
	public List<SysMenu> getListOfSysMenu() {
		SqlSession session = this.getSession();
		List<SysMenu> list = session.getMapper(SysMenuMapper.class).getAllList();
//		session.commit();
		closeSession(session);
		return list;
	}
	

	@Override
	public boolean add(SysFuncPoint model) {
		SqlSession session = this.getSession();
		session.getMapper(SysFuncPointMapper.class).add(model);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public boolean save(SysFuncPoint model) {
		SqlSession session = this.getSession();
		session.getMapper(SysFuncPointMapper.class).save(model);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public boolean deleteModelOfSysFuncPoint(int funcPointId) {
		SqlSession session = this.getSession();
		session.getMapper(SysFuncPointMapper.class).delete( funcPointId);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public SysFuncPoint getModelOfSysFuncPoint(int id) {
		SqlSession session = this.getSession();
		SysFuncPoint model = session.getMapper(SysFuncPointMapper.class).get(id);
//		session.commit();
		closeSession(session);
		return model;
	}

	@Override
	public List<SysFuncPoint> getListOfSysFuncPoint() {
		SqlSession session = this.getSession();
		List<SysFuncPoint> list = session.getMapper(SysFuncPointMapper.class).getAllList();
//		session.commit();
		closeSession(session);
		return list;
	}
	

	@Override
	public boolean add(SysDataAuth model) {
		SqlSession session = this.getSession();
		session.getMapper(SysDataAuthMapper.class).add(model);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public boolean save(SysDataAuth model) {
		SqlSession session = this.getSession();
		session.getMapper(SysDataAuthMapper.class).save(model);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public boolean deleteModelOfSysDataAuth(int authId) {
		SqlSession session = this.getSession();
		session.getMapper(SysDataAuthMapper.class).delete( authId);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public SysDataAuth getModelOfSysDataAuth(int id) {
		SqlSession session = this.getSession();
		SysDataAuth model = session.getMapper(SysDataAuthMapper.class).get(id);
//		session.commit();
		closeSession(session);
		return model;
	}

	@Override
	public List<SysDataAuth> getListOfSysDataAuth() {
		SqlSession session = this.getSession();
		List<SysDataAuth> list = session.getMapper(SysDataAuthMapper.class).getAllList();
//		session.commit();
		closeSession(session);
		return list;
	}
	

	@Override
	public boolean add(SysDataAuthItem model) {
		SqlSession session = this.getSession();
		session.getMapper(SysDataAuthItemMapper.class).add(model);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public boolean save(SysDataAuthItem model) {
		SqlSession session = this.getSession();
		session.getMapper(SysDataAuthItemMapper.class).save(model);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public boolean deleteModelOfSysDataAuthItem(int authItemId) {
		SqlSession session = this.getSession();
		session.getMapper(SysDataAuthItemMapper.class).delete( authItemId);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public SysDataAuthItem getModelOfSysDataAuthItem(int id) {
		SqlSession session = this.getSession();
		SysDataAuthItem model = session.getMapper(SysDataAuthItemMapper.class).get(id);
//		session.commit();
		closeSession(session);
		return model;
	}

	@Override
	public List<SysDataAuthItem> getListOfSysDataAuthItem() {
		SqlSession session = this.getSession();
		List<SysDataAuthItem> list = session.getMapper(SysDataAuthItemMapper.class).getAllList();
//		session.commit();
		closeSession(session);
		return list;
	}
	

	@Override
	public boolean add(SysFile model) {
		SqlSession session = this.getSession();
		session.getMapper(SysFileMapper.class).add(model);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public boolean save(SysFile model) {
		SqlSession session = this.getSession();
		session.getMapper(SysFileMapper.class).save(model);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public boolean deleteModelOfSysFile(int fileId) {
		SqlSession session = this.getSession();
		session.getMapper(SysFileMapper.class).delete( fileId);
		session.commit();
		closeSession(session);
		return true;
	}

	@Override
	public SysFile getModelOfSysFile(int id) {
		SqlSession session = this.getSession();
		SysFile model = session.getMapper(SysFileMapper.class).get(id);
//		session.commit();
		closeSession(session);
		return model;
	}

	@Override
	public List<SysFile> getListOfSysFile() {
		SqlSession session = this.getSession();
		List<SysFile> list = session.getMapper(SysFileMapper.class).getAllList();
//		session.commit();
		closeSession(session);
		return list;
	}

	@Override
	public List<SysDictItem> getListOfSysDictItem(String dirKeyCode) {
		// TODO Auto-generated method stub
		return null;
	}
/*
	@Override
	public boolean add(SysSequence model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(SysSequence model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteModelOfSysSequence(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SysSequence getModelOfSysSequence(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysSequence> getListOfSysSequence() {
		// TODO Auto-generated method stub
		return null;
	}
*/
	
/*
	@Override
	public boolean add(SysLog model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(SysLog model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteModelOfSysLog(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SysLog getModelOfSysLog(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysLog> getListOfSysLog() {
		// TODO Auto-generated method stub
		return null;
	}
*/

/*
	@Override
	public boolean add(SysArea model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(SysArea model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteModelOfSysArea(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SysArea getModelOfSysArea(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysArea> getListOfSysArea() {
		// TODO Auto-generated method stub
		return null;
	}
*/
	
	@Override
	public List<SysRole> getUserRoles(int userId) {
		return this.getSession().getMapper(SysMapper.class).getUserRoles(userId);
	}

	@Override
	public List<SysTab> getUserTabs(int userId) {
		return this.getSession().getMapper(SysMapper.class).getUserTabs(userId);
	}

	@Override
	public List<SysMenu> getUserMenus(int userId) {
		return this.getSession().getMapper(SysMapper.class).getUserMenus(userId);
	}

	@Override
	public List<SysFuncPoint> getUserFuncPoints(int userId) {
		return this.getSession().getMapper(SysMapper.class).getUserFuncPoints(userId);
	}

	@Override
	public List<SysDataAuth> getUserDataAuths(int userId) {
		return this.getSession().getMapper(SysMapper.class).getUserDataAuths(userId);
	}

	@Override
	public List<SysDataAuthItem> getUserDataAuthItems(int userId) {
		return this.getSession().getMapper(SysMapper.class).getUserDataAuthItems(userId);
	}

	@Override
	public UserPermissions getUserPermissions(int userId) {
		UserPermissions userPermissions = new UserPermissions();
		List<IntegerStringTuple> tuples = this.getSession().getMapper(SysMapper.class).getUserPermissioinIds(userId);
		userPermissions.setRoles(new ArrayList<Integer>());
		userPermissions.setTabs(new ArrayList<Integer>());
		userPermissions.setMenus(new ArrayList<Integer>());
		userPermissions.setFuncPoints(new ArrayList<Integer>());
		userPermissions.setDataAuths(new ArrayList<Integer>());
		userPermissions.setDataAuthItems(new ArrayList<Integer>());
		for(IntegerStringTuple e:tuples)
		{
			if("role".equals(e.getSecond()))
				userPermissions.getRoles().add(e.getFirst());
			else if("tab".equals(e.getSecond()))
				userPermissions.getTabs().add(e.getFirst());
			else if("menu".equals(e.getSecond()))
				userPermissions.getMenus().add(e.getFirst());
			else if("funcpoint".equals(e.getSecond()))
				userPermissions.getFuncPoints().add(e.getFirst());
			else if("dataauth".equals(e.getSecond()))
				userPermissions.getDataAuths().add(e.getFirst());
			else if("dataauthitem".equals(e.getSecond()))
				userPermissions.getDataAuthItems().add(e.getFirst());
		}
		return userPermissions;
	}

	@Override
	public List<SysMenu> getTabMenus(int tabId) {
		return this.getSession().getMapper(SysMapper.class).getTabMenus(tabId);
	}

	@Override
	public List<TwoTuple<Integer, Integer>> getTabMenuTuples() {
		List<TwoTuple<Integer,Integer>> list = new ArrayList<TwoTuple<Integer,Integer>>();
		List<TwoIntegerTuple> originalList = this.getSession().getMapper(SysMapper.class).getTabMenuTuples();
		for(TwoIntegerTuple e:originalList)
			list.add(new TwoTuple<Integer,Integer>(e.getFirst(),e.getSecond()));
		return list;
	}

	

}
