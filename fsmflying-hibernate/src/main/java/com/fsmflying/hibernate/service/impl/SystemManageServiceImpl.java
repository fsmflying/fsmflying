package com.fsmflying.hibernate.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Table;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.fsmflying.helpers.SqlParameter;
import com.fsmflying.hibernate.service.HibernateSessionAccessable;
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
import com.fsmflying.sys.service.ILogService;
import com.fsmflying.util.TwoTuple;

/**
 * 
 * @author FangMing
 *
 */
@SuppressWarnings("unchecked")
@Service
@Transactional
public class SystemManageServiceImpl
		implements com.fsmflying.sys.service.ISystemManageService, HibernateSessionAccessable {

	@Autowired
	ILogService logService;

	SessionFactory sessionFactory;
	HibernateTemplate hibernateTemplate;
	int sessionObtainedWay = 0;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public Session getSession() {
		switch (getSessionObtainedWay()) {
		case 0:
			return this.getSessionFactory().getCurrentSession();
		case 1:
			return this.getSessionFactory().openSession();
		case 2:
			return this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		case 3:
			return this.getHibernateTemplate().getSessionFactory().openSession();
		default:
			return this.getSessionFactory().getCurrentSession();

		}
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}

	public int getSessionObtainedWay() {
		return sessionObtainedWay;
	}

	public void setSessionObtainedWay(int sessionObtainedWay) {
		this.sessionObtainedWay = sessionObtainedWay;
	}

	public void closeSession(Session session) {
		int sessionObtainedWay = getSessionObtainedWay();
		if (sessionObtainedWay == 1 || sessionObtainedWay == 3) {
			session.close();
		}
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public <T> List<T> getListOf(Class<T> cls, List<SqlParameter<?>> parameters) {
		Session session = getSession();
		session.beginTransaction();
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
				for(java.beans.PropertyDescriptor pd : pds)
				{
					if(pd.getName().equals(parameters.get(i).getName())){
						quseryStringBuilder
							.append(" and e." + parameters.get(i).getName() + "=:" + parameters.get(i).getName());
						break;
					}
				}
			}
		}
		Query query = session.createQuery(quseryStringBuilder.toString());
		if (parameters != null && parameters.size() > 0) {
			for (int i = 0; i < parameters.size(); i++) {
				for(java.beans.PropertyDescriptor pd : pds)
				{
					if(pd.getName().equals(parameters.get(i).getName())){
						query.setParameter(parameters.get(i).getName(), parameters.get(i).getValue());
						break;
					}
				}
			}
		}
		
		List<T> list = (List<T>) query.list();
		Hibernate.initialize(list);
		session.getTransaction().commit();
		closeSession(session);
		return list;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public <T> T getModelOf(Class<T> clazz,List<SqlParameter<?>> parameters)
	{
		Session session = getSession();
		session.beginTransaction();
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
				for(java.beans.PropertyDescriptor pd : pds)
				{
					if(pd.getName().equals(parameters.get(i).getName())){
						quseryStringBuilder
							.append(" and e." + parameters.get(i).getName() + "=:" + parameters.get(i).getName());
						break;
					}
				}
			}
		}
		Query query = session.createQuery(quseryStringBuilder.toString());
		if (parameters != null && parameters.size() > 0) {
			for (int i = 0; i < parameters.size(); i++) {
				for(java.beans.PropertyDescriptor pd : pds)
				{
					if(pd.getName().equals(parameters.get(i).getName())){
						query.setParameter(parameters.get(i).getName(), parameters.get(i).getValue());
						break;
					}
				}
			}
		}
		
		T model = (T) query.uniqueResult();
		Hibernate.initialize(model);
		session.getTransaction().commit();
		closeSession(session);
		return model;
	}
	
	public <T> boolean deleteModelOf(Class<T> clazz,List<SqlParameter<?>> parameters)
	{
		Session session = getSession();
		session.beginTransaction();
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
				for(java.beans.PropertyDescriptor pd : pds)
				{
					if(pd.getName().equals(parameters.get(i).getName())){
						quseryStringBuilder
							.append(" and e." + parameters.get(i).getName() + "=:" + parameters.get(i).getName());
						break;
					}
				}
			}
		}
		Query query = session.createQuery(quseryStringBuilder.toString());
		if (parameters != null && parameters.size() > 0) {
			for (int i = 0; i < parameters.size(); i++) {
				for(java.beans.PropertyDescriptor pd : pds)
				{
					if(pd.getName().equals(parameters.get(i).getName())){
						query.setParameter(parameters.get(i).getName(), parameters.get(i).getValue());
						break;
					}
				}
			}
		}
//		int flag = query.executeUpdate();
		query.executeUpdate();
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}
	
	public boolean add(SysUser model) {
		Session session = getSession();
		session.beginTransaction();
		session.save(model);
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	public boolean save(SysUser model) {
		Session session = getSession();
		session.beginTransaction();
		System.out.println("save[SysUser]:userId=" + model.getUserId());
		session.update(model);
		// session.save(model);
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	public boolean deleteModelOfSysUser(int userId) {
		Session session = getSession();
		session.beginTransaction();
		session.delete(new SysUser(userId));
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public SysUser getModelOfSysUser(int userId) {
		Session session = getSession();
		session.beginTransaction();
		SysUser model = (SysUser) session.get(SysUser.class, userId);
		session.getTransaction().commit();
		closeSession(session);
		return model;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public SysUser getModelOfSysUser(String username, boolean refreshCache) {
		Session session = getSession();
		session.beginTransaction();
		SysUser model = (SysUser) session.createQuery("from SysUser e where e.username=:username")
				.setParameter("username", username).uniqueResult();
		Hibernate.initialize(model);
		session.getTransaction().commit();
		closeSession(session);
		return model;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<SysUser> getListOfSysUser() {
		Session session = getSession();
		session.beginTransaction();
		List<SysUser> list = (List<SysUser>) session.createQuery("from SysUser e").list();
		Hibernate.initialize(list);
		session.getTransaction().commit();
		closeSession(session);
		return list;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<SysUser> getListOfSysUser(List<SqlParameter<?>> parameters) {
		Session session = getSession();
		session.beginTransaction();
		StringBuilder quseryStringBuilder = new StringBuilder("from SysUser e where 1=1 ");
		if (parameters != null && parameters.size() > 0) {
			for (int i = 0; i < parameters.size(); i++) {
				quseryStringBuilder
						.append(" and e." + parameters.get(i).getName() + "=:" + parameters.get(i).getName());
			}
		}
		Query query = session.createQuery(quseryStringBuilder.toString());
		if (parameters != null && parameters.size() > 0) {
			for (int i = 0; i < parameters.size(); i++) {
				query.setParameter(parameters.get(i).getName(), parameters.get(i).getValue());
			}
		}
		List<SysUser> list = (List<SysUser>) query.list();
		Hibernate.initialize(list);
		session.getTransaction().commit();
		closeSession(session);
		return list;
	}

	public boolean add(SysDictDir model) {
		Session session = getSession();
		session.beginTransaction();
		session.save(model);
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	public boolean save(SysDictDir model) {
		Session session = getSession();
		session.beginTransaction();
		session.update(model);
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	public boolean deleteModelOfSysDictDir(int dirId) {
		Session session = getSession();
		session.beginTransaction();
		session.delete(new SysDictDir(dirId));
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	public SysDictDir getModelOfSysDictDir(int dirId) {
		Session session = getSession();
		session.beginTransaction();
		SysDictDir model = (SysDictDir) session.get(SysDictDir.class, dirId);
		session.getTransaction().commit();
		closeSession(session);
		return model;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<SysDictDir> getListOfSysDictDir() {
		Session session = getSession();
		session.beginTransaction();
		List<SysDictDir> list = (List<SysDictDir>) session.createQuery("from SysDictDir e").list();
		Hibernate.initialize(list);
		session.getTransaction().commit();
		closeSession(session);
		return list;
	}

	public boolean add(SysDictItem model) {
		Session session = getSession();
		session.beginTransaction();
		session.save(model);
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	public boolean save(SysDictItem model) {
		Session session = getSession();
		session.beginTransaction();
		session.update(model);
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	public boolean deleteModelOfSysDictItem(int itemId) {
		Session session = getSession();
		session.beginTransaction();
		session.delete(new SysDictItem(itemId));
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public SysDictItem getModelOfSysDictItem(int itemId) {
		Session session = getSession();
		session.beginTransaction();
		SysDictItem model = (SysDictItem) session.get(SysDictItem.class, itemId);
		session.getTransaction().commit();
		closeSession(session);
		return model;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<SysDictItem> getListOfSysDictItem() {

		Session session = getSession();
		session.beginTransaction();
		List<SysDictItem> list = (List<SysDictItem>) session.createQuery("from SysDictItem e").list();
		Hibernate.initialize(list);
		session.getTransaction().commit();
		closeSession(session);
		return list;
	}

	public boolean add(SysConfig model) {
		Session session = getSession();
		session.beginTransaction();
		session.save(model);
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	public boolean save(SysConfig model) {
		Session session = getSession();
		session.beginTransaction();
		session.update(model);
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	public boolean deleteModelOfSysConfig(int configId) {
		Session session = getSession();
		session.beginTransaction();
		session.delete(new SysConfig(configId));
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public SysConfig getModelOfSysConfig(int configId) {
		Session session = getSession();
		session.beginTransaction();
		SysConfig model = (SysConfig) session.get(SysConfig.class, configId);
		session.getTransaction().commit();
		closeSession(session);
		return model;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<SysConfig> getListOfSysConfig() {
		Session session = getSession();
		session.beginTransaction();
		List<SysConfig> list = (List<SysConfig>) session.createQuery("from SysConfig e").list();
		Hibernate.initialize(list);
		session.getTransaction().commit();
		closeSession(session);
		return list;
	}

	public boolean add(SysCustomPage model) {
		Session session = getSession();
		session.beginTransaction();
		session.save(model);
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	public boolean save(SysCustomPage model) {
		Session session = getSession();
		session.beginTransaction();
		session.update(model);
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	public boolean deleteModelOfSysCustomPage(int customPageId) {
		Session session = getSession();
		session.beginTransaction();
		session.delete(new SysCustomPage(customPageId));
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public SysCustomPage getModelOfSysCustomPage(int customPageId) {
		Session session = getSession();
		session.beginTransaction();
		SysCustomPage model = (SysCustomPage) session.get(SysCustomPage.class, customPageId);
		session.getTransaction().commit();
		closeSession(session);
		return model;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<SysCustomPage> getListOfSysCustomPage() {
		Session session = getSession();
		session.beginTransaction();
		List<SysCustomPage> list = (List<SysCustomPage>) session.createQuery("from SysCustomPage e").list();
		Hibernate.initialize(list);
		session.getTransaction().commit();
		closeSession(session);
		return list;
	}

	public boolean add(SysEmployee model) {
		Session session = getSession();
		session.beginTransaction();
		session.save(model);
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	public boolean save(SysEmployee model) {
		Session session = getSession();
		session.beginTransaction();
		session.update(model);
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	public boolean deleteModelOfSysEmployee(int emplId) {
		Session session = getSession();
		session.beginTransaction();
		session.delete(new SysEmployee(emplId));
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public SysEmployee getModelOfSysEmployee(int emplId) {
		Session session = getSession();
		session.beginTransaction();
		SysEmployee model = (SysEmployee) session.get(SysEmployee.class, emplId);
		session.getTransaction().commit();
		closeSession(session);
		return model;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<SysEmployee> getListOfSysEmployee() {
		Session session = getSession();
		session.beginTransaction();
		List<SysEmployee> list = (List<SysEmployee>) session.createQuery("from SysEmployee e").list();
		Hibernate.initialize(list);
		session.getTransaction().commit();
		closeSession(session);
		return list;
	}

	public boolean add(SysCompany model) {
		Session session = getSession();
		session.beginTransaction();
		session.save(model);
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	public boolean save(SysCompany model) {
		Session session = getSession();
		session.beginTransaction();
		session.update(model);
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	public boolean deleteModelOfSysCompany(int companyId) {
		Session session = getSession();
		session.beginTransaction();
		session.delete(new SysCompany(companyId));
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public SysCompany getModelOfSysCompany(int companyId) {
		Session session = getSession();
		session.beginTransaction();
		SysCompany model = (SysCompany) session.get(SysCompany.class, companyId);
		session.getTransaction().commit();
		closeSession(session);
		return model;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<SysCompany> getListOfSysCompany() {
		Session session = getSession();
		session.beginTransaction();
		List<SysCompany> list = (List<SysCompany>) session.createQuery("from SysCompany e").list();
		Hibernate.initialize(list);
		session.getTransaction().commit();
		closeSession(session);
		return list;
	}

	public boolean add(SysDepartment model) {
		Session session = getSession();
		session.beginTransaction();
		session.save(model);
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	public boolean save(SysDepartment model) {
		Session session = getSession();
		session.beginTransaction();
		session.update(model);
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	public boolean deleteModelOfSysDepartment(int deptId) {
		Session session = getSession();
		session.beginTransaction();
		session.delete(new SysDepartment(deptId));
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public SysDepartment getModelOfSysDepartment(int deptId) {
		Session session = getSession();
		session.beginTransaction();
		SysDepartment model = (SysDepartment) session.get(SysDepartment.class, deptId);
		session.getTransaction().commit();
		closeSession(session);
		return model;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<SysDepartment> getListOfSysDepartment() {
		Session session = getSession();
		session.beginTransaction();
		List<SysDepartment> list = (List<SysDepartment>) session.createQuery("from SysDepartment e").list();
		Hibernate.initialize(list);
		session.getTransaction().commit();
		closeSession(session);
		return list;
	}

	public boolean add(SysRole model) {
		Session session = getSession();
		session.beginTransaction();
		session.save(model);
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	public boolean save(SysRole model) {
		Session session = getSession();
		session.beginTransaction();
		session.update(model);
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	public boolean deleteModelOfSysRole(int roleId) {
		Session session = getSession();
		session.beginTransaction();
		session.delete(new SysRole(roleId));
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public SysRole getModelOfSysRole(int roleId) {
		Session session = getSession();
		session.beginTransaction();
		SysRole model = (SysRole) session.get(SysRole.class, roleId);
		session.getTransaction().commit();
		closeSession(session);
		return model;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<SysRole> getListOfSysRole() {
		Session session = getSession();
		session.beginTransaction();
		List<SysRole> list = (List<SysRole>) session.createQuery("from SysRole e").list();
		Hibernate.initialize(list);
		session.getTransaction().commit();
		closeSession(session);
		return list;
	}

	public boolean add(SysTab model) {
		Session session = getSession();
		session.beginTransaction();
		session.save(model);
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	public boolean save(SysTab model) {
		Session session = getSession();
		session.beginTransaction();
		session.update(model);
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	public boolean deleteModelOfSysTab(int tabId) {
		Session session = getSession();
		session.beginTransaction();
		session.delete(new SysTab(tabId));
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public SysTab getModelOfSysTab(int tabId) {
		Session session = getSession();
		session.beginTransaction();
		SysTab model = (SysTab) session.get(SysTab.class, tabId);
		session.getTransaction().commit();
		closeSession(session);
		return model;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<SysTab> getListOfSysTab() {
		Session session = getSession();
		session.beginTransaction();
		List<SysTab> list = (List<SysTab>) session.createQuery("from SysTab e").list();
		Hibernate.initialize(list);
		session.getTransaction().commit();
		closeSession(session);
		return list;
	}

	public boolean add(SysMenu model) {
		Session session = getSession();
		session.beginTransaction();
		session.save(model);
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	public boolean save(SysMenu model) {
		Session session = getSession();
		session.beginTransaction();
		session.update(model);
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	public boolean deleteModelOfSysMenu(int menuId) {
		Session session = getSession();
		session.beginTransaction();
		session.delete(new SysMenu(menuId));
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public SysMenu getModelOfSysMenu(int menuId) {
		Session session = getSession();
		session.beginTransaction();
		SysMenu model = (SysMenu) session.get(SysMenu.class, menuId);
		session.getTransaction().commit();
		closeSession(session);
		return model;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<SysMenu> getListOfSysMenu() {
		Session session = getSession();
		session.beginTransaction();
		List<SysMenu> list = (List<SysMenu>) session.createQuery("from SysMenu e").list();
		Hibernate.initialize(list);
		session.getTransaction().commit();
		closeSession(session);
		return list;
	}

	public boolean add(SysFuncPoint model) {
		Session session = getSession();
		session.beginTransaction();
		session.save(model);
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	public boolean save(SysFuncPoint model) {
		Session session = getSession();
		session.beginTransaction();
		session.update(model);
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	public boolean deleteModelOfSysFuncPoint(int funcPointId) {
		Session session = getSession();
		session.beginTransaction();
		session.delete(new SysFuncPoint(funcPointId));
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public SysFuncPoint getModelOfSysFuncPoint(int funcPointId) {
		Session session = getSession();
		session.beginTransaction();
		SysFuncPoint model = (SysFuncPoint) session.get(SysFuncPoint.class, funcPointId);
		session.getTransaction().commit();
		closeSession(session);
		return model;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<SysFuncPoint> getListOfSysFuncPoint() {
		Session session = getSession();
		session.beginTransaction();
		List<SysFuncPoint> list = (List<SysFuncPoint>) session.createQuery("from SysFuncPoint e").list();
		Hibernate.initialize(list);
		session.getTransaction().commit();
		closeSession(session);
		return list;
	}

	public boolean add(SysDataAuth model) {
		Session session = getSession();
		session.beginTransaction();
		session.save(model);
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	public boolean save(SysDataAuth model) {
		Session session = getSession();
		session.beginTransaction();
		session.update(model);
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	public boolean deleteModelOfSysDataAuth(int authId) {
		Session session = getSession();
		session.beginTransaction();
		session.delete(new SysDataAuth(authId));
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public SysDataAuth getModelOfSysDataAuth(int authId) {
		Session session = getSession();
		session.beginTransaction();
		SysDataAuth model = (SysDataAuth) session.get(SysDataAuth.class, authId);
		session.getTransaction().commit();
		closeSession(session);
		return model;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<SysDataAuth> getListOfSysDataAuth() {
		Session session = getSession();
		session.beginTransaction();
		List<SysDataAuth> list = (List<SysDataAuth>) session.createQuery("from SysDataAuth e").list();
		Hibernate.initialize(list);
		session.getTransaction().commit();
		closeSession(session);
		return list;
	}

	public boolean add(SysDataAuthItem model) {
		Session session = getSession();
		session.beginTransaction();
		session.save(model);
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	public boolean save(SysDataAuthItem model) {
		Session session = getSession();
		session.beginTransaction();
		session.update(model);
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	public boolean deleteModelOfSysDataAuthItem(int authItemId) {
		Session session = getSession();
		session.beginTransaction();
		session.delete(new SysDataAuthItem(authItemId));
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public SysDataAuthItem getModelOfSysDataAuthItem(int authItemId) {
		Session session = getSession();
		session.beginTransaction();
		SysDataAuthItem model = (SysDataAuthItem) session.get(SysDataAuthItem.class, authItemId);
		session.getTransaction().commit();
		closeSession(session);
		return model;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<SysDataAuthItem> getListOfSysDataAuthItem() {
		Session session = getSession();
		session.beginTransaction();
		List<SysDataAuthItem> list = (List<SysDataAuthItem>) session.createQuery("from SysDataAuthItem e").list();
		Hibernate.initialize(list);
		session.getTransaction().commit();
		closeSession(session);
		return list;
	}

	public boolean add(SysFile model) {
		Session session = getSession();
		session.beginTransaction();
		session.save(model);
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	public boolean save(SysFile model) {
		Session session = getSession();
		session.beginTransaction();
		session.update(model);
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	public boolean deleteModelOfSysFile(int fileId) {
		Session session = getSession();
		session.beginTransaction();
		session.delete(new SysFile(fileId));
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public SysFile getModelOfSysFile(int fileId) {
		Session session = getSession();
		session.beginTransaction();
		SysFile model = (SysFile) session.get(SysFile.class, fileId);
		session.getTransaction().commit();
		closeSession(session);
		return model;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<SysFile> getListOfSysFile() {
		Session session = getSession();
		session.beginTransaction();
		List<SysFile> list = (List<SysFile>) session.createQuery("from SysFile e").list();
		Hibernate.initialize(list);
		session.getTransaction().commit();
		closeSession(session);
		return list;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<SysDictItem> getListOfSysDictItem(String dirKeyCode) {
		Session session = getSession();
		session.beginTransaction();
		List<SysDictItem> list = (List<SysDictItem>) session
				.createQuery(
						"from SysDictItem e inner join SysDictDir f on e.dirId=f.dirId where lower(e.keyCode)=lower(:keyCode)")
				.setParameter("keyCode", dirKeyCode).list();
		Hibernate.initialize(list);
		session.getTransaction().commit();
		closeSession(session);
		return list;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<SysRole> getUserRoles(int userId) {

		Session session = getSession();
		session.beginTransaction();
		List<SysRole> list = (List<SysRole>) session
				.createSQLQuery(
						"select t.* from sys_roles t inner join sys_ruserrole r on t.roleId=r.roleId where r.userId=:userId and dbdeleted=0")
				.addEntity(SysRole.class).setParameter("userId", userId).list();
		Hibernate.initialize(list);
		session.getTransaction().commit();
		closeSession(session);
		return list;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<SysTab> getUserTabs(int userId) {
		Session session = getSession();
		session.beginTransaction();
		List<SysTab> list = (List<SysTab>) session
				.createSQLQuery("select t.* from sys_tabs t inner join ("
						+ " select distinct tabid from sys_ruserrole a inner join sys_roles b on a.roleid=b.roleid"
						+ " inner join sys_rroletab c on a.roleid=c.roleid"
						+ " where b.dbdeleted=0 and a.userid=:userId" + ") r on t.tabid=r.tabid where dbdeleted=0")
				.addEntity(SysTab.class).setParameter("userId", userId).list();
		Hibernate.initialize(list);
		session.getTransaction().commit();
		closeSession(session);
		return list;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<SysMenu> getUserMenus(int userId) {
		Session session = getSession();
		session.beginTransaction();
		List<SysMenu> list = (List<SysMenu>) session
				.createSQLQuery("select t.* from sys_menus t inner join ("
						+ " select distinct menuid from sys_ruserrole a inner join sys_roles b on a.roleid=b.roleid"
						+ " inner join sys_rrolemenu c on a.roleid=c.roleid"
						+ " where b.dbdeleted=0 and a.userid=:userId" + ") r on t.menuid=r.menuid where dbdeleted=0")
				.addEntity(SysMenu.class).setParameter("userId", userId).list();
		Hibernate.initialize(list);
		session.getTransaction().commit();
		closeSession(session);
		return list;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<SysFuncPoint> getUserFuncPoints(int userId) {
		Session session = getSession();
		session.beginTransaction();
		List<SysFuncPoint> list = (List<SysFuncPoint>) session
				.createSQLQuery("select t.* from sys_funcpoints t inner join ("
						+ " select distinct funcPointId from sys_ruserrole a inner join sys_roles b on a.roleid=b.roleid"
						+ " inner join sys_rrolefuncpoint c on a.roleid=c.roleid"
						+ " where b.dbdeleted=0 and a.userid=:userId"
						+ ") r on t.funcPointId=r.funcPointId where dbdeleted=0")
				.addEntity(SysFuncPoint.class).setParameter("userId", userId).list();
		Hibernate.initialize(list);
		session.getTransaction().commit();
		closeSession(session);
		return list;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<SysDataAuth> getUserDataAuths(int userId) {
		Session session = getSession();
		session.beginTransaction();
		List<SysDataAuth> list = (List<SysDataAuth>) session
				.createSQLQuery("select t.* from sys_dataauths t inner join ("
						+ " select distinct authId from sys_ruserrole a inner join sys_roles b on a.roleid=b.roleid"
						+ " inner join sys_rroledataauth c on a.roleid=c.roleid"
						+ " where b.dbdeleted=0 and a.userid=:userId"
						+ ") r on t.authId=r.authId where dbdeleted=0 and t.disabled=0")
				.addEntity(SysDataAuth.class).setParameter("userId", userId).list();
		Hibernate.initialize(list);
		session.getTransaction().commit();
		closeSession(session);
		return list;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<SysDataAuthItem> getUserDataAuthItems(int userId) {
		Session session = getSession();
		session.beginTransaction();
		List<SysDataAuthItem> list = (List<SysDataAuthItem>) session
				.createSQLQuery("select t.* from sys_dataauthitems t inner join ("
						+ " select distinct authId from sys_ruserrole a inner join sys_roles b on a.roleid=b.roleid"
						+ " inner join sys_rroledataauth c on a.roleid=c.roleid"
						+ " where b.dbdeleted=0 and a.userid=:userId" + ") r on t.authItemId=r.authItemId"
						+ " inner join sys_dataauths s on t.authId=s.authId"
						+ " where t.dbdeleted=0 and s.dbdeleted=0 and s.disabled=0")
				.addEntity(SysDataAuthItem.class).setParameter("userId", userId).list();
		Hibernate.initialize(list);
		session.getTransaction().commit();
		closeSession(session);
		return list;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public UserPermissions getUserPermissions(int userId) {
		UserPermissions userPermissions = new UserPermissions(userId);
		Session session = getSession();
		session.beginTransaction();

		List<Object[]> listResults = (List<Object[]>) session
				.createSQLQuery("select roleId from sys_roles a inner join sys_ruserrole b on a.roleId=b.roleId "
						+ " where a.dbdeleted=0 and b.userId=:userId")
				.setParameter("userId", userId).list();
		Hibernate.initialize(listResults);
		List<Integer> list = new ArrayList<Integer>();
		for (Object[] arr : listResults)
			list.add((Integer) arr[0]);
		userPermissions.setRoles(list);

		listResults = (List<Object[]>) session
				.createSQLQuery("select c.tabId from sys_roles a inner join sys_ruserrole b on a.roleId=b.roleId "
						+ " inner join sys_rroletab c on c.roleId=a.roleId"
						+ " inner join sys_tabs d on d.tabId=c.tabId"
						+ " where a.dbdeleted=0 and d.dbdeleted=0 and b.userId=:userId")
				.setParameter("userId", userId).list();
		Hibernate.initialize(listResults);
		list = new ArrayList<Integer>();
		for (Object[] arr : listResults)
			list.add((Integer) arr[0]);
		userPermissions.setTabs(list);

		listResults = (List<Object[]>) session
				.createSQLQuery("select c.menuId from sys_roles a inner join sys_ruserrole b on a.roleId=b.roleId "
						+ " inner join sys_rrolemenu c on c.roleId=a.roleId"
						+ " inner join sys_menus d on d.menuId=c.menuId"
						+ " where a.dbdeleted=0 and d.dbdeleted=0 and b.userId=:userId")
				.setParameter("userId", userId).list();
		Hibernate.initialize(listResults);
		list = new ArrayList<Integer>();
		for (Object[] arr : listResults)
			list.add((Integer) arr[0]);
		userPermissions.setMenus(list);

		listResults = (List<Object[]>) session
				.createSQLQuery("select c.funcPointId from sys_roles a inner join sys_ruserrole b on a.roleId=b.roleId "
						+ " inner join sys_rrolefuncpoint c on c.roleId=a.roleId"
						+ " inner join sys_funcpoints d on d.funcPointId=c.funcPointId"
						+ " where a.dbdeleted=0 and d.dbdeleted=0 and b.userId=:userId")
				.setParameter("userId", userId).list();
		Hibernate.initialize(listResults);
		list = new ArrayList<Integer>();
		for (Object[] arr : listResults)
			list.add((Integer) arr[0]);
		userPermissions.setFuncPoints(list);

		listResults = (List<Object[]>) session
				.createSQLQuery("select c.authId from sys_roles a inner join sys_ruserrole b on a.roleId=b.roleId "
						+ " inner join sys_rroledataauth c on c.roleId=a.roleId"
						+ " inner join sys_dataauths d on d.authId=c.authId"
						+ " where a.dbdeleted=0 and d.disabled=0 and d.dbdeleted=0 and b.userId=:userId")
				.setParameter("userId", userId).list();
		Hibernate.initialize(listResults);
		list = new ArrayList<Integer>();
		for (Object[] arr : listResults)
			list.add((Integer) arr[0]);
		userPermissions.setFuncPoints(list);

		listResults = (List<Object[]>) session
				.createSQLQuery("select c.authItemId from sys_roles a inner join sys_ruserrole b on a.roleId=b.roleId "
						+ " inner join sys_rroledataauthitem c on c.roleId=a.roleId"
						+ " inner join sys_dataauthitems d on d.authItemId=c.authItemId"
						+ " inner join sys_dataauths e on e.authId=d.authId"
						+ " where a.dbdeleted=0 and e.disabled=0 and e.dbdeleted=0 and d.dbdeleted=0 and b.userId=:userId")
				.setParameter("userId", userId).list();
		Hibernate.initialize(listResults);
		list = new ArrayList<Integer>();
		for (Object[] arr : listResults)
			list.add((Integer) arr[0]);
		userPermissions.setFuncPoints(list);

		session.getTransaction().commit();
		closeSession(session);
		return userPermissions;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<SysMenu> getTabMenus(int tabId) {
		Session session = getSession();
		session.beginTransaction();
		List<SysMenu> list = (List<SysMenu>) session
				.createSQLQuery(
						"select t.* from sys_menus t inner join sys_rtabmenu r on t.menuid=r.menuid where dbdeleted=0 and r.tabId=:tabId")
				.addEntity(SysMenu.class).setParameter("tabId", tabId).list();
		Hibernate.initialize(list);
		session.getTransaction().commit();
		closeSession(session);
		return list;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<TwoTuple<Integer, Integer>> getTabMenuTuples() {
		Session session = getSession();
		session.beginTransaction();
		List<Object[]> listResults = (List<Object[]>) session
				.createSQLQuery(
						"select distinct r.tabId,r.menuId from sys_rtabmenu r inner join sys_tabs t on r.tabId=t.tabId"
								+ " inner join sys_menus m on m.menuId=r.menuId where t.dbdeleted=0 and m.dbdeleted=0")
				.list();
		Hibernate.initialize(listResults);
		List<TwoTuple<Integer, Integer>> list = new ArrayList<TwoTuple<Integer, Integer>>();
		for (Object[] e : listResults)
			list.add(new TwoTuple<Integer, Integer>((Integer) e[0], (Integer) e[1]));
		session.getTransaction().commit();
		closeSession(session);
		return list;
	}

}
