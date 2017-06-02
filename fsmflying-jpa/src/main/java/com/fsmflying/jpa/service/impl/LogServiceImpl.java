package com.fsmflying.jpa.service.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import com.fsmflying.jpa.service.JpaAccessable;
import com.fsmflying.sys.dm.SysLog;
import com.fsmflying.sys.service.AbstractLogService;

@SuppressWarnings("unchecked")
public class LogServiceImpl extends AbstractLogService implements JpaAccessable {

	@PersistenceContext(unitName="common_log")
	EntityManager entityManager;
	
	EntityManagerFactory entityManagerFactory;
	
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory)
	{
		this.entityManagerFactory = entityManagerFactory;
		this.entityManager = this.entityManagerFactory.createEntityManager();
	}

	public void close() {
		if (this.entityManagerFactory != null)
			this.entityManagerFactory.close();
	}
	
	@Transactional
	public boolean add(SysLog model) {
		this.entityManager.persist(model);
		return true;
	}
	
	@Transactional
	public boolean save(SysLog model) {
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
		System.out.println(logger+":"+message);
		super.writeLog(logger, message);
		
	}

}
