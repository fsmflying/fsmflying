package com.fsmflying.mybatis.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.fsmflying.mybatis.dao.IMybatisAccessable;
import com.fsmflying.mybatis.mapper.SysLogMapper;
import com.fsmflying.sys.dm.SysLog;
import com.fsmflying.sys.service.AbstractLogService;

public class LogServiceImpl extends AbstractLogService implements IMybatisAccessable{

	SqlSessionFactory sessionFactory;

//	@Override
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
	
//	@Override
	public boolean add(SysLog model) {
		SqlSession session = this.getSession();
		session.getMapper(SysLogMapper.class).add(model);
		session.commit();
		closeSession(session);
		return true;
	}

//	@Override
	public boolean save(SysLog model) {
		SqlSession session = this.getSession();
		session.getMapper(SysLogMapper.class).save(model);
		session.commit();
		closeSession(session);
		return true;
	}

//	@Override
	public boolean deleteModelOfSysLog(int id) {
		SqlSession session = this.getSession();
		session.getMapper(SysLogMapper.class).delete(id);
		session.commit();
		closeSession(session);
		return true;
	}

//	@Override
	public SysLog getModelOfSysLog(int id) {
		SqlSession session = this.getSession();
		SysLog model = session.getMapper(SysLogMapper.class).get(id);
//		session.commit();
		closeSession(session);
		return model;
	}

//	@Override
	public List<SysLog> getListOfSysLog() {
		SqlSession session = this.getSession();
		List<SysLog> list = session.getMapper(SysLogMapper.class).getAllList();
//		session.commit();
		closeSession(session);
		return list;
	}

}
