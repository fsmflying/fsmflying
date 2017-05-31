package com.fsmflying.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.fsmflying.sys.dao.ISystemManageDao;
import com.fsmflying.sys.dm.SysConfig;
import com.fsmflying.sys.service.ConfigService;

public class ConfigServiceImpl implements ConfigService {

	//@Resource
	ISystemManageDao systemManageDao;

	Map<String, String> configMap;

	List<SysConfig> configList;
	
	@Resource
	@Override
	public void setSystemManageDao(ISystemManageDao systemManageDao) {
		this.systemManageDao = systemManageDao;
	}

	public void initService() {
		configMap = new HashMap<String, String>();
		configList = systemManageDao.getListOfSysConfig();
		for (SysConfig e : configList) {
			if (!configMap.containsKey(e.getConfigKey().toUpperCase())) {
				configMap.put(e.getConfigKey().toUpperCase(), e.getConfigValue());
			}
		}

	}
	
	@Override
	public Map<String,String> getAllConfigs()
	{
		if (configMap == null)
			initService();
		return configMap;
	}

	@Override
	public String getConfigValue(String configKey) {
		if (configMap == null)
			initService();
		if (configMap != null && configMap.containsKey(configKey.toUpperCase()))
			return configMap.get(configKey.toUpperCase());
		return "";
	}

	@Override
	public int getSessionExpireMinutes() {
		String value = getConfigValue("SessionExpireMinutes");
		if ("".equals(value) || !value.matches("[1-9][0-9]*"))
			return 30;
		else {
			return Integer.parseInt(value);
		}
	}

	@Override
	public int getSessionExpireIntervalMinutes() {
		String value = getConfigValue("SessionExpireIntervalMinutes");
		if ("".equals(value) || !value.matches("[1-9][0-9]*"))
			return 5;
		else {
			return Integer.parseInt(value);
		}
	}

	@Override
	public int getSessionExpirePolicy() {
		String value = getConfigValue("SessionExpirePolicy");
		if ("".equals(value) || !value.matches("[1-9][0-9]*"))
			return 30;
		else {
			return Integer.parseInt(value);
		}
	}

	@Override
	public int getPasswordMinLength() {
		String value = getConfigValue("PasswordMinLength");
		if ("".equals(value) || !value.matches("[1-9][0-9]*"))
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
		if ("".equals(value) || !value.matches("[1-9][0-9]*"))
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

	

}
