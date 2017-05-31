package com.fsmflying.sys.service;


import com.fsmflying.sys.dao.ISystemManageDao;

public interface SystemManageService extends ISystemManageService {
	
	/**
	 * 
	 * @param systemManageDao
	 */
	public void setSystemManageDao(ISystemManageDao systemManageDao);
}
