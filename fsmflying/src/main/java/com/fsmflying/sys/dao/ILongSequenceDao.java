package com.fsmflying.sys.dao;

public interface ILongSequenceDao {

	/**
	 * 
	 * @param keyName 指定的序列的键值
	 * @param increment 相邻序列号之间的增量
	 * @return 下一个序列号
	 */
	long getNextId(String keyName, long increment);

	/**
	 * 以keyName为键值的序列，生成一组序列号，没有此序列则创建;相邻序列号之间的增量为increment;
	 * @param generateCount 生成的序列号数量
	 * @param keyName 指定的序列的键值
	 * @param increment 相邻序列号之间的增量
	 * @return 序列号数组
	 */
	long[] getNextId(long generateCount, String keyName, long increment);

}