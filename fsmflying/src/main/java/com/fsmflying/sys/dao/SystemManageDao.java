package com.fsmflying.sys.dao;

import javax.sql.DataSource;

public interface SystemManageDao extends ISystemManageDao {
	
	/**
	 * ��������Դ�����ڴ����ݿ�����
	 * @param dataSource ����Դ
	 */
	public void setDataSource(DataSource dataSource);
	
	
}
