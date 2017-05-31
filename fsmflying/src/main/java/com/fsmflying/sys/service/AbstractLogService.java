package com.fsmflying.sys.service;

import java.util.Calendar;
import java.util.Date;
import com.fsmflying.sys.dm.SysLog;

public abstract class AbstractLogService implements ILogService {

	private boolean isWriteDatabase = false;
	private String appName;
	private int logLevel = 1;
	
	public String getDefaultLogLevelString()
	{
		switch(this.logLevel)
		{
			case 0:
				return "DEBUG";
			case 1:
				return "INFO";
			case 2:
				return "WARN";
			case 3:
				return "ERROR";
			case 4:
				return "FATAL";
			
			default:
				return "INFO";
		}
	}

	@Override
	public void writeLog(String logLevel, String logger, int operatorId, String operatorName, Date operationTime,
			String operationAddress, String operationType, String machineIp, String machineName, String browser,
			String appName, String clientSystemName, String message) {
		SysLog model = new SysLog();
		model.setLogThread(Thread.currentThread().getName());
		if(logLevel == null || logLevel.trim().equals(""))
			model.setLogLevel(this.getDefaultLogLevelString());
		else
			model.setLogLevel(logLevel);
		model.setLogger(logger);
		model.setOperatorId(operatorId);
		model.setOperatorName(operatorName);
		model.setOperationAddr(operationAddress);
		if (operationTime == null)
			model.setOperationTime(Calendar.getInstance().getTime());
		else
			model.setOperationTime(operationTime);
		model.setOperationType(operationType);
		model.setMachineIp(machineIp);
		model.setMachineName(machineName);
		model.setBrowser(browser);
		if(appName == null || appName.trim().equals(""))
			model.setAppName(this.getAppName());
		else
			model.setAppName(appName);
		model.setMessage(message);
		if(this.isWriteDatabase)
			add(model);
	}

	@Override
	public void writeLog(String logger, String message) {
	
		this.writeLog(this.getDefaultLogLevelString(), logger, -1, "", null, null, null, null, null, null, appName, null, message);
	}

	@Override
	public void writeDebugLog(String logger, String message) {
		this.writeLog("DEBUG", logger, -1, "", null, null, null, null, null, null, null, null, message);
	}

	@Override
	public void writeInfoLog(String logger, String message) {
		this.writeLog("INFO", logger, -1, "", null, null, null, null, null, null, null, null, message);
	}

	@Override
	public void writeWarnLog(String logger, String message) {
		this.writeLog("WARN", logger, -1, "", null, null, null, null, null, null, null, null, message);
	}

	@Override
	public void writeErrorLog(String logger, String message) {
		this.writeLog("ERROR", logger, -1, "", null, null, null, null, null, null, null, null, message);
	}

	@Override
	public void writeFatalLog(String logger, String message) {
		this.writeLog("FATAL", logger, -1, "", null, null, null, null, null, null, null, null, message);
	}

	@Override
	public String getAppName() {
		return this.appName;
	}

	@Override
	public void setAppName(String appName) {
		this.appName= appName;
	}

	@Override
	public int getDefaultLogLevel() {
		return this.logLevel;
	}

	@Override
	public void setDefaultLogLevel(int logLevel) {
		this.logLevel = logLevel;
	}

	@Override
	public boolean getIsWriteDatabase() {
		return this.isWriteDatabase;
	}

	@Override
	public void setIsWriteDatabase(boolean isWriteDatabase) {
		this.isWriteDatabase = isWriteDatabase;
	}

}
