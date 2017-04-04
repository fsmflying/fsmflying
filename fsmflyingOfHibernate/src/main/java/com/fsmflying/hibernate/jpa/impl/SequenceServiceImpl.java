package com.fsmflying.hibernate.jpa.impl;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
//import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
//import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;

import com.fsmflying.hibernate.jpa.HibernateJpaAccessable;
//import com.fsmflying.sys.dm.SysLog;
import com.fsmflying.sys.dm.SysSequence;
import com.fsmflying.sys.service.AbstractSequenceService;
import com.fsmflying.sys.service.ILogService;

//@Service("jpaSequenceService")
public class SequenceServiceImpl extends AbstractSequenceService implements HibernateJpaAccessable {

//	@Autowired
	ILogService logService;

	EntityManager entityManager;

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManager = entityManagerFactory.createEntityManager();
	}
	
	public void setLogService(ILogService logService)
	{
		this.logService = logService;
	}

	
	@javax.transaction.Transactional(javax.transaction.Transactional.TxType.REQUIRES_NEW)
	@org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRES_NEW)
	public int generateNextId(String keyName, int increment) {
		if (keyName == null)
			throw new IllegalArgumentException(
					" the method 'SequenceServiceImpl.getNextId(String keyName)' have be passed 'null' !");

		SysSequence model = this.entityManager.find(SysSequence.class, keyName.toLowerCase().trim());
		int value = -1;
		Date generateTime = Calendar.getInstance().getTime();
		if (model != null) {
			value = model.getNextValue();
			model.setNextValue(model.getNextValue() + increment);
			model.setGeneratedTime(generateTime);
			this.entityManager.persist(model);

			this.entityManager
					.createNativeQuery(
							"insert into sys_sequenceHistories(keyName,generatedValue,generatedTime) values(:keyName,:generatedValue,:generatedTime)")
					.setParameter("keyName", model.getKeyName()).setParameter("generatedValue", model.getNextValue())
					.setParameter("generatedTime", model.getGeneratedTime()).executeUpdate();

		} else {
			value = 1;
			model = new SysSequence();
			model.setKeyName(keyName.toLowerCase().trim());
			model.setNextValue(1 + increment);
			model.setGeneratedTime(generateTime);
			this.entityManager.merge(model);

			this.entityManager
					.createNativeQuery(
							"insert into sys_sequenceHistories(keyName,generatedValue,generatedTime) values(:keyName,:generatedValue,:generatedTime)")
					.setParameter("keyName", model.getKeyName()).setParameter("generatedValue", 1)
					.setParameter("generatedTime", generateTime).executeUpdate();

			this.entityManager
					.createNativeQuery(
							"insert into sys_sequenceHistories(keyName,generatedValue,generatedTime) values(:keyName,:generatedValue,:generatedTime)")
					.setParameter("keyName", model.getKeyName()).setParameter("generatedValue", 1 + increment)
					.setParameter("generatedTime", generateTime).executeUpdate();
		}

		logService.writeLog(this.getClass().getCanonicalName(),
				"method[getNextId(String keyName, int increment)] Generate sequence[keyName=" + model.getKeyName()
						+ "]:" + value);
		return value;
	}

	@javax.transaction.Transactional(javax.transaction.Transactional.TxType.REQUIRES_NEW)
	@org.springframework.transaction.annotation.Transactional(propagation = org.springframework.transaction.annotation.Propagation.REQUIRES_NEW)
	public int[] generateNextIds(int generateCount, String keyName, int increment) {
		if (keyName == null)
			throw new IllegalArgumentException(
					" the method 'SequenceServiceImpl.getNextId(String keyName)' have be passed 'null' !");
		System.out.println("int[] getNextId(int generateCount, String keyName, int increment){generateCount:"
				+ generateCount + ",keyName:" + keyName + ",increment:" + increment + "}");
		SysSequence model = this.entityManager.getReference(SysSequence.class, keyName.toLowerCase().trim());
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
			this.entityManager.persist(model);

			for (int i = 0; i < values.length; i++) {
				values[i] = value + increment * i;
				System.out.println(values[i]);
				if (i > 0) {
					this.entityManager
							.createNativeQuery(
									"insert into sys_sequenceHistories(keyName,generatedValue,generatedTime) values(:keyName,:generatedValue,:generatedTime)")
							.setParameter("keyName", model.getKeyName())
							.setParameter("generatedValue", value + increment * i)
							.setParameter("generatedTime", (i == 0) ? model.getGeneratedTime() : generateTime)
							.executeUpdate();
				}
			}

			this.entityManager
					.createNativeQuery(
							"insert into sys_sequenceHistories(keyName,generatedValue,generatedTime) values(:keyName,:generatedValue,:generatedTime)")
					.setParameter("keyName", model.getKeyName()).setParameter("generatedValue", model.getNextValue())
					.setParameter("generatedTime", model.getGeneratedTime()).executeUpdate();

		} else {
			value = 1;
			model = new SysSequence();
			model.setKeyName(keyName.toLowerCase().trim());
			model.setNextValue(1 + generateCount * increment);
			model.setGeneratedTime(generateTime);
			this.entityManager.merge(model);

			for (int i = 0; i < values.length; i++) {
				values[i] = value + increment * i;
				this.entityManager
						.createNativeQuery(
								"insert into sys_sequenceHistories(keyName,generatedValue,generatedTime) values(:keyName,:generatedValue,:generatedTime)")
						.setParameter("keyName", model.getKeyName()).setParameter("generatedValue", values[i])
						.setParameter("generatedTime", generateTime).executeUpdate();
			}

			this.entityManager
					.createNativeQuery(
							"insert into sys_sequenceHistories(keyName,generatedValue,generatedTime) values(:keyName,:generatedValue,:generatedTime)")
					.setParameter("keyName", model.getKeyName()).setParameter("generatedValue", model.getNextValue())
					.setParameter("generatedTime", model.getGeneratedTime()).executeUpdate();

		}
		logService.writeLog(this.getClass().getCanonicalName(),
				"method[getNextId(int generateCount, String keyName, int increment)] Generate sequence[keyName="
						+ model.getKeyName() + "]:" + values.toString());
		return values;
	}

}
