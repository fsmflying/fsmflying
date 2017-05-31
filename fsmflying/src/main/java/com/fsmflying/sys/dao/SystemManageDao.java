package com.fsmflying.sys.dao;

import javax.sql.DataSource;

public interface SystemManageDao extends ISystemManageDao {
	
	/**
	 * 设置数据源，用于打开数据库连接
	 * @param dataSource 数据源
	 */
	public void setDataSource(DataSource dataSource);
	
	
}
