package com.fsmflying.sys.dao;

import javax.sql.DataSource;

public interface LongSequenceDao extends ILongSequenceDao {
	
	/**
	 * ��������Դ�����ڴ����ݿ�����
	 * @param dataSource ����Դ
	 */
	public void setDataSource(DataSource dataSource);
	
	/**
	 * ��"default"Ϊ��ֵ�����У�������һ�����кţ�û�д������򴴽�
	 * @return ��һ�����к�
	 */
	public long getNextIdByDefault();
	
	/**
	 * ��keyNameΪ��ֵ�����У�������һ�����кţ�û�д������򴴽�;�������к�֮�������Ϊ1;
	 * @param keyName ָ�������еļ�ֵ
	 * @return ��һ�����к�
	 */
	public long getNextId(String keyName);
	
	/**
	 * ��keyNameΪ��ֵ�����У�����һ�����кţ�û�д������򴴽�;�������к�֮�������Ϊ1;
	 * @param generateCount ���ɵ����к�����
	 * @param keyName ָ�������еļ�ֵ
	 * @return ���к�����
	 */
	public long[] getNextId(long generateCount,String keyName);
	
	/**
	 * ��"default"Ϊ��ֵ�����У�����һ�����кţ�û�д������򴴽�;�������к�֮�������Ϊ1;
	 * @param generateCount ���ɵ����к�����
	 * @return
	 */
	public long[] getNextId(long generateCount);
	
	/**
	 * ��"default"Ϊ��ֵ�����У�����һ�����кţ�û�д������򴴽�;�������к�֮�������Ϊincrement;
	 * @param generateCount ���ɵ����к�����
	 * @param increment �������к�֮�������
	 * @return
	 */
	public long[] getNextId(long generateCount,long increment);
	
}
