package com.fsmflying.sys.service;

import java.util.Map;

public interface IConfigService{

	/**
	 * �õ�Ӧ������
	 * @return��Ӧ������
	 */
	String getAppName();
	
	/**
	 * session���ڲ��ԣ�<br/>
	 * 0: ��ʾ������೤ʱ��û�л�͹��ڣ�ͨ�������SessionExpireIntervalMinutes���������ã�Ĭ��ֵΪ5����<br/>
	 * 1: ��ʾ�ӵ�¼ʱ����㣬���ٷ��ӹ��ڣ�ͨ�������SessionExpirePolicy���������ã�Ĭ��ֵΪ30����.<br/>
	 * @return
	 */
	int getSessionExpirePolicy();
	/**
	 * �õ�Session����ʱ��(��λΪ����)���ӵ�¼��ʼ����ʱ�䣬Ĭ��Ϊ30����
	 * @return
	 */
	int getSessionExpireMinutes();
	/**
	 * �õ�Sesssion����ʱ��(��λΪ����)�������һ�η��ʿ�ʼ����ʱ�䣬Ĭ��Ϊ5����
	 * @return
	 */
	int getSessionExpireIntervalMinutes();
	
	/**
	 * �õ�
	 * @return
	 */
	int getPasswordMinLength();
	/**
	 * �õ��������󳤶�,Ĭ��ֵ30��ֻ��λ��12-30֮��
	 * @return
	 */
	int getPasswordMaxLength();
	
	/**
	 * �õ�ϵͳ������
	 * @param configKey
	 * @return������ֵ
	 */
	String getConfigValue(String configKey);
	
	Map<String, String> getAllConfigs();
	
	
}
