package com.fsmflying.sys.dao;


/**
 * 序列号生成器的数据访问对象，主要提供以数据库表为基础生成序列号的功能
 * @author FangMing
 *
 */
//public interface SequenceDao extends ISequenceDao {
//	
//	/**
//	 * 以keyName为键值的序列，生成下一个序列号，没有此序列则创建;相邻序列号之间的增量为1;
//	 * @param keyName 指定的序列的键值
//	 * @param increment 序列号之间的间隔
//	 * @return 下一个序列号
//	 */
//	public int getNextId(String keyName,int increment);
//	
//	/**
//	 * 以keyName为键值的序列，生成一组序列号，没有此序列则创建;相邻序列号之间的增量为1;
//	 * @param generateCount 生成的序列号数量
//	 * @param keyName 指定的序列的键值
//	 * @param increment 序列号之间的间隔
//	 * @return 序列号数组
//	 */
//	public int[] getNextId(int generateCount,String keyName,int increment);
//	
//	
//}