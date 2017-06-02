package com.fsmflying.hibernate.jpa.impl;

//import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
//import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import com.fsmflying.hibernate.jpa.HibernateJpaAccessable;
import com.fsmflying.sys.dm.SysLog;
import com.fsmflying.sys.service.AbstractLogService;

@SuppressWarnings("unchecked")
//@Service("jpaLogService")
public class LogServiceImpl extends AbstractLogService implements HibernateJpaAccessable {

	EntityManager entityManager;
	
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory)
	{
		this.entityManager = entityManagerFactory.createEntityManager();
	}
	
	@Transactional
	public boolean add(SysLog model) {
		System.out.println(this.getClass().getCanonicalName()+".add():"+model.getMessage());
		this.entityManager.persist(model);
		return true;
	}
	
	@Transactional
	public boolean save(SysLog model) {
		System.out.println(this.getClass().getCanonicalName()+".save():"+model.getMessage());
		this.entityManager.merge(model);
		return true;
	}

	@Transactional
	public boolean deleteModelOfSysLog(int id) {
		this.entityManager.remove(this.entityManager.getReference(SysLog.class, id));
		return true;
	}

	public SysLog getModelOfSysLog(int id) {
		return this.entityManager.find(SysLog.class, id);
	}


	public List<SysLog> getListOfSysLog() {
		return (List<SysLog>)this.entityManager.createQuery("select o from SysLog o").getResultList();
	}
	

	
	@Override
	public void writeLog(String logger, String message) {
		// TODO Auto-generated method stub
		System.out.println(this.getClass().getCanonicalName()+".isWriteDatabase:"+this.getIsWriteDatabase());
		System.out.println(logger+":"+message);
		super.writeLog(logger, message);
		
	}

}
