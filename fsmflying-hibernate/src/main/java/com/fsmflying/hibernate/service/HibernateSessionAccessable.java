package com.fsmflying.hibernate.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;

public interface HibernateSessionAccessable {
	
	public void setSessionFactory(SessionFactory sessionFactory);
	public SessionFactory getSessionFactory();
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate);
	public HibernateTemplate getHibernateTemplate();
	
	/**
	 * 得到Session的获取方式:<br/>
	 * [0:默认值，sessionFactory.getCurrentSession()];<br/>
	 * [1:sessionFactory.openSession()];<br/>
	 * [2:hibernateTemplate.getSessionFactory().getCurrentSession()];<br/>
	 * [3:hibernateTemplate.getSessionFactory().openSession()];<br/>
	 * [others:sessionFactory.getCurrentSession()]
	 * @return
	 */
	public int  getSessionObtainedWay();
	
	/**
	 * 设置Session的获取方式:<br/>
	 * [0:默认值，sessionFactory.getCurrentSession()];<br/>
	 * [1:sessionFactory.openSession()];<br/>
	 * [2:hibernateTemplate.getSessionFactory().getCurrentSession()];<br/>
	 * [3:hibernateTemplate.getSessionFactory().openSession()];<br/>
	 * [others:sessionFactory.getCurrentSession()]
	 * @param sessionObtainedWay　Session的获取方式
	 */
	public void setSessionObtainedWay(int sessionObtainedWay);
	
	/**
	 * 从sessionFactory或hibernateTemplate.getSessionFactory()中获取Session
	 * @return Session
	 */
	public Session getSession();
//	default public Session getSession(){
//		switch(getSessionObtainedWay())
//		{
//			case 0:
//				return this.getSessionFactory().getCurrentSession();
//			case 1:
//				return this.getSessionFactory().openSession();
//			case 2:
//				return this.getHibernateTemplate().getSessionFactory().getCurrentSession();
//			case 3:
//				return this.getHibernateTemplate().getSessionFactory().openSession();
//			default:
//				return this.getSessionFactory().getCurrentSession();
//			
//		}
//	}
	
	public void closeSession(Session session);
	
//	default public void closeSession(Session session) {
//		int sessionObtainedWay = getSessionObtainedWay();
//		if (sessionObtainedWay == 1 || sessionObtainedWay == 3) {
//			session.close();
//		}
//	}
}
