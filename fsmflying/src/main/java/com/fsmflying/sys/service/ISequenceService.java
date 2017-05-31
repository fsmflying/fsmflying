package com.fsmflying.sys.service;

public interface ISequenceService {

	/**
	 * ��"default"Ϊ��ֵ�����У�������һ�����кţ�û�д������򴴽�
	 * @return ��һ�����к�
	 */
//	default public int getNextIdByDefault(){
//		return getNextId("default");
//	}
	int generateNextIdByDefault();

	/**
	 * ��keyNameΪ��ֵ�����У�������һ�����кţ�û�д������򴴽�;�������к�֮�������Ϊ1;
	 * @param keyName ָ�������еļ�ֵ
	 * @return ��һ�����к�
	 */
	int generateNextId(String keyName);

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

	/**
	 * ��keyNameΪ��ֵ�����У�����һ�����кţ�û�д������򴴽�;�������к�֮�������Ϊ1;
	 * @param generateCount ���ɵ����к�����
	 * @param keyName ָ�������еļ�ֵ
	 * @return ���к�����
	 */
	int[] generateNextIds(int generateCount, String keyName);

	/**
	 * ��"default"Ϊ��ֵ�����У�����һ�����кţ�û�д������򴴽�;�������к�֮�������Ϊ1;
	 * @param generateCount ���ɵ����к�����
	 * @return
	 */
	int[] generateNextIds(int generateCount);

	/**
	 * ��"default"Ϊ��ֵ�����У�����һ�����кţ�û�д������򴴽�;�������к�֮�������Ϊincrement;
	 * @param generateCount ���ɵ����к�����
	 * @param increment �������к�֮�������
	 * @return
	 */
	int[] generateNextIds(int generateCount, int increment);

}