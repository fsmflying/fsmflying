package com.fsmflying.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import javax.annotation.Resource;

import com.fsmflying.sys.dm.SysConfig;
import com.fsmflying.sys.service.IConfigService;
import com.fsmflying.sys.service.ISystemManageService;


public class DefaultConfigServiceImpl implements IConfigService {

	List<SysConfig> configList;
	Map<String, String> configMap;
	ISystemManageService systemManageService;
	
	String[] configFileLocations;

	public void setSystemManageService(ISystemManageService systemManageService) {
		this.systemManageService = systemManageService;
	}
	
	public String[] getConfigFileLocations() {
		return configFileLocations;
	}
	

	public void setConfigFileLocations(String[] configFileLocations) {
		this.configFileLocations = configFileLocations;
		parseConfigFiles();
	}
	
	public void parseConfigFiles(){
		
	}

	public String getConfigValue(String configKey) {
		if (configMap == null)
			initService();
		if (configKey != null && !configMap.containsKey(configKey.toUpperCase().trim())) {
			return configMap.get(configKey.toUpperCase().trim());
		}
		return null;
	}

	public Map<String, String> getAllConfigs() {
		if (configMap == null)
			initService();
		return configMap;
	}

	public void initService() {
//		System.out.println(systemManageService);
		configList = systemManageService.getListOfSysConfig();
		configMap = new HashMap<String, String>();
		for (SysConfig e : configList) {
			if (e.getConfigKey() != null && !configMap.containsKey(e.getConfigKey().toUpperCase())) {
				configMap.put(e.getConfigKey().toUpperCase(), e.getConfigValue());
			}
		}
	}

	@Override
	public int getSessionExpireMinutes() {
		String value = getConfigValue("SessionExpireMinutes");
		if (value == null || "".equals(value) || !value.matches("[1-9][0-9]*"))
			return 30;
		else {
			return Integer.parseInt(value);
		}
	}

	@Override
	public int getSessionExpireIntervalMinutes() {
		String value = getConfigValue("SessionExpireIntervalMinutes");
		if (value == null || "".equals(value) || !value.matches("[1-9][0-9]*"))
			return 5;
		else {
			return Integer.parseInt(value);
		}
	}

	@Override
	public int getSessionExpirePolicy() {
		String value = getConfigValue("SessionExpirePolicy");
		if (value == null || "".equals(value) || !value.matches("[1-9][0-9]*"))
			return 30;
		else {
			return Integer.parseInt(value);
		}
	}

	@Override
	public int getPasswordMinLength() {
		String value = getConfigValue("PasswordMinLength");
		if (value == null || "".equals(value) || !value.matches("[1-9][0-9]*"))
			return 6;
		else {
			int length = Integer.parseInt(value);
			if (length < 6)
				return 6;
			else if (length > 12)
				return 12;
			return length;
		}
	}

	@Override
	public int getPasswordMaxLength() {
		String value = getConfigValue("PasswordMaxLength");
		if (value == null || "".equals(value) || !value.matches("[1-9][0-9]*"))
			return 30;
		else {
			int length = Integer.parseInt(value);
			if (length < 12)
				return 12;
			else if (length > 30)
				return 30;
			return length;
		}
	}

	@Override
	public String getAppName() {
		return getConfigValue("AppName");
	}

	@Override
	public int getPasswordErrorMaxCount() {
		String value = getConfigValue("PasswordErrorMaxCount");
		if ("".equals(value) || !value.matches("[1-9][0-9]*"))
			return 5;
		else {
			int val = Integer.parseInt(value);
			if (val < 0)
				return 1;
			else if (val > 10)
				return 10;
			return val;
		}
	}

	@Override
	public int getPasswordErrorDisableMinutes() {
		String value = getConfigValue("PasswordErrorDisableMinutes");
		if ("".equals(value) || !value.matches("[1-9][0-9]*"))
			return 30;
		else {
			int val = Integer.parseInt(value);
			if (val < 0)
				return 1;
//			else if (val > 10)
//				return 10;
			return val;
		}
	}
}
