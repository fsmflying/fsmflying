package com.fsmflying.sys.dao;

import javax.sql.DataSource;

public interface SystemManageDao extends ISystemManageDao {
	
	
	public void setDataSource(DataSource dataSource);
	
	
}
