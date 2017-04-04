package com.fsmflying.mybatis.dao.impl;

import java.util.Calendar;
import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.fsmflying.mybatis.dao.IMybatisAccessable;
import com.fsmflying.mybatis.mapper.SysSequenceMapper;
import com.fsmflying.sys.dao.ISequenceDao;
import com.fsmflying.sys.dm.SysSequence;
//import com.fsmflying.sys.service.AbstractSequenceService;

public class SequenceDaoImpl implements ISequenceDao, IMybatisAccessable {

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
	public int generateNextId(String keyName, int increment) {
		if (keyName == null || keyName.trim().isEmpty() || increment <= 0)
			throw new IllegalArgumentException(
					this.getClass().getCanonicalName() + ".generateNextIds(..): invalid parameters.");

		SqlSession session = getSession();
		SysSequenceMapper mapper = session.getMapper(SysSequenceMapper.class);
		SysSequence model = mapper.getSequence(keyName);
		Date generatedTime = Calendar.getInstance().getTime();
		int value = -1;
		if (model != null) {
			value = model.getNextValue();
			model.setNextValue(value + increment);
			model.setGeneratedTime(generatedTime);
			mapper.saveSequence(model);
			mapper.addSequenceHistory(new SysSequence(keyName, value + increment, generatedTime));
		} else {
			value = 1;
			model = new SysSequence(keyName, value + increment, generatedTime);
			mapper.addSequence(new SysSequence(keyName, value + increment, generatedTime));
			mapper.addSequenceHistory(new SysSequence(keyName, value, generatedTime));
			mapper.addSequenceHistory(new SysSequence(keyName, value + increment, generatedTime));
		}
		session.commit();
		closeSession(session);
		return 0;
	}

	@Override
	public int[] generateNextIds(int generateCount, String keyName, int increment) {

		if (keyName == null || keyName.trim().isEmpty() || generateCount <= 0 || increment <= 0)
			throw new IllegalArgumentException(
					this.getClass().getCanonicalName() + ".generateNextIds(..): invalid parameters.");
		int[] values = new int[generateCount];
		int value = -1;
		for (int i = 0; i < values.length; i++) {
			values[i] = -1;
		}
		SqlSession session = getSession();
		SysSequenceMapper mapper = session.getMapper(SysSequenceMapper.class);
		SysSequence model = mapper.getSequence(keyName);
		Date generatedTime = Calendar.getInstance().getTime();
		if (model != null) {
			value = model.getNextValue();
			model.setNextValue(value + increment * generateCount);
			model.setGeneratedTime(generatedTime);
			mapper.saveSequence(model);
			for (int i = 0; i < values.length; i++) {
				values[i] = value + i * increment;
				if (i > 0)
					mapper.addSequenceHistory(new SysSequence(keyName, values[i], generatedTime));
			}
			mapper.addSequenceHistory(new SysSequence(keyName, value + increment * generateCount, generatedTime));
		} else {
			value = 1;
			model = new SysSequence(keyName, value + increment, generatedTime);
			mapper.addSequence(new SysSequence(keyName, value + increment, generatedTime));
			for (int i = 0; i < values.length; i++) {
				values[i] = value + i * increment;
				mapper.addSequenceHistory(new SysSequence(keyName, values[i], generatedTime));
			}
			mapper.addSequenceHistory(new SysSequence(keyName, value + increment * generateCount, generatedTime));
		}
		session.commit();
		closeSession(session);
		return null;
	}

}
