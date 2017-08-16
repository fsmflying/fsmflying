package com.fsmflying.sys.service;

import java.util.Map;

public interface IConfigService{

	String getAppName();

	int getSessionExpirePolicy();

	int getSessionExpireMinutes();
	
	int getSessionExpireIntervalMinutes();
	
	int getPasswordMinLength();

	int getPasswordMaxLength();

	/**
	 * 尝试登录时，登录失败的最大次数，建议设置为5
	 * @return
	 */
	int getPasswordErrorMaxCount();
	
	/**
	 * 尝试登录时，当达到最大失败次数时，禁用的时间
	 * @return
	 */
	int getPasswordErrorDisableMinutes();
		
	String getConfigValue(String configKey);
	
	Map<String, String> getAllConfigs();
	
	
}
