package com.fsmflying.mybatis.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.fsmflying.mybatis.dao.IMybatisAccessable;
import com.fsmflying.mybatis.mapper.SysLogMapper;
import com.fsmflying.sys.dao.ILogDao;
import com.fsmflying.sys.dm.SysLog;

public class LogDaoImpl implements ILogDao, IMybatisAccessable {

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
		SqlSession session = this.sessionFactory.openSession();
		session.getMapper(SysLogMapper.class).add(model);
		session.commit();
		return true;
	}

//	@Override
	public boolean save(SysLog model) {
		SqlSession session = this.sessionFactory.openSession();
		session.getMapper(SysLogMapper.class).save(model);
		session.commit();
		return true;
	}

//	@Override
	public boolean deleteModelOfSysLog(int id) {
		SqlSession session = this.sessionFactory.openSession();
		session.getMapper(SysLogMapper.class).delete(id);
		session.commit();
		return true;
	}

//	@Override
	public SysLog getModelOfSysLog(int id) {
		SqlSession session = this.sessionFactory.openSession();
		SysLog model  = session.getMapper(SysLogMapper.class).get(id);
		return model;
	}

//	@Override
	public List<SysLog> getListOfSysLog() {
		SqlSession session = this.sessionFactory.openSession();
		List<SysLog> list  = session.getMapper(SysLogMapper.class).getAllList();
		return list;
	}

	

}
