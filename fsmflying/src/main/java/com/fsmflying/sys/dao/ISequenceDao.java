package com.fsmflying.sys.dao;

public interface ISequenceDao {

	/**
	 * 
	 * @param keyName ָ�������еļ�ֵ
	 * @param increment �������к�֮�������
	 * @return ��һ�����к�
	 */
	int generateNextId(String keyName, int increment);

	/**
	 * ��keyNameΪ��ֵ�����У�����һ�����кţ�û�д������򴴽�;�������к�֮�������Ϊincrement;
	 * @param generateCount ���ɵ����к�����
	 * @param keyName ָ�������еļ�ֵ
	 * @param increment �������к�֮�������
	 * @return ���к�����
	 */
	int[] generateNextIds(int generateCount, String keyName, int increment);

}