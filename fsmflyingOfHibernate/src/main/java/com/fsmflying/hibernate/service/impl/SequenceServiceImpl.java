package com.fsmflying.hibernate.service.impl;

import java.util.Calendar;
import java.util.Date;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.fsmflying.hibernate.service.HibernateSessionAccessable;
import com.fsmflying.sys.dm.SysLog;
import com.fsmflying.sys.dm.SysSequence;
import com.fsmflying.sys.service.AbstractSequenceService;
import com.fsmflying.sys.service.ILogService;

@Service
public class SequenceServiceImpl extends AbstractSequenceService implements HibernateSessionAccessable {

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
	
	@javax.transaction.Transactional(javax.transaction.Transactional.TxType.REQUIRES_NEW)
	@org.springframework.transaction.annotation.Transactional(propagation=Propagation.REQUIRES_NEW)
	public int generateNextId(String keyName, int increment) {
		if (keyName == null)
			throw new IllegalArgumentException(
					" the method 'SequenceServiceImpl.getNextId(String keyName)' have be passed 'null' !");
		
		Session session = getSession();
		session.beginTransaction();
		SysSequence model = (SysSequence) session.get(SysSequence.class, keyName.toLowerCase().trim());
		int value = -1;
		Date generateTime = Calendar.getInstance().getTime();
		if (model != null) {
			value = model.getNextValue();
			model.setNextValue(model.getNextValue() + increment);
			model.setGeneratedTime(generateTime);
			session.update(model);

			session.createSQLQuery(
					"insert into sys_sequenceHistories(keyName,generatedValue,generatedTime) values(:keyName,:generatedValue,:generatedTime)")
					.setParameter("keyName", model.getKeyName()).setParameter("generatedValue", model.getNextValue())
					.setParameter("generatedTime", model.getGeneratedTime()).executeUpdate();
		} else {
			value = 1;
			model = new SysSequence();
			model.setKeyName(keyName.toLowerCase().trim());
			model.setNextValue(1 + increment);
			model.setGeneratedTime(generateTime);
			session.save(model);

			session.createSQLQuery(
					"insert into sys_sequenceHistories(keyName,generatedValue,generatedTime) values(:keyName,:generatedValue,:generatedTime)")
					.setParameter("keyName", model.getKeyName()).setParameter("generatedValue", 1)
					.setParameter("generatedTime", generateTime).executeUpdate();

			session.createSQLQuery(
					"insert into sys_sequenceHistories(keyName,generatedValue,generatedTime) values(:keyName,:generatedValue,:generatedTime)")
					.setParameter("keyName", model.getKeyName()).setParameter("generatedValue", 1 + increment)
					.setParameter("generatedTime", generateTime).executeUpdate();
		}
		session.getTransaction().commit();
		closeSession(session);
		SysLog log = new SysLog();
		log.setMessage("");

		logService.writeLog(this.getClass().getCanonicalName(),
				"method[getNextId(String keyName, int increment)] Generate sequence[keyName=" + model.getKeyName()
						+ "]:" + value);
		return value;
	}

	@javax.transaction.Transactional(javax.transaction.Transactional.TxType.REQUIRES_NEW)
	@org.springframework.transaction.annotation.Transactional(propagation=Propagation.REQUIRES_NEW)
	public int[] generateNextIds(int generateCount, String keyName, int increment) {
		if (keyName == null)
			throw new IllegalArgumentException(
					" the method 'SequenceServiceImpl.getNextId(String keyName)' have be passed 'null' !");
		System.out.println("int[] getNextId(int generateCount, String keyName, int increment){generateCount:"
				+ generateCount + ",keyName:" + keyName + ",increment:" + increment + "}");
		Session session = getSession();
		session.beginTransaction();
		SysSequence model = (SysSequence) session.get(SysSequence.class, keyName.toLowerCase().trim());
		int[] values = new int[generateCount];
		for (int i = 0; i < values.length; i++)
			values[i] = -1;
		int value = -1;
		Date generateTime = Calendar.getInstance().getTime();
		if (model != null) {
			value = model.getNextValue();
			System.out.println("value=" + value);
			model.setNextValue(model.getNextValue() + generateCount * increment);
			model.setGeneratedTime(generateTime);
			session.update(model);

			for (int i = 0; i < values.length; i++) {
				values[i] = value + increment * i;
				System.out.println(values[i]);
				if (i > 0)
					session.createSQLQuery(
							"insert into sys_sequenceHistories(keyName,generatedValue,generatedTime) values(:keyName,:generatedValue,:generatedTime)")
							.setParameter("keyName", model.getKeyName())
							.setParameter("generatedValue", value + increment * i)
							.setParameter("generatedTime", (i == 0) ? model.getGeneratedTime() : generateTime)
							.executeUpdate();
			}

			session.createSQLQuery(
					"insert into sys_sequenceHistories(keyName,generatedValue,generatedTime) values(:keyName,:generatedValue,:generatedTime)")
					.setParameter("keyName", model.getKeyName()).setParameter("generatedValue", model.getNextValue())
					.setParameter("generatedTime", model.getGeneratedTime()).executeUpdate();

		} else {
			value = 1;
			model = new SysSequence();
			model.setKeyName(keyName.toLowerCase().trim());
			model.setNextValue(1 + generateCount * increment);
			model.setGeneratedTime(generateTime);
			session.save(model);

			for (int i = 0; i < values.length; i++) {
				values[i] = value + increment * i;
				session.createSQLQuery(
						"insert into sys_sequenceHistories(keyName,generatedValue,generatedTime) values(:keyName,:generatedValue,:generatedTime)")
						.setParameter("keyName", model.getKeyName()).setParameter("generatedValue", values[i])
						.setParameter("generatedTime", generateTime).executeUpdate();
			}

			session.createSQLQuery(
					"insert into sys_sequenceHistories(keyName,generatedValue,generatedTime) values(:keyName,:generatedValue,:generatedTime)")
					.setParameter("keyName", model.getKeyName()).setParameter("generatedValue", model.getNextValue())
					.setParameter("generatedTime", model.getGeneratedTime()).executeUpdate();

		}
		session.getTransaction().commit();
		closeSession(session);
		logService.writeLog(this.getClass().getCanonicalName(),
				"method[getNextId(int generateCount, String keyName, int increment)] Generate sequence[keyName="
						+ model.getKeyName() + "]:" + values.toString());
		return values;
	}

}
