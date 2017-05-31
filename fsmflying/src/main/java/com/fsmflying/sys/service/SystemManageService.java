package com.fsmflying.sys.service;


import com.fsmflying.sys.dao.ISystemManageDao;

public interface SystemManageService extends ISystemManageService {
	
	/**
	 * 设置数据访问对象，提供对系统管理模块数据源的访问
	 * @param systemManageDao 数据访问对象
	 */
	public void setSystemManageDao(ISystemManageDao systemManageDao);
}
