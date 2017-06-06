package com.fsmflying.sys.service;

import java.util.Date;
import java.util.List;

import com.fsmflying.sys.dm.SysLog;

public interface ILogService {

	public boolean add(SysLog model);

	public boolean save(SysLog model);

	public boolean deleteModelOfSysLog(int id);

	public SysLog getModelOfSysLog(int id);

	public List<SysLog> getListOfSysLog();

	public void writeLog(String logLevel, String logger, int operatorId, String operatorName, Date operationTime,String operationAddress,
			String operationType, String machineIp, String machineName, String browser, String appName,
			String clientSystemName, String message);

	void writeDebugLog(String logger,String message);

	void writeInfoLog(String logger,String message);

	void writeWarnLog(String logger,String message);

	void writeErrorLog(String logger,String message);

	void writeFatalLog(String logger,String message);

	void writeLog(String logger, String message);
	
	public String getAppName();
	
	public void setAppName(String appName);
	
	/**
	 * 0:[DEGUG];1:[INFO];2:[WARN];3:[ERROR];4:[FATAL];
	 */
	public int getDefaultLogLevel();

	/**
	 * 0:[DEGUG];1:[INFO];2:[WARN];3:[ERROR];4:[FATAL];
	 * @param logLevel 日志级别
	 */
	public void setDefaultLogLevel(int logLevel);
	
	public boolean getIsWriteDatabase();
	
	/**
	 * @param isWriteDatabase
	 */
	public void setIsWriteDatabase(boolean isWriteDatabase);
	
	

	// public String getLogLevel();
	// public String getLogger();
	// public String getOperationId();
	// public String getOperationName();

}
