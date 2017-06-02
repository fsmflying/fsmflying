package com.fsmflying.jpa.service.impl;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fsmflying.jpa.service.JpaAccessable;
import com.fsmflying.sys.dm.SysSequence;
import com.fsmflying.sys.service.AbstractSequenceService;

//@Service("jpaSequenceService")
public class SequenceServiceImpl extends AbstractSequenceService implements JpaAccessable {

	// ILogService logService;
	Logger logger = LoggerFactory.getLogger(SequenceServiceImpl.class);

	@PersistenceContext(unitName="common_sequence")
	EntityManager entityManager;

	EntityManagerFactory entityManagerFactory;

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;// .createEntityManager();
		this.entityManager = this.entityManagerFactory.createEntityManager();
	}

	public void close() {
		if (this.entityManagerFactory != null)
			this.entityManagerFactory.close();
	}

	// public void setLogService(ILogService logService) {
	//// this.logService = logService;
	// }

	@javax.transaction.Transactional(javax.transaction.Transactional.TxType.REQUIRES_NEW)
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

		// logService.writeLog(this.getClass().getCanonicalName(),
		// "method[getNextId(String keyName, int increment)] Generate
		// sequence[keyName=" + model.getKeyName()
		// + "]:" + value);
		logger.debug("method[getNextId(String keyName, int increment)] Generate sequence[keyName=" + model.getKeyName()
				+ "]:" + value);
		return value;
	}

	@javax.transaction.Transactional(javax.transaction.Transactional.TxType.REQUIRES_NEW)
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
		// logService.writeLog(this.getClass().getCanonicalName(),
		// "method[getNextId(int generateCount, String keyName, int increment)]
		// Generate sequence[keyName="
		// + model.getKeyName() + "]:" + values.toString());
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < values.length; i++) {
			sb.append(i > 0 ? ("," + values[i]) : values[i]);
		}
		sb.append("]");
		logger.debug("method[getNextId(String keyName, int increment)] Generate sequence[keyName=" + model.getKeyName()
				+ "]:" + sb.toString());
		return values;
	}

}
