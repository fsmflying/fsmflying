package com.fsmflying.sys.dao;

public interface ILongSequenceDao {

	/**
	 * 
	 * @param keyName ָ�������еļ�ֵ
	 * @param increment �������к�֮�������
	 * @return ��һ�����к�
	 */
	long getNextId(String keyName, long increment);

	/**
	 * ��keyNameΪ��ֵ�����У�����һ�����кţ�û�д������򴴽�;�������к�֮�������Ϊincrement;
	 * @param generateCount ���ɵ����к�����
	 * @param keyName ָ�������еļ�ֵ
	 * @param increment �������к�֮�������
	 * @return ���к�����
	 */
	long[] getNextId(long generateCount, String keyName, long increment);

}