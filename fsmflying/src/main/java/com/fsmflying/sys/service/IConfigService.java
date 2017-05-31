package com.fsmflying.sys.service;

import java.util.Map;

public interface IConfigService{

	/**
	 * 得到应用名称
	 * @return　应用名称
	 */
	String getAppName();
	
	/**
	 * session到期策略，<br/>
	 * 0: 表示按最近多长时间没有活动就过期，通过配置项“SessionExpireIntervalMinutes”进行配置，默认值为5分钟<br/>
	 * 1: 表示从登录时间计算，多少分钟过期，通过配置项“SessionExpirePolicy”进行配置，默认值为30分钟.<br/>
	 * @return
	 */
	int getSessionExpirePolicy();
	/**
	 * 得到Session过期时长(单位为分钟)，从登录开始计算时间，默认为30分钟
	 * @return
	 */
	int getSessionExpireMinutes();
	/**
	 * 得到Sesssion过期时长(单位为分钟)，从最后一次访问开始计算时间，默认为5分钟
	 * @return
	 */
	int getSessionExpireIntervalMinutes();
	
	/**
	 * 得到
	 * @return
	 */
	int getPasswordMinLength();
	/**
	 * 得到密码的最大长度,默认值30，只能位于12-30之间
	 * @return
	 */
	int getPasswordMaxLength();
	
	/**
	 * 得到系统配置项
	 * @param configKey
	 * @return　配置值
	 */
	String getConfigValue(String configKey);
	
	Map<String, String> getAllConfigs();
	
	
}
