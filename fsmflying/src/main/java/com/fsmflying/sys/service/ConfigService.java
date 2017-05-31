package com.fsmflying.sys.service;


import com.fsmflying.sys.dao.ISystemManageDao;

public interface ConfigService extends IConfigService {
	void setSystemManageDao(ISystemManageDao systemManageDao);
}
