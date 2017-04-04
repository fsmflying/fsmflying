package com.fsmflying.mybatis.dao;

import org.apache.ibatis.session.SqlSessionFactory;

public interface IMybatisAccessable {
	public void setSessionFactory(SqlSessionFactory sessionFactory);
}
