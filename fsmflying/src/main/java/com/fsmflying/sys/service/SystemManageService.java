package com.fsmflying.sys.service;


import com.fsmflying.sys.dao.ISystemManageDao;

public interface SystemManageService extends ISystemManageService {
	
	/**
	 * �������ݷ��ʶ����ṩ��ϵͳ����ģ������Դ�ķ���
	 * @param systemManageDao ���ݷ��ʶ���
	 */
	public void setSystemManageDao(ISystemManageDao systemManageDao);
}
