package com.fsmflying.hibernate.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

import com.fsmflying.hibernate.service.HibernateSessionAccessable;
import com.fsmflying.sys.dm.SysLog;
import com.fsmflying.sys.service.AbstractLogService;

@Service
@SuppressWarnings("unchecked")
public class LogServiceImpl extends AbstractLogService implements HibernateSessionAccessable {

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


	@Transactional
	public boolean add(SysLog model) {
		Session session = getSession();
		session.beginTransaction();
		if (this.getIsWriteDatabase())
			session.persist(model);
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}
	
	@Transactional
	public boolean save(SysLog model) {
		Session session = getSession();
		session.beginTransaction();
		if (this.getIsWriteDatabase())
			session.merge(model);
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}
	
	@Transactional
	public boolean deleteModelOfSysLog(int logId) {
		Session session = getSession();
		session.beginTransaction();
		session.delete(new SysLog(logId));
		session.getTransaction().commit();
		closeSession(session);
		return true;
	}
	
	@Transactional
	public SysLog getModelOfSysLog(int logId) {
		Session session = getSession();
		session.beginTransaction();
		SysLog model = (SysLog) session.createQuery("from SysLog e where e.logId=:logId").setParameter("logId", logId);
		session.getTransaction().commit();
		closeSession(session);
		return model;
	}

	public List<SysLog> getListOfSysLog() {
		Session session = getSession();
		session.beginTransaction();
		List<SysLog> list = (List<SysLog>) session.createQuery("from SysLog e");
		session.getTransaction().commit();
		closeSession(session);
		return list;
	}
}
