package com.fsmflying.sys.dao;

import javax.sql.DataSource;

public interface LongSequenceDao extends ILongSequenceDao {
	
	public void setDataSource(DataSource dataSource);
	
	public long getNextIdByDefault();
	
	public long getNextId(String keyName);

	public long[] getNextId(long generateCount,String keyName);

	public long[] getNextId(long generateCount);
	
	public long[] getNextId(long generateCount,long increment);
	
}
