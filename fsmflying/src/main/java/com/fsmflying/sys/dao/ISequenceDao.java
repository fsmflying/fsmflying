package com.fsmflying.sys.dao;

public interface ISequenceDao {

	/**
	 * 
	 * @param keyName 指定的序列的键值
	 * @param increment 相邻序列号之间的增量
	 * @return 下一个序列号
	 */
	int generateNextId(String keyName, int increment);

	/**
	 * 以keyName为键值的序列，生成一组序列号，没有此序列则创建;相邻序列号之间的增量为increment;
	 * @param generateCount 生成的序列号数量
	 * @param keyName 指定的序列的键值
	 * @param increment 相邻序列号之间的增量
	 * @return 序列号数组
	 */
	int[] generateNextIds(int generateCount, String keyName, int increment);

}