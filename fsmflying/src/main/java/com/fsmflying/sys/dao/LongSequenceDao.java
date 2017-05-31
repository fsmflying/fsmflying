package com.fsmflying.sys.dao;

import javax.sql.DataSource;

public interface LongSequenceDao extends ILongSequenceDao {
	
	/**
	 * 设置数据源，用于打开数据库连接
	 * @param dataSource 数据源
	 */
	public void setDataSource(DataSource dataSource);
	
	/**
	 * 以"default"为键值的序列，生成下一个序列号，没有此序列则创建
	 * @return 下一个序列号
	 */
	public long getNextIdByDefault();
	
	/**
	 * 以keyName为键值的序列，生成下一个序列号，没有此序列则创建;相邻序列号之间的增量为1;
	 * @param keyName 指定的序列的键值
	 * @return 下一个序列号
	 */
	public long getNextId(String keyName);
	
	/**
	 * 以keyName为键值的序列，生成一组序列号，没有此序列则创建;相邻序列号之间的增量为1;
	 * @param generateCount 生成的序列号数量
	 * @param keyName 指定的序列的键值
	 * @return 序列号数组
	 */
	public long[] getNextId(long generateCount,String keyName);
	
	/**
	 * 以"default"为键值的序列，生成一组序列号，没有此序列则创建;相邻序列号之间的增量为1;
	 * @param generateCount 生成的序列号数量
	 * @return
	 */
	public long[] getNextId(long generateCount);
	
	/**
	 * 以"default"为键值的序列，生成一组序列号，没有此序列则创建;相邻序列号之间的增量为increment;
	 * @param generateCount 生成的序列号数量
	 * @param increment 相邻序列号之间的增量
	 * @return
	 */
	public long[] getNextId(long generateCount,long increment);
	
}
