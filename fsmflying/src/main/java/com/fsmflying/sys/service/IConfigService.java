package com.fsmflying.sys.service;

import java.util.Map;

public interface IConfigService{

	String getAppName();

	int getSessionExpirePolicy();

	int getSessionExpireMinutes();
	
	int getSessionExpireIntervalMinutes();
	
	int getPasswordMinLength();

	int getPasswordMaxLength();
		
	String getConfigValue(String configKey);
	
	Map<String, String> getAllConfigs();
	
	
}
