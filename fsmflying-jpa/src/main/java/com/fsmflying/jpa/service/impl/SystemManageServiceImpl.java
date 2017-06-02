package com.fsmflying.jpa.service.impl;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.transaction.Transactional;

import com.fsmflying.helpers.SqlParameter;
import com.fsmflying.jpa.service.JpaAccessable;
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

@SuppressWarnings("unchecked")
public class SystemManageServiceImpl implements ISystemManageService, JpaAccessable {


	@PersistenceContext(unitName="common_sysadmin")
	EntityManager entityManager;

	EntityManagerFactory entityManagerFactory;
	
	public void close() {
		if (this.entityManagerFactory != null)
			this.entityManagerFactory.close();
	}
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		System.out.println(this.getClass().getCanonicalName()+".setEntityManagerFactory(..) executed !");
		System.out.println(entityManagerFactory);
		this.entityManagerFactory = entityManagerFactory;
		this.entityManager = this.entityManagerFactory.createEntityManager();
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public <T> List<T> getListOf(Class<T> cls, List<SqlParameter<?>> parameters) {
		String entityName = null;
		java.beans.BeanInfo beanInfo = null;
		if (cls != null && cls.isAnnotationPresent(Table.class)) {
			entityName = cls.getSimpleName();
			try {
				beanInfo = java.beans.Introspector.getBeanInfo(cls);
				if (beanInfo != null && parameters != null && parameters.size() > 0) {
					for (SqlParameter<?> param : parameters) {
						for (java.beans.PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
							if (pd.getName().toLowerCase().equals(param.getName().toLowerCase().trim())) {
								param.setName(pd.getName());
								break;
							}
						}
					}

				}
			} catch (java.beans.IntrospectionException e) {
				e.printStackTrace();
			}
		}
		StringBuilder quseryStringBuilder = new StringBuilder("from " + entityName + " e where 1=1 ");
		java.beans.PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		if (parameters != null && parameters.size() > 0) {
			for (int i = 0; i < parameters.size(); i++) {
				for (java.beans.PropertyDescriptor pd : pds) {
					if (pd.getName().toLowerCase().equals(parameters.get(i).getName())) {
						quseryStringBuilder
								.append(" and e." + parameters.get(i).getName() + "=:" + parameters.get(i).getName());
						break;
					}
				}
			}
		}
		javax.persistence.Query query = this.entityManager.createQuery(quseryStringBuilder.toString());
		if (parameters != null && parameters.size() > 0) {
			for (int i = 0; i < parameters.size(); i++) {
				for (java.beans.PropertyDescriptor pd : pds) {
					if (pd.getName().toLowerCase().equals(parameters.get(i).getName())) {
						query.setParameter(parameters.get(i).getName(), parameters.get(i).getValue());
						break;
					}
				}
			}
		}

		List<T> list = (List<T>) query.getResultList();
		return list;
	}

	public <T> T getModelOf(Class<T> clazz, List<SqlParameter<?>> parameters) {
		String entityName = null;
		java.beans.BeanInfo beanInfo = null;
		if (clazz != null && clazz.isAnnotationPresent(Table.class)) {
			entityName = clazz.getSimpleName();
			try {
				beanInfo = java.beans.Introspector.getBeanInfo(clazz);
				if (beanInfo != null && parameters != null && parameters.size() > 0) {
					for (SqlParameter<?> param : parameters) {
						for (java.beans.PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
							if (pd.getName().toLowerCase().equals(param.getName().toLowerCase().trim())) {
								param.setName(pd.getName());
								break;
							}
						}
					}

				}
			} catch (java.beans.IntrospectionException e) {
				e.printStackTrace();
			}
		}
		StringBuilder quseryStringBuilder = new StringBuilder("from " + entityName + " e where 1=1 ");
		java.beans.PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		if (parameters != null && parameters.size() > 0) {
			for (int i = 0; i < parameters.size(); i++) {
				for (java.beans.PropertyDescriptor pd : pds) {
					if (pd.getName().toLowerCase().equals(parameters.get(i).getName())) {
						quseryStringBuilder
								.append(" and e." + parameters.get(i).getName() + "=:" + parameters.get(i).getName());
						break;
					}
				}
			}
		}
		javax.persistence.Query query = this.entityManager.createQuery(quseryStringBuilder.toString());
		if (parameters != null && parameters.size() > 0) {
			for (int i = 0; i < parameters.size(); i++) {
				for (java.beans.PropertyDescriptor pd : pds) {
					if (pd.getName().toLowerCase().equals(parameters.get(i).getName())) {
						query.setParameter(parameters.get(i).getName(), parameters.get(i).getValue());
						break;
					}
				}
			}
		}

		T model = (T) query.getSingleResult();
		return model;
	}

	@Transactional
	public <T> boolean deleteModelOf(Class<T> clazz, List<SqlParameter<?>> parameters) {
		String entityName = null;
		java.beans.BeanInfo beanInfo = null;
		if (clazz != null && clazz.isAnnotationPresent(Table.class)) {
			entityName = clazz.getSimpleName();
			try {
				beanInfo = java.beans.Introspector.getBeanInfo(clazz);
				if (beanInfo != null && parameters != null && parameters.size() > 0) {
					for (SqlParameter<?> param : parameters) {
						for (java.beans.PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
							if (pd.getName().toLowerCase().equals(param.getName().toLowerCase().trim())) {
								param.setName(pd.getName());
								break;
							}
						}
					}

				}
			} catch (java.beans.IntrospectionException e) {
				e.printStackTrace();
			}
		}
		StringBuilder quseryStringBuilder = new StringBuilder("delete from " + entityName + " e where 1=1 ");
		java.beans.PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		if (parameters != null && parameters.size() > 0) {
			for (int i = 0; i < parameters.size(); i++) {
				for (java.beans.PropertyDescriptor pd : pds) {
					if (pd.getName().toLowerCase().equals(parameters.get(i).getName())) {
						quseryStringBuilder
								.append(" and e." + parameters.get(i).getName() + "=:" + parameters.get(i).getName());
						break;
					}
				}
			}
		}
		javax.persistence.Query query = this.entityManager.createQuery(quseryStringBuilder.toString());
		if (parameters != null && parameters.size() > 0) {
			for (int i = 0; i < parameters.size(); i++) {
				for (java.beans.PropertyDescriptor pd : pds) {
					if (pd.getName().toLowerCase().equals(parameters.get(i).getName())) {
						query.setParameter(parameters.get(i).getName(), parameters.get(i).getValue());
						break;
					}
				}
			}
		}
		query.executeUpdate();
		return true;
	}

	@Transactional
	public boolean add(SysUser model) {
		this.entityManager.persist(model);
		//this.entityManagerFactory.close();
		return true;
	}

	@Transactional
	public boolean save(SysUser model) {
		this.entityManager.merge(model);
		return true;
	}

	@Transactional
	public boolean deleteModelOfSysUser(int id) {
		this.entityManager.remove(this.entityManager.getReference(SysUser.class, id));
		return true;
	}

	public SysUser getModelOfSysUser(int id) {
		return this.entityManager.find(SysUser.class, id);
	}

	public SysUser getModelOfSysUser(String username, boolean refreshCache) {
		System.out.println(this.entityManager);
//		this.entityManager.close();
		return (SysUser) this.entityManager.createQuery("select o from SysUser o  where o.username=:username")
				.setParameter("username", username).getSingleResult();
	}

	public List<SysUser> getListOfSysUser() {
		return (List<SysUser>) this.entityManager.createQuery("select o from SysUser o").getResultList();
	}

	public List<SysUser> getListOfSysUser(List<SqlParameter<?>> parameters) {

		StringBuilder queryStringBuilder = new StringBuilder("from SysUser e where 1=1 ");
		java.beans.BeanInfo beanInfo = null;
		java.beans.PropertyDescriptor[] propertyDescriptors = null;
		try {
			beanInfo = Introspector.getBeanInfo(SysUser.class);
			propertyDescriptors = beanInfo.getPropertyDescriptors();
		} catch (IntrospectionException e1) {
			e1.printStackTrace();
		}
		if (parameters != null && parameters.size() > 0) {
			for (int i = 0; i < parameters.size(); i++) {
				for (java.beans.PropertyDescriptor e : propertyDescriptors) {
					if (e.getName().toLowerCase().equals(parameters.get(i).getName().toLowerCase())) {
						queryStringBuilder
								.append(" and e." + parameters.get(i).getName() + "=:" + parameters.get(i).getName());
						parameters.get(i).setName(e.getName());
						break;
					}
				}
			}
		}
		javax.persistence.Query query = this.entityManager.createQuery(queryStringBuilder.toString());
		if (parameters != null && parameters.size() > 0) {
			for (int i = 0; i < parameters.size(); i++) {
				for (java.beans.PropertyDescriptor e : propertyDescriptors) {
					if (e.getName().equals(parameters.get(i).getName())) {
						query.setParameter(parameters.get(i).getName(), parameters.get(i).getValue());
						break;
					}
				}
			}
		}
		List<SysUser> list = (List<SysUser>) query.getResultList();
		return list;
	}

	@Transactional
	public boolean add(SysRole model) {
		this.entityManager.persist(model);
		return true;
	}

	@Transactional
	public boolean save(SysRole model) {
		this.entityManager.merge(model);
		return true;
	}

	@Transactional
	public boolean deleteModelOfSysRole(int id) {
		this.entityManager.remove(this.entityManager.getReference(SysRole.class, id));
		return true;
	}

	public SysRole getModelOfSysRole(int id) {
		return this.entityManager.find(SysRole.class, id);
	}

	public List<SysRole> getListOfSysRole() {
		return (List<SysRole>) this.entityManager.createQuery("select o from SysRole o").getResultList();
	}

	@Transactional
	public boolean add(SysCompany model) {
		this.entityManager.persist(model);
		return true;
	}

	@Transactional
	public boolean save(SysCompany model) {
		this.entityManager.merge(model);
		return true;
	}

	@Transactional
	public boolean deleteModelOfSysCompany(int id) {
		this.entityManager.remove(this.entityManager.getReference(SysCompany.class, id));
		return true;
	}

	public SysCompany getModelOfSysCompany(int id) {
		return this.entityManager.find(SysCompany.class, id);
	}

	public List<SysCompany> getListOfSysCompany() {
		return (List<SysCompany>) this.entityManager.createQuery("select o from SysCompany o").getResultList();
	}

	@Transactional
	public boolean add(SysDepartment model) {
		this.entityManager.persist(model);
		return true;
	}

	@Transactional
	public boolean save(SysDepartment model) {
		this.entityManager.merge(model);
		return true;
	}

	@Transactional
	public boolean deleteModelOfSysDepartment(int id) {
		this.entityManager.remove(this.entityManager.getReference(SysDepartment.class, id));
		return true;
	}

	public SysDepartment getModelOfSysDepartment(int id) {
		return this.entityManager.find(SysDepartment.class, id);
	}

	public List<SysDepartment> getListOfSysDepartment() {
		return (List<SysDepartment>) this.entityManager.createQuery("select o from SysDepartment o").getResultList();
	}

	@Transactional
	public boolean add(SysEmployee model) {
		this.entityManager.persist(model);
		return true;
	}

	@Transactional
	public boolean save(SysEmployee model) {
		this.entityManager.merge(model);
		return true;
	}

	@Transactional
	public boolean deleteModelOfSysEmployee(int id) {
		this.entityManager.remove(this.entityManager.getReference(SysEmployee.class, id));
		return true;
	}

	public SysEmployee getModelOfSysEmployee(int id) {
		return this.entityManager.find(SysEmployee.class, id);
	}

	public List<SysEmployee> getListOfSysEmployee() {
		return (List<SysEmployee>) this.entityManager.createQuery("select o from SysEmployee o").getResultList();
	}

	@Transactional
	public boolean add(SysTab model) {
		this.entityManager.persist(model);
		return true;
	}

	@Transactional
	public boolean save(SysTab model) {
		this.entityManager.merge(model);
		return true;
	}

	@Transactional
	public boolean deleteModelOfSysTab(int id) {
		this.entityManager.remove(this.entityManager.getReference(SysTab.class, id));
		return true;
	}

	public SysTab getModelOfSysTab(int id) {
		return this.entityManager.find(SysTab.class, id);
	}

	public List<SysTab> getListOfSysTab() {
		return (List<SysTab>) this.entityManager.createQuery("select o from SysTab o").getResultList();
	}

	@Transactional
	public boolean add(SysMenu model) {
		this.entityManager.persist(model);
		return true;
	}

	@Transactional
	public boolean save(SysMenu model) {
		this.entityManager.merge(model);
		return true;
	}

	@Transactional
	public boolean deleteModelOfSysMenu(int id) {
		this.entityManager.remove(this.entityManager.getReference(SysMenu.class, id));
		return true;
	}

	public SysMenu getModelOfSysMenu(int id) {
		return this.entityManager.find(SysMenu.class, id);
	}

	public List<SysMenu> getListOfSysMenu() {
		return (List<SysMenu>) this.entityManager.createQuery("select o from SysMenu o").getResultList();
	}

	@Transactional
	public boolean add(SysFuncPoint model) {
		this.entityManager.persist(model);
		return true;
	}

	@Transactional
	public boolean save(SysFuncPoint model) {
		this.entityManager.merge(model);
		return true;
	}

	@Transactional
	public boolean deleteModelOfSysFuncPoint(int id) {
		this.entityManager.remove(this.entityManager.getReference(SysFuncPoint.class, id));
		return true;
	}

	public SysFuncPoint getModelOfSysFuncPoint(int id) {
		return this.entityManager.find(SysFuncPoint.class, id);
	}

	public List<SysFuncPoint> getListOfSysFuncPoint() {
		return (List<SysFuncPoint>) this.entityManager.createQuery("select o from SysFuncPoint o").getResultList();
	}

	@Transactional
	public boolean add(SysConfig model) {
		this.entityManager.persist(model);
		return false;
	}

	@Transactional
	public boolean save(SysConfig model) {
		this.entityManager.merge(model);
		return true;
	}

	@Transactional
	public boolean deleteModelOfSysConfig(int id) {
		this.entityManager.remove(this.entityManager.getReference(SysConfig.class, id));
		return true;
	}

	public SysConfig getModelOfSysConfig(int id) {
		return this.entityManager.find(SysConfig.class, id);
	}

	public List<SysConfig> getListOfSysConfig() {
		return (List<SysConfig>) this.entityManager.createQuery("select o from SysConfig o where o.dbDeleted=0")
				.getResultList();
	}

	@Transactional
	public boolean add(SysCustomPage model) {
		this.entityManager.persist(model);
		return true;
	}

	@Transactional
	public boolean save(SysCustomPage model) {
		this.entityManager.merge(model);
		return true;
	}

	@Transactional
	public boolean deleteModelOfSysCustomPage(int id) {
		this.entityManager.remove(this.entityManager.getReference(SysCustomPage.class, id));
		return true;
	}

	public SysCustomPage getModelOfSysCustomPage(int id) {
		return this.entityManager.find(SysCustomPage.class, id);
	}

	public List<SysCustomPage> getListOfSysCustomPage() {
		return (List<SysCustomPage>) this.entityManager.createQuery("select o from SysCustomPage o").getResultList();
	}

	@Transactional
	public boolean add(SysDataAuth model) {
		this.entityManager.persist(model);
		return true;
	}

	@Transactional
	public boolean save(SysDataAuth model) {
		this.entityManager.merge(model);
		return true;
	}

	@Transactional
	public boolean deleteModelOfSysDataAuth(int id) {
		this.entityManager.remove(this.entityManager.getReference(SysDataAuth.class, id));
		return true;
	}

	public SysDataAuth getModelOfSysDataAuth(int id) {
		return this.entityManager.find(SysDataAuth.class, id);
	}

	public List<SysDataAuth> getListOfSysDataAuth() {
		return (List<SysDataAuth>) this.entityManager.createQuery("select o from SysDataAuth o").getResultList();
	}

	@Transactional
	public boolean add(SysDataAuthItem model) {
		this.entityManager.persist(model);
		return true;
	}

	@Transactional
	public boolean save(SysDataAuthItem model) {
		this.entityManager.merge(model);
		return true;
	}

	@Transactional
	public boolean deleteModelOfSysDataAuthItem(int id) {
		this.entityManager.remove(this.entityManager.getReference(SysDataAuthItem.class, id));
		return true;
	}

	public SysDataAuthItem getModelOfSysDataAuthItem(int id) {
		return this.entityManager.find(SysDataAuthItem.class, id);
	}

	public List<SysDataAuthItem> getListOfSysDataAuthItem() {
		return (List<SysDataAuthItem>) this.entityManager.createQuery("select o from SysDataAuthItem o")
				.getResultList();
	}

	@Transactional
	public boolean add(SysDictDir model) {
		this.entityManager.persist(model);
		return true;
	}

	@Transactional
	public boolean save(SysDictDir model) {
		this.entityManager.merge(model);
		return true;
	}

	@Transactional
	public boolean deleteModelOfSysDictDir(int id) {
		this.entityManager.remove(this.entityManager.getReference(SysDictDir.class, id));
		return true;
	}

	public SysDictDir getModelOfSysDictDir(int id) {
		return this.entityManager.find(SysDictDir.class, id);
	}

	public List<SysDictDir> getListOfSysDictDir() {
		return (List<SysDictDir>) this.entityManager.createQuery("select o from SysDictDir o").getResultList();
	}

	@Transactional
	public boolean add(SysDictItem model) {
		this.entityManager.persist(model);
		return true;
	}

	@Transactional
	public boolean save(SysDictItem model) {
		this.entityManager.merge(model);
		return true;
	}

	@Transactional
	public boolean deleteModelOfSysDictItem(int id) {
		this.entityManager.remove(this.entityManager.getReference(SysDictItem.class, id));
		return true;
	}

	public SysDictItem getModelOfSysDictItem(int id) {
		return this.entityManager.find(SysDictItem.class, id);
	}

	public List<SysDictItem> getListOfSysDictItem() {
		return (List<SysDictItem>) this.entityManager.createQuery("select o from SysDictItem o").getResultList();
	}

	public List<SysDictItem> getListOfSysDictItem(String dirKeyCode) {
		return (List<SysDictItem>) this.entityManager.createQuery("select o from SysDictItem o").getResultList();
	}

	@Transactional
	public boolean add(SysFile model) {
		this.entityManager.persist(model);
		return true;
	}

	@Transactional
	public boolean save(SysFile model) {
		this.entityManager.merge(model);
		return true;
	}

	@Transactional
	public boolean deleteModelOfSysFile(int id) {
		this.entityManager.remove(this.entityManager.getReference(SysFile.class, id));
		return true;
	}

	public SysFile getModelOfSysFile(int id) {
		return this.entityManager.find(SysFile.class, id);
	}

	public List<SysFile> getListOfSysFile() {
		return (List<SysFile>) this.entityManager.createQuery("select o from SysFile o").getResultList();
	}

	public List<SysRole> getUserRoles(int userId) {
		return this.entityManager.createNativeQuery(
				"select t.* from sys_roles t inner join sys_ruserrole r on t.roleId=r.roleId where r.userId=:userId and dbdeleted=0",
				SysRole.class).setParameter("userId", userId).getResultList();
	}

	public List<SysTab> getUserTabs(int userId) {
		return this.entityManager
				.createNativeQuery("select t.* from sys_tabs t inner join ("
						+ " select distinct tabid from sys_ruserrole a inner join sys_roles b on a.roleid=b.roleid"
						+ " inner join sys_rroletab c on a.roleid=c.roleid"
						+ " where b.dbdeleted=0 and a.userid=:userId" + ") r on t.tabid=r.tabid where dbdeleted=0",
						SysTab.class)
				.setParameter("userId", userId).getResultList();
	}

	public List<SysMenu> getUserMenus(int userId) {
		return (List<SysMenu>) this.entityManager
				.createNativeQuery("select t.* from sys_menus t inner join ("
						+ " select distinct menuid from sys_ruserrole a inner join sys_roles b on a.roleid=b.roleid"
						+ " inner join sys_rrolemenu c on a.roleid=c.roleid"
						+ " where b.dbdeleted=0 and a.userid=:userId" + ") r on t.menuid=r.menuid where dbdeleted=0",
						SysMenu.class)
				.setParameter("userId", userId).getResultList();
	}

	public List<SysFuncPoint> getUserFuncPoints(int userId) {
		return (List<SysFuncPoint>) this.entityManager.createNativeQuery("select t.* from sys_funcpoints t inner join ("
				+ " select distinct funcPointId from sys_ruserrole a inner join sys_roles b on a.roleid=b.roleid"
				+ " inner join sys_rrolefuncpoint c on a.roleid=c.roleid" + " where b.dbdeleted=0 and a.userid=:userId"
				+ ") r on t.funcPointId=r.funcPointId where dbdeleted=0", SysFuncPoint.class)
				.setParameter("userId", userId).getResultList();
	}

	public List<SysDataAuth> getUserDataAuths(int userId) {
		return (List<SysDataAuth>) this.entityManager.createNativeQuery("select t.* from sys_dataauths t inner join ("
				+ " select distinct authId from sys_ruserrole a inner join sys_roles b on a.roleid=b.roleid"
				+ " inner join sys_rroledataauth c on a.roleid=c.roleid" + " where b.dbdeleted=0 and a.userid=:userId"
				+ ") r on t.authId=r.authId where dbdeleted=0 and t.disabled=0", SysDataAuth.class)
				.setParameter("userId", userId).getResultList();
	}

	public List<SysDataAuthItem> getUserDataAuthItems(int userId) {
		return (List<SysDataAuthItem>) this.entityManager
				.createNativeQuery("select t.* from sys_dataauthitems t inner join ("
						+ " select distinct authId from sys_ruserrole a inner join sys_roles b on a.roleid=b.roleid"
						+ " inner join sys_rroledataauth c on a.roleid=c.roleid"
						+ " where b.dbdeleted=0 and a.userid=:userId" + ") r on t.authItemId=r.authItemId"
						+ " inner join sys_dataauths s on t.authId=s.authId"
						+ " where t.dbdeleted=0 and s.dbdeleted=0 and s.disabled=0", SysDataAuthItem.class)
				.setParameter("userId", userId).getResultList();
	}

	public UserPermissions getUserPermissions(int userId) {
		UserPermissions userPermissions = new UserPermissions(userId);

		List<Object[]> listResults = (List<Object[]>) this.entityManager
				.createNativeQuery("select roleId from sys_roles a inner join sys_ruserrole b on a.roleId=b.roleId "
						+ " where a.dbdeleted=0 and b.userId=:userId")
				.setParameter("userId", userId).getResultList();
		List<Integer> list = new ArrayList<Integer>();
		for (Object[] arr : listResults)
			list.add((Integer) arr[0]);
		userPermissions.setRoles(list);

		listResults = (List<Object[]>) entityManager
				.createNativeQuery("select c.tabId from sys_roles a inner join sys_ruserrole b on a.roleId=b.roleId "
						+ " inner join sys_rroletab c on c.roleId=a.roleId"
						+ " inner join sys_tabs d on d.tabId=c.tabId"
						+ " where a.dbdeleted=0 and d.dbdeleted=0 and b.userId=:userId")
				.setParameter("userId", userId).getResultList();
		list = new ArrayList<Integer>();
		for (Object[] arr : listResults)
			list.add((Integer) arr[0]);
		userPermissions.setTabs(list);

		listResults = (List<Object[]>) entityManager
				.createNativeQuery("select c.menuId from sys_roles a inner join sys_ruserrole b on a.roleId=b.roleId "
						+ " inner join sys_rrolemenu c on c.roleId=a.roleId"
						+ " inner join sys_menus d on d.menuId=c.menuId"
						+ " where a.dbdeleted=0 and d.dbdeleted=0 and b.userId=:userId")
				.setParameter("userId", userId).getResultList();
		list = new ArrayList<Integer>();
		for (Object[] arr : listResults)
			list.add((Integer) arr[0]);
		userPermissions.setMenus(list);

		listResults = (List<Object[]>) entityManager
				.createNativeQuery(
						"select c.funcPointId from sys_roles a inner join sys_ruserrole b on a.roleId=b.roleId "
								+ " inner join sys_rrolefuncpoint c on c.roleId=a.roleId"
								+ " inner join sys_funcpoints d on d.funcPointId=c.funcPointId"
								+ " where a.dbdeleted=0 and d.dbdeleted=0 and b.userId=:userId")
				.setParameter("userId", userId).getResultList();
		list = new ArrayList<Integer>();
		for (Object[] arr : listResults)
			list.add((Integer) arr[0]);
		userPermissions.setFuncPoints(list);

		listResults = (List<Object[]>) entityManager
				.createNativeQuery("select c.authId from sys_roles a inner join sys_ruserrole b on a.roleId=b.roleId "
						+ " inner join sys_rroledataauth c on c.roleId=a.roleId"
						+ " inner join sys_dataauths d on d.authId=c.authId"
						+ " where a.dbdeleted=0 and d.disabled=0 and d.dbdeleted=0 and b.userId=:userId")
				.setParameter("userId", userId).getResultList();
		// Hibernate.initialize(listResults);
		list = new ArrayList<Integer>();
		for (Object[] arr : listResults)
			list.add((Integer) arr[0]);
		userPermissions.setFuncPoints(list);

		listResults = (List<Object[]>) entityManager
				.createNativeQuery(
						"select c.authItemId from sys_roles a inner join sys_ruserrole b on a.roleId=b.roleId "
								+ " inner join sys_rroledataauthitem c on c.roleId=a.roleId"
								+ " inner join sys_dataauthitems d on d.authItemId=c.authItemId"
								+ " inner join sys_dataauths e on e.authId=d.authId"
								+ " where a.dbdeleted=0 and e.disabled=0 and e.dbdeleted=0 and d.dbdeleted=0 and b.userId=:userId")
				.setParameter("userId", userId).getResultList();
		list = new ArrayList<Integer>();
		for (Object[] arr : listResults)
			list.add((Integer) arr[0]);
		userPermissions.setFuncPoints(list);

		return userPermissions;
	}

	public List<SysMenu> getTabMenus(int tabId) {
		return (List<SysMenu>) this.entityManager
				.createNativeQuery("select t.* from sys_menus t" + " inner join sys_rtabmenu r on t.menuid=r.menuid"
						+ " where dbdeleted=0 and r.tabId=:tabId", SysMenu.class)
				.setParameter("tabId", tabId).getResultList();
	}

	public List<TwoTuple<Integer, Integer>> getTabMenuTuples() {
		List<Object[]> listResults = (List<Object[]>) this.entityManager
				.createNativeQuery("select distinct r.tabId,r.menuId from sys_rtabmenu r"
						+ " inner join sys_tabs t on r.tabId=t.tabId"
						+ " inner join sys_menus m on m.menuId=r.menuId where t.dbdeleted=0 and m.dbdeleted=0")
				.getResultList();
		List<TwoTuple<Integer, Integer>> list = new ArrayList<TwoTuple<Integer, Integer>>();
		for (Object[] e : listResults)
			list.add(new TwoTuple<Integer, Integer>((Integer) e[0], (Integer) e[1]));

		return list;
	}

}
