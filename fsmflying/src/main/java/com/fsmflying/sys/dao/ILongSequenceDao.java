package com.fsmflying.sys.dao;

public interface ILongSequenceDao {

	/**
	 * 根据键名称生成一个序列号
	 * @param keyName 键名称
	 * @param increment 增量
	 * @return 生成的键值 
	 */
	long getNextId(String keyName, long increment);

	/**
	 * 根据键名称生成指定数量的序列号
	 * @param generateCount 生成数量
	 * @param keyName 键名称
	 * @param increment 增量
	 * @return 生成的键值数组
	 */
	long[] getNextId(long generateCount, String keyName, long increment);

}